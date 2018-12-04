package com.nqt.zozoo.banhang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.banhang.BanHangSoBanFragment.OnListFragmentInteractionListener;
import com.nqt.zozoo.banhang.quanlyban.SoBanContent;
import com.nqt.zozoo.banhang.quanlyban.SoBanContent.SoBan;
import com.nqt.zozoo.banhang.quanlyban.SoBanFragment;
import com.nqt.zozoo.database.MyDatabase;
import com.nqt.zozoo.dummy.BanHangContent.BanHangItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BanHangItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class BanHangSoBanRecyclerViewAdapter extends RecyclerView.Adapter<BanHangSoBanRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<SoBan> mValues;
    private final OnListFragmentInteractionListener mListener;

    public BanHangSoBanRecyclerViewAdapter(List<SoBan> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_so_ban_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //  holder.mItem = mValues.get(position);
        mValues = new MyDatabase(context).getAllDanhSachBan();
        holder.txtTiteSoBan.setText(mValues.get(position).getTenBan());
        holder.mView = SoBanFragment.newInstance(Integer.parseInt(mValues.get(position).getSoBan()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SoBanFragment mView;
        public TextView txtTiteSoBan;
        public SoBanFragment soBanFragment;

        public ViewHolder(View view) {
            super(view);
            txtTiteSoBan = view.findViewById(R.id.txt_title_so_ban);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
