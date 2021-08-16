package me.mandaveiga.mult.controller.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.mandaveiga.mult.controller.BaseController;
import me.mandaveiga.mult.controller.validator.AtividadeValidator;
import me.mandaveiga.mult.errors.BusinessException;
import me.mandaveiga.mult.model.atividade.Atividade;
import me.mandaveiga.mult.model.atividade.AtividadeManager;
import me.mandaveiga.mult.model.atividade.Execution;
import me.mandaveiga.mult.model.error.ApplicationError;
import me.mandaveiga.mult.service.impl.atividade.AtividadeService;
import me.mandaveiga.mult.service.impl.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/execucao")
@Api(value = "Activities execution")
public class ExecucaoController extends BaseController<Atividade>{

    private final AtividadeValidator atividadeValidator;
    private final AtividadeService atividadeService;

    @Autowired
    public ExecucaoController(AtividadeService service, AtividadeValidator atividadeValidator, PessoaService pessoaService) {
        super(service);
        this.atividadeValidator = atividadeValidator;
        this.atividadeService = service;
    }

    @GetMapping(path = "", produces = "application/json")
    @ApiOperation(value = "Execution status")
    public ResponseEntity<Object> getIsExecuting() {
        AtividadeManager.getInstance().updatePorcentagemDeConclusao();
        AtividadeManager isExecuting = AtividadeManager.getInstance();
        return ResponseEntity.ok(isExecuting);
    }

    @PutMapping(path = "", produces = "application/json")
    @ApiOperation(value = "start Executing")
    public ResponseEntity<Object> startExecuting() {
        try {
            Execution execution = atividadeService.startingExecution();

            return ResponseEntity.ok(execution);
        }catch (BusinessException e) {
            return ResponseEntity.badRequest().body(new ApplicationError(e.getMessage()));
        }
    }
}
