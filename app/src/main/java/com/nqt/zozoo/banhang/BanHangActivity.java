package com.nqt.zozoo.banhang;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.Ban;

public class BanHangActivity extends AppCompatActivity implements View.OnClickListener, BanHangSoBanFragment.OnListFragmentInteractionListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar tlbBHSB;
    private TextView txtTitle;
    private ImageView imgBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_hang);

        tabLayout = findViewById(R.id.ban_hang_tab);
        viewPager = findViewById(R.id.ban_hang_viewpager);
        tlbBHSB = findViewById(R.id.tlb_fragment_bhsb);
        txtTitle = findViewById(R.id.txt_title);
        imgBackStack = findViewById(R.id.img_backstack);

        txtTitle.setText(R.string.ban_hang);
        imgBackStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setSupportActionBar(tlbBHSB);
        getSupportActionBar().setTitle("");

        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager() {
        BanHangPagerAdapter adapter = new BanHangPagerAdapter(getSupportFragmentManager(), this);
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
    public void onListFragmentInteraction(Ban ban) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_backstack:
                finish();
                System.out.print("Close Activity");
                break;
        }
    }
}
