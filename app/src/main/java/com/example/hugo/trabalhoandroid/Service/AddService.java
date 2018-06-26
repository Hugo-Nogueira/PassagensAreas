package com.example.hugo.trabalhoandroid.Service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AddService extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... param) {
        try {
            URL url = null;
            url = new URL("https://service.davesmartins.com.br/api/usuarios");

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(95 * 1000);
            urlConnection.setConnectTimeout(95 * 1000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-Environment", "android");

            String auxiliar = "{\"email\":  \""+param[0]+"\" , \"id\": \"0\" , \"login\": \""+param[1]+"\", \"nome\": \" "+param[2]+"\", \"senha\": \""+param[3]+"\"}";
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(auxiliar.getBytes());

            urlConnection.connect();
            Integer aux = urlConnection.getResponseCode();
            return aux.toString();

        } catch (MalformedURLException e) {
            return "Erro1: "+e.getMessage();
        } catch (IOException e) {
            return "Erro2: "+e.getMessage();
        }

    }
}
