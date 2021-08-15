package me.mandaveiga.mult.repository.impl;

import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends BaseRepository<Pessoa> {
}
