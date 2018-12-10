package com.nqt.zozoo.banhang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MonAnFragment extends Fragment {
    private static final String PAGE = "page";
    private static final String TITLE = "title";


    public MonAnFragment() {
    }

    public static MonAnFragment newInstance(int page, String title) {
        Bundle args = new Bundle();
        MonAnFragment fragment = new MonAnFragment();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
