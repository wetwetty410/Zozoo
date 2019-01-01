package com.nqt.zozoo.adapter.monanadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.MonOrder;

import java.util.List;

public class MonAnOrderAdapter extends RecyclerView.Adapter<MonAnOrderAdapter.ViewHolder> {
    private List<MonOrder> monOrderList;

    public MonAnOrderAdapter(List<MonOrder> monOrders) {
        this.monOrderList = monOrders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mon_an_dang_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTenMonOrder.setText(monOrderList.get(position).getTenMonOrder());
        holder.txtSoMonOrder.setText(monOrderList.get(position).getSoLuongOrder());
        holder.txtTrangThai.setText(monOrderList.get(position).getTrangThaiOrder());
    }

    @Override
    public int getItemCount() {
        return monOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTenMonOrder;
        private TextView txtSoMonOrder;
        private TextView txtTrangThai;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenMonOrder = itemView.findViewById(R.id.txt_ten_mon_dang_order);
            txtSoMonOrder = itemView.findViewById(R.id.txt_so_lương_dang_order);
            txtTrangThai = itemView.findViewById(R.id.txt_trang_thai_order);
        }
    }
}
