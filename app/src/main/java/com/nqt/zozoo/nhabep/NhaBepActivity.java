package com.nqt.zozoo.nhabep;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;

public class NhaBepActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TextView txtTitle;
    private ImageView imgBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nha_bep);
        tabLayout = findViewById(R.id.nha_bep_tab);
        viewPager = findViewById(R.id.nha_bep_viewpager);
        toolbar = findViewById(R.id.tlb_nha_bep);
        txtTitle = findViewById(R.id.txt_nha_bep_title);
        imgBackStack = findViewById(R.id.img_nha_bep_backstack);

        txtTitle.setText("Nhà Bếp");
        imgBackStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    private void setupViewPager(){

    }
}
