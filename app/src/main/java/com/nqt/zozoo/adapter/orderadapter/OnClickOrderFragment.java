package com.nqt.zozoo.adapter.orderadapter;

import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.NhomMonAn;

/**
 * Created by USER on 12/15/2018.
 */

public interface OnClickOrderFragment {
    void OnListenerClickMonAn(MonAn monAn, int position);

    void OnListenerClickNhomMonAn(NhomMonAn nhomMonAn, int position);

    void OnClickRemoveItem(MonAn monAn, int position);

}
