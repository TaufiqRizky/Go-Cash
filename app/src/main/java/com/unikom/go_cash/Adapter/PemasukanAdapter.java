package com.unikom.go_cash.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PemasukanAdapter extends RecyclerView.Adapter<PemasukanAdapter.KeuanganHolder> {


    private List<Keuangan> data = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public KeuanganHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);

        return new KeuanganHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeuanganHolder holder, int position) {
        Keuangan keuangan = data.get(position);

        holder.setDetails(keuangan);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Keuangan> keuangan) {
        this.data = keuangan;
        notifyDataSetChanged();
    }

    public Keuangan getUangat(int position) {
        return data.get(position);
    }

    class KeuanganHolder extends RecyclerView.ViewHolder {

        private TextView txtNama, txtDeskripsi, txtTanggal, txtUang;

        KeuanganHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtUang = itemView.findViewById(R.id.txtUang);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(data.get(position));
                    }

                }
            });
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

    public interface OnItemClickListener {
        void onItemClick(Keuangan keuangan);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
