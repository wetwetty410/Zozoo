package com.nqt.zozoo.adapter.orderadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.OrderListFood;

import java.util.List;

/**
 * Created by USER on 12/24/2018.
 */

public class ListOrderFoodAdapter extends RecyclerView.Adapter<ListOrderFoodAdapter.ViewHoler> {
    private List<OrderListFood> monAnOrder;
    private OnClickOrder onClickOrderFragment;


    public ListOrderFoodAdapter(List<OrderListFood> orderDanhSachMon, OnClickOrder onClickOrderFragment) {
        this.onClickOrderFragment = onClickOrderFragment;
        this.monAnOrder = orderDanhSachMon;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_food_ordered, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.txtTenMonAn.setText(monAnOrder.get(position).getTenMonAn());
        String donGia = regexCommafy(String.valueOf(monAnOrder.get(position).getGiaTien()));
        holder.txtDonGia.setText(donGia);
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
                    int giaMon = Integer.parseInt(monAnOrder.get(getAdapterPosition()).getGiaTien());
                    int soLuong = monAnOrder.get(getAdapterPosition()).getSoLuong();
                    if (soLuong > 1) {
                        giaMon = Integer.parseInt(monAnOrder.get(getAdapterPosition()).getGiaTien()) / soLuong;
                        soLuong--;
                        monAnOrder.get(getAdapterPosition()).setSoLuong(soLuong);
                        txtSoLuong.setText(String.valueOf(monAnOrder.get(getAdapterPosition()).getSoLuong()));
                        String donGia = regexCommafy(String.valueOf(soLuong * giaMon));
                        monAnOrder.get(getAdapterPosition()).setGiaTien(String.valueOf(soLuong * giaMon));
                        txtDonGia.setText(donGia);
                        onClickOrderFragment.OnClickGiamSoLuong(monAnOrder.get(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });

            imgTang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int soLuong = monAnOrder.get(getAdapterPosition()).getSoLuong();
                    int giaMon = Integer.parseInt(monAnOrder.get(getAdapterPosition()).getGiaTien()) / soLuong;
                    if (soLuong < 1000) {
                        soLuong++;
                        monAnOrder.get(getAdapterPosition()).setSoLuong(soLuong);
                        txtSoLuong.setText(String.valueOf(monAnOrder.get(getAdapterPosition()).getSoLuong()));
                        String donGia = regexCommafy(String.valueOf(soLuong * giaMon));
                        monAnOrder.get(getAdapterPosition()).setGiaTien(String.valueOf(soLuong * giaMon));
                        txtDonGia.setText(donGia);
                        onClickOrderFragment.OnClickTangSoLuong(monAnOrder.get(getAdapterPosition()), getAdapterPosition());
                    }

                }
            });
        }
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
}