package com.unikom.go_cash;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import com.unikom.go_cash.Database.KeuanganDatabase;
import com.unikom.go_cash.Database.keuanganDAO;
import com.unikom.go_cash.Entity.Keuangan;

import java.util.List;

public class KeuanganRepository {
    private keuanganDAO dao;
    private LiveData<List<Keuangan>> pemasukan;

    public  KeuanganRepository(Application application){
        KeuanganDatabase db = KeuanganDatabase.getInstance(application);
        dao=db.keuanganDAO();
        pemasukan= dao.getPemasukan();
    }

    public void insert (Keuangan keuangan){
        new InsertPemasukanAsnycTask(dao).execute(keuangan);
    }

    public void update (Keuangan keuangan){
        new UpdatePemasukanAsnycTask(dao).execute(keuangan);
    }

    public void delete (Keuangan keuangan){
        new DeletePemasukanAsnycTask(dao).execute(keuangan);
    }

    public LiveData<List<Keuangan>> getPemasukan() {
        return pemasukan;
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

    private  static class  DeletePemasukanAsnycTask extends AsyncTask<Keuangan,Void,Void>{
        private  keuanganDAO dao;

        private DeletePemasukanAsnycTask(keuanganDAO dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Keuangan... keuangans) {
            dao.delete(keuangans[0]);
            return null;
        }
    }
}
