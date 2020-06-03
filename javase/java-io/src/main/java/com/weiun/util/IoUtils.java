package com.weiun.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IoUtils {


    public static String readInputStreamAsString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        bufferedReader.lines().forEach(sb::append);
        bufferedReader.close();
        return sb.toString();
    }
}
