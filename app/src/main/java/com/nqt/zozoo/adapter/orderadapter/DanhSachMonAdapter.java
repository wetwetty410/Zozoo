package com.nqt.zozoo.adapter.orderadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.banhang.OrderFragment;
import com.nqt.zozoo.banhang.OrderFragment.OnListFragmentInteractionListener;
import com.nqt.zozoo.utils.MonAn;

import java.util.List;

/**
 * Created by USER on 12/14/2018.
 */

public class DanhSachMonAdapter extends RecyclerView.Adapter<DanhSachMonAdapter.ViewHolder> {
    private Context context;
    private OnClickRecyclerViewMonAn orderListener;
    private List<MonAn> monAnList;
    private int soMonAn;

    public DanhSachMonAdapter(List<MonAn> monAns, OnClickRecyclerViewMonAn orderListener) {
        this.monAnList = monAns;
        this.soMonAn = monAnList.size();
        this.orderListener = orderListener;
        soMonAn = monAnList.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_danh_sach_do, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTenMon.setText(monAnList.get(position).getTenMonAn());
        holder.txtGiaTien.setText(String.valueOf(monAnList.get(position).getDonGia()));
        holder.txtDonVi.setText(monAnList.get(position).getDonViTinh());
        holder.txtSoLuong.setText(String.valueOf(monAnList.get(position).getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return soMonAn;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View view;
        private TextView txtTenMon;
        private TextView txtGiaTien;
        private TextView txtDonVi;
        private TextView txtSoLuong;

        public ViewHolder(final View itemView) {
            super(itemView);
            view = itemView;
            txtTenMon = itemView.findViewById(R.id.txt_order_ten_mon_an);
            txtGiaTien = itemView.findViewById(R.id.txt_order_gia_mon);
            txtDonVi = itemView.findViewById(R.id.txt_order_don_vi);
            txtSoLuong = itemView.findViewById(R.id.txt_order_so_luong);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //    OrderFragment.onListFragmentInteractionListener(monAnList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
        }
    }
}
