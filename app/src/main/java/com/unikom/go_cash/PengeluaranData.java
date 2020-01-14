package com.unikom.go_cash;

import com.unikom.go_cash.Model.Pengeluaran;

import java.util.ArrayList;

public class PengeluaranData {

    private static String[] tanggalPengeluaran = {
            "1-01-2020",
            "2-01-2020",
            "3-01-2020",
            "4-01-2020",
            "5-01-2020",
            "6-01-2020"
    };

    private static Integer[] jumlahPengeluaran = {
            1000000,
            2000000,
            3000000,
            4000000,
            5000000,
            6000000,
    };

    private static String[] detailsPengeluaran = {
            "Mading",
            "Biaya Komsumsi",
            "Biaya Transportasi",
            "Peringatan Tahun Baru",
            "Seminar Tamanku Hutanku",
            "Seminar Cloud Computing",
    };

    static ArrayList<Pengeluaran> getListData() {
        ArrayList<Pengeluaran> list = new ArrayList<>();
        for (int position = 0; position < tanggalPengeluaran.length; position++) {
            Pengeluaran pengeluaran = new Pengeluaran();
            pengeluaran.setTanggal(tanggalPengeluaran[position]);
            pengeluaran.setJumlah(jumlahPengeluaran[position]);
            pengeluaran.setDetail(detailsPengeluaran[position]);
            list.add(pengeluaran);
        }
        return list;
    }

}
