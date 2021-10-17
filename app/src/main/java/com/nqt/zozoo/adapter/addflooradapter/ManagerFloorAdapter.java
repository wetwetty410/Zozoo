package com.nqt.zozoo.adapter.addflooradapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.utils.Floor;

import java.util.List;

/**
 * Created by USER on 12/22/2018.
 */

public class ManagerFloorAdapter extends RecyclerView.Adapter<ManagerFloorAdapter.ViewHolder> {
    private final List<Floor> tangList;
    private int mPosition = 0;
    private final CallBackManagerFloor callBackManagerFloor;

    public ManagerFloorAdapter(List<Floor> tangList, CallBackManagerFloor callBackManagerFloor) {
        this.tangList = tangList;
        this.callBackManagerFloor = callBackManagerFloor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_manager_floor, parent, false);
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
        private final TextView txtTang;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTang = itemView.findViewById(R.id.txt_item_floor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtTang.setActivated(true);
                    notifyItemChanged(mPosition);
                    callBackManagerFloor.OnClickChonTang(tangList.get(getAdapterPosition()), getAdapterPosition());
                    mPosition = getAdapterPosition();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    openMenu(v, tangList.get(getAdapterPosition()), getAdapterPosition());
                    return true;
                }
            });
        }

        private void openMenu(View view, final Floor tang, final int position) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.menu_manager_edit_floor, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_doi_ten_tang:
                            callBackManagerFloor.OnClickDoiTenTang(tang, position);
                            break;
                        case R.id.menu_xoa_tang:
                            callBackManagerFloor.OnClickXoaTang(tang, position);
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    }
}
