package com.nqt.zozoo.banhang.quanlyban;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import static com.nqt.zozoo.banhang.quanlyban.SoBanContent.*;

/**
 * Created by USER on 12/3/2018.
 */

public class SoBanFragment extends Fragment {

    private static final String SO_BAN = "number-ban";
    private SoBanFragment.OnListFragmentInteractionListener mListener;

    public static SoBanFragment newInstance(int numberBan) {

        SoBanFragment fragment = new SoBanFragment();
        Bundle args = new Bundle();
        args.putInt(SO_BAN, numberBan);
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteractionListener(SoBan soBan);
    }
}
