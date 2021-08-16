package me.mandaveiga.mult.model.atividade;

import lombok.Getter;
import lombok.Setter;

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

    private boolean isExecuting;

    private AtividadeManager() {
        this.isExecuting = false;
    }
}
