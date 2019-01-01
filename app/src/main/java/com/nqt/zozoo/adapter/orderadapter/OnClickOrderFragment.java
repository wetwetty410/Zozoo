package com.nqt.zozoo.adapter.orderadapter;

import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.NhomMonAn;
import com.nqt.zozoo.utils.OrderDanhSachMon;

/**
 * Created by USER on 12/15/2018.
 */

public interface OnClickOrderFragment {
    void OnListenerClickMonAn(MonAn monAn, int position);

    void OnListenerClickNhomMonAn(NhomMonAn nhomMonAn, int position);

    void OnClickRemoveItem(OrderDanhSachMon orderDanhSachMon, int position);

    void OnClickTangSoLuong(OrderDanhSachMon orderDanhSachMon, int position);

    void OnClickGiamSoLuong(OrderDanhSachMon orderDanhSachMon, int position);

}
