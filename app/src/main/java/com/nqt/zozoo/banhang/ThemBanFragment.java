package com.nqt.zozoo.banhang;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.SoBanRecyclerViewAdapter;
import com.nqt.zozoo.adapter.thembanadapter.OnClickThemBanFragment;
import com.nqt.zozoo.adapter.thembanadapter.ThemTangAdapter;
import com.nqt.zozoo.database.TangDatabase;
import com.nqt.zozoo.utils.Tang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/20/2018.
 */

public class ThemBanFragment extends Fragment implements OnClickThemBanFragment {
    private TangDatabase tangDatabase;
    private List<Tang> tangList;
    //   private HashMap<String, String> tenTang;
    private List<String> tenTang;
    private static Context mContext;

    private RecyclerView rcvThemTang;
    private RecyclerView rcvThemBan;
    private Button btnThemTang;
    private Button btnThemBan;
    private EditText edtThemBan;

    private SoBanRecyclerViewAdapter soBanRecyclerViewAdapter;
    private ThemTangAdapter themTangAdapter;

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
        rcvThemBan = view.findViewById(R.id.rcv_them_ban);
        rcvThemTang = view.findViewById(R.id.rcv_them_tang);
        btnThemBan = view.findViewById(R.id.btn_them_ban);
        btnThemTang = view.findViewById(R.id.btn_them_tang);
        edtThemBan = view.findViewById(R.id.edt_them_ban);

        Context context = view.getContext();
        LinearLayoutManager llnManagerThemTang = new LinearLayoutManager(context);
        themTangAdapter = new ThemTangAdapter(tangList, this);

        return view;
    }

    @Override
    public void onClcikThemTang(List<Tang> tangList) {
    }
}
