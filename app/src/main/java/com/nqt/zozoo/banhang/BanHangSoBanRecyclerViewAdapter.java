package com.nqt.zozoo.banhang;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.banhang.BanHangSoBanFragment.OnListFragmentInteractionListener;
import com.nqt.zozoo.banhang.quanlyban.SoBanRecyclerViewAdapter;

import java.util.Collections;
import java.util.List;

import static com.nqt.zozoo.banhang.quanlyban.SoBanContent.SoBan;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class BanHangSoBanRecyclerViewAdapter extends RecyclerView.Adapter<BanHangSoBanRecyclerViewAdapter.ViewHolder> {

    private SoBanRecyclerViewAdapter soBanRecyclerViewAdapter;
    private Context context;
    private List<SoBan> mValues;
    private final OnListFragmentInteractionListener mListener;

    public BanHangSoBanRecyclerViewAdapter(List<SoBan> items, OnListFragmentInteractionListener listener, Context mContext) {
        mValues = items;
        mListener = listener;
        context = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ban_hang_so_ban, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.itemView;
        holder.txtTiteSoBan.setText(mValues.get(position).tenBan);
        int numColumn = Integer.parseInt(mValues.get(position).soBan);
        if (numColumn <= 1) {
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 5);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            holder.recyclerView.setLayoutManager(gridLayoutManager);
        }
        soBanRecyclerViewAdapter = new SoBanRecyclerViewAdapter(Collections.singletonList(mValues.get(position).soBan), mListener,context);
        holder.recyclerView.setAdapter(soBanRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView txtTiteSoBan;
        public RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            itemView = view;
            txtTiteSoBan = view.findViewById(R.id.txt_title_so_ban);
            recyclerView = view.findViewById(R.id.danh_sach_ban_item_list);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
