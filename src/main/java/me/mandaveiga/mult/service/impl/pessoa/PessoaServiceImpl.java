package me.mandaveiga.mult.service.impl.pessoa;

import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.repository.impl.PessoaRepository;
import me.mandaveiga.mult.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl extends BaseService<Pessoa> implements PessoaService {

    @Autowired
    public PessoaServiceImpl(PessoaRepository repository) {
        super(repository);
    }
}
