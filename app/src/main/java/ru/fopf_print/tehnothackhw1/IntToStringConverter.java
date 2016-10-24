package ru.fopf_print.tehnothackhw1;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

class IntToStringConverter {
    private StringBuilder number = null;
    private Resources resources = null;

    IntToStringConverter(Resources res) {
        number = new StringBuilder();
        resources = res;
    }

    String MakeStringFromInteger(int i) {

        if (i/100  > 0) {
            number.append(resources.getStringArray(R.array.numbers_array)[i/100 + 26]);
        }
        if ((i/10) % 10 == 1) {
            number.append(resources.getStringArray(R.array.numbers_array)[(i % 10) + 9]);
        } else {
            if ((i/10) % 10 > 1) {
                number.append(resources.getStringArray(R.array.numbers_array)[((i/10) % 10) + 17]);
            }
            if (i % 10 != 0) {
                number.append(resources.getStringArray(R.array.numbers_array)[i % 10 - 1]);
            }
        }
        String n = number.toString();
        number.delete(0, number.length());
        return n;
    }
}
