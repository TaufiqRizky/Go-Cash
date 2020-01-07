package com.unikom.go_cash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListPengeluaranAdapter extends RecyclerView.Adapter<ListPengeluaranAdapter.ListViewHolder>{

    private ArrayList<PengeluaranModel> listPengeluaran;

    public ListPengeluaranAdapter(ArrayList<PengeluaranModel> listPengeluaran) {
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
        PengeluaranModel pengeluaran = listPengeluaran.get(position);

        holder.tvTanggal.setText(pengeluaran.getTanggal());
        holder.tvJumlah.setText(pengeluaran.getJumlah());
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
