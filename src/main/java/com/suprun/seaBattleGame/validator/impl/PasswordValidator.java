package com.suprun.seaBattleGame.validator.impl;

import com.suprun.seaBattleGame.validator.Validator;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String> {
    private static final String VALID_PASSWORD_REGEX = "^(?=.*[0-9]).{8,}$";

    private static Validator<String> instance;

    private PasswordValidator() {

    }

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new PasswordValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String password) {
        return Pattern.matches(VALID_PASSWORD_REGEX, password);
    }
}
