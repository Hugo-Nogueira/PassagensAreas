package com.example.hugo.trabalhoandroid.Service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class FinalizarCompraService extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... param) {
        try {
            URL url = null;

            url = new URL("https://service.davesmartins.com.br/api/voo/"+param[1]+"/poltronas/"+param[2]+"" );

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.setReadTimeout(95 * 1000);
            urlConnection.setConnectTimeout(95 * 1000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("code", param[0]);
            urlConnection.setRequestProperty("X-Environment", "android");

            urlConnection.connect();

            Integer a =  urlConnection.getResponseCode();

            return a+"";

            /*String finalJson = "";
            if (urlConnection.getResponseCode() == 200) {
                InputStream responseBody = urlConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader reader = new BufferedReader(responseBodyReader);
                StringBuffer buffer = new StringBuffer();
                String line ="";

                while ((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                finalJson = buffer.toString();
            } else {
                finalJson = urlConnection.getResponseCode()+"";
            }

            return finalJson;
            */

        } catch (MalformedURLException e) {
            return "Erro1: "+e.getMessage();
        } catch (IOException e) {
            return "Erro2: "+e.getMessage();
        }
    }
}
