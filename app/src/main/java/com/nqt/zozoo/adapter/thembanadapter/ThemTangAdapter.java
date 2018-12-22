package com.nqt.zozoo.adapter.thembanadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.Tang;

import java.util.List;

/**
 * Created by USER on 12/22/2018.
 */

public class ThemTangAdapter extends RecyclerView.Adapter<ThemTangAdapter.ViewHolder> {
    private List<Tang> tangList;
    OnClickThemBanFragment onClickThemBanFragment;

    public ThemTangAdapter(List<Tang> tangList, OnClickThemBanFragment onClickThemBanFragment) {
        this.tangList = tangList;
        this.onClickThemBanFragment = onClickThemBanFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_them_tang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTang.setText(tangList.get(position).getTenTang());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtTang;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTang = itemView.findViewById(R.id.txt_item_tang);
        }
    }
}
