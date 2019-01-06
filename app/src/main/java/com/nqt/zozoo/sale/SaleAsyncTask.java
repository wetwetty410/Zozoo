package com.nqt.zozoo.sale;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by USER on 1/2/2019.
 */

public class SaleAsyncTask extends AsyncTask<Void, Integer, Void> {
    private AppCompatActivity appCompatActivity;
    private ProgressDialog progressDrawable;
    private android.content.Intent intent;

    public SaleAsyncTask(AppCompatActivity appCompatActivity) {
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
        intent = new Intent(appCompatActivity, SaleActivity.class);
        progressDrawable = new ProgressDialog(appCompatActivity);
        progressDrawable.setMessage("Loading....");
        progressDrawable.setIndeterminate(false);
        progressDrawable.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDrawable.setCancelable(true);
        progressDrawable.show();

    }

    public SaleAsyncTask() {
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
        super.onPostExecute(aVoid);
        appCompatActivity.startActivity(intent);
    }
}
