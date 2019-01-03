package com.nqt.zozoo.nhabep;

import android.app.Fragment;
import android.os.Bundle;

public class BepFragment extends Fragment {
    public static BepFragment newInstance() {

        Bundle args = new Bundle();

        BepFragment fragment = new BepFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
