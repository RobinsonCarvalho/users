package com.learning.users.repository;

import com.learning.users.model.User;
import jakarta.validation.Validation;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public interface UserRepository {

    jakarta.validation.Validator VALIDATOR = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    void create(User user);

    void update(User user);

    User read(String email);

    void delete(String email);

    int count();

}
