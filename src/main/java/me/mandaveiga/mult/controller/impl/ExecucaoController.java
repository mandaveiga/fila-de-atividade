package me.mandaveiga.mult.controller.impl;

import io.micrometer.core.lang.NonNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.mandaveiga.mult.controller.dto.ExecutionDto;
import me.mandaveiga.mult.model.atividade.AtividadeManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/execucao")
@Api(value = "Activities execution")
public class ExecucaoController {

    @GetMapping(path = "", produces = "application/json")
    @ApiOperation(value = "")
    public ResponseEntity<Object> getIsExecuting() {
        boolean isExecuting = AtividadeManager.getInstance().isExecuting();

        ExecutionDto executionDto = new ExecutionDto(isExecuting);

        return ResponseEntity.ok(executionDto);
    }

    @PutMapping(path = "", produces = "application/json")
    @ApiOperation(value = "")
    public ResponseEntity<Object> updateIsExecuting(@RequestBody @NonNull ExecutionDto body) {
        AtividadeManager.getInstance().setExecuting(body.isExecuting());

        return ResponseEntity.noContent().build();
    }
}
