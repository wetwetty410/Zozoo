package com.nqt.zozoo.sale;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.Food;

public class SaleActivity extends AppCompatActivity implements View.OnClickListener,
        LocationTableFragment.OnListFragmentInteractionListener,
        ViewOrderFoodFragment.OnListFragmentInteractionListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar tlbBHSB;
    private TextView txtTitle;
    private ImageView imgBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

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
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));

        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#232F34"));
        }
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
    public void onListFragmentInteraction(Table ban) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_backstack:
                finish();
                break;
        }
    }

    @Override
    public void onListFragmentInteraction(Food monAn) {

    }
}
