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
import com.nqt.zozoo.adapter.MonAnRecyclerViewAdapter;
import com.nqt.zozoo.database.MonAnDatabase;
import com.nqt.zozoo.utils.MonAn;

import java.util.List;

public class MonAnFragment extends Fragment {
    private static final String PAGE = "page";
    private static final String TITLE = "title";
    private OnListFragmentInteractionListener mListener;
    private RecyclerView rcvMonAn;
    private MonAnDatabase monAnDatabase;
    private List<MonAn> monAnList;

    public MonAnFragment() {
    }

    public static MonAnFragment newInstance(int page, String title) {
        Bundle args = new Bundle();
        MonAnFragment fragment = new MonAnFragment();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monAnDatabase = new MonAnDatabase(getContext());
        monAnList = monAnDatabase.getAllBan();
        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mon_an, container, false);
        rcvMonAn = view.findViewById(R.id.rcv_mon_an);
        Context context = view.getContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rcvMonAn.setLayoutManager(layoutManager);
        rcvMonAn.setAdapter(new MonAnRecyclerViewAdapter(monAnList, mListener, context));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BanHangSoBanFragment.OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }

    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MonAn monAn);
    }
}
