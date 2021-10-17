package com.nqt.zozoo.sale;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.Food;

import java.util.Objects;

public class SaleActivity extends AppCompatActivity implements View.OnClickListener,
        LocationTableFragment.OnListFragmentInteractionListener,
        ViewOrderFoodFragment.OnListFragmentInteractionListener {
    private static final String TAG = "SaleActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar tlbBHSB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        tabLayout = findViewById(R.id.ban_hang_tab);
        viewPager = findViewById(R.id.ban_hang_viewpager);
        tlbBHSB = findViewById(R.id.tlb_fragment_bhsb);

        setSupportActionBar(tlbBHSB);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Bán Hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
    }


    private void setupViewPager() {
        SalePagerAdapter adapter = new SalePagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Table ban) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onListFragmentInteraction(Food monAn) {

    }

}
