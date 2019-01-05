package com.nqt.zozoo.sale;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.saleadapter.LocationTableRecyclerViewAdapter;
import com.nqt.zozoo.database.TableDatabase;
import com.nqt.zozoo.utils.Table;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class LocationTableFragment extends Fragment {

    // TODO: Customize parameter argument names
    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String PAGE = "page";
    public static final String TITLE = "title";
    // TODO: Customize parameters

    private int mPage;
    private String mTitle;
    private List<Table> soBans;
    private int mColumnCount = 1;
    private Display display;
    private OnListFragmentInteractionListener mListener;
    private ScrollView scrBanHangSoBan;
    private RecyclerView rcvBanHangSoBan;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationTableFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    // Khởi tạo fragment
    public static LocationTableFragment newInstance(int columnCount, int page, String title) {
        LocationTableFragment fragment = new LocationTableFragment();
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
        soBans = new TableDatabase(getContext()).getAllBan();
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
        final View view = inflater.inflate(R.layout.fragment_ban_hang_so_ban, container, false);
        view.post(new Runnable() {
            @Override
            public void run() {
                anim(view);
            }
        });
        rcvBanHangSoBan = view.findViewById(R.id.so_ban_list);

        Context context = view.getContext();
        // Tạo adapter cho recyclerView
        rcvBanHangSoBan.setLayoutManager(new LinearLayoutManager(context));

        rcvBanHangSoBan.setAdapter(new LocationTableRecyclerViewAdapter(mListener, context));
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

    private void anim(View myView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            int cx = myView.getWidth() / 2;
            int cy = myView.getHeight() / 2;

            // get the final radius for the clipping circle
            float finalRadius = (float) Math.hypot(cx, cy);

            // create the animator for this view (the start radius is zero)
            Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius);

            // make the view visible and start the animation
            myView.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            // set the view to visible without a circular reveal animation below Lollipop
            myView.setVisibility(View.VISIBLE);
        }
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Table ban);
    }
}
