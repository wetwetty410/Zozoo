package com.nqt.zozoo.manager;

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
import com.nqt.zozoo.adapter.saleadapter.ViewTableRecyclerViewAdapter;
import com.nqt.zozoo.adapter.addflooradapter.OnClickAddFloor;
import com.nqt.zozoo.adapter.addflooradapter.AddFloorAdapter;
import com.nqt.zozoo.database.TableDatabase;
import com.nqt.zozoo.database.FloorDatabase;
import com.nqt.zozoo.dialog.AddItemDialog;
import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.Floor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/20/2018.
 */

public class AddTableFragment extends Fragment implements OnClickAddFloor, View.OnClickListener {
    private FloorDatabase tangDatabase;
    private List<Floor> tangList;
    private TableDatabase banDatabase;
    private List<Table> banList;
    //   private HashMap<String, String> tenTang;
    private List<String> tenTangList;

    private String maTang;

    private Toolbar toolbar;
    private ImageView imgBack;
    private TextView txtTitle;

    private RecyclerView rcvThemTang;
    private RecyclerView rcvThemBan;
    private Button btnThemTang;
    private Button btnThemBan;
    private EditText edtThemBan;

    private ViewTableRecyclerViewAdapter soBanRecyclerViewAdapter;
    private AddFloorAdapter themTangAdapter;

    public AddTableFragment() {
    }

    public static AddTableFragment newInstance() {
        Bundle args = new Bundle();
        AddTableFragment fragment = new AddTableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tangDatabase = new FloorDatabase(getContext());
        tangList = tangDatabase.getAllTang();
        tenTangList = new ArrayList<>();
        for (Floor tang : tangList) tenTangList.add(tang.getTenTang());

        maTang = tangList.get(0).getMaTang();
        banDatabase = new TableDatabase(getContext());
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
        themTangAdapter = new AddFloorAdapter(tangList, this);
        llnManagerThemTang.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvThemTang.setLayoutManager(llnManagerThemTang);
        rcvThemTang.setAdapter(themTangAdapter);

        GridLayoutManager gridLayoutManagerThemBan = new GridLayoutManager(context, 4);
        soBanRecyclerViewAdapter = new ViewTableRecyclerViewAdapter(banList, this, getContext(), true);
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
            Table ban = new Table();
            int maBan = Integer.parseInt(Iterables.getLast(banDatabase.getAllBan()).getId());
            ban.setMaBan("b" + (maBan + 1));
            ban.setMaLoaiBan("v1");
            ban.setMaTang(maTang);
            ban.setTenBan(tenBan);
            banDatabase.addBan(ban);
            banList.add(ban);
            Toast.makeText(getContext(), "Thêm bàn thành công!", Toast.LENGTH_SHORT).show();
            soBanRecyclerViewAdapter = new ViewTableRecyclerViewAdapter(banList, this, getContext(), true);
            rcvThemBan.setAdapter(soBanRecyclerViewAdapter);
        } else {
            Toast.makeText(getContext(), "Bàn đã tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkTenBanExist(String tenBan) {
        for (Table ban : banList) {
            if (ban.getTenBan().equalsIgnoreCase(tenBan)) {
                return false;
            }
        }
        return true;
    }

    private void onClickBtnThemTang() {
        AddItemDialog themTangItemDialog = new AddItemDialog(getContext());
        themTangItemDialog.setOnClickThemBanFragment(this);
        themTangItemDialog.show();
    }

    private void onClickBtnSuaTang(Floor tang) {
        AddItemDialog themTangItemDialog = new AddItemDialog(getContext(), tang, "editTang");
        themTangItemDialog.setOnClickThemBanFragment(this);
        themTangItemDialog.show();
    }

    private void onClickBtnSuaBan(Table ban) {
        AddItemDialog themTangItemDialog = new AddItemDialog(getContext(), ban, "editBan");
        themTangItemDialog.setOnClickThemBanFragment(this);
        themTangItemDialog.show();
    }

    @Override
    public void OnClickThemTang(String tenTang) {
        if (tenTang.matches("")) {
            Toast.makeText(getContext(), "Bạn chưa điền tên tầng", Toast.LENGTH_SHORT).show();
        } else if (!tenTangList.contains(tenTang)) {
            Floor tang = new Floor();
            String maTang = Iterables.getLast(tangList).getId();
            tang.setMaTang("t" + (Integer.parseInt(maTang) + 1));
            tang.setTenTang(tenTang);
            tangList.add(tang);
            tenTangList.add(tenTang);
            tangDatabase.addTang(tang);
            themTangAdapter = new AddFloorAdapter(tangList, this);
            rcvThemTang.setAdapter(themTangAdapter);
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Tầng đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnClickSuaTang(Floor tang, String tenTang) {
        if (tenTang.matches("")) {
            Toast.makeText(getContext(), "Bạn chưa điền tên tầng", Toast.LENGTH_SHORT).show();
        } else if (!tenTangList.contains(tenTang)) {
            tang.setTenTang(tenTang);
            tangDatabase.updateTang(tang, tang.getId());
            tangList = tangDatabase.getAllTang();
            themTangAdapter = new AddFloorAdapter(tangList, this);
            rcvThemTang.setAdapter(themTangAdapter);
            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Tầng đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnClickSuaBan(Table ban, String tenBan) {
        if (tenBan.matches("")) {
            Toast.makeText(getContext(), "Bạn chưa điền tên bàn", Toast.LENGTH_SHORT).show();
        } else if (checkTenBanExist(tenBan)) {
            ban.setTenBan(tenBan);
            banDatabase.updateBan(ban, ban.getId());
            banList = banDatabase.getSoBan(maTang);
            soBanRecyclerViewAdapter = new ViewTableRecyclerViewAdapter(banList, this, getContext(), true);
            rcvThemBan.setAdapter(soBanRecyclerViewAdapter);
            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Bàn đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnClickChonTang(Floor tang, int position) {
        maTang = tang.getMaTang();
        banList = banDatabase.getSoBan(maTang);
        soBanRecyclerViewAdapter = new ViewTableRecyclerViewAdapter(banList, this, getContext(), true);
        rcvThemBan.setAdapter(soBanRecyclerViewAdapter);
    }

    @Override
    public void OnClickDoiTenTang(Floor tang, int position) {
        onClickBtnSuaTang(tang);
    }

    @Override
    public void OnClickXoaTang(final Floor tang, int position) {
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
    public void OnClickDoiTenBan(Table ban, int position) {
        onClickBtnSuaBan(ban);
    }

    @Override
    public void OnClickXoaBan(final Table ban, int position) {
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
