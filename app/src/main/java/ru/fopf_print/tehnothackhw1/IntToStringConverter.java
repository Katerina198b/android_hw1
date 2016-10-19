package ru.fopf_print.tehnothackhw1;

/**
 * Created by oem on 19.10.16.
 */

public class IntToStringConverter {
    Integer MAX = 1000;
    private StringBuilder number = null;

    private void one (Integer i) {
        switch (i) {
            case 1:
                number.append("один ");
                return;
            case 2:
                number.append("два ");
                return;
            case 3:
                number.append("три ");
                return;
            case 4:
                number.append("четыре ");
                return;
            case 5:
                number.append("пять ");
                return;
            case 6:
                number.append("шесть ");
                return;
            case 7:
                number.append("семь ");
                return;
            case 8:
                number.append("восемь ");
                return;
            case 9:
                number.append("девять ");
        }
    }

    private void two(Integer i) {
        switch (i) {
            case 2:
                number.append("двадцать ");
                return;
            case 3:
                number.append("тридцать ");
                return;
            case 4:
                number.append("сорок ");
                return;
            case 5:
                number.append("пятьдесят ");
                return;
            case 6:
                number.append("шестьдесят ");
                return;
            case 7:
                number.append("семьдесят ");
                return;
            case 8:
                number.append("восемьдесят ");
                return;
            case 9:
                number.append("девяносто ");
        }
    }

    private void three(Integer i) {
        switch (i) {
            case 1:
                number.append("сто ");
                return;
            case 2:
                number.append("двести ");
                return;
            case 3:
                number.append("триста ");
                return;
            case 4:
                number.append("четыреста ");
                return;
            case 5:
                number.append("пятьсот ");
                return;
            case 6:
                number.append("шестьсот ");
                return;
            case 7:
                number.append("семьсот ");
                return;
            case 8:
                number.append("восемсот ");
                return;
            case 9:
                number.append("девятьсот ");
        }
    }

    public void decade(Integer i) {
        switch (i) {
            case 0:
                number.append("десять");
                return;
            case 1:
                number.append("одиннадцать ");
                return;
            case 2:
                number.append("двенадцать ");
                return;
            case 3:
                number.append("тринадцать ");
                return;
            case 4:
                number.append("четырнадцать ");
                return;
            case 5:
                number.append("пятнадцать ");
                return;
            case 6:
                number.append("шестнадцать ");
                return;
            case 7:
                number.append("семнадцать ");
                return;
            case 8:
                number.append("восемнадцать ");
                return;
            case 9:
                number.append("девятнадцать ");
        }
    }

    public IntToStringConverter() {
        number = new StringBuilder();
    }

    public String MakeStringFromInteger(int i) {

        if (i/100  > 0) {
            three(i / 100);
        }
        if ((i/10) % 10 == 1) {
            decade(i % 10);
        } else {
            if ((i/10) % 10 > 1) {
                two((i/10) % 10);
            }
            if (i % 10 != 0) {
                one(i % 10);
            }
        }
        String n = number.toString();
        number.delete(0, number.length());
        return n;
    }
}
