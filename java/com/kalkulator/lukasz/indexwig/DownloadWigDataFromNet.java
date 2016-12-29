package com.kalkulator.lukasz.indexwig;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Lukasz on 2016-12-28.
 */

class DownloadWigDataFromNet extends AsyncTask<String, Void, ArrayList<Float>> {
    public AsyncResponse delegate = null;

    @Override
    protected void onPostExecute(ArrayList<Float> result) {
        delegate.processFinish(result);
    }


    @Override
    protected ArrayList<Float> doInBackground(String... params) {
        ArrayList<Float> outList=new ArrayList<Float>();
        String urlString=params[0];

        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlString);
            urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));

            String line;
            int count=0;
            while ((line = r.readLine()) != null) {
                if(count>0) {
                    StringTokenizer tk = new StringTokenizer(line = line.replaceAll(",","."));
                    outList.add(Float.parseFloat(tk.nextToken()));
                }
                count++;
            }

        } catch (IOException e) {
            Log.e("error","błąd połączenia");
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            }
        return outList;
        }






}
