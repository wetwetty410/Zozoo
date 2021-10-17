package com.nqt.zozoo.manager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.addgroupfoodadapter.OnClickAddGroupFood;
import com.nqt.zozoo.adapter.addgroupfoodadapter.AddGroupFoodAdapter;
import com.nqt.zozoo.database.GroupFoodDatabase;
import com.nqt.zozoo.dialog.AddItemDialog;
import com.nqt.zozoo.utils.GroupFood;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.util.List;
import java.util.Objects;

/**
 * Created by USER on 1/3/2019.
 */

public class AddGroupFoodFragment extends Fragment implements View.OnClickListener, OnClickAddGroupFood {
    private List<GroupFood> nhomMonAnList;
    private GroupFoodDatabase nhomMonAnDatabase;
    private RecyclerView rcvThemNhom;
    private AddGroupFoodAdapter themNhomAdapter;
    private Toolbar toolbar;

    public static AddGroupFoodFragment newInstance() {
        Bundle args = new Bundle();
        AddGroupFoodFragment fragment = new AddGroupFoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nhomMonAnDatabase = new GroupFoodDatabase(getContext());
        nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_group_food, container, false);
        toolbar = view.findViewById(R.id.tlb_fragment_them_nhom);
        rcvThemNhom = view.findViewById(R.id.rcv_them_nhom_mon_an);

        Context context = view.getContext();
        final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        assert appCompatActivity != null;
        appCompatActivity.setSupportActionBar(toolbar);
        Objects.requireNonNull(appCompatActivity.getSupportActionBar()).setTitle("QL.Nhóm món ăn");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        themNhomAdapter = new AddGroupFoodAdapter(nhomMonAnList, this);
        rcvThemNhom.setLayoutManager(layoutManager);
        rcvThemNhom.setAdapter(themNhomAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {
    }

    private void onClickThemNhom() {
        FragmentManager fragmentManager = getFragmentManager();
        AddItemDialog themTangItemDialog = AddItemDialog.newInstance("", "addNhom");
        themTangItemDialog.setOnClickThemNhomFragment(this);
        themTangItemDialog.show(fragmentManager, null);
    }

    private void onClickSuaNhom(GroupFood nhomMonAn) {
        FragmentManager fragmentManager = getFragmentManager();
        AddItemDialog themTangItemDialog = AddItemDialog.newInstance(nhomMonAn.getTenNhonMonAn(), "editNhom");
        themTangItemDialog.setOnClickThemNhomFragment(this);
        themTangItemDialog.show(fragmentManager, null);
    }

    @Override
    public void OnClickXoaNhom(GroupFood nhomMonAn, int position) {
        nhomMonAnList.remove(nhomMonAn);
        nhomMonAnDatabase.deleteNhomMonAn(nhomMonAn.getMaNhonMonAn());
        themNhomAdapter = new AddGroupFoodAdapter(nhomMonAnList, this);
        rcvThemNhom.setAdapter(themNhomAdapter);
    }

    @Override
    public void OnClickSuaNhom(GroupFood nhomMonAn, int position) {
        onClickSuaNhom(nhomMonAn);
    }

    @Override
    public void OnClickSuaNhom(GroupFood nhomMonAn, String tenNhom) {
        if (tenNhom.equals("")) {
            Toast.makeText(getContext(), "Tên nhóm không được để trống", Toast.LENGTH_SHORT).show();
        } else if (!checkNhomContainTen(tenNhom)) {
            Toast.makeText(getContext(), "Tên nhóm đã tồn tại", Toast.LENGTH_SHORT).show();
        } else {
            GroupFood monAn = new GroupFood();
            monAn.setTenNhonMonAn(tenNhom);
            nhomMonAnDatabase.updateNhomMonAn(monAn, monAn.getMaNhonMonAn());
            nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();
            themNhomAdapter = new AddGroupFoodAdapter(nhomMonAnList, this);
            rcvThemNhom.setAdapter(themNhomAdapter);
            Toast.makeText(getContext(), "Sửa nhóm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkNhomContainMa(String maMonAn) {
        for (GroupFood monAn : nhomMonAnList) {
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
        for (GroupFood monAn : nhomMonAnList) {
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
            GroupFood monAn = new GroupFood();
            monAn.setMaNhonMonAn(maNhom);
            monAn.setTenNhonMonAn(tenNhom);
            nhomMonAnDatabase.addNhomMonAn(monAn);
            nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();
            themNhomAdapter = new AddGroupFoodAdapter(nhomMonAnList, this);
            rcvThemNhom.setAdapter(themNhomAdapter);
            Toast.makeText(getContext(), "Thêm nhóm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_manager_group_food, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_group_food) {
            onClickThemNhom();
        }
        return super.onOptionsItemSelected(item);
    }
}
