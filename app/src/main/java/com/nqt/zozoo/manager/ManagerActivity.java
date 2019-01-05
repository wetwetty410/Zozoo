package com.nqt.zozoo.manager;

import android.graphics.PorterDuff;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTaiKhoan;
    private Button btnSoDoBan;
    private Button btnNhomMonAn;
    private Button btnMonAn;
    private ImageView imgBack;
    private Toolbar toolbar;
    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly);
        btnTaiKhoan = findViewById(R.id.btn_tai_khoan);
        btnSoDoBan = findViewById(R.id.btn_so_do_phong_ban);
        btnNhomMonAn = findViewById(R.id.btn_quan_ly_nhom_mon_an);
        btnMonAn = findViewById(R.id.btn_quan_ly_mon_an);
        toolbar = findViewById(R.id.tlb_act_quan_ly);
        imgBack = findViewById(R.id.img_quan_ly_backstack);
        txtTitle = findViewById(R.id.txt_quan_ly_title);

//        buttonEffect(btnTaiKhoan);
//        buttonEffect(btnSoDoBan);
//        buttonEffect(btnMonAn);
//        buttonEffect(btnNhomMonAn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        txtTitle.setText("Quản Lý");

        imgBack.setOnClickListener(this);
        btnTaiKhoan.setOnClickListener(this);
        btnSoDoBan.setOnClickListener(this);
        btnNhomMonAn.setOnClickListener(this);
        btnMonAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tai_khoan:
                break;
            case R.id.btn_so_do_phong_ban:
                onClickSoDoBan();
                break;
            case R.id.img_quan_ly_backstack:
                onBackPressed();
                break;
            case R.id.btn_quan_ly_nhom_mon_an:
                onClickNhomMonAn();
                break;
            case R.id.btn_quan_ly_mon_an:
                break;
            default:
                break;
        }
    }

    public static void buttonEffect(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    private void onClickNhomMonAn() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, AddGroupFoodFragment.newInstance(this));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void onClickSoDoBan() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, AddTableFragment.newInstance(this));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
