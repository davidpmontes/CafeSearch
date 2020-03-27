package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetNearbyPlaces extends AsyncTask<Object, String, String> {
    GoogleMap googleMap;
    String url;
    InputStream is;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String data;
    public AsyncResponse delegate = null;

    @Override
    protected String doInBackground(Object... params) {
        googleMap = (GoogleMap)params[0];
        url = (String)params[1];

        try {
            URL myurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)myurl.openConnection();
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line = "";
            stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

            data = stringBuilder.toString();

        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s)
    {
        JSONArray resultsArray;
        try{
            JSONObject parentObject = new JSONObject(s);
            resultsArray = parentObject.getJSONArray("results");

            delegate.processFinish(resultsArray);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
