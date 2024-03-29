package com.nqt.zozoo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nqt.zozoo.fastorder.FastOrderActivity;
import com.nqt.zozoo.kitchen.KitchenActivity;
import com.nqt.zozoo.sale.SaleAsyncTask;
import com.nqt.zozoo.manager.ManagerActivity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final String EMAIL = "truongnq410@gmail.com";
    private static final String SUBJECT = "Zozoo-complaint";
    private Toolbar toolbar;
    private View llnBanHang;
    private View llnDatTaiQuay;
    private View llnNhaBep;
    private View llnNhaKho;
    private View llnBaoCao;
    private View llnCaiDat;
    private View llnTroGiup;
    private View llnQuanLy;
    private View llnHuongDan;
    private FloatingActionButton fab;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // clearAppData();
        toolbar = findViewById(R.id.toolbar);
        llnBanHang = findViewById(R.id.lln_ban_hang);
        llnNhaBep = findViewById(R.id.lln_nha_bep);
        llnDatTaiQuay = findViewById(R.id.lln_dat_tai_quay);
        llnNhaKho = findViewById(R.id.lln_kho);
        llnBaoCao = findViewById(R.id.lln_bao_cao);
        llnCaiDat = findViewById(R.id.lln_cai_dat);
        llnQuanLy = findViewById(R.id.lln_quan_ly);
        llnTroGiup = findViewById(R.id.lln_tro_giup);
        llnHuongDan = findViewById(R.id.lln_huong_dan);
        fab = (FloatingActionButton) findViewById(R.id.fab_email);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fab.setOnClickListener(this);
        llnBanHang.setOnClickListener(this);
        llnQuanLy.setOnClickListener(this);
        llnDatTaiQuay.setOnClickListener(this);
        llnNhaBep.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_camera:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    break;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                SaleAsyncTask banHangAsyncTask = new SaleAsyncTask(this);
                banHangAsyncTask.execute();
                break;
            // Handle the camera action
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lln_ban_hang:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    break;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Log.d(TAG, "onClick: btn ban hang");

                SaleAsyncTask banHangAsyncTask = new SaleAsyncTask(this);
                banHangAsyncTask.execute();
                break;

            case R.id.lln_quan_ly:
                startActivity(new Intent(this, ManagerActivity.class));
                break;

            case R.id.lln_dat_tai_quay:
                startActivity(new Intent(this, FastOrderActivity.class));
                break;
            case R.id.lln_nha_bep:
                startActivity(new Intent(this, KitchenActivity.class));
                break;
            case R.id.fab_email:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL});
                intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            default:
                break;
        }
    }

}
