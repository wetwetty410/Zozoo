package com.nqt.zozoo.banhang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class MenuFragment extends Fragment {
    private static final String PAGE = "page";
    private static final String TITLE = "title";

    public MenuFragment() {
    }

    public static MenuFragment newInstance(int page, String title) {

        Bundle args = new Bundle();

        MenuFragment fragment = new MenuFragment();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
