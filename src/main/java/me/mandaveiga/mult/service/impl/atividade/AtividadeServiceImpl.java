package me.mandaveiga.mult.service.impl.atividade;

import me.mandaveiga.mult.errors.BusinessException;
import me.mandaveiga.mult.model.atividade.Atividade;
import me.mandaveiga.mult.model.atividade.AtividadeManager;
import me.mandaveiga.mult.model.atividade.Execution;
import me.mandaveiga.mult.repository.impl.AtividadeRepository;
import me.mandaveiga.mult.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeServiceImpl extends BaseService<Atividade> implements AtividadeService {

    @Autowired
    public AtividadeServiceImpl(AtividadeRepository repository) {
        super(repository);
    }

    public Execution startingExecution() throws BusinessException {

        List<Atividade> atividades = (List<Atividade>) repository.findAll();

        boolean allActivitiesHasAssociatedPerson = atividades.stream().allMatch(atividade -> atividade.getPessoa() != null);

        if (!allActivitiesHasAssociatedPerson) {
            throw new BusinessException("Não é possível executar enquanto há atividades sem qualquer pessoa associada.");
        }

        int minutosDeEsforcoTotal = calculateExecutionQueue(atividades);

        LocalDateTime timeStart = LocalDateTime.now();
        LocalDateTime timeEnd = timeStart.plusMinutes(minutosDeEsforcoTotal);

        AtividadeManager.getInstance().setExecuting(true);
        AtividadeManager.getInstance().setDateEnd(timeEnd);
        AtividadeManager.getInstance().setDateStart(timeStart);

        return Execution.builder()
                .executing(AtividadeManager.getInstance().isExecuting())
                .timeStart(timeStart)
                .timeEnd(timeEnd)
                .percentage(calculatePercentage(timeStart, timeEnd))
                .build();
    }

    public static int calculatePercentage(LocalDateTime timeStart, LocalDateTime timeEnd){
        long now = System.currentTimeMillis();
        long start = timeStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long end = timeEnd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (start >= end || now >= end) {
            return 100;
        }

        if (now <= start) {
            return 0;
        }

        return (int) (100 - ((end - now) * 100 / (end - start)));
    }

    private int calculateExecutionQueue(List<Atividade> atividades) throws BusinessException {
        Optional<Atividade> maiorAtividade = atividades
                .stream()
                .max(Comparator.comparing(Atividade::getExecucao));

        if(!maiorAtividade.isPresent()){
            throw new BusinessException("Não há nenhuma atividade para ser executada");
        }
        return maiorAtividade.get().getEsforco();
    }

    public int calculateExecutionActivity(int esforco, int produtividade) {
        double execution = esforco - (esforco * (Double.valueOf(produtividade) / 100));

        return (int) execution;
    }
}
