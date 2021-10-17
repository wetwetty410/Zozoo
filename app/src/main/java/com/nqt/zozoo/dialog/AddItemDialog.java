package com.nqt.zozoo.dialog;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.addflooradapter.CallBackManagerFloor;
import com.nqt.zozoo.adapter.addgroupfoodadapter.OnClickAddGroupFood;
import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.GroupFood;
import com.nqt.zozoo.utils.Floor;

import java.util.Objects;

/**
 * Created by USER on 12/24/2018.
 */

public class AddItemDialog extends DialogFragment {
    private static final String DATA = "data";
    private static final String TYPE = "type";
    private EditText edtThem;
    private String type = "";
    private Button btnThem;
    private Floor tang;
    private Table ban;
    private GroupFood nhomMonAn;
    private CallBackManagerFloor onClickThemBanFragment;
    private OnClickAddGroupFood onClickThemNhomFragment;
    private OrientationEventListener myOrientationEventListener;
    private static final String TAG = "AddItemDialog";


    public AddItemDialog() {
    }

    public static AddItemDialog newInstance(String data, String type) {

        Bundle args = new Bundle();

        AddItemDialog fragment = new AddItemDialog();
        args.putString(DATA, data);
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_floor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        int orrientation = getResources().getConfiguration().orientation;
        if (orrientation == Configuration.ORIENTATION_LANDSCAPE) {
            int widthPercent = 50;
            setWidthPercent(widthPercent);
        } else {
            setWidthFullScreen();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void setWidthPercent(int widthPercent) {
        float percent = (float) widthPercent / 100;
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect(0, 0, dm.widthPixels, dm.heightPixels);
        float percentWith = rect.width() * percent;
        Objects.requireNonNull(getDialog().getWindow()).setLayout((int) percentWith, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void setWidthFullScreen() {
        Objects.requireNonNull(getDialog().getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    private void init(View view) {
        edtThem = view.findViewById(R.id.edt_dialog_them_tang);
        btnThem = view.findViewById(R.id.btn_dialog_them_tang);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }
        if (type.equals("editTang") || type.equals("editBan") || type.endsWith("editNhom")) {
            btnThem.setText("Lưu");
        }
        switch (type) {
            case "editBan":
                edtThem.setInputType(InputType.TYPE_CLASS_NUMBER);
                edtThem.setHint("Tên bàn");
                edtThem.setText(getArguments().getString(DATA));
                break;
            case "editTang":
                edtThem.setText(getArguments().getString(DATA));
                edtThem.setHint("Tên Tầng");
                break;
            case "editNhom":
                edtThem.setText(getArguments().getString(DATA));
                edtThem.setHint("Tên Nhóm");
                break;
            case "addNhom":
                edtThem.setHint("Tên Nhóm");
                break;
            default:
                edtThem.setHint("Tên Tầng");
                break;
        }
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (type) {
                    case "editTang":
                        onClickThemBanFragment.OnClickSuaTang(tang, String.valueOf(edtThem.getText()));
                        dismiss();
                        break;
                    case "editBan":
                        onClickThemBanFragment.OnClickSuaBan(String.valueOf(edtThem.getText()));
                        dismiss();
                        break;
                    case "editNhom":
                        onClickThemNhomFragment.OnClickSuaNhom(nhomMonAn, String.valueOf(edtThem.getText()));
                        dismiss();
                        break;
                    case "addNhom":
                        onClickThemNhomFragment.OnClickThemNhom(String.valueOf(edtThem.getText()));
                        dismiss();
                        break;
                    default:
                        onClickThemBanFragment.OnClickThemTang(String.valueOf(edtThem.getText()));
                        dismiss();
                        break;
                }
            }
        });

    }

    public void setOnClickThemBanFragment(CallBackManagerFloor onClickThemBanFragment) {
        this.onClickThemBanFragment = onClickThemBanFragment;
    }

    public void setOnClickThemNhomFragment(OnClickAddGroupFood onClickThemNhomFragment) {
        this.onClickThemNhomFragment = onClickThemNhomFragment;
    }
}
