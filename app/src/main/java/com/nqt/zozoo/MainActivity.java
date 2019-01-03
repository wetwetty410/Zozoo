package com.nqt.zozoo;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nqt.zozoo.banhang.BanHangActivity;
import com.nqt.zozoo.banhang.BanHangAsyncTask;
import com.nqt.zozoo.database.DatabaseManager;
import com.nqt.zozoo.quanly.QuanLyActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final String EMAIL = "truongnguyen41096@gmail.com";
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(this);
        llnBanHang.setOnClickListener(this);
        llnQuanLy.setOnClickListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_camera:
                Intent intent = new Intent(this, BanHangActivity.class);
                startActivity(intent);
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
                Log.d(TAG, "onClick: btn ban hang");
                BanHangAsyncTask banHangAsyncTask = new BanHangAsyncTask(this);
                banHangAsyncTask.execute();
                break;

            case R.id.lln_quan_ly:
                startActivity(new Intent(this, QuanLyActivity.class));
                break;
            case R.id.fab:
                Log.d(TAG, "onClick: fab");
                try {
                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    final PackageManager pm = getPackageManager();
                    final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
                    ResolveInfo best = null;
                    for (final ResolveInfo info : matches)
                        if (info.activityInfo.packageName.endsWith(".gm") ||
                                info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
                    if (best != null)
                        intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                    startActivity(intent);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + EMAIL));
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
//                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
//                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    //TODO smth
                }
            default:
                break;
        }
    }

}
