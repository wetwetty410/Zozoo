package com.nqt.zozoo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.addflooradapter.OnClickAddFloor;
import com.nqt.zozoo.adapter.addgroupfoodadapter.OnClickAddGroupFood;
import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.GroupFood;
import com.nqt.zozoo.utils.Floor;

/**
 * Created by USER on 12/24/2018.
 */

public class AddItemDialog extends Dialog {
    private EditText edtThemTang;
    private String type = "";
    private Button btnThemTang;
    private Floor tang;
    private Table ban;
    private GroupFood nhomMonAn;
    private OnClickAddFloor onClickThemBanFragment;
    private OnClickAddGroupFood onClickThemNhomFragment;

    public AddItemDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    public AddItemDialog(Context context, Floor tangs, String typeDialog) {
        super(context);
        this.type = typeDialog;
        this.tang = tangs;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    public AddItemDialog(Context context, Table bans, String typeDialog) {
        super(context);
        this.type = typeDialog;
        this.ban = bans;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    public AddItemDialog(Context context, GroupFood nhomMonAn, String typeDialog) {
        super(context);
        this.type = typeDialog;
        this.nhomMonAn = nhomMonAn;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    public AddItemDialog(Context context, String typeDialog) {
        super(context);
        this.type = typeDialog;
        this.nhomMonAn = nhomMonAn;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    private void init() {
        edtThemTang = findViewById(R.id.edt_dialog_them_tang);
        btnThemTang = findViewById(R.id.btn_dialog_them_tang);
        if (type.equals("editTang") || type.equals("editBan") || type.endsWith("editNhom")) {
            btnThemTang.setText("Lưu");
        }
        if (type.equals("editBan")) {
            edtThemTang.setHint("Tên bàn");
            edtThemTang.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        } else if (type.equals("editTang")) {
            edtThemTang.setHint("Tên Tầng");
        } else {
            edtThemTang.setHint("Tên Nhóm");

        }
        btnThemTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type.equals("editTang")) {
                    onClickThemBanFragment.OnClickSuaTang(tang, String.valueOf(edtThemTang.getText()));
                    dismiss();
                } else if (type.equals("editBan")) {
                    onClickThemBanFragment.OnClickSuaBan(ban, String.valueOf(edtThemTang.getText()));
                    dismiss();
                } else if (type.equals("editNhom")) {
                    onClickThemNhomFragment.OnClickSuaNhom(nhomMonAn, String.valueOf(edtThemTang.getText()));
                    dismiss();
                } else if (type.equals("addNhom")) {
                    onClickThemNhomFragment.OnClickThemNhom(String.valueOf(edtThemTang.getText()));
                    dismiss();
                } else {
                    onClickThemBanFragment.OnClickThemTang(String.valueOf(edtThemTang.getText()));
                    dismiss();
                }
            }
        });
    }

    public void setOnClickThemBanFragment(OnClickAddFloor onClickThemBanFragment) {
        this.onClickThemBanFragment = onClickThemBanFragment;
    }

    public void setOnClickThemNhomFragment(OnClickAddGroupFood onClickThemNhomFragment) {
        this.onClickThemNhomFragment = onClickThemNhomFragment;
    }
}
