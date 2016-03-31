package com.example.manishdwibedy.stockmarketviewer.asynctasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by manishdwibedy on 3/21/16.
 */
public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView imageView;
    private boolean isLoad;

    public ImageLoadTask(String url, ImageView imageView, boolean isLoad) {
        this.url = url;
        this.imageView = imageView;
        this.isLoad = isLoad;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if(isLoad)
        {
            imageView.setImageBitmap(result);
        }

        //PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);

    }

}