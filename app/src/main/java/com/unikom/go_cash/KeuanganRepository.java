package com.unikom.go_cash;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.unikom.go_cash.Database.KeuanganDatabase;
import com.unikom.go_cash.Database.keuanganDAO;
import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.Model.User;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class KeuanganRepository {
    private keuanganDAO dao;
    private LiveData<List<Keuangan>> pemasukan,pengeluaran,laporan;
    private LiveData<Integer> sumPemasukan,sumPengeluaran;
    private LiveData<String[]> tahun;
    public String thn,bln,us,ps;
    private User user;

    public  KeuanganRepository(Application application){
        KeuanganDatabase db = KeuanganDatabase.getInstance(application);
        dao=db.keuanganDAO();
        pemasukan= dao.getPemasukan();
        pengeluaran= dao.getPengeluaran();
        laporan= dao.getlaporan();
        sumPemasukan=dao.SumPemasukan();
        sumPengeluaran=dao.SumPengeluaran();
        tahun = dao.getTahun();
        //user= dao.getUser(us,ps);
    }

    public void insert (Keuangan keuangan){
        new InsertPemasukanAsnycTask(dao).execute(keuangan);
    }
    public void insertUser (User usr){
        new InsertUserAsnycTask(dao).execute(usr);
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

    public LiveData<List<Keuangan>> getLaporan() {

        return laporan;
    }

    public User getUser(String usname, String pass){
        us=usname;
        ps=pass;
        Log.d(TAG, "getUser: "+usname+pass);
        return user;
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

    private  static class  InsertUserAsnycTask extends AsyncTask<User,Void,Void>{
        private  keuanganDAO dao;

        private InsertUserAsnycTask(keuanganDAO dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(User... usr) {
            dao.insertUser(usr[0]);
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
