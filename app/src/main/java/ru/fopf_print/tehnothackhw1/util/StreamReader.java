package ru.fopf_print.tehnothackhw1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamReader {

    public static String getResponseString(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
        } finally {
            stream.close();
        }
        return sb.toString();
    }

}
