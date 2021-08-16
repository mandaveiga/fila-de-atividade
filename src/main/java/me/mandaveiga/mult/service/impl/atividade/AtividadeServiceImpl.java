package me.mandaveiga.mult.service.impl.atividade;

import me.mandaveiga.mult.model.atividade.Atividade;
import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.repository.impl.AtividadeRepository;
import me.mandaveiga.mult.repository.impl.PessoaRepository;
import me.mandaveiga.mult.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtividadeServiceImpl extends BaseService<Atividade> implements AtividadeService {

    @Autowired
    public AtividadeServiceImpl(AtividadeRepository repository) {
        super(repository);
    }

}
