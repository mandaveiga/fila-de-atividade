package me.mandaveiga.mult.service.impl.atividade;

import me.mandaveiga.mult.model.atividade.Atividade;
import me.mandaveiga.mult.model.atividade.Execution;
import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.service.CrudService;

import java.util.Optional;

public interface AtividadeService extends CrudService<Atividade> {
    Execution startingExecution() throws ClassNotFoundException;
}
