package com.nqt.zozoo.kitchen;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.sale.DepthPageTransformer;

public class KitchenActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TextView txtTitle;
    private ImageView imgBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        tabLayout = findViewById(R.id.nha_bep_tab);
        viewPager = findViewById(R.id.nha_bep_viewpager);
        toolbar = findViewById(R.id.tlb_nha_bep);
        txtTitle = findViewById(R.id.txt_nha_bep_title);
        imgBackStack = findViewById(R.id.img_nha_bep_backstack);

        txtTitle.setText("Nhà Bếp");
        imgBackStack.setOnClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
    }

    private void setupViewPager() {
        KitchenPagerAdapter adapter = new KitchenPagerAdapter(getSupportFragmentManager());
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_nha_bep_backstack:
                finish();
                break;
        }
    }
}
