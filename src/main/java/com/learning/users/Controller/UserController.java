package com.learning.users.Controller;

import com.learning.users.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import java.util.Set;

public class UserController {

    public static final Validator VALIDATOR = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    public boolean addUser(User user) {

        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(user);
        if(!violations.isEmpty()){
            violations.forEach(userConstraintViolation -> System.out.println(userConstraintViolation.getMessage()));
            return false;
        }
        return true;

    }
}
