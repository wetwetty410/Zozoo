package com.nqt.zozoo.adapter.thembanadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.Tang;

import java.util.List;

/**
 * Created by USER on 12/22/2018.
 */

public class ThemTangAdapter extends RecyclerView.Adapter<ThemTangAdapter.ViewHolder> {
    private List<Tang> tangList;
    private int mPosition = 0;
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
        if (mPosition == 0) {
            holder.txtTang.setActivated(true);
        }
        if (mPosition != position) {
            holder.txtTang.setActivated(false);
        }
    }

    @Override
    public int getItemCount() {
        return tangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtTang;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtTang = itemView.findViewById(R.id.txt_item_tang);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtTang.setActivated(true);
                    notifyItemChanged(mPosition);
                    onClickThemBanFragment.OnClickChonTang(tangList.get(getAdapterPosition()), getAdapterPosition());
                    mPosition = getAdapterPosition();
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    openMenu(v, getAdapterPosition());
                    return true;
                }
            });
        }

        private void openMenu(View view, final int position) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.menu_edit_tang, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    return false;
                }
            });
            popupMenu.show();
        }
    }
}
