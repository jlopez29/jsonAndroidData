package com.jlopez.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by JLOPEZ on 8/15/2017.
 */

public class ShowRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_record);

        TextView idValue = (TextView)findViewById(R.id.recordID);
        TextView nameValue = (TextView)findViewById(R.id.recordName);
        TextView phoneValue = (TextView)findViewById(R.id.recordPhone);
        TextView dateValue = (TextView)findViewById(R.id.recordDate);
        TextView ageValue = (TextView)findViewById(R.id.recordAge);

        idValue.setText(getIntent().getStringExtra("EXTRA_ID"));
        nameValue.setText(getIntent().getStringExtra("EXTRA_NAME"));
        phoneValue.setText(getIntent().getStringExtra("EXTRA_PHONE"));
        dateValue.setText(getIntent().getStringExtra("EXTRA_DATE"));
        ageValue.setText(getIntent().getStringExtra("EXTRA_AGE"));


        new DownloadImageTask((ImageView) findViewById(R.id.recordImage))
                .execute(getIntent().getStringExtra("EXTRA_IMAGE"));



    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
