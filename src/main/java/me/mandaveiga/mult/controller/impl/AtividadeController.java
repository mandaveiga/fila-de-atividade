package me.mandaveiga.mult.controller.impl;

import io.micrometer.core.lang.NonNull;
import io.micrometer.core.lang.Nullable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.mandaveiga.mult.controller.BaseController;
import me.mandaveiga.mult.controller.dto.CreateAtividadeDto;
import me.mandaveiga.mult.controller.dto.UpdateAtividadeDto;
import me.mandaveiga.mult.controller.validator.AtividadeValidator;
import me.mandaveiga.mult.model.atividade.Atividade;
import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.service.impl.atividade.AtividadeServiceImpl;
import me.mandaveiga.mult.service.impl.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividade")
@Api(value = "Atividades")
public class AtividadeController extends BaseController<Atividade> {

    private final AtividadeValidator atividadeValidator;
    private final PessoaService pessoaService;

    @Autowired
    public AtividadeController(AtividadeServiceImpl service, AtividadeValidator atividadeValidator, PessoaService pessoaService) {
        super(service);
        this.pessoaService = pessoaService;
        this.atividadeValidator = atividadeValidator;
    }

    @PostMapping(path = "", produces = "application/json")
    @ApiOperation(value = "Persist a new activity in database.")
    public ResponseEntity<Object> save(@RequestBody @NonNull CreateAtividadeDto body, @Nullable BindingResult result) {
        atividadeValidator.validate(body, result);

        Optional<Pessoa> entityPessoa = pessoaService.findById(Optional.ofNullable(body.getPessoaId()).orElse(0L));

        Atividade atividade = Atividade.builder()
                .esforco(body.getEsforco())
                .pessoa(entityPessoa.orElse(null)).build();

        Optional<Atividade> entity = service.save(atividade);

        return entity
                .map((x) -> ResponseEntity.ok((Object) x))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Update activity.")
    public ResponseEntity<Object> update(@PathVariable("id") @NonNull String idAtividade, @RequestBody @NonNull UpdateAtividadeDto body) {

        Optional<Atividade> atividadeEntity = service.findById(Long.parseLong(idAtividade));
        if(!atividadeEntity.isPresent()){
           return ResponseEntity.notFound().build();
        }

        Optional<Pessoa> pessoaEntity = pessoaService.findById(body.getPessoaId());
        if(!pessoaEntity.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        Atividade atividade = atividadeEntity.get();
        atividade.setPessoa(pessoaEntity.get());

        Optional<Atividade> entity = service.save(atividade);

        return entity
                .map((x) -> ResponseEntity.ok((Object) x))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(path = "", produces = "application/json")
    @ApiOperation(value = "Persist a new activity in database.")
    public ResponseEntity<Object> getAll() {
        List<Atividade> entities = service.findAll();

        return ResponseEntity.ok(entities);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "")
    public ResponseEntity<Object> show(@PathVariable("id") @NonNull String id ) {
        Optional<Atividade> entity = service.findById(Long.parseLong(id));

        return entity
                .map((x) -> ResponseEntity.ok((Object) x))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "")
    public ResponseEntity<Object> deleteActivityById(@PathVariable(value = "id") long id) {
        Optional<Boolean> isDeleted = service.deleteById(id);

        return isDeleted
                .filter((x)-> x.equals(true))
                .map((x)-> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
