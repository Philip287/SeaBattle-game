package com.suprun.seaBattleGame.validator.impl;

import com.suprun.seaBattleGame.service.ContentGame;
import com.suprun.seaBattleGame.validator.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathValidator implements Validator {

    private static Validator<String> instance;

    private FilePathValidator() {

    }

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new FilePathValidator();
        }
        return instance;
    }


    @Override
    public boolean validate(Object o) {
        String path = o.toString();
        if (path == null || path.isEmpty() || path.trim().isEmpty()) {
            return false;
        }
        boolean flag = false;
        File file = new File(path);
        if (file.exists()) {
            if (file.length() > 0) {
                flag = true;
            }
        } else {
            try {
                file.createNewFile();
                Path pathFile = Paths.get(ContentGame.PATH);
                Files.writeString(pathFile, ContentGame.JSON_ADMIN_REGISTER);
                flag = true;
            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        }
        return flag;
    }
}
