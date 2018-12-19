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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nqt.zozoo.R;
import com.nqt.zozoo.adapter.orderadapter.DanhSachMonAdapter;
import com.nqt.zozoo.adapter.orderadapter.NhomMonAnAdapter;
import com.nqt.zozoo.adapter.orderadapter.OnClickOrderFragment;
import com.nqt.zozoo.database.MonAnDatabase;
import com.nqt.zozoo.database.NhomMonAnDatabase;
import com.nqt.zozoo.utils.MonAn;
import com.nqt.zozoo.utils.NhomMonAn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 12/12/2018.
 */

public class OrderFragment extends Fragment implements OnClickOrderFragment, View.OnClickListener {
    private NhomMonAnDatabase nhomMonAnDatabase;
    private MonAnDatabase monAnDatabase;
    private List<NhomMonAn> nhomMonAnList;
    private List<MonAn> monAnList;
    private List<MonAn> monAnOrder;
    private HashMap<String, String> saveSoLuong;
    private static Context mContext;

    private Button btnTatCa;
    private Button btnHuyBo;
    private Button btnLuu;
    private Button btnXoaHet;
    private RecyclerView rcvNhomMonAn;
    private RecyclerView rcvMonAn;
    private RecyclerView rcvOrder;
    private RecyclerView.Adapter<ViewHoler> orderAdapter;

    private DanhSachMonAdapter danhSachMonAdapter;

    public OrderFragment() {
    }

    public static OrderFragment newInstance(Context context) {
        mContext = context;
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nhomMonAnDatabase = new NhomMonAnDatabase(mContext);
        nhomMonAnList = nhomMonAnDatabase.getAllNhomMonAn();

        monAnDatabase = new MonAnDatabase(mContext);
        monAnList = monAnDatabase.getAllMonAn();
        monAnOrder = new ArrayList<>();
        saveSoLuong = new HashMap<>();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        rcvNhomMonAn = view.findViewById(R.id.rcv_order_nhom_thuc_an);
        rcvMonAn = view.findViewById(R.id.rcv_order_mon_an);
        rcvOrder = view.findViewById(R.id.rcv_order_list);
        btnTatCa = view.findViewById(R.id.btn_order_tat_ca_mon);
        btnHuyBo = view.findViewById(R.id.btn_order_huy_bo);
        btnLuu = view.findViewById(R.id.btn_order_luu);

        Context context = view.getContext();

        LinearLayoutManager layoutManagerMonAn = new LinearLayoutManager(context);
        rcvMonAn.setLayoutManager(layoutManagerMonAn);
        danhSachMonAdapter = new DanhSachMonAdapter(monAnList, this);
        rcvMonAn.setAdapter(danhSachMonAdapter);

        LinearLayoutManager layoutManagerNhomMon = new LinearLayoutManager(context);
        rcvNhomMonAn.setLayoutManager(layoutManagerNhomMon);
        rcvNhomMonAn.setAdapter(new NhomMonAnAdapter(nhomMonAnList, this));

        LinearLayoutManager layoutManagerOrder = new LinearLayoutManager(context);
        rcvOrder.setLayoutManager(layoutManagerOrder);

        btnTatCa.setOnClickListener(this);
        btnHuyBo.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        return view;
    }

    @Override
    public void OnListenerClickMonAn(MonAn monAn, int position) {
        if (!monAnOrder.contains(monAn)) {
            monAnOrder.add(monAn);
            saveSoLuong.put(monAn.getMaMonAn(), "1");

            orderAdapter = new RecyclerView.Adapter<ViewHoler>() {
                @Override
                public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_order_list, parent, false);
                    return new ViewHoler(view);
                }

                @Override
                public void onBindViewHolder(ViewHoler holder, int position) {
                    holder.txtTenMonAn.setText(monAnOrder.get(position).getTenMonAn());
                    holder.txtDonGia.setText(String.valueOf(monAnOrder.get(position).getDonGia()));
                    holder.txtDonVi.setText(monAnOrder.get(position).getDonViTinh());
                    int soLuong = Integer.parseInt(saveSoLuong.get((monAnOrder.get(position).getMaMonAn())));
                    holder.txtSoLuong.setText(String.valueOf(soLuong));
                }

                @Override
                public int getItemCount() {
                    return monAnOrder.size();
                }
            };
            orderAdapter.notifyDataSetChanged();
            rcvOrder.setAdapter(orderAdapter);

            monAnList.remove(monAn);
            rcvMonAn.setAdapter(new DanhSachMonAdapter(monAnList, this));
        }

    }

    @Override
    public void OnListenerClickNhomMonAn(final NhomMonAn nhomMonAn, int position) {
        List<MonAn> newMonAnList = removeItemDEFNhom(monAnList, nhomMonAn.getMaNhonMonAn());
        danhSachMonAdapter = new DanhSachMonAdapter(newMonAnList, this);
        rcvMonAn.setAdapter(danhSachMonAdapter);
    }

    public List<MonAn> removeItemDEFNhom(List<MonAn> monAns, String manhom) {
        List<MonAn> newListMonAn = new ArrayList<>();
        for (int i = 0; i < monAns.size(); i++) {
            if (monAns.get(i).getNhomMonAn().equals(manhom)) {
                newListMonAn.add(monAns.get(i));
            }
        }
        return newListMonAn;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_tat_ca_mon:
                rcvMonAn.setAdapter(new DanhSachMonAdapter(monAnList, this));
                break;
            case R.id.btn_order_huy_bo:
                getActivity().onBackPressed();
                break;
            default:
                break;
        }
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private View view;
        private TextView txtTenMonAn;
        private TextView txtDonGia;
        private TextView txtDonVi;
        private TextView txtSoLuong;
        private ImageView imgTang;
        private ImageView imgGiam;
        private ImageView imgXoa;

        public ViewHoler(View itemView) {
            super(itemView);
            view = itemView;
            txtTenMonAn = itemView.findViewById(R.id.txt_order_list_ten_mon_an);
            txtDonGia = itemView.findViewById(R.id.txt_order_list_gia_tien);
            txtDonVi = itemView.findViewById(R.id.txt_order_list_don_vi);
            txtSoLuong = itemView.findViewById(R.id.txt_order_list_so_luong);
            imgGiam = itemView.findViewById(R.id.img_order_giam_so_luong);
            imgTang = itemView.findViewById(R.id.img_order_tang_so_luong);
            imgXoa = itemView.findViewById(R.id.img_order_xoa);
            imgXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monAnOrder.remove(getAdapterPosition());
                }
            });
            imgGiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = String.valueOf(monAnOrder.get(getAdapterPosition()).getMaMonAn());
                    int soLuong = Integer.parseInt(saveSoLuong.get(key));
                    if (soLuong > 1) {
                        soLuong--;
                    }
                    saveSoLuong.remove(key);
                    saveSoLuong.put(key, String.valueOf(soLuong));
                    txtSoLuong.setText(String.valueOf(soLuong));
                }
            });

            imgTang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = String.valueOf(monAnOrder.get(getAdapterPosition()).getMaMonAn());
                    int soLuong = Integer.parseInt(saveSoLuong.get(key));
                    int soLuongMax = monAnOrder.get(getAdapterPosition()).getSoLuong();
                    if (soLuongMax == 0) {
                        soLuongMax = 100;
                    }
                    if (soLuong < soLuongMax) {
                        soLuong++;
                    }
                    saveSoLuong.remove(key);
                    saveSoLuong.put(key, String.valueOf(soLuong));
                    txtSoLuong.setText(String.valueOf(soLuong));
                }
            });
        }
    }
}