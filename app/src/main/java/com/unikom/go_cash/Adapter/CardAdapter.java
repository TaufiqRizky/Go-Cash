package com.unikom.go_cash.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.R;
import com.unikom.go_cash.ReportFragment;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.PlanetHolder> {

    private ReportFragment context;
    private ArrayList<Keuangan> keuangans;

    public CardAdapter(ReportFragment context, ArrayList<Keuangan> keuangans) {
        this.context = context;
        this.keuangans = keuangans;
    }

    @NonNull
    @Override
    public PlanetHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);

        return new PlanetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetHolder holder, int position) {
        Keuangan keuangan = keuangans.get(position);
        holder.setDetails(keuangan);
    }

    @Override
    public int getItemCount() {
        return keuangans.size();
    }

    class PlanetHolder extends RecyclerView.ViewHolder {

        private TextView txtNama, txtDeskripsi, txtTanggal, txtUang;

        PlanetHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtUang = itemView.findViewById(R.id.txtUang);
        }

        void setDetails(Keuangan keuangan) {
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

            txtNama.setText(keuangan.getNama());
            txtDeskripsi.setText(keuangan.getDesc());
            txtTanggal.setText(keuangan.getTgl());
            txtUang.setText(formatRupiah.format((double) keuangan.getUang()));
        }

    }
}
