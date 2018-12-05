package com.nqt.zozoo.banhang;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
    private List<BanHangItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public BanHangSoBanRecyclerViewAdapter(List<BanHangItem> items, OnListFragmentInteractionListener listener, Context mContext) {
        mValues = items;
        mListener = listener;
        context = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ban_hang_so_ban_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //  holder.mItem = mValues.get(position);
        holder.txtTiteSoBan.setText(mValues.get(position).tenBan);
        SoBanFragment fragment = mValues.get(position).fragment;
        android.support.v4.app.FragmentManager manager = fragment.getFragmentManager();
        holder.soBanFragment.setup(this.context, manager);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTiteSoBan;
        public FragmentTabHost soBanFragment;

        public ViewHolder(View view) {
            super(view);
            txtTiteSoBan = view.findViewById(R.id.txt_title_so_ban);
            soBanFragment = view.findViewById(R.id.danh_sach_ban_item_list);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
