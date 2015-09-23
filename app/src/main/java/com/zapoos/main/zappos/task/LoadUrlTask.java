package com.zapoos.main.zappos.task;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zapoos.main.zappos.model.Response;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vipulmittal on 08/08/15.
 */
public class LoadUrlTask extends AsyncTask<String, Void, Response> {
    private ResponseCallback callback;
    private Class<? extends Response> responseClass;
    private String error;

    public ResponseCallback getCallback() {
        return callback;
    }

    public void setCallback(ResponseCallback callback) {
        this.callback = callback;
    }

    public LoadUrlTask(ResponseCallback callback, Context context, Class responseClass) {
        this.callback = callback;
        this.responseClass = responseClass;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Response doInBackground(String... locations) {
        URL url;
        try {
            String dataUrl = locations[0];
            url = new URL(dataUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String response = readStream(in);

            return new Gson().fromJson(response, responseClass);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            error = "No results found for the gives search term.";
        } catch (IOException e) {
            error = "Network not available.";
        }

        return null;
    }

    private String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(Response response) {
        if (response == null)
            callback.onError(error);
        else
            callback.onResponse(response);
    }


    public interface ResponseCallback {
        void onResponse(Response response);

        void onError(String message);
    }
}
