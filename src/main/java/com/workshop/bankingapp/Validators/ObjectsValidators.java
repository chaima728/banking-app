package com.workshop.bankingapp.Validators;

import com.workshop.bankingapp.Exceptions.ObjectValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ObjectsValidators <T>{

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void Validaate(T objecttovalidate) {
        Set<ConstraintViolation<T>> violations = validator.validate(objecttovalidate);
        if(!violations.isEmpty()) {
            Set<String> errormessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectValidationException(errormessages, objecttovalidate.getClass().getName() );
        }

    }
}
