package com.nqt.zozoo.adapter.kitchenadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.database.FoodOrderDatabase;
import com.nqt.zozoo.database.FoodStatusDatabase;
import com.nqt.zozoo.utils.FoodOrder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 1/5/2019.
 */

public class TableKitchenFoodAdapter extends RecyclerView.Adapter<TableKitchenFoodAdapter.ViewHolder> {
    private Animation animation;
    private List<String> nameTable;
    private FoodKitchenAdapter foodKitchenAdapter;
    private List<FoodOrder> foodOrders;
    private FoodOrderDatabase foodOrderDatabase;
    private Context context;
    private FoodStatusDatabase foodStatusDatabase;
    private HashMap<String, String> foodStatus;

    public TableKitchenFoodAdapter(List<String> nameTable, Context context) {
        this.nameTable = nameTable;
        this.context = context;
        animation = AnimationUtils.loadAnimation(context, R.anim.shake);
        foodOrderDatabase = new FoodOrderDatabase(context);
        foodStatusDatabase = new FoodStatusDatabase(context);
        foodStatus = foodStatusDatabase.mapAllFoodStatus();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kitchen, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        foodOrders = foodOrderDatabase.getMonOrderWithBan(nameTable.get(position));
        holder.txtLocationTable.setText(nameTable.get(position));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        foodKitchenAdapter = new FoodKitchenAdapter(foodOrders, foodStatus, context);
        holder.rcvTable.setLayoutManager(gridLayoutManager);
        holder.rcvTable.setAdapter(foodKitchenAdapter);
        String status = checkAllStatus(foodOrders);
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
            case "wat":
                break;
            default:
                break;
        }
    }

    private String checkAllStatus(List<FoodOrder> foodOrders) {
        String str = "";
        for (FoodOrder foodOrder : foodOrders) {
            str = foodOrder.getTrangThaiOrder();
            for (FoodOrder foodOrder1 : foodOrders) {
                if (!foodOrder.equalsStatus(foodOrder1)) {
                    return "wat";
                }
            }
        }
        return str;
    }

    @Override
    public int getItemCount() {
        return nameTable.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtLocationTable;
        private ImageView imgProcessing;
        private ImageView imgDone;
        private ImageView imgCallWait;
        private RecyclerView rcvTable;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtLocationTable = itemView.findViewById(R.id.txt_bep_vi_tri_ban);
            imgProcessing = itemView.findViewById(R.id.img_dang_che_bien);
            imgDone = itemView.findViewById(R.id.img_da_xong);
            imgCallWait = itemView.findViewById(R.id.img_goi_phuc_vu);
            rcvTable = itemView.findViewById(R.id.rcv_bep_mon_an);


            imgProcessing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodOrders = foodOrderDatabase.getMonOrderWithBan(nameTable.get(getAdapterPosition()));
                    if (imgProcessing.getAnimation() == null) {
                        imgProcessing.startAnimation(animation);
                    }
                    imgDone.clearAnimation();
                    imgCallWait.clearAnimation();
                    updateStatus(foodOrders, "pos", rcvTable, getAdapterPosition());
                }
            });
            imgDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodOrders = foodOrderDatabase.getMonOrderWithBan(nameTable.get(getAdapterPosition()));
                    imgProcessing.clearAnimation();
                    if (imgDone.getAnimation() == null) {
                        imgDone.startAnimation(animation);
                    }
                    imgCallWait.clearAnimation();
                    updateStatus(foodOrders, "don", rcvTable, getAdapterPosition());
                }
            });
            imgCallWait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodOrders = foodOrderDatabase.getMonOrderWithBan(nameTable.get(getAdapterPosition()));
                    imgProcessing.clearAnimation();
                    imgDone.clearAnimation();
                    if (imgCallWait.getAnimation() == null) {
                        imgCallWait.startAnimation(animation);
                    }
                    updateStatus(foodOrders, "cal", rcvTable, getAdapterPosition());
                }
            });
        }

    }

    private void updateStatus(List<FoodOrder> foodOrders, String status, RecyclerView recyclerView, int position) {
        for (FoodOrder foodOrder : foodOrders) {
            foodOrder.setTrangThaiOrder(status);
            foodOrderDatabase.updateStatus(status, foodOrder.getMaMon(), foodOrder.getTenBanOrder());
        }
        foodKitchenAdapter = new FoodKitchenAdapter(foodOrders, foodStatus, context);
        recyclerView.setAdapter(foodKitchenAdapter);
    }
}
