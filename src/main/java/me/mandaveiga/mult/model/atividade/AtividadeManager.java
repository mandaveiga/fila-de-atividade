package me.mandaveiga.mult.model.atividade;

import lombok.Getter;
import lombok.Setter;
import me.mandaveiga.mult.service.impl.atividade.AtividadeServiceImpl;

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
    private int porcentagemDeConclusao;

    private void setPorcentagemDeConclusao(int porcentagemDeConclusao) {
        this.porcentagemDeConclusao = porcentagemDeConclusao;
    }

    public int updatePorcentagemDeConclusao() {
        this.porcentagemDeConclusao = AtividadeServiceImpl.calculatePercentage(this.getDateStart(), this.DateEnd);
        setPorcentagemDeConclusao(porcentagemDeConclusao);
        return porcentagemDeConclusao;
    }

    private AtividadeManager() {
        this.executing = false;
    }
}
