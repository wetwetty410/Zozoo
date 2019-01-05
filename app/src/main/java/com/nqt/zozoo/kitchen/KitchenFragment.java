package com.nqt.zozoo.kitchen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nqt.zozoo.R;
import com.nqt.zozoo.database.FoodOrderDatabase;
import com.nqt.zozoo.utils.FoodOrder;

import java.util.List;

public class KitchenFragment extends Fragment {
    private List<FoodOrder> monOrderList;
    private FoodOrderDatabase monOrderDatabase;
    private List<String> tenBanOrder;
    private RecyclerView rcvTable;

    public static KitchenFragment newInstance() {

        Bundle args = new Bundle();

        KitchenFragment fragment = new KitchenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        monOrderDatabase = new FoodOrderDatabase(getContext());
        tenBanOrder = monOrderDatabase.getBanOrder();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bep, container, false);

        Context context = view.getContext();

        return view;
    }
}
