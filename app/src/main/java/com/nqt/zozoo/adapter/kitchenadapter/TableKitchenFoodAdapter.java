package com.nqt.zozoo.adapter.kitchenadapter;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.database.FoodOrderDatabase;
import com.nqt.zozoo.utils.FoodOrder;

import java.util.List;

/**
 * Created by USER on 1/5/2019.
 */

public class TableKitchenFoodAdapter extends RecyclerView.Adapter<TableKitchenFoodAdapter.ViewHolder> {
    private List<String> nameTable;
    private List<FoodOrder> foodOrders;
    private FoodOrderDatabase foodOrderDatabase;
    private Context context;
    public TableKitchenFoodAdapter(List<String> nameTable, Context context) {
        this.nameTable = nameTable;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bep, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtLocationTable.setText(nameTable.get(position));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
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
        }
    }
}
