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
public class Atividade extends AbstractModel implements Comparable {

    @ManyToOne
    @JoinColumn(name="pessoaId", referencedColumnName = "id")
    private Pessoa pessoa;

    @Column(nullable = false)
    private int esforco;

    @Column(nullable = false)
    private int execucao;

    @Override
    public int compareTo(Object o) {
        Atividade atividade = (Atividade) o;
        if (this.esforco < atividade.esforco) {
            return -1;
        }
        if (this.esforco > atividade.esforco) {
            return 1;
        }
        return 0;
    }
}