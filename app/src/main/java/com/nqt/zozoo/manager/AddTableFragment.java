package com.nqt.zozoo.manager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.collect.Iterables;
import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.saleadapter.ViewTableRecyclerViewAdapter;
import com.nqt.zozoo.adapter.addflooradapter.CallBackManagerFloor;
import com.nqt.zozoo.adapter.addflooradapter.ManagerFloorAdapter;
import com.nqt.zozoo.database.TableDatabase;
import com.nqt.zozoo.database.FloorDatabase;
import com.nqt.zozoo.dialog.AddItemDialog;
import com.nqt.zozoo.dialog.ManagerFloorDialog;
import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.Floor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by USER on 12/20/2018.
 */

public class AddTableFragment extends Fragment implements CallBackManagerFloor, View.OnClickListener {
    public static final String TAG = "AddTableFragment";
    private FloorDatabase tangDatabase;
    private List<Floor> tangList;
    private TableDatabase banDatabase;
    private List<Table> banList;
    private List<Table> allBanList;
    //   private HashMap<String, String> tenTang;
    private List<String> tenTangList;

    private String maTang;

    private Toolbar toolbar;

    private RecyclerView rcvThemTang;
    private RecyclerView rcvThemBan;
    private Button btnThemTang;
    private Button btnThemBan;
    private EditText edtThemBan;

    private ViewTableRecyclerViewAdapter soBanRecyclerViewAdapter;
    private ManagerFloorAdapter themTangAdapter;

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
        allBanList = banDatabase.getAllBan();
//        tenTang = new HashMap<>();
//        for (Tang tang : tangList) tenTang.put(tang.getMaTang(), tang.getTenTang());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_floor, container, false);

        toolbar = view.findViewById(R.id.tlb_fragment_them_ban);

        rcvThemBan = view.findViewById(R.id.rcv_them_ban);
        rcvThemTang = view.findViewById(R.id.rcv_them_tang);
        btnThemBan = view.findViewById(R.id.btn_them_ban);
        btnThemTang = view.findViewById(R.id.btn_them_tang);
        edtThemBan = view.findViewById(R.id.edt_them_ban);

        Context context = view.getContext();

        final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        assert appCompatActivity != null;
        appCompatActivity.setSupportActionBar(toolbar);
        Objects.requireNonNull(appCompatActivity.getSupportActionBar()).setTitle("Thêm Bàn");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edtThemBan.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayoutManager llnManagerThemTang = new LinearLayoutManager(context);
        themTangAdapter = new ManagerFloorAdapter(tangList, this);
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
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_them_tang:
                onClickBtnThemTang();
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
        // String tenBan = String.valueOf(edtThemBan.getText());
        StringBuilder tenBan = new StringBuilder(edtThemBan.getText());
        if (tenBan.toString().matches("")) {
            Toast.makeText(getContext(), "Không có gì để thêm!", Toast.LENGTH_SHORT).show();
        } else if (checkTenBanExist(tenBan.toString())) {
            Table ban = new Table();
            int maBan = Integer.parseInt(Iterables.getLast(banDatabase.getAllBan()).getId());
            ban.setMaBan("b" + (maBan + 1));
            ban.setMaLoaiBan("v1");
            ban.setMaTang(maTang);
            ban.setTenBan(tenBan.toString());
            banDatabase.addBan(ban);
            banList.add(ban);
            Toast.makeText(getContext(), "Thêm bàn thành công!", Toast.LENGTH_SHORT).show();
            soBanRecyclerViewAdapter = new ViewTableRecyclerViewAdapter(banList, this, getContext(), true);
            rcvThemBan.setAdapter(soBanRecyclerViewAdapter);
            tenBan.setLength(0);
        } else {
            Toast.makeText(getContext(), "Bàn đã tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkTenBanExist(String tenBan) {
        for (Table ban : allBanList) {
            if (ban.getTenBan().equalsIgnoreCase(tenBan)) {
                return false;
            }
        }
        return true;
    }

    private Table getTableUpdate(String tenBan) {
        Table table = new Table();
        for (Table ban : allBanList) {
            if (ban.getTenBan().equalsIgnoreCase(tenBan)) {
                table = ban;
            }
        }
        return table;
    }

    private void onClickBtnThemTang() {
        FragmentManager fragmentManager = getFragmentManager();
        AddItemDialog themTangItemDialog = new AddItemDialog();
        themTangItemDialog.setOnClickThemBanFragment(this);
        themTangItemDialog.show(fragmentManager, null);
    }

    private void onClickBtnSuaTang(Floor tang) {
        FragmentManager fragmentManager = getFragmentManager();

        ManagerFloorDialog managerFloorDialog = ManagerFloorDialog.newInstance(tang.getTenTang(), "editTang");
        managerFloorDialog.setCallBackManagerFloor(this);
        managerFloorDialog.show(fragmentManager, null);
    }

    private void onClickBtnSuaBan(Table ban) {
        FragmentManager fragmentManager = getFragmentManager();
        AddItemDialog suaBanItemDialog = AddItemDialog.newInstance(ban.getTenBan(), "editBan");
        suaBanItemDialog.setOnClickThemBanFragment(this);
        suaBanItemDialog.show(fragmentManager, null);
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
            themTangAdapter = new ManagerFloorAdapter(tangList, this);
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
            themTangAdapter = new ManagerFloorAdapter(tangList, this);
            rcvThemTang.setAdapter(themTangAdapter);
            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Tầng đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnClickSuaBan(String tenBan) {
        Table table = new Table();
        if (tenBan.matches("")) {
            Toast.makeText(getContext(), "Bạn chưa điền tên bàn", Toast.LENGTH_SHORT).show();
        } else if (checkTenBanExist(tenBan)) {
            table = getTableUpdate(tenBan);
            table.setTenBan(tenBan);
            banDatabase.updateBan(table, table.getId());
            Log.d(TAG, "OnClickSuaBan: " + table.toString());
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
                tenTangList.remove(tang.getTenTang());
                tangDatabase.deleteTang(tang.getId());
                for (Table table : banList) {
                    banDatabase.deleteTableInFloor(tang.getMaTang());
                    banList.remove(table);
                }
                showFloor();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    private void showFloor() {
        tangList = tangDatabase.getAllTang();
        themTangAdapter = new ManagerFloorAdapter(tangList, this);
        rcvThemTang.setAdapter(themTangAdapter);
    }

    @Override
    public void OnClickDoiTenBan(Table ban, int position) {
        onClickBtnSuaBan(ban);
    }

    @Override
    public void OnClickXoaBan(final Table ban, final int position) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Bạn có chắc chắn muốn xóa?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                banDatabase.deleteBan(banList.get(position).getMaBan());
                banList.remove(ban);
                showTable();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();

    }

    private void showTable() {
        banList = banDatabase.getSoBan(maTang);
        soBanRecyclerViewAdapter = new ViewTableRecyclerViewAdapter(banList, this, getContext(), true);
        rcvThemBan.setAdapter(soBanRecyclerViewAdapter);
    }
}
