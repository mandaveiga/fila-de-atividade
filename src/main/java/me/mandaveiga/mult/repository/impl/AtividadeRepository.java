package me.mandaveiga.mult.repository.impl;

import me.mandaveiga.mult.model.atividade.Atividade;
import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtividadeRepository extends BaseRepository<Atividade> {
}
