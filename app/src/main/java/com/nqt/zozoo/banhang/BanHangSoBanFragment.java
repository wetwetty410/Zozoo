package com.nqt.zozoo.banhang;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.BanHangSoBanRecyclerViewAdapter;
import com.nqt.zozoo.database.BanDatabase;
import com.nqt.zozoo.utils.Ban;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BanHangSoBanFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String PAGE = "page";
    private static final String TITLE = "title";
    // TODO: Customize parameters

    private int mPage;
    private String mTitle;
    private List<Ban> soBans;
    private int mColumnCount = 1;
    private Display display;
    private OnListFragmentInteractionListener mListener;
    private ScrollView scrBanHangSoBan;
    private RecyclerView rcvBanHangSoBan;
    private FloatingActionButton actionButton;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BanHangSoBanFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    // Khởi tạo fragment
    public static BanHangSoBanFragment newInstance(int columnCount, int page, String title) {
        BanHangSoBanFragment fragment = new BanHangSoBanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soBans = new BanDatabase(getContext()).getAllBan();
        // Nhận dữ liệu từ ViewPager chính
        if (getArguments() != null) {
            mPage = getArguments().getInt(PAGE, 0);
            mTitle = getArguments().getString(TITLE);
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ban_hang_so_ban, container, false);
        rcvBanHangSoBan = view.findViewById(R.id.so_ban_list);
        scrBanHangSoBan = view.findViewById(R.id.scr_ban_hang_so_ban);
        actionButton = view.findViewById(R.id.fab_them_so_ban);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(android.R.id.content, ThemBanFragment.newInstance(getContext()));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        Context context = view.getContext();
        // Tạo adapter cho recyclerView

        if (mColumnCount <= 1) {
            rcvBanHangSoBan.setLayoutManager(new LinearLayoutManager(context));
        } else {
            rcvBanHangSoBan.setLayoutManager(new GridLayoutManager(context, mColumnCount, GridLayoutManager.HORIZONTAL, false));
        }
        rcvBanHangSoBan.setAdapter(new BanHangSoBanRecyclerViewAdapter(mListener, context));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    ;

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Ban ban);
    }
}
