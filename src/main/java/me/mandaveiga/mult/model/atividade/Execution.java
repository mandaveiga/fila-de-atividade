package me.mandaveiga.mult.model.atividade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class Execution {
    private Boolean executing;
    private LocalDateTime timeEnd;
    private int percentage;
    private LocalDateTime timeStart;
}
