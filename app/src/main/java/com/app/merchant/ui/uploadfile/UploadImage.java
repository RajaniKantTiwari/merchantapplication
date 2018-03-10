package com.app.merchant.ui.uploadfile;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;

import com.app.merchant.event.EncodedBitmap;
import com.app.merchant.ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;


public class UploadImage extends AsyncTask<Void, Void, String> {
    private int type;
    private final BaseActivity activity;
    private Bitmap image;

    public UploadImage(BaseActivity activity, Bitmap image, int type) {
        this.activity = activity;
        this.image = image;
        this.type=type;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //activity.showProgress();

    }

    @Override
    protected String doInBackground(Void... params) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //compress the image to jpg format
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        String encodeImage =Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
      return encodeImage;
    }

    @Override
    protected void onPostExecute(String encodeImage) {
        EventBus.getDefault().post(new EncodedBitmap(type,encodeImage));
        //activity.hideProgress();
    }
}
