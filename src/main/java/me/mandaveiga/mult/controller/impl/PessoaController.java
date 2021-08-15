package me.mandaveiga.mult.controller.impl;

import io.micrometer.core.lang.NonNull;
import io.micrometer.core.lang.Nullable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.mandaveiga.mult.controller.BaseController;
import me.mandaveiga.mult.controller.validator.PessoaValidator;
import me.mandaveiga.mult.model.error.ApplicationError;
import me.mandaveiga.mult.model.pessoa.Pessoa;
import me.mandaveiga.mult.service.impl.pessoa.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.servlet.function.ServerResponse.badRequest;

@RestController
@RequestMapping("/pessoa")
@Api(value = "Pessoas")
public class PessoaController extends BaseController<Pessoa> {

    private final PessoaValidator pessoaValidator;

    @Autowired
    public PessoaController(PessoaServiceImpl service, PessoaValidator pessoaValidator) {
        super(service);
        this.pessoaValidator = pessoaValidator;
    }

    @PostMapping(path = "", produces = "application/json")
    @ApiOperation(value = "Persist a new user in database.")
    public ResponseEntity<Object> save(@RequestBody @NonNull Pessoa body, @Nullable BindingResult result) {
        pessoaValidator.validate(body, result);

        if (result.hasErrors()) {
            ObjectError validationError = result.getAllErrors().get(0);
            ApplicationError error = new ApplicationError(validationError.getCode());

            return ResponseEntity.badRequest().body(error);
        }

        Optional<Pessoa> entity = service.save(body);

        return entity
                .map((x) -> ResponseEntity.ok((Object) x))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
