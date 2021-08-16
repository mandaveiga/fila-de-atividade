package me.mandaveiga.mult.model.atividade;

import lombok.*;
import me.mandaveiga.mult.model.AbstractModel;
import me.mandaveiga.mult.model.pessoa.Pessoa;

import javax.persistence.*;

@Entity
@Table(name = "atividades")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Atividade extends AbstractModel {

    @ManyToOne
    @JoinColumn(name="pessoaId", referencedColumnName = "id")
    private Pessoa pessoa;

    @Column(nullable = false)
    private int esforco;

    @Column(nullable = true)
    private int execucao;

}