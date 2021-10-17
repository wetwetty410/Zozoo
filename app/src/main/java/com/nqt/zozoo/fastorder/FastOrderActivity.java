package com.nqt.zozoo.fastorder;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.sale.SaleActivity;

public class FastOrderActivity extends AppCompatActivity {

    private static final String TAG = "FastOrderActivity";

    private ImageView imgBackStack;
    private TextView txtTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_order);

        imgBackStack = findViewById(R.id.img_backstack_fast_order);
        txtTitle = findViewById(R.id.txt_title_fast_order);

        imgBackStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), SaleActivity.class);
//
            }
        });

        Log.d(TAG, "onCreate: ");

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (i < 100) {
//                    i++;
//                    try {
//                        Thread.sleep(100);
//                        Log.d(TAG, "run: " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if (i==99){
//                        txtTitle.setText("fd");
//                    }
//                }
//            }
//        };
//        new Thread(runnable).start();


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }
}
