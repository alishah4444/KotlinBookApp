package com.iaminca.openai.utils;

import java.util.Random;
import java.util.UUID;

public class CodeUtil {


    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public static String getUpperUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","").toUpperCase();
    }
}
