package com.nqt.zozoo.adapter.orderadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.OrderList;

import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 12/24/2018.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHoler> {
    private List<OrderList> monAnOrder;
    private OnClickOrderFragment onClickOrderFragment;


    public OrderListAdapter(List<OrderList> orderList, OnClickOrderFragment onClickOrderFragment) {
        this.onClickOrderFragment = onClickOrderFragment;
        this.monAnOrder = orderList;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_list, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.txtTenMonAn.setText(monAnOrder.get(position).getTenMonAn());
        holder.txtDonGia.setText(String.valueOf(monAnOrder.get(position).getGiaTien()));
        holder.txtSoLuong.setText(String.valueOf(monAnOrder.get(position).getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return monAnOrder.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtTenMonAn;
        private TextView txtDonGia;
        private TextView txtDonVi;
        private TextView txtSoLuong;
        private ImageView imgTang;
        private ImageView imgGiam;
        private ImageView imgXoa;

        public ViewHoler(View itemView) {
            super(itemView);
            view = itemView;
            txtTenMonAn = itemView.findViewById(R.id.txt_order_list_ten_mon_an);
            txtDonGia = itemView.findViewById(R.id.txt_order_list_gia_tien);
            txtDonVi = itemView.findViewById(R.id.txt_order_list_don_vi);
            txtSoLuong = itemView.findViewById(R.id.txt_order_list_so_luong);
            imgGiam = itemView.findViewById(R.id.img_order_giam_so_luong);
            imgTang = itemView.findViewById(R.id.img_order_tang_so_luong);
            imgXoa = itemView.findViewById(R.id.img_order_xoa);
            imgXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickOrderFragment.OnClickRemoveItem(monAnOrder.get(getAdapterPosition()), getAdapterPosition());
                }
            });
            imgGiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int soLuong = monAnOrder.get(getAdapterPosition()).getSoLuong();
                    if (soLuong > 0) {
                        soLuong--;
                    }
                    monAnOrder.get(getAdapterPosition()).setSoLuong(soLuong);
                    txtSoLuong.setText(String.valueOf(monAnOrder.get(getAdapterPosition()).getSoLuong()));
                    onClickOrderFragment.OnClickGiamSoLuong(monAnOrder.get(getAdapterPosition()), getAdapterPosition());
                }
            });

            imgTang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int soLuong = monAnOrder.get(getAdapterPosition()).getSoLuong();
                    if (soLuong < 1000) {
                        soLuong++;
                    }
                    monAnOrder.get(getAdapterPosition()).setSoLuong(soLuong);
                    txtSoLuong.setText(String.valueOf(monAnOrder.get(getAdapterPosition()).getSoLuong()));
                    onClickOrderFragment.OnClickTangSoLuong(monAnOrder.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }

}
