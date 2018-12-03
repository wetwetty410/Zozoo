package com.nqt.zozoo.banhang.quanlyban;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.banhang.MyItemRecyclerViewAdapter;
import com.nqt.zozoo.banhang.quanlyban.SoBanContent.SoBan;

import java.util.List;

/**
 * Created by USER on 12/3/2018.
 */

public class SoBanRecyclerViewAdapter extends RecyclerView.Adapter<SoBanRecyclerViewAdapter.ViewHolder> {

    private List<SoBan> soBans;
    private SoBanFragment.OnListFragmentInteractionListener mListener;

    public SoBanRecyclerViewAdapter(List<SoBan> items, SoBanFragment.OnListFragmentInteractionListener listener) {
        soBans = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.so_ban_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.soBan = soBans.get(position);
        holder.txtThuTuBan.setText(soBans.get(position).soBan);
    }

    @Override
    public int getItemCount() {
        return soBans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtThuTuBan;
        private SoBan soBan;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtThuTuBan = itemView.findViewById(R.id.txt_thu_tu_ban);
        }
    }

}
