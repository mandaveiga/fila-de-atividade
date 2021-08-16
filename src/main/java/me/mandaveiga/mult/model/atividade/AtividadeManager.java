package me.mandaveiga.mult.model.atividade;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class AtividadeManager {

    private static AtividadeManager INSTANCE;

    public static AtividadeManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AtividadeManager();
        }

        return INSTANCE;
    }

    private boolean executing;
    private LocalDateTime DateEnd;
    private LocalDateTime DateStart;

    private AtividadeManager() {
        this.executing = false;
    }
}
