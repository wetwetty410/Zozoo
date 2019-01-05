package com.nqt.zozoo.adapter.addflooradapter;

import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.Floor;

/**
 * Created by USER on 12/22/2018.
 */

public interface OnClickAddFloor {
    void OnClickThemTang(String tenTang);

    void OnClickSuaTang(Floor tang, String tenTang);

    void OnClickSuaBan(Table ban, String tenBan);

    void OnClickChonTang(Floor tang, int position);

    void OnClickDoiTenTang(Floor tang, int position);

    void OnClickXoaTang(Floor tang, int position);

    void OnClickDoiTenBan(Table ban, int position);

    void OnClickXoaBan(Table ban, int position);
}
