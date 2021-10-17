package com.nqt.zozoo.manager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.addfoodadapter.OnClickAddFoodListener;
import com.nqt.zozoo.adapter.addfoodadapter.ViewFoodAdapter;
import com.nqt.zozoo.adapter.addfoodadapter.ViewGroupFoodAdapter;
import com.nqt.zozoo.database.FoodDatabase;
import com.nqt.zozoo.database.GroupFoodDatabase;
import com.nqt.zozoo.dialog.AddFoodDialog;
import com.nqt.zozoo.utils.Food;
import com.nqt.zozoo.utils.GroupFood;

import java.util.List;
import java.util.Objects;

public class AddFoodFragment extends Fragment implements View.OnClickListener, OnClickAddFoodListener {
    private List<Food> foodList;
    private List<GroupFood> groupFoodList;
    private GroupFoodDatabase groupFoodDatabase;
    private FoodDatabase foodDatabase;
    private Toolbar toolbar;
    private Button btnAllFood;
    private RecyclerView rcvViewGroupFood;
    private RecyclerView rcvViewFood;
    private FloatingActionButton fabAddFoods;
    private ViewGroupFoodAdapter viewGroupFoodAdapter;
    private ViewFoodAdapter viewFoodAdapter;

    public AddFoodFragment() {

    }

    public static AddFoodFragment newInstance() {

        Bundle args = new Bundle();

        AddFoodFragment fragment = new AddFoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodDatabase = new FoodDatabase(getContext());
        groupFoodDatabase = new GroupFoodDatabase(getContext());
        groupFoodList = groupFoodDatabase.getAllNhomMonAn();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_foods, container, false);
        toolbar = view.findViewById(R.id.tlb_fragment_add_food);

        btnAllFood = view.findViewById(R.id.btn_all_food);
        rcvViewGroupFood = view.findViewById(R.id.rcv_view_group_food);
        rcvViewFood = view.findViewById(R.id.rcv_view_food);
        fabAddFoods = view.findViewById(R.id.fab_fag_manager_foods_add);

        Context context = view.getContext();
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        assert appCompatActivity != null;
        appCompatActivity.setSupportActionBar(toolbar);
        Objects.requireNonNull(appCompatActivity.getSupportActionBar()).setTitle("Thêm Món");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        viewGroupFoodAdapter = new ViewGroupFoodAdapter(this, context);
        rcvViewGroupFood.setLayoutManager(linearLayoutManager);
        rcvViewGroupFood.setAdapter(viewGroupFoodAdapter);

        LinearLayoutManager layoutManagerFood = new LinearLayoutManager(context);
        foodList = foodDatabase.getMonAnWithGroup(groupFoodList.get(0).getMaNhonMonAn());
        viewFoodAdapter = new ViewFoodAdapter(foodList, this, context);
        rcvViewFood.setLayoutManager(layoutManagerFood);
        rcvViewFood.setAdapter(viewFoodAdapter);

        fabAddFoods.setOnClickListener(this);
        btnAllFood.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_all_food:
                foodList = foodDatabase.getAllMonAn();
                viewFoodAdapter = new ViewFoodAdapter(foodList, this, getContext());
                rcvViewFood.setAdapter(viewFoodAdapter);
                break;
            case R.id.fab_fag_manager_foods_add:
                foodList = foodDatabase.getAllMonAn();
                AddFoodDialog addFoodDialog = new AddFoodDialog(foodList, getContext());
                addFoodDialog.setOnClickAddFoodListener(this);
                addFoodDialog.show();
                break;

//            case R.id.btn_food_free:
//                foodList = foodDatabase.getMonAnWithGroup("");
//                viewFoodAdapter = new ViewFoodAdapter(foodList, this, getContext());
//                rcvViewFood.setAdapter(viewFoodAdapter);
//                break;
        }
    }

    @Override
    public void OnClickGroupListener(GroupFood groupFood, int position) {
        foodList = foodDatabase.getMonAnWithGroup(groupFoodList.get(position).getMaNhonMonAn());
        viewFoodAdapter = new ViewFoodAdapter(foodList, this, getContext());
        rcvViewFood.setAdapter(viewFoodAdapter);
    }

    @Override
    public void OnClickAddFoodListener(Food food) {
        foodDatabase.addMonAn(food);
        foodList = foodDatabase.getMonAnWithGroup(food.getNhomMonAn());
        viewFoodAdapter = new ViewFoodAdapter(foodList, this, getContext());
        rcvViewFood.setAdapter(viewFoodAdapter);
    }
}
