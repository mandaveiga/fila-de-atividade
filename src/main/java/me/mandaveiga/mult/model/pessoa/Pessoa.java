package me.mandaveiga.mult.model.pessoa;

import lombok.*;
import me.mandaveiga.mult.model.AbstractModel;

import javax.persistence.*;

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
}