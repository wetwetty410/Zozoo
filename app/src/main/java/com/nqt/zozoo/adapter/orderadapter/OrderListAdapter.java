package com.nqt.zozoo.adapter.orderadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.NhomMonAn;

import java.util.List;

import static com.nqt.zozoo.banhang.OrderFragment.OnListFragmentInteractionListener;

/**
 * Created by USER on 12/12/2018.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHoler> {
    private OnClickRecyclerViewMonAn mListener;
    private MonAn monAnList;
    private int soNhomThucAn;

    public OrderListAdapter(MonAn monAns, OnClickRecyclerViewMonAn listener) {
        this.mListener = listener;
        this.monAnList = monAns;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_list, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.txtTenMonAn.setText(monAnList.getTenMonAn());
        holder.txtTenMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtTenMonAn;

        public ViewHoler(View itemView) {
            super(itemView);
            view = itemView;
            txtTenMonAn = itemView.findViewById(R.id.txt_order_list_ten_mon_an);
        }
    }
}
