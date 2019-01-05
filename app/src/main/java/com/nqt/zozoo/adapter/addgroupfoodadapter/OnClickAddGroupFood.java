package com.nqt.zozoo.adapter.addgroupfoodadapter;

import com.nqt.zozoo.utils.GroupFood;

/**
 * Created by USER on 1/3/2019.
 */

public interface OnClickAddGroupFood {
    void OnClickXoaNhom(GroupFood nhomMonAn, int position);

    void OnClickSuaNhom(GroupFood nhomMonAn, int position);

    void OnClickSuaNhom(GroupFood nhomMonAn, String tenNhom);

    void OnClickThemNhom(String tenNhom);
}
