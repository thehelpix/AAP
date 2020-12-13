package ru.TheHelpix.aap.utils;

import java.io.*;
import java.net.*;

public class HTTP {


    public static void post(String s, String s2) {
        try {
            URLConnection openConnection = new URL(s).openConnection();
            openConnection.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openConnection.getOutputStream());
            outputStreamWriter.write(s2);
            outputStreamWriter.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            outputStreamWriter.close();
            bufferedReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String get(String s) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setConnectTimeout(60000);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
            httpURLConnection.setRequestProperty("Accept", "*/*");
            InputStream inputStream;
            if (httpURLConnection.getResponseCode() >= 400) {
                inputStream = httpURLConnection.getErrorStream();
            }
            else {
                inputStream = httpURLConnection.getInputStream();
            }
            String string = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedReader.close();
            return string;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "fail";
        }
    }


}


