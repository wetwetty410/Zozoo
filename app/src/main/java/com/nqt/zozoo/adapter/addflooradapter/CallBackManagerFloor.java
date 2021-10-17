package com.nqt.zozoo.adapter.addflooradapter;

import com.nqt.zozoo.utils.Table;
import com.nqt.zozoo.utils.Floor;

/**
 * Created by USER on 12/22/2018.
 */

public interface CallBackManagerFloor {

    void OnClickThemTang(String tenTang);

    void OnClickSuaTang(Floor tang, String tenTang);

    void OnClickChonTang(Floor tang, int position);

    void OnClickDoiTenTang(Floor tang, int position);



    void OnClickXoaTang(Floor tang, int position);

    void OnClickSuaBan(String tenBan);

    void OnClickDoiTenBan(Table ban, int position);

    void OnClickXoaBan(Table ban, int position);
}
