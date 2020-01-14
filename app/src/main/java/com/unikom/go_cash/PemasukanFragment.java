package com.unikom.go_cash;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unikom.go_cash.Adapter.PemasukanAdapter;
import com.unikom.go_cash.Model.Keuangan;

import java.util.ArrayList;


public class PemasukanFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60; // menampilkan data sebanyak value
    private RecyclerView recyclerView;
    private PemasukanAdapter adapter;
    private ArrayList<Keuangan> keuanganArrayList;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    //protected ListpemasukanAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset, mDataset2;
    protected int[] mDataset3;

    int [] icon = {R.drawable.ic_user, R.drawable.ic_user,R.drawable.ic_user};
    String [] judul = {"","Kampus Koding","Kampus Koding"};
    String [] deskripsi = {"belajar programming","belajar programming","belajar programming"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_listpemasukan_adapter, container, false);
//        rootView.setTag(TAG);
//
//        //BEGIN_INCLUDE(initializeRecyclerView)
//        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rcpemasukan);
//
//        mLayoutManager = new LinearLayoutManager(getActivity());
//
//        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        Button btnapawelah = rootView.findViewById(R.id.btnTambah);

        btnapawelah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new InputPemasukan());
                fragmentTransaction.commit();
                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InputPemasukan()).commit();
            }
        });

//        if (savedInstanceState != null) {
//            // Restore saved layout manager type.
//            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
//                    .getSerializable(KEY_LAYOUT_MANAGER);
//        }
//        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
//
//        mAdapter = new ListpemasukanAdapter(mDataset,mDataset2,mDataset3);
//        // Set CustomAdapter as the adapter for RecyclerView.
//        mRecyclerView.setAdapter(mAdapter);
//        // END_INCLUDE(initializeRecyclerView)
        initView(rootView);
        return rootView;
    }


    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        mDataset = new String[judul.length];
        mDataset2 = new String[deskripsi.length];
        mDataset3 = new int[icon.length];
        for (int i = 0; i < judul.length; i++) {
            mDataset[i] = judul[i];
            mDataset2[i] = deskripsi[i];
            mDataset3[i] = icon[i];
        }

//         class InputPemasukan extends Fragment {
//          public  InputPemasukan(){
//            //constructor
//        }
//
//        @Nullable
//        @Override
//        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            View view = inflater.inflate(R.layout.activity_listpemasukan_adapter, container, false);
//
//
//            final Button TambahPemasukan = (Button)view.findViewById(R.id.btnTambah);
//            TambahPemasukan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent pindah = new Intent(getActivity(), com.unikom.go_cash.InputPemasukan.class);
////                    startActivity(pindah);
////                    Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();
//
//
//
//                }
//                //}
//            });
//            return view;
//        }
//
//
//    }


}
    private void initView(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.rcpemasukan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        keuanganArrayList = new ArrayList<>();
        adapter = new PemasukanAdapter(this, keuanganArrayList);
        recyclerView.setAdapter(adapter);
        // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        createListData();
    }

    private void createListData() {
        Keuangan keuangan = new Keuangan("19/08/2019", "Maemunah", "Uang Kas Mingguan", 2000);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("19/08/2019", "Epul", "Uang Kas Mingguan", 2000);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("19/08/2020", "Udin", "Uang Kas Mingguan", 3000);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("19/02/2019", "Siti", "Uang Kas Mingguan", 5000);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("14/07/2019", "Jubaedah", "Uang Kas Mingguan", 2500);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("9/02/2017", "Alip", "Uang Kas Mingguan", 500);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("1/08/2018", "Maemunah", "Uang Kas Mingguan", 2000);
        keuanganArrayList.add(keuangan);

        adapter.notifyDataSetChanged();
    }
    public void pindah(View view){
        //Toast.makeText(getActivity(),"test", Toast.LENGTH_SHORT).show();
//        Intent pindah = new Intent(getActivity(), ListpemasukanAdapter.class);
//        startActivity(pindah);
      //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InputPemasukan()).commit();
    }
}


