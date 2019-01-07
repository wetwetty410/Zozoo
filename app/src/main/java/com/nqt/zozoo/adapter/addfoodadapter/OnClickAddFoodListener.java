package com.nqt.zozoo.adapter.addfoodadapter;

import com.nqt.zozoo.utils.Food;
import com.nqt.zozoo.utils.GroupFood;

public interface OnClickAddFoodListener {
    void OnClickGroupListener(GroupFood groupFood, int position);

    void OnClickAddFoodListener(Food food);
}
