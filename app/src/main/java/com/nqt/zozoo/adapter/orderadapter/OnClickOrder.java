package com.nqt.zozoo.adapter.orderadapter;

import com.nqt.zozoo.utils.Food;
import com.nqt.zozoo.utils.GroupFood;
import com.nqt.zozoo.utils.OrderListFood;

/**
 * Created by USER on 12/15/2018.
 */

public interface OnClickOrder {
    void OnListenerClickMonAn(Food monAn, int position);

    void OnListenerClickNhomMonAn(GroupFood nhomMonAn, int position);

    void OnClickRemoveItem(OrderListFood orderDanhSachMon, int position);

    void OnClickTangSoLuong(OrderListFood orderDanhSachMon, int position);

    void OnClickGiamSoLuong(OrderListFood orderDanhSachMon, int position);

}
