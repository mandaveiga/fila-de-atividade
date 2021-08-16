package me.mandaveiga.mult.controller.validator;

import me.mandaveiga.mult.controller.dto.CreateAtividadeDto;
import me.mandaveiga.mult.errors.ValidateException;
import me.mandaveiga.mult.model.atividade.Atividade;
import me.mandaveiga.mult.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AtividadeValidator implements Validator {

    @Autowired
    public AtividadeValidator() {

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Atividade.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateAtividadeDto atividade = (CreateAtividadeDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "esforco", "esforco.empty");

        if (atividade.getEsforco() < 1 || atividade.getEsforco() > 100) {
            errors.rejectValue("esforco", "Esforço deve ser entre 1 e 100.");
        }

        if (errors.hasErrors()) {
            throw new ValidateException(errors.getAllErrors().get(0).getCode());
        }
    }
}