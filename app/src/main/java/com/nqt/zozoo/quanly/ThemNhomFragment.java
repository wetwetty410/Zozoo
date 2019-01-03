package com.nqt.zozoo.quanly;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.themnhomadapter.OnClickThemNhomFragment;
import com.nqt.zozoo.adapter.themnhomadapter.ThemNhomAdapter;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.dialog.ThemTangItemDialog;
import com.nqt.zozoo.utils.NhomMonAn;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.util.List;
import java.util.Random;

/**
 * Created by USER on 1/3/2019.
 */

public class ThemNhomFragment extends Fragment implements View.OnClickListener, OnClickThemNhomFragment {
    private static Context mcontext;
    private List<NhomMonAn> nhomMonAnList;
    private NhomMonAnDatabase nhomMonAnDatabase;
    private TextView txtTitle;
    private TextView txtThemNhom;
    private ImageView imgBackStack;
    private RecyclerView rcvThemNhom;
    private ThemNhomAdapter themNhomAdapter;
    private Toolbar toolbar;

    public static ThemNhomFragment newInstance(Context context) {
        mcontext = context;
        Bundle args = new Bundle();
        ThemNhomFragment fragment = new ThemNhomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nhomMonAnDatabase = new NhomMonAnDatabase(mcontext);
        nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_nhom_mon_an, container, false);
        toolbar = view.findViewById(R.id.tlb_fragment_them_nhom);
        txtTitle = view.findViewById(R.id.txt_them_nhom_title);
        txtThemNhom = view.findViewById(R.id.txt_them_nhom);
        imgBackStack = view.findViewById(R.id.img_them_nhom_backstack);
        rcvThemNhom = view.findViewById(R.id.rcv_them_nhom_mon_an);

        Context context = view.getContext();
        final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("");
        txtTitle.setText("QL Nhóm");

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        themNhomAdapter = new ThemNhomAdapter(nhomMonAnList, this);
        rcvThemNhom.setLayoutManager(layoutManager);
        rcvThemNhom.setAdapter(themNhomAdapter);

        txtThemNhom.setOnClickListener(this);
        imgBackStack.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_them_nhom_backstack:
                getActivity().onBackPressed();
                break;
            case R.id.txt_them_nhom:
                onClickThemNhom();
            default:
                break;
        }

    }

    private void onClickThemNhom() {
        ThemTangItemDialog themTangItemDialog = new ThemTangItemDialog(getContext(), "addNhom");
        themTangItemDialog.setOnClickThemNhomFragment(this);
        themTangItemDialog.show();
    }

    private void onClickSuaNhom(NhomMonAn nhomMonAn) {
        ThemTangItemDialog themTangItemDialog = new ThemTangItemDialog(getContext(), nhomMonAn, "editNhom");
        themTangItemDialog.setOnClickThemNhomFragment(this);
        themTangItemDialog.show();
    }

    @Override
    public void OnClickXoaNhom(NhomMonAn nhomMonAn, int position) {
        nhomMonAnList.remove(nhomMonAn);
        nhomMonAnDatabase.deleteNhomMonAn(nhomMonAn.getMaNhonMonAn());
        themNhomAdapter = new ThemNhomAdapter(nhomMonAnList, this);
        rcvThemNhom.setAdapter(themNhomAdapter);
    }

    @Override
    public void OnClickSuaNhom(NhomMonAn nhomMonAn, int position) {
        onClickSuaNhom(nhomMonAn);
    }

    @Override
    public void OnClickSuaNhom(NhomMonAn nhomMonAn, String tenNhom) {
        if (tenNhom.equals("")) {
            Toast.makeText(getContext(), "Tên nhóm không được để trống", Toast.LENGTH_SHORT).show();
        } else if (!checkNhomContainTen(tenNhom)) {
            Toast.makeText(getContext(), "Tên nhóm đã tồn tại", Toast.LENGTH_SHORT).show();
        } else {
            NhomMonAn monAn = new NhomMonAn();
            monAn.setTenNhonMonAn(tenNhom);
            nhomMonAnDatabase.updateNhomMonAn(monAn, monAn.getMaNhonMonAn());
            nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();
            themNhomAdapter = new ThemNhomAdapter(nhomMonAnList, this);
            rcvThemNhom.setAdapter(themNhomAdapter);
            Toast.makeText(getContext(), "Sửa nhóm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkNhomContainMa(String maMonAn) {
        for (NhomMonAn monAn : nhomMonAnList) {
            if (monAn.getMaNhonMonAn().equals(maMonAn)) {
                return false;
            }
        }
        return true;
    }

    private String creatId() {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('A', 'Z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
        return randomStringGenerator.generate(3);
    }

    private boolean checkNhomContainTen(String nhomMonAn) {
        for (NhomMonAn monAn : nhomMonAnList) {
            if (monAn.getTenNhonMonAn().equals(nhomMonAn)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void OnClickThemNhom(String tenNhom) {
        if (tenNhom.equals("")) {
            Toast.makeText(getContext(), "Tên nhóm không được để trống", Toast.LENGTH_SHORT).show();
        } else if (!checkNhomContainTen(tenNhom)) {
            Toast.makeText(getContext(), "Tên nhóm đã tồn tại", Toast.LENGTH_SHORT).show();
        } else {
            String maNhom = creatId();
            while (!checkNhomContainMa(maNhom)) {
                maNhom = creatId();
            }
            NhomMonAn monAn = new NhomMonAn();
            monAn.setMaNhonMonAn(maNhom);
            monAn.setTenNhonMonAn(tenNhom);
            nhomMonAnDatabase.addNhomMonAn(monAn);
            nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();
            themNhomAdapter = new ThemNhomAdapter(nhomMonAnList, this);
            rcvThemNhom.setAdapter(themNhomAdapter);
            Toast.makeText(getContext(), "Thêm nhóm thành công", Toast.LENGTH_SHORT).show();
        }
    }
}
