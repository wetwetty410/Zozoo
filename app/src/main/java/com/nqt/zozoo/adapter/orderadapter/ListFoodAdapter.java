package com.nqt.zozoo.adapter.orderadapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.Food;

import java.util.List;

/**
 * Created by USER on 12/14/2018.
 */

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ViewHolder> {
    private Context context;
    private final OnClickOrder orderListener;
    private final List<Food> monAnList;


    public ListFoodAdapter(List<Food> monAns, OnClickOrder orderListener) {
        this.monAnList = monAns;
        this.orderListener = orderListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_food_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if (position % 2 == 0) {
//            holder.view.setBackgroundColor(Color.YELLOW);
//        } else {
//            holder.view.setBackgroundColor(Color.GREEN);
//        }
        holder.txtTenMon.setText(monAnList.get(position).getTenMonAn());
        holder.txtGiaTien.setText(setGia(String.valueOf(monAnList.get(position).getDonGia())));
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        private final TextView txtTenMon;
        private final TextView txtGiaTien;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTenMon = itemView.findViewById(R.id.txt_order_ten_mon_an);
            txtGiaTien = itemView.findViewById(R.id.txt_order_gia_mon);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderListener.OnListenerClickMonAn(monAnList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

    }

    private void deleteItem(List<Food> monAns, int position) {
        monAns.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position, monAns);
    }


    private String setGia(String giaTien) {

        if (giaTien.length() > 6 && giaTien.endsWith("000000")) {
            giaTien = giaTien.substring(0, giaTien.lastIndexOf("000000")) + "TR";
        } else if (giaTien.length() > 3 && giaTien.endsWith("000")) {
            giaTien = giaTien.substring(0, giaTien.lastIndexOf("000")) + "K";
        }
        return giaTien;
    }
}
