package com.nqt.zozoo.adapter.orderadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.MonAnRecyclerViewAdapter;
import com.nqt.zozoo.banhang.OrderFragment;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.utils.NhomMonAn;

import java.util.List;

import static com.nqt.zozoo.banhang.OrderFragment.*;

/**
 * Created by USER on 12/12/2018.
 */

public class NhomMonAnAdapter extends RecyclerView.Adapter<NhomMonAnAdapter.ViewHoler> {
    private NhomMonAnDatabase nhomMonAnDatabase;
    private OnListFragmentInteractionListener mListener;
    private List<NhomMonAn> nhomMonAnList;
    private Context mContext;
    private int soNhomThucAn;

    public NhomMonAnAdapter(List<NhomMonAn> nhomMonAns, OnListFragmentInteractionListener listener, Context context) {
        this.mListener = listener;
        this.mContext = context;
        this.nhomMonAnList = nhomMonAns;
        soNhomThucAn = nhomMonAns.size();
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nhom_thuc_an, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.btnNhomThucAn.setText(nhomMonAnList.get(position).getTenNhonMonAn());
        holder.btnNhomThucAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return soNhomThucAn;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private View view;
        private Button btnNhomThucAn;

        public ViewHoler(View itemView) {
            super(itemView);
            view = itemView;
            btnNhomThucAn = itemView.findViewById(R.id.btn_nhom_thuc_an);
        }
    }
}
