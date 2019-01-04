package com.nqt.zozoo.banhang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;

/**
 * Created by USER on 1/2/2019.
 */

public class BanHangAsyncTask extends AsyncTask<Void, Integer, Void> {
    @SuppressLint("StaticFieldLeak")
    private AppCompatActivity appCompatActivity;
    private ProgressDialog progressDrawable;
    private android.content.Intent intent;

    public BanHangAsyncTask(AppCompatActivity appCompatActivity) {
        super();
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        intent = new Intent(appCompatActivity, BanHangActivity.class);
        progressDrawable = new ProgressDialog(appCompatActivity);
        progressDrawable.setMessage("Loading....");
        progressDrawable.setIndeterminate(false);
        progressDrawable.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDrawable.setCancelable(true);
        progressDrawable.show();

    }

    public BanHangAsyncTask() {
        super();
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
        appCompatActivity.startActivity(intent);
        super.onPostExecute(aVoid);
    }
}
