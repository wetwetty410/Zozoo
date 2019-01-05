package com.nqt.zozoo.adapter.kitchenadapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.FoodOrder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 1/5/2019.
 */

public class FoodKitchenAdapter extends RecyclerView.Adapter<FoodKitchenAdapter.ViewHolder> {
    private List<FoodOrder> foodOrders;
    private HashMap<String, String> mapFoodStatus;
    private int widthScreen;
    private Context context;

    public FoodKitchenAdapter(List<FoodOrder> foodOrders, HashMap<String, String> mapFoodStatus, Context context) {
        this.foodOrders = foodOrders;
        this.mapFoodStatus = mapFoodStatus;
        this.context = context;
        widthScreen = Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bep_mon_an, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtFoodName.setText(foodOrders.get(position).getTenMonOrder());
        holder.txtCountFood.setText(foodOrders.get(position).getSoLuongOrder());
        String status = foodOrders.get(position).getTrangThaiOrder();
        Animation animation = AnimationUtils.loadAnimation(holder.view.getContext(), R.anim.shake);
        int indexWidth = (int) (convertDpToPixel(widthScreen, context) - (convertDpToPixel(5, context) * 3));
        if (mapFoodStatus.containsKey(status)) {
            switch (status) {
                case "pos":
                    holder.imgProcessing.startAnimation(animation);
                    break;
                case "don":
                    holder.imgDone.startAnimation(animation);
                    break;
                case "cal":
                    holder.imgCallWait.startAnimation(animation);
                    break;
                default:
                    holder.imgProcessing.startAnimation(animation);
            }
        }

    }

    @Override
    public int getItemCount() {
        return foodOrders.size();
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtFoodName;
        private TextView txtCountFood;
        private ImageView imgProcessing;
        private ImageView imgDone;
        private ImageView imgCallWait;

        public ViewHolder(View itemView) {
            super(itemView);txtFoodName = itemView.findViewById(R.id.txt_bep_ten_mon);
            txtCountFood = itemView.findViewById(R.id.txt_bep_so_luong_mon);
            imgProcessing = itemView.findViewById(R.id.img_mon_dang_che_bien);
            imgDone = itemView.findViewById(R.id.img_mon_da_xong);
            imgCallWait = itemView.findViewById(R.id.img_mon_goi_phuc_vu);
        }
    }
}
