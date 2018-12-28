package com.nqt.zozoo.adapter.thembanadapter;

import android.content.Context;

import com.nqt.zozoo.dialog.AddItemDialog;
import com.nqt.zozoo.utils.Tang;

import java.util.List;

/**
 * Created by USER on 12/22/2018.
 */

public interface OnClickThemBanFragment {
    void OnClickThemTang(String tenTang);

    void OnClickChonTang(Tang tang, int position);

    void OnClickDoiTenTang(Tang tang, int position);

    void OnClickDoiXoaTang(Tang tang, int position);
}
