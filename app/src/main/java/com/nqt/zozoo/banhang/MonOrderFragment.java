package com.nqt.zozoo.banhang;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.monanadapter.BanMonAnOrderAdapter;
import com.nqt.zozoo.adapter.monanadapter.OnClickMonOrderFragment;
import com.nqt.zozoo.database.MonAnDatabase;
import com.nqt.zozoo.database.MonOrderDatabase;
import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.MonOrder;

import java.util.List;

public class MonOrderFragment extends Fragment {
    private static final String PAGE = "page";
    private static final String TITLE = "title";
    private OnClickMonOrderFragment mListener;
    private RecyclerView rcvMonAn;
    private MonOrderDatabase monOrderDatabase;
    private List<String> monOrderList;

    public static MonOrderFragment newInstance(int page, String title) {
        Bundle args = new Bundle();
        MonOrderFragment fragment = new MonOrderFragment();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monOrderDatabase = new MonOrderDatabase(getContext());
        monOrderList = monOrderDatabase.getBanOrder();
        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mon_an, container, false);
        rcvMonAn = view.findViewById(R.id.rcv_ban_order_mon);
        Context context = view.getContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rcvMonAn.setLayoutManager(layoutManager);
        rcvMonAn.setAdapter(new BanMonAnOrderAdapter(monOrderList, mListener,context));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BanHangSoBanFragment.OnListFragmentInteractionListener) {
        //    mListener = (OnClickMonOrderFragment) context;
        } else {
            throw new RuntimeException(context.toString());
        }

    }
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MonAn monAn);
    }
}
