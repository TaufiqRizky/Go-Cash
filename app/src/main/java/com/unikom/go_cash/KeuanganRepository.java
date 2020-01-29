package com.unikom.go_cash;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.unikom.go_cash.Database.KeuanganDatabase;
import com.unikom.go_cash.Database.keuanganDAO;
import com.unikom.go_cash.Entity.Keuangan;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class KeuanganRepository {
    private keuanganDAO dao;
    private LiveData<List<Keuangan>> pemasukan,pengeluaran,laporan;
    private LiveData<Integer> sumPemasukan,sumPengeluaran;
    private LiveData<String[]> tahun;
    public String thn,bln;

    public  KeuanganRepository(Application application){
        KeuanganDatabase db = KeuanganDatabase.getInstance(application);
        dao=db.keuanganDAO();
        pemasukan= dao.getPemasukan();
        pengeluaran= dao.getPengeluaran();
        laporan= dao.getlaporanBlnThn(thn,bln);
        sumPemasukan=dao.SumPemasukan();
        sumPengeluaran=dao.SumPengeluaran();
        tahun = dao.getTahun();
    }

    public void insert (Keuangan keuangan){
        new InsertPemasukanAsnycTask(dao).execute(keuangan);
    }

    public void update (Keuangan keuangan){
        new UpdatePemasukanAsnycTask(dao).execute(keuangan);
    }

    public void delete (Keuangan keuangan){
        new DeleteAsyncTask(dao).execute(keuangan);
    }
    public void deleteAll() {
        new DeleteAllAsyncTask(dao).execute();
    }

    public LiveData<Integer> getSumPemasukan() {
        return sumPemasukan;
    }
    public LiveData<Integer> getSumPengeluaran() {
        return sumPengeluaran;
    }
    public LiveData<List<Keuangan>> getPemasukan() {
        return pemasukan;
    }

    public LiveData<List<Keuangan>> getPengeluaran() {
        return pengeluaran;
    }

    public LiveData<String[]> getTahun() {
        return tahun;
    }

    public LiveData<List<Keuangan>> getLaporan(String thn , String bln) {
        this.thn=thn;
        this.bln=bln;
        return laporan;
    }

    private  static class  InsertPemasukanAsnycTask extends AsyncTask<Keuangan,Void,Void>{
        private  keuanganDAO dao;

        private InsertPemasukanAsnycTask(keuanganDAO dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Keuangan... keuangans) {
            dao.insert(keuangans[0]);
            return null;
        }
    }

    private  static class  UpdatePemasukanAsnycTask extends AsyncTask<Keuangan,Void,Void>{
        private  keuanganDAO dao;

        private UpdatePemasukanAsnycTask(keuanganDAO dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Keuangan... keuangans) {
            dao.update(keuangans[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private keuanganDAO dao;

        private DeleteAllAsyncTask(keuanganDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Keuangan, Void, Void> {
        private keuanganDAO dao;

        private DeleteAsyncTask(keuanganDAO keuanagan) {
            this.dao = keuanagan;
        }

        @Override
        protected Void doInBackground(final Keuangan... uang) {
            dao.deleteKeuangan(uang[0]);
            Log.d(TAG, "doInBackground: "+uang[0]);
            return null;
        }
    }
}
