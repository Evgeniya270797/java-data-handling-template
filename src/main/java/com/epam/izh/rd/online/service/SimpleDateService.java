package com.epam.izh.rd.online.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class SimpleDateService implements DateService {

    /**
     * Метод парсит дату в строку
     *
     * @param localDate дата
     * @return строка с форматом день-месяц-год(01-01-1970)
     */
    @Override
    public String parseDate(LocalDate localDate) {
        String pattern = "dd-MM-yyyy";

        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern(pattern);
        String date = formatter.format(localDate);


        return date;
    }

    /**
     * Метод парсит строку в дату
     *
     * @param string строка в формате год-месяц-день часы:минуты (1970-01-01 00:00)
     * @return дата и время
     */
    @Override
    public LocalDateTime parseString(String string) {
        LocalDateTime time;
        time =LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return time;


    }

    /**
     * Метод конвертирует дату в строку с заданным форматом
     *
     * @param localDate исходная дата
     * @param formatter формат даты
     * @return полученная строка
     */
    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {

        DateTimeFormatter format =formatter;
        String date = format.format(localDate);

        return date;
    }

    /**
     * Метод получает следующий високосный год
     *
     * @return високосный год
     */
    @Override
    public long getNextLeapYear() {
        LocalDate now =  LocalDate.now();
        long getYear = now.getYear();

        while(true){
            boolean isLeap = Year.of((int) getYear).isLeap();
            if (isLeap)
                break;
            else {
                now =  now.plusYears(1);
                getYear = now.getYear();
            }
        }

        return getYear;
    }

    /**
     * Метод считает число секунд в заданном году
     *
     * @return число секунд
     */
    @Override
    public long getSecondsInYear(int year) {
        int numberOfDaysInYear = Year.of(year).isLeap() ? 366 : 365;

        return numberOfDaysInYear * 24 * 60 * 60;
    }


}
