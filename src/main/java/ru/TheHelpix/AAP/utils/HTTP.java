package ru.TheHelpix.AAP.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HTTP {
    public static void main(final String[] array) {
    }

    public static String post(final String s, final String s2) {
        try {
            final URLConnection openConnection = new URL(s).openConnection();
            openConnection.setDoOutput(true);
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openConnection.getOutputStream());
            outputStreamWriter.write(s2);
            outputStreamWriter.flush();
            String string = "";
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                string += line;
            }
            outputStreamWriter.close();
            bufferedReader.close();
            return string;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "fail";
        }
    }

    public static String get(final String s) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
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
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                string += line;
            }
            bufferedReader.close();
            return string;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "fail";
        }
    }
}


