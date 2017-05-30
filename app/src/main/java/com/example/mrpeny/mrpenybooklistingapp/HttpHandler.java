package com.example.mrpeny.mrpenybooklistingapp;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by MrPeny on 2017. 05. 30..
 */

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public String fetchBookData(String requestUrl) {
        String response = null;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream =
                        new BufferedInputStream(httpURLConnection.getInputStream());
                response = convertStreamToString(inputStream);
            } else {
                Log.e(TAG, "Request error. Status code: " + httpURLConnection.getResponseCode() +
                        " " + httpURLConnection.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        return response;
    }

    private String convertStreamToString(InputStream inputStream) {
        if (inputStream != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            try {
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
               Log.e(TAG, "BufferedReader error: " + e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "inputStream closing error: " + e.getMessage());
                }
            }
            return stringBuilder.toString();
        } else {
            return "";
        }
    }
}
