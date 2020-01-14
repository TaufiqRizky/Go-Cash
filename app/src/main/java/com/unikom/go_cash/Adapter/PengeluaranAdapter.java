package com.unikom.go_cash.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unikom.go_cash.Model.Pengeluaran;
import com.unikom.go_cash.R;

import java.util.ArrayList;

public class PengeluaranAdapter extends RecyclerView.Adapter<PengeluaranAdapter.ListViewHolder>{

    private ArrayList<Pengeluaran> listPengeluaran;

    public PengeluaranAdapter(ArrayList<Pengeluaran> listPengeluaran) {
        this.listPengeluaran = listPengeluaran;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_row_pengeluaran, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Pengeluaran pengeluaran = listPengeluaran.get(position);

        holder.tvTanggal.setText(pengeluaran.getTanggal());
        holder.tvJumlah.setText(String.valueOf(pengeluaran.getJumlah()));
        holder.tvDetail.setText(pengeluaran.getDetail());

    }

    @Override
    public int getItemCount() {
        return listPengeluaran.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvJumlah, tvDetail;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_item_tanggal);
            tvJumlah = itemView.findViewById(R.id.tv_item_jumlah);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
        }
    }
}
