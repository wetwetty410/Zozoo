package com.nqt.zozoo.banhang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by USER on 1/2/2019.
 */

public class BanHangAsyncTask extends AsyncTask<Void, Integer, Void> {
    @SuppressLint("StaticFieldLeak")
    private AppCompatActivity appCompatActivity;
    private ProgressDialog progressDrawable;

    public BanHangAsyncTask(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDrawable = new ProgressDialog(appCompatActivity);
        progressDrawable.setMessage("Loading....");
        progressDrawable.setIndeterminate(false);
        progressDrawable.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDrawable.setCancelable(true);
        progressDrawable.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(900);
        } catch (InterruptedException ignored) {
        }
        progressDrawable.dismiss();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        appCompatActivity.startActivity(new Intent(appCompatActivity, BanHangActivity.class));
    }
}