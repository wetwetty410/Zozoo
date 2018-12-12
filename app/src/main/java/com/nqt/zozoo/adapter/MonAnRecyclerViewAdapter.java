package com.nqt.zozoo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.database.MonAnDatabase;
import com.nqt.zozoo.utils.MonAn;

import java.util.List;

import static com.nqt.zozoo.banhang.MonAnFragment.OnListFragmentInteractionListener;

/**
 * Created by USER on 12/11/2018.
 */

public class MonAnRecyclerViewAdapter extends RecyclerView.Adapter<MonAnRecyclerViewAdapter.ViewHolder> {
    private MonAnDatabase monAnDatabase;
    private Context context;
    private int soMonAn;
    private List<MonAn> monAnList;
    private OnListFragmentInteractionListener mListener;


    public MonAnRecyclerViewAdapter(List<MonAn> monAns, OnListFragmentInteractionListener listener, Context mContext) {
        monAnList = monAns;
        mListener = listener;
        context = mContext;

        monAnDatabase = new MonAnDatabase(context);
        monAnList = monAnDatabase.getAllBan();
        soMonAn = monAnList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_mon_an, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtMonAn.setText(monAnList.get(position).getTenMonAn());
        holder.txtGiaTien.setText(String.valueOf(monAnList.get(position).getDonGia()));
        holder.txtDonViTinh.setText(monAnList.get(position).getDonViTinh());
    }

    @Override
    public int getItemCount() {
        return soMonAn;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtMonAn;
        private TextView txtGiaTien;
        private TextView txtDonViTinh;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txtMonAn = itemView.findViewById(R.id.txt_mon_an);
            txtGiaTien = itemView.findViewById(R.id.txt_gia_tien_mon);
            txtDonViTinh = itemView.findViewById(R.id.txt_don_vi_tinh);
        }
    }
}
