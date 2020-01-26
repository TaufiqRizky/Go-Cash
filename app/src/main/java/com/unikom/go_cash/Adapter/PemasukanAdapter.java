package com.unikom.go_cash.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.PemasukanFragment;
import com.unikom.go_cash.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PemasukanAdapter extends RecyclerView.Adapter<PemasukanAdapter.KeuanganHolder> {

    private PemasukanFragment context;
    private List<Keuangan> data = new ArrayList<>();



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
        this.data=keuangan;
        notifyDataSetChanged();
    }

    class KeuanganHolder extends RecyclerView.ViewHolder {

        private TextView txtNama, txtDeskripsi, txtTanggal, txtUang;

        KeuanganHolder(View itemView) {
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
    class KeuanganDiffCallback extends DiffUtil.Callback {

        private final List<Keuangan> oldPosts, newPosts;

        public KeuanganDiffCallback(List<Keuangan> oldPosts, List<Keuangan> newPosts) {
            this.oldPosts = oldPosts;
            this.newPosts = newPosts;
        }

        @Override
        public int getOldListSize() {
            return oldPosts.size();
        }

        @Override
        public int getNewListSize() {
            return newPosts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).getId() == newPosts.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }
}
