package com.learning.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.UtilPersonal;

public class UtilPersonalTest {

    @Test
    void shouldCertifyEmailIsWithinPattern() {

        Assertions.assertAll(
                () -> Assertions.assertTrue(UtilPersonal.validateEmail("email@test.com")),
                () -> Assertions.assertFalse(UtilPersonal.validateEmail("emailtest.com"))
        );

    }

}
