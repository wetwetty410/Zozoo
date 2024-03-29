package com.nqt.zozoo.adapter.addgroupfoodadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.GroupFood;

import java.util.List;

/**
 * Created by USER on 1/3/2019.
 */

public class AddGroupFoodAdapter extends RecyclerView.Adapter<AddGroupFoodAdapter.ViewHolder> {
    private List<GroupFood> nhomMonAnList;
    private OnClickAddGroupFood onClickThemNhomFragment;

    public AddGroupFoodAdapter(List<GroupFood> nhomMonAnList, OnClickAddGroupFood onClickThemNhomFragment) {
        this.nhomMonAnList = nhomMonAnList;
        this.onClickThemNhomFragment = onClickThemNhomFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_manager_group_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTenNhom.setText(nhomMonAnList.get(position).getTenNhonMonAn());
    }

    @Override
    public int getItemCount() {
        return nhomMonAnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtTenNhom;
        private ImageView imgXoaNhom;
        private ImageView imgSuaNhom;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTenNhom = itemView.findViewById(R.id.txt_ten_nhom);
            imgXoaNhom = itemView.findViewById(R.id.img_xoa_nhom);
            imgSuaNhom = itemView.findViewById(R.id.img_sua_nhom);

            imgXoaNhom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickThemNhomFragment.OnClickXoaNhom(nhomMonAnList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
            imgSuaNhom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickThemNhomFragment.OnClickSuaNhom(nhomMonAnList.get(getAdapterPosition()), getAdapterPosition());
                }
            });

        }
    }
}
