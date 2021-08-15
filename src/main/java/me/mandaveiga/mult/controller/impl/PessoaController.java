package me.mandaveiga.mult.controller.impl;

import io.swagger.annotations.Api;
import me.mandaveiga.mult.controller.BaseController;
import me.mandaveiga.mult.controller.validator.PessoaValidator;
import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.service.impl.pessoa.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
@Api(value = "Pessoas")
public class PessoaController extends BaseController<Pessoa> {

    private final PessoaValidator pessoaValidator;

    @Autowired
    public PessoaController(PessoaServiceImpl service, PessoaValidator pessoaValidator) {
        super(service);
        this.pessoaValidator = pessoaValidator;
    }
}
