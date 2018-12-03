package com.nqt.zozoo.banhang;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nqt.zozoo.R;

public class BanHangActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_hang);

        tabLayout=findViewById(R.id.ban_hang_tab);
        viewPager=findViewById(R.id.ban_hang_viewpager);

        tabLayout.setupWithViewPager(viewPager);
    }
}
