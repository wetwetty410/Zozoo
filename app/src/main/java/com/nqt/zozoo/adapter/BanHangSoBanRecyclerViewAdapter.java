package com.nqt.zozoo.adapter;

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
import com.nqt.zozoo.database.BanDatabase;
import com.nqt.zozoo.database.TangDatabase;
import com.nqt.zozoo.utils.Ban;
import com.nqt.zozoo.utils.Tang;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class BanHangSoBanRecyclerViewAdapter extends RecyclerView.Adapter<BanHangSoBanRecyclerViewAdapter.ViewHolder> {

    private SoBanRecyclerViewAdapter soBanRecyclerViewAdapter;
    private Context context;
    private BanDatabase banDatabase;
    private TangDatabase tangDatabase;
    private List<Ban> banList;
    private List<Tang> tangList;
    private final OnListFragmentInteractionListener mListener;

    public BanHangSoBanRecyclerViewAdapter(OnListFragmentInteractionListener listener, Context mContext) {
        mListener = listener;
        context = mContext;

        banDatabase = new BanDatabase(context);
        tangDatabase = new TangDatabase(context);
        tangList = tangDatabase.getAllTang();
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
        holder.txtTiteSoBan.setText(tangList.get(position).getTenTang());
        String maTang = tangList.get(position).getMaTang();

        // Lấy thông tin số bàn trong một tầng từ CSDL
        banList = banDatabase.getSoBan(maTang);
        if (banList.size() <= 1) {
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 5);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            holder.recyclerView.setLayoutManager(gridLayoutManager);
        }
        //       Gắn item cho RecycleView bằng các adapter
        soBanRecyclerViewAdapter = new SoBanRecyclerViewAdapter(banList, mListener, context);
        holder.recyclerView.setAdapter(soBanRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return tangList.size();
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
