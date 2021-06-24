package com.epam.izh.rd.online.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        Path path = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "sensitive_data.txt");
        String fileContent = "";
        try {
            fileContent = Files.lines(path).reduce("", String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileContent.replaceAll("(\\d{4})\\s\\d{4}\\s\\d{4}\\s(\\d{4})", "$1 **** **** $2");
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        Path path = Paths.get(System.getProperty("user.dir") + File.separator + "src"+  File.separator +"main"+ File.separator +"resources"+ File.separator +"sensitive_data.txt" );
        String fileContent ="";
        try{
            fileContent = Files.lines(path).reduce("", String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileContent = fileContent.replaceAll("\\$\\{payment_amount}", String.valueOf((int) paymentAmount));
        fileContent = fileContent.replaceAll("\\$\\{balance}", String.valueOf((int) balance));
        return fileContent;
    }
}
