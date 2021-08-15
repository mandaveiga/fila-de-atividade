package me.mandaveiga.mult.controller.validator;

import me.mandaveiga.mult.model.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PessoaValidator implements Validator {

    @Autowired
    public PessoaValidator() {

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Pessoa.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Pessoa pessoa = (Pessoa) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "produtividade", "email.produtividade");

        if (pessoa.getProdutividade() < 30) {
            errors.rejectValue("produtividade", "Porcentagem de produtividade invalida");
        }
    }
}