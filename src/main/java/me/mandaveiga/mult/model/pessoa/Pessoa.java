package me.mandaveiga.mult.model.pessoa;

import lombok.*;
import me.mandaveiga.mult.model.AbstractModel;
import me.mandaveiga.mult.model.atividade.Atividade;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.List;

@Entity
@Table(name = "pessoas")
@EqualsAndHashCode(callSuper = true)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa extends AbstractModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int produtividade;

//    @OneToMany(mappedBy = "pessoa")
//    private List<Atividade> atividades;
}