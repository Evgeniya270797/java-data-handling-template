package com.epam.izh.rd.online.service;

import java.util.Arrays;
import java.util.Locale;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     *
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        return base.replace(remove,"");
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {

        String[] splitString = text.split("");
       if(splitString[splitString.length-1].equals("?")){
           return true;
       }else{
           return false;
       }


    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {

        StringBuilder stringBuilder = new StringBuilder();
        for (String elem:elements) {
            stringBuilder.append(elem);
        }

        return stringBuilder.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     *
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {

        String[] string = text.split("");

        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < string.length; i++) {

            if(i%2==0){
                newString= newString.append((string[i].toLowerCase(Locale.ROOT)));
            }else{
                newString=newString.append(string[i].toUpperCase(Locale.ROOT));
            }
        }


        return newString.toString();
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     *
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     *
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {
        if(string.trim().equals("")){
            return false;
        }
        String newString = new StringBuilder(string).reverse().toString().toLowerCase(Locale.ROOT).replace(" ", "");
        return newString.equals(string.toLowerCase(Locale.ROOT).replace(" ", ""));
    }

}
