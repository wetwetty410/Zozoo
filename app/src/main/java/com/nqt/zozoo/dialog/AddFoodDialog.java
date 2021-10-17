package com.nqt.zozoo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.collect.Iterables;
import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.addfoodadapter.OnClickAddFoodListener;
import com.nqt.zozoo.database.GroupFoodDatabase;
import com.nqt.zozoo.utils.Food;
import com.nqt.zozoo.utils.GroupFood;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AddFoodDialog extends Dialog {
    private static final String TAG = "AddFoodDialog";
    private OnClickAddFoodListener onClickAddFoodListener;
    private EditText edtNameFood;
    private AppCompatSpinner spnGroup;
    private EditText edtCostFood;
    private Button btnCancel;
    private Button btnSave;
    private List<GroupFood> groupFoods;
    private GroupFoodDatabase groupFoodDatabase;
    private Context context;
    private List<String> nameGroup;
    private List<Food> foods;
    private String group;
    private String idGroup;

    public AddFoodDialog(List<Food> foods, Context context) {
        super(context);
        this.foods = foods;
        this.context = context;
        groupFoodDatabase = new GroupFoodDatabase(context);
        groupFoods = groupFoodDatabase.getAllNhomMonAn();
        nameGroup = new ArrayList<>();
        nameGroup.add("");
        for (GroupFood groupFood : groupFoods) {
            nameGroup.add(groupFood.getTenNhonMonAn());
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_food);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    private void init() {
        edtNameFood = findViewById(R.id.edt_name_food);
        spnGroup = findViewById(R.id.spn_group_food);
        edtCostFood = findViewById(R.id.edt_cost_food);
        btnCancel = findViewById(R.id.btn_cancel_add_food);
        btnSave = findViewById(R.id.btn_save_add_food);
        edtCostFood.setInputType(InputType.TYPE_CLASS_NUMBER);

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, nameGroup);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGroup.setAdapter(adapter);

        spnGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                group = nameGroup.get(position);
                idGroup = groupFoods.get(position).getMaNhonMonAn();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                idGroup = "";
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(edtNameFood.getText()).equals("") ||
                        String.valueOf(edtCostFood.getText()).equals("")) {
                    Toast.makeText(getContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (checkContain(foods, String.valueOf(edtNameFood.getText()))) {
                    Food food = new Food();
                    int id = Integer.parseInt(Iterables.getLast(foods).getId());
                    food.setMaMonAn("mo" + (id + 1));
                    food.setNhomMonAn(idGroup);
                    food.setDonGia(Integer.parseInt(String.valueOf(edtCostFood.getText())));
                    food.setTenMonAn(String.valueOf(edtNameFood.getText()));
                    food.setDonViTinh("VNĐ");
                    onClickAddFoodListener.OnClickAddFoodListener(food);
                    dismiss();
                    Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Món ăn đã tồn tại!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if (edtNameFood.length() > 0) {
            edtNameFood.getText().clear();
        }

        if (edtCostFood.length() > 0) {
            edtCostFood.getText().clear();
        }

        edtCostFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String patternVND = "###,###,###,###,### VNĐ";
                DecimalFormat decimalVND = new DecimalFormat();
                decimalVND.applyPattern(patternVND);
                String price = edtCostFood.getText().toString();

                if (hasFocus && price.isEmpty()) {
                    edtCostFood.setText(R.string.price_default);
                    return;
                }
                String regexKeepNumber = "[^\\d.]";

                if (hasFocus) {
                    price = price.replaceAll(regexKeepNumber, "");
                    edtCostFood.setText(price);
                    return;
                }

                price = price.replaceAll(regexKeepNumber, "");
                edtCostFood.setText(decimalVND.format(Integer.parseInt(price)));
            }
        });

    }

    private boolean checkContain(List<Food> foods, String nameFood) {
        for (Food food : foods) {
            if (nameFood.equals(food.getTenMonAn())) {
                return false;
            }
        }
        return true;
    }

    public void setOnClickAddFoodListener(OnClickAddFoodListener onClickAddFoodListener) {
        this.onClickAddFoodListener = onClickAddFoodListener;
    }
}
