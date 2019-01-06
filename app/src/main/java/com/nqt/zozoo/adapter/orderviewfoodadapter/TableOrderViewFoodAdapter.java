package com.nqt.zozoo.adapter.orderviewfoodadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.database.FoodOrderDatabase;
import com.nqt.zozoo.database.FoodStatusDatabase;
import com.nqt.zozoo.utils.FoodOrder;
import com.nqt.zozoo.utils.FoodStatus;

import java.util.HashMap;
import java.util.List;

public class TableOrderViewFoodAdapter extends RecyclerView.Adapter<TableOrderViewFoodAdapter.ViewHolder> {
    private List<String> banOrderList;
    private OnClickOrderViewFood onClickMonOrderFragment;
    private Context context;
    private OrderViewFoodAdapter monAnOrderAdapter;
    private FoodOrderDatabase monOrderDatabase;
    private List<FoodOrder> monOrderList;
    private boolean isExpland;
    private HashMap<Integer, Boolean> map;

    @SuppressLint("UseSparseArrays")
    public TableOrderViewFoodAdapter(List<String> banOrderList, OnClickOrderViewFood onClickMonOrderFragment, Context context) {
        this.banOrderList = banOrderList;
        this.onClickMonOrderFragment = onClickMonOrderFragment;
        this.context = context;
        monOrderDatabase = new FoodOrderDatabase(context);
        map = new HashMap<>();
        for (int i = 0; i < banOrderList.size(); i++) {
            map.put(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mon_an_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTenBanOrder.setText(String.valueOf(banOrderList.get(position)));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        monOrderList = monOrderDatabase.getMonOrderWithBan(banOrderList.get(position));
        monAnOrderAdapter = new OrderViewFoodAdapter(monOrderList, context);
        holder.rcvMonOrder.setLayoutManager(linearLayoutManager);
        holder.rcvMonOrder.setAdapter(monAnOrderAdapter);
        isExpland = map.get(position);
        holder.rcvMonOrder.setVisibility(isExpland ? View.VISIBLE : View.GONE);
        holder.imgCollOrExp.setActivated(isExpland);
    }

    @Override
    public int getItemCount() {
        return banOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtTenBanOrder;
        private ImageView imgCollOrExp;
        private RecyclerView rcvMonOrder;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTenBanOrder = itemView.findViewById(R.id.txt_ten_ban_dang_order);
            imgCollOrExp = itemView.findViewById(R.id.img_collapse_expland);
            rcvMonOrder = itemView.findViewById(R.id.rcv_mon_an_dang_order);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isExpland = rcvMonOrder.getVisibility() != View.VISIBLE;
                    TransitionManager.beginDelayedTransition(rcvMonOrder);
                    map.put(getAdapterPosition(), isExpland);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
