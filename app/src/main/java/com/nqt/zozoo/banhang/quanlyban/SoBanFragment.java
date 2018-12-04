package com.nqt.zozoo.banhang.quanlyban;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nqt.zozoo.R;

import static com.nqt.zozoo.banhang.quanlyban.SoBanContent.*;

/**
 * Created by USER on 12/3/2018.
 */

public class SoBanFragment extends Fragment {

    private int mSoBan;
    private static final String SO_BAN = "number-ban";
    private SoBanFragment.OnListFragmentInteractionListener mListener;

    public static SoBanFragment newInstance(int numberBan) {

        SoBanFragment fragment = new SoBanFragment();
        Bundle args = new Bundle();
        args.putInt(SO_BAN, numberBan);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSoBan = getArguments().getInt(SO_BAN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.so_ban_item, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mSoBan <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mSoBan));
            }
            recyclerView.setAdapter(new SoBanRecyclerViewAdapter(SoBanContent.soBans, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteractionListener(SoBan soBan);
    }
}
