package com.nqt.zozoo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.thembanadapter.OnClickThemBanFragment;
import com.nqt.zozoo.adapter.themnhomadapter.OnClickThemNhomFragment;
import com.nqt.zozoo.utils.Ban;
import com.nqt.zozoo.utils.NhomMonAn;
import com.nqt.zozoo.utils.Tang;

/**
 * Created by USER on 12/24/2018.
 */

public class ThemTangItemDialog extends Dialog {
    private EditText edtThemTang;
    private String type = "";
    private Button btnThemTang;
    private Tang tang;
    private Ban ban;
    private NhomMonAn nhomMonAn;
    private OnClickThemBanFragment onClickThemBanFragment;
    private OnClickThemNhomFragment onClickThemNhomFragment;

    public ThemTangItemDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    public ThemTangItemDialog(Context context, Tang tangs, String typeDialog) {
        super(context);
        this.type = typeDialog;
        this.tang = tangs;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    public ThemTangItemDialog(Context context, Ban bans, String typeDialog) {
        super(context);
        this.type = typeDialog;
        this.ban = bans;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    public ThemTangItemDialog(Context context, NhomMonAn nhomMonAn, String typeDialog) {
        super(context);
        this.type = typeDialog;
        this.nhomMonAn = nhomMonAn;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    public ThemTangItemDialog(Context context, String typeDialog) {
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

    public void setOnClickThemBanFragment(OnClickThemBanFragment onClickThemBanFragment) {
        this.onClickThemBanFragment = onClickThemBanFragment;
    }

    public void setOnClickThemNhomFragment(OnClickThemNhomFragment onClickThemNhomFragment) {
        this.onClickThemNhomFragment = onClickThemNhomFragment;
    }
}
