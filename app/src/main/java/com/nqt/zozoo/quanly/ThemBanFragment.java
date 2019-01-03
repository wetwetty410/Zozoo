package com.nqt.zozoo.quanly;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Iterables;
import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.SoBanRecyclerViewAdapter;
import com.nqt.zozoo.adapter.thembanadapter.OnClickThemBanFragment;
import com.nqt.zozoo.adapter.thembanadapter.ThemTangAdapter;
import com.nqt.zozoo.database.BanDatabase;
import com.nqt.zozoo.database.TangDatabase;
import com.nqt.zozoo.dialog.ThemTangItemDialog;
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

    private String maTang;

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

        maTang = tangList.get(0).getMaTang();
        banDatabase = new BanDatabase(mContext);
        banList = banDatabase.getSoBan(maTang);
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
        edtThemBan.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayoutManager llnManagerThemTang = new LinearLayoutManager(context);
        themTangAdapter = new ThemTangAdapter(tangList, this);
        llnManagerThemTang.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvThemTang.setLayoutManager(llnManagerThemTang);
        rcvThemTang.setAdapter(themTangAdapter);

        GridLayoutManager gridLayoutManagerThemBan = new GridLayoutManager(context, 4);
        soBanRecyclerViewAdapter = new SoBanRecyclerViewAdapter(banList, this, mContext, true);
        rcvThemBan.setLayoutManager(gridLayoutManagerThemBan);
        rcvThemBan.setAdapter(soBanRecyclerViewAdapter);

        edtThemBan.setOnClickListener(this);
        btnThemBan.setOnClickListener(this);
        btnThemTang.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_them_tang:
                onClickBtnThemTang();
                break;
            case R.id.img_them_ban_backstack:
                getActivity().onBackPressed();
                break;
            case R.id.btn_them_ban:
                onClickBtnThemBan();
                break;
            case R.id.edt_them_ban:
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            default:
                break;

        }
    }

    private void onClickBtnThemBan() {
        String tenBan = String.valueOf(edtThemBan.getText());
        if (tenBan.matches("")) {
            Toast.makeText(getContext(), "Không có gì để thêm!", Toast.LENGTH_SHORT).show();
        } else if (checkTenBanExist(tenBan)) {
            Ban ban = new Ban();
            int maBan = Integer.parseInt(Iterables.getLast(banDatabase.getAllBan()).getId());
            ban.setMaBan("b" + (maBan + 1));
            ban.setMaLoaiBan("v1");
            ban.setMaTang(maTang);
            ban.setTenBan(tenBan);
            banDatabase.addBan(ban);
            banList.add(ban);
            Toast.makeText(getContext(), "Thêm bàn thành công!", Toast.LENGTH_SHORT).show();
            soBanRecyclerViewAdapter = new SoBanRecyclerViewAdapter(banList, this, mContext, true);
            rcvThemBan.setAdapter(soBanRecyclerViewAdapter);
        } else {
            Toast.makeText(getContext(), "Bàn đã tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkTenBanExist(String tenBan) {
        for (Ban ban : banList) {
            if (ban.getTenBan().equalsIgnoreCase(tenBan)) {
                return false;
            }
        }
        return true;
    }

    private void onClickBtnThemTang() {
        ThemTangItemDialog themTangItemDialog = new ThemTangItemDialog(getContext());
        themTangItemDialog.setOnClickThemBanFragment(this);
        themTangItemDialog.show();
    }

    private void onClickBtnSuaTang(Tang tang) {
        ThemTangItemDialog themTangItemDialog = new ThemTangItemDialog(getContext(), tang, "editTang");
        themTangItemDialog.setOnClickThemBanFragment(this);
        themTangItemDialog.show();
    }

    private void onClickBtnSuaBan(Ban ban) {
        ThemTangItemDialog themTangItemDialog = new ThemTangItemDialog(getContext(), ban, "editBan");
        themTangItemDialog.setOnClickThemBanFragment(this);
        themTangItemDialog.show();
    }

    @Override
    public void OnClickThemTang(String tenTang) {
        if (tenTang.matches("")) {
            Toast.makeText(getContext(), "Bạn chưa điền tên tầng", Toast.LENGTH_SHORT).show();
        } else if (!tenTangList.contains(tenTang)) {
            Tang tang = new Tang();
            String maTang = Iterables.getLast(tangList).getId();
            tang.setMaTang("t" + (Integer.parseInt(maTang) + 1));
            tang.setTenTang(tenTang);
            tangList.add(tang);
            tenTangList.add(tenTang);
            tangDatabase.addTang(tang);
            themTangAdapter = new ThemTangAdapter(tangList, this);
            rcvThemTang.setAdapter(themTangAdapter);
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Tầng đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnClickSuaTang(Tang tang, String tenTang) {
        if (tenTang.matches("")) {
            Toast.makeText(getContext(), "Bạn chưa điền tên tầng", Toast.LENGTH_SHORT).show();
        } else if (!tenTangList.contains(tenTang)) {
            tang.setTenTang(tenTang);
            tangDatabase.updateTang(tang, tang.getId());
            tangList = tangDatabase.getAllTang();
            themTangAdapter = new ThemTangAdapter(tangList, this);
            rcvThemTang.setAdapter(themTangAdapter);
            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Tầng đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnClickSuaBan(Ban ban, String tenBan) {
        if (tenBan.matches("")) {
            Toast.makeText(getContext(), "Bạn chưa điền tên bàn", Toast.LENGTH_SHORT).show();
        } else if (checkTenBanExist(tenBan)) {
            ban.setTenBan(tenBan);
            banDatabase.updateBan(ban, ban.getId());
            banList = banDatabase.getSoBan(maTang);
            soBanRecyclerViewAdapter = new SoBanRecyclerViewAdapter(banList, this, mContext, true);
            rcvThemBan.setAdapter(soBanRecyclerViewAdapter);
            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Bàn đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnClickChonTang(Tang tang, int position) {
        maTang = tang.getMaTang();
        banList = banDatabase.getSoBan(maTang);
        soBanRecyclerViewAdapter = new SoBanRecyclerViewAdapter(banList, this, mContext, true);
        rcvThemBan.setAdapter(soBanRecyclerViewAdapter);
    }

    @Override
    public void OnClickDoiTenTang(Tang tang, int position) {
        onClickBtnSuaTang(tang);
    }

    @Override
    public void OnClickXoaTang(final Tang tang, int position) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Bạn có chắc chắn muốn xóa?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tangDatabase.deleteTang(tang.getId());
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void OnClickDoiTenBan(Ban ban, int position) {
        onClickBtnSuaBan(ban);
    }

    @Override
    public void OnClickXoaBan(final Ban ban, int position) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Bạn có chắc chắn muốn xóa?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tangDatabase.deleteTang(ban.getId());
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
