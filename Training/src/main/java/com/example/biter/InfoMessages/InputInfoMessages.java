package com.example.biter.InfoMessages;

public class InputInfoMessages {
    private final static String existUser = "Користувач вже існує";
    private final static String badInput = "Некоректно введені дані";
    private final static String goodInput = "Все круто";

    public static int getGoodInputCode(){
        return 1;
    }
    public static int getBadInputCode(){
        return 2;
    }
    public static int getExistUserCode(){
        return 3;
    }

    public static String getInfo(int code){
        switch (code){
            case 1:
                return goodInput;
            case 2:
                return badInput;
            default:
                return existUser;
        }
    }


}
