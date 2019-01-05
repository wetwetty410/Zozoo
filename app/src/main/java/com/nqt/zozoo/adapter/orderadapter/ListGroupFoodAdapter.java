package com.nqt.zozoo.adapter.orderadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.GroupFood;

import java.util.List;

/**
 * Created by USER on 12/12/2018.
 */

public class ListGroupFoodAdapter extends RecyclerView.Adapter<ListGroupFoodAdapter.ViewHoler> {
    private OnClickOrder mListener;
    private List<GroupFood> nhomMonAnList;

    public ListGroupFoodAdapter(List<GroupFood> nhomMonAns, OnClickOrder listener) {
        this.mListener = listener;
        this.nhomMonAnList = nhomMonAns;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nhom_thuc_an, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.btnNhomThucAn.setText(nhomMonAnList.get(position).getTenNhonMonAn());
    }

    @Override
    public int getItemCount() {
        return nhomMonAnList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private View view;
        private Button btnNhomThucAn;

        public ViewHoler(View itemView) {
            super(itemView);
            view = itemView;
            btnNhomThucAn = itemView.findViewById(R.id.btn_nhom_thuc_an);
            btnNhomThucAn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.OnListenerClickNhomMonAn(nhomMonAnList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
