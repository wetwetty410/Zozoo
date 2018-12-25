package com.nqt.zozoo.adapter.orderadapter;

import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.NhomMonAn;
import com.nqt.zozoo.utils.OrderList;

import java.util.HashMap;

/**
 * Created by USER on 12/15/2018.
 */

public interface OnClickOrderFragment {
    void OnListenerClickMonAn(MonAn monAn, int position);

    void OnListenerClickNhomMonAn(NhomMonAn nhomMonAn, int position);

    void OnClickRemoveItem(OrderList orderList, int position);

    void OnClickTangSoLuong(OrderList orderList, int position);

    void OnClickGiamSoLuong(OrderList orderList, int position);

}
