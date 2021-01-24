package com.example.brikouli.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Bitmap doInBackground(String... strings) {

        String urldisplay = strings[0];
        Bitmap pic = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            pic = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return pic;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
