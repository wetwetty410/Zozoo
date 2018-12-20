package com.nqt.zozoo.banhang;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.nqt.zozoo.R;
import com.nqt.zozoo.database.TangDatabase;
import com.nqt.zozoo.utils.Tang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 12/20/2018.
 */

public class ThemBanFragment extends Fragment {
    private TangDatabase tangDatabase;
    private List<Tang> tangList;
    //   private HashMap<String, String> tenTang;
    private List<String> tenTang;
    private Spinner spnThemBan;
    private static Context mContext;

    public ThemBanFragment() {
    }

    public static ThemBanFragment newInstance(Context context) {
        mContext = context;
        Bundle args = new Bundle();
        ThemBanFragment fragment = new ThemBanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tangDatabase = new TangDatabase(mContext);
        tangList = tangDatabase.getAllTang();
        tenTang = new ArrayList<>();
        for (Tang tang : tangList) tenTang.add(tang.getTenTang());
//        tenTang = new HashMap<>();
//        for (Tang tang : tangList) tenTang.put(tang.getMaTang(), tang.getTenTang());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_ban, container, false);
        Context context = view.getContext();
        spnThemBan = view.findViewById(R.id.spn_list_tang);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.fragment_them_ban, tenTang);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnThemBan.setAdapter(adapter);
        spnThemBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }
}
