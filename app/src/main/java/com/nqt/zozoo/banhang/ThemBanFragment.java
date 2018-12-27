package com.nqt.zozoo.banhang;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.SoBanRecyclerViewAdapter;
import com.nqt.zozoo.adapter.thembanadapter.OnClickThemBanFragment;
import com.nqt.zozoo.adapter.thembanadapter.ThemTangAdapter;
import com.nqt.zozoo.database.BanDatabase;
import com.nqt.zozoo.database.TangDatabase;
import com.nqt.zozoo.dialog.AddItemDialog;
import com.nqt.zozoo.utils.Ban;
import com.nqt.zozoo.utils.Tang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/20/2018.
 */

public class ThemBanFragment extends Fragment implements OnClickThemBanFragment, View.OnClickListener {
    private TangDatabase tangDatabase;
    private List<Tang> tangList;
    private BanDatabase banDatabase;
    private List<Ban> banList;
    //   private HashMap<String, String> tenTang;
    private List<String> tenTangList;
    private static Context mContext;

    private Toolbar toolbar;
    private ImageView imgBack;
    private TextView txtTitle;

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
        tenTangList = new ArrayList<>();
        for (Tang tang : tangList) tenTangList.add(tang.getTenTang());

        banDatabase = new BanDatabase(mContext);
        banList = banDatabase.getAllBan();
//        tenTang = new HashMap<>();
//        for (Tang tang : tangList) tenTang.put(tang.getMaTang(), tang.getTenTang());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_ban, container, false);

        toolbar = view.findViewById(R.id.tlb_fragment_them_ban);
        imgBack = view.findViewById(R.id.img_them_ban_backstack);
        txtTitle = view.findViewById(R.id.txt_them_ban_title);

        rcvThemBan = view.findViewById(R.id.rcv_them_ban);
        rcvThemTang = view.findViewById(R.id.rcv_them_tang);
        btnThemBan = view.findViewById(R.id.btn_them_ban);
        btnThemTang = view.findViewById(R.id.btn_them_tang);
        edtThemBan = view.findViewById(R.id.edt_them_ban);

        Context context = view.getContext();

        final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("");
        txtTitle.setText("Quản Lý Bàn");

        LinearLayoutManager llnManagerThemTang = new LinearLayoutManager(context);
        themTangAdapter = new ThemTangAdapter(tangList, this);
        llnManagerThemTang.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvThemTang.setLayoutManager(llnManagerThemTang);
        rcvThemTang.setAdapter(themTangAdapter);

        GridLayoutManager gridLayoutManagerThemBan = new GridLayoutManager(context, 5);
        soBanRecyclerViewAdapter = new SoBanRecyclerViewAdapter(banList)
        btnThemTang.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_them_tang:
                onClickBtnThemTang();
                break;
            default:
                break;

        }
    }

    private void onClickBtnThemTang() {
        AddItemDialog addItemDialog = new AddItemDialog(getContext());
        addItemDialog.setOnClickThemBanFragment(this);
        addItemDialog.show();
    }

    @Override
    public void OnClickThemTang(String tenTang) {
        if (tenTangList.contains(tenTang)) {
            Tang tang = new Tang();
            String maTang = tangList.get(tangList.size() - 1).getId();
            tang.setMaTang("t" + (Integer.parseInt(maTang) + 1));
            tang.setTenTang(tenTang);
            tangDatabase.addTang(tang);
        } else {
            Toast.makeText(getContext(), "Tầng đã tồn tại", Toast.LENGTH_LONG).show();
        }
    }
}
