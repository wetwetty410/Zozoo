package com.nqt.zozoo.adapter.themnhomadapter;

import com.nqt.zozoo.utils.NhomMonAn;

/**
 * Created by USER on 1/3/2019.
 */

public interface OnClickThemNhomFragment {
    void OnClickXoaNhom(NhomMonAn nhomMonAn, int position);

    void OnClickSuaNhom(NhomMonAn nhomMonAn, int position);

    void OnClickSuaNhom(NhomMonAn nhomMonAn, String tenNhom);

    void OnClickThemNhom(String tenNhom);
}
