package com.nqt.zozoo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.thembanadapter.OnClickThemBanFragment;

/**
 * Created by USER on 12/24/2018.
 */

public class AddItemDialog extends Dialog {
    private EditText edtThemTang;
    private Button btnThemTang;
    private OnClickThemBanFragment onClickThemBanFragment;

    public AddItemDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_them_tang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    private void init() {
        edtThemTang = findViewById(R.id.edt_dialog_them_tang);
        btnThemTang = findViewById(R.id.btn_dialog_them_tang);

        btnThemTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickThemBanFragment.OnClickThemTang(String.valueOf(edtThemTang.getText()));
                dismiss();
            }
        });
    }

    public void setOnClickThemBanFragment(OnClickThemBanFragment onClickThemBanFragment) {
        this.onClickThemBanFragment = onClickThemBanFragment;
    }

}
