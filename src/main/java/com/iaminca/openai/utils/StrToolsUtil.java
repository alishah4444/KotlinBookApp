package com.iaminca.openai.utils;

public class StrToolsUtil {
    public static int[] strToIntegerArray(String str){
        String[] split = str.split(",");
       int[] intArray = new int[split.length];
        for(int i =0;i<split.length-1;i++){
            Integer integer = Integer.valueOf(split[i]);
            intArray[i] =integer;
        }
        return intArray;
    }
}
