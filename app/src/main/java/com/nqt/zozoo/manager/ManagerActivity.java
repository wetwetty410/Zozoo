package com.nqt.zozoo.manager;

import android.graphics.PorterDuff;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        btnTaiKhoan = findViewById(R.id.btn_tai_khoan);
        btnSoDoBan = findViewById(R.id.btn_so_do_phong_ban);
        btnNhomMonAn = findViewById(R.id.btn_quan_ly_nhom_mon_an);
        btnMonAn = findViewById(R.id.btn_quan_ly_mon_an);
        toolbar = findViewById(R.id.tlb_act_quan_ly);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản Lý");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            case R.id.btn_quan_ly_nhom_mon_an:
                onClickNhomMonAn();
                break;
            case R.id.btn_quan_ly_mon_an:
                onClickMonAn();
                break;

            default:
                break;
        }
    }

    private void onClickMonAn() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, AddFoodFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void onClickNhomMonAn() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, AddGroupFoodFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void onClickSoDoBan() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, AddTableFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
