package com.nqt.zozoo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.nqt.zozoo.R;

public class AddFoodDialog extends Dialog {
    private EditText edtNameFood;
    private AppCompatSpinner spnGroup;
    private EditText edtCostFood;

    public AddFoodDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init();
    }

    private void init() {
    }
}
