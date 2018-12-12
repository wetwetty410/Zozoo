package com.nqt.zozoo.banhang;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.orderadapter.NhomMonAnAdapter;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.utils.NhomMonAn;

import java.util.List;

/**
 * Created by USER on 12/12/2018.
 */

public class OrderFragment extends Fragment {
    private NhomMonAnDatabase nhomMonAnDatabase;
    private List<NhomMonAn> nhomMonAnList;
    private static Context mContext;

    private OnListFragmentInteractionListener nhomMonAnListener;
    private RecyclerView rcvNhomMonAn;

    public static OrderFragment newInstance(Context context) {
        mContext = context;
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nhomMonAnDatabase = new NhomMonAnDatabase(mContext);
        nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        rcvNhomMonAn = view.findViewById(R.id.rcv_nhom_thuc_an);
        Context context = view.getContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rcvNhomMonAn.setLayoutManager(layoutManager);
        rcvNhomMonAn.setAdapter(new NhomMonAnAdapter(nhomMonAnList, nhomMonAnListener, context));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteractionListener(NhomMonAn nhomMonAn);
    }
}