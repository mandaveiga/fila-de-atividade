package me.mandaveiga.mult.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAtividadeDto {
    private Long pessoaId;
    private int esforco;
}
