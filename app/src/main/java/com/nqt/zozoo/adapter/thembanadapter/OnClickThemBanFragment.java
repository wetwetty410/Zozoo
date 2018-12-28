package com.nqt.zozoo.adapter.thembanadapter;

import com.nqt.zozoo.utils.Ban;
import com.nqt.zozoo.utils.Tang;

/**
 * Created by USER on 12/22/2018.
 */

public interface OnClickThemBanFragment {
    void OnClickThemTang(String tenTang);

    void OnClickSuaTang(Tang tang, String tenTang);

    void OnClickSuaBan(Ban ban, String tenBan);

    void OnClickChonTang(Tang tang, int position);

    void OnClickDoiTenTang(Tang tang, int position);

    void OnClickXoaTang(Tang tang, int position);

    void OnClickDoiTenBan(Ban ban, int position);

    void OnClickXoaBan(Ban ban, int position);
}
