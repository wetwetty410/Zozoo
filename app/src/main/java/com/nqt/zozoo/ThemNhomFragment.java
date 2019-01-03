package com.nqt.zozoo;

import android.content.Context;
import android.media.Image;
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

import com.nqt.zozoo.adapter.themnhomadapter.OnClickThemNhomFragment;
import com.nqt.zozoo.adapter.themnhomadapter.ThemNhomAdapter;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.dialog.ThemTangItemDialog;
import com.nqt.zozoo.utils.NhomMonAn;

import java.util.List;

/**
 * Created by USER on 1/3/2019.
 */

public class ThemNhomFragment extends Fragment implements View.OnClickListener, OnClickThemNhomFragment {
    private List<NhomMonAn> nhomMonAnList;
    private NhomMonAnDatabase nhomMonAnDatabase;
    private TextView txtTitle;
    private TextView txtThemNhom;
    private ImageView imgBackStack;
    private RecyclerView rcvThemNhom;
    private ThemNhomAdapter themNhomAdapter;
    private Toolbar toolbar;

    public static ThemNhomFragment newInstance() {

        Bundle args = new Bundle();
        ThemNhomFragment fragment = new ThemNhomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nhomMonAnDatabase = new NhomMonAnDatabase(getContext());
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
        txtTitle.setText("Nhóm");

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

    }

    @Override
    public void OnClickThemNhom(String tenNhom) {

    }
}
