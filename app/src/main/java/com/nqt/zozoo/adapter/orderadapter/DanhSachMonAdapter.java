package com.nqt.zozoo.adapter.orderadapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.MonAn;

import java.util.List;

/**
 * Created by USER on 12/14/2018.
 */

public class DanhSachMonAdapter extends RecyclerView.Adapter<DanhSachMonAdapter.ViewHolder> {
    private Context context;
    private OnClickOrderFragment orderListener;
    private List<MonAn> monAnList;


    public DanhSachMonAdapter(List<MonAn> monAns, OnClickOrderFragment orderListener) {
        this.monAnList = monAns;
        this.orderListener = orderListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_danh_sach_do, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position%2==0){
            holder.view.setBackgroundColor(Color.YELLOW);
        }else {
            holder.view.setBackgroundColor(Color.GREEN);
        }
        holder.txtTenMon.setText(monAnList.get(position).getTenMonAn());
        holder.txtGiaTien.setText(String.valueOf(monAnList.get(position).getDonGia()));
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtTenMon;
        private TextView txtGiaTien;
        private TextView txtDonVi;
        private TextView txtSoLuong;

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

    private void deleteItem(List<MonAn> monAns, int position) {
        monAns.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position, monAns);
    }
}
