package me.mandaveiga.mult.service.impl.atividade;

import me.mandaveiga.mult.model.atividade.Atividade;
import me.mandaveiga.mult.model.atividade.AtividadeManager;
import me.mandaveiga.mult.model.atividade.Execution;
import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.repository.impl.AtividadeRepository;
import me.mandaveiga.mult.repository.impl.PessoaRepository;
import me.mandaveiga.mult.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeServiceImpl extends BaseService<Atividade> implements AtividadeService {

    @Autowired
    public AtividadeServiceImpl(AtividadeRepository repository) {
        super(repository);
    }

    public Execution startingExecution() throws ClassNotFoundException {
        //Todo antes de iniciar validar se todas as tarefas estao com pessoa associadas

        int minutosDeEsforcoTotal = runtimeQueue((List<Atividade>) repository.findAll());

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

    private int calculatePercentage(LocalDateTime timeStart, LocalDateTime timeEnd){
        //TODO IMPLEMENTAR
        return 0;
    }

    private int runtimeQueue(List<Atividade> atividades) throws ClassNotFoundException {
        //TODO Criar novo campo em atividade para guardar o execucao com a pessoa
        Optional<Atividade> maiorAtividade = atividades
                .stream()
                .max(Comparator.comparing(Atividade::getEsforco));

        if(!maiorAtividade.isPresent()){
            throw new ClassNotFoundException();
        }
        return maiorAtividade.get().getEsforco();
    }
}
