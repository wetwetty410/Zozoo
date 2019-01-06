package com.nqt.zozoo.adapter.addfoodadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.Food;

import java.util.List;

public class ViewFoodAdapter extends RecyclerView.Adapter<ViewFoodAdapter.ViewHolder> {
    private List<Food> foodList;
    private OnClickAddFoodListener onClickAddFoodListener;
    private Context context;

    public ViewFoodAdapter(List<Food> foodList, OnClickAddFoodListener onClickAddFoodListener, Context context) {
        this.foodList = foodList;
        this.onClickAddFoodListener = onClickAddFoodListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtNameFood.setText(foodList.get(position).getTenMonAn());
        holder.txtCostFood.setText(regexCommafy(String.valueOf(foodList.get(position).getDonGia())));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    private static String regexCommafy(String inputNum) {
        String regex = "(\\d)(?=(\\d{3})+$)";
        String[] splittedNum = inputNum.split("\\.");
        if (splittedNum.length == 2) {
            return splittedNum[0].replaceAll(regex, "$1,") + "." + splittedNum[1];
        } else {
            return inputNum.replaceAll(regex, "$1,");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtNameFood;
        private TextView txtCostFood;
        private ImageView imgRemoveGroup;
        private ImageView imgEditFood;
        private ImageView imgDeleteFood;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtNameFood = itemView.findViewById(R.id.txt_view_name_food);
            txtCostFood = itemView.findViewById(R.id.txt_view_cost_food);
            imgRemoveGroup = itemView.findViewById(R.id.img_view_remove_food_group);
            imgEditFood = itemView.findViewById(R.id.img_view_edit_food);
            imgDeleteFood = itemView.findViewById(R.id.img_view_delele_food);

        }
    }
}
