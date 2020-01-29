package com.unikom.go_cash.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unikom.go_cash.Database.keuanganDAO;
import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.KeuanganRepository;
import com.unikom.go_cash.Model.User;

import java.util.List;

public class PemasukanViewModel extends AndroidViewModel {
    private KeuanganRepository repo;
    private  LiveData<List<Keuangan>> pemasukan,pengeluaran,laporan;
    private LiveData<Integer> sumPemasukan,sumPengeluaran;
    private LiveData<String []> tahun;
    public  String thn,bln,us,ps;
    public  keuanganDAO dao ;
    public User userr;


    public PemasukanViewModel(@NonNull Application application) {
        super(application);
        repo = new KeuanganRepository(application);
        pemasukan = repo.getPemasukan();
        pengeluaran = repo.getPengeluaran();
        laporan = repo.getLaporan();
        sumPemasukan=repo.getSumPemasukan();
        sumPengeluaran=repo.getSumPengeluaran();
        tahun=repo.getTahun();
        userr=repo.getUser(us,ps);
    }

    public  void  insert (Keuangan keuangan){
        repo.insert(keuangan);
        Log.d("test", "insert: " +keuangan);
    }
    public  void  insertUser (User usr){
        repo.insertUser(usr);
    }

    public  void  update (Keuangan keuangan){
        repo.update(keuangan);
    }

    public  void  delete (Keuangan keuangan){
        repo.delete(keuangan);
    }

    public  void  deleteTest (int id){

        dao.deleteTest(id);

    }
    public void deleteAll() {
        repo.deleteAll();
    }

    public LiveData<Integer> getSumPemasukan() {
        return sumPemasukan;
    }
    public LiveData<Integer> getSumPengeluaran() {
        return sumPengeluaran;
    }
    public User getUser(String username,String pass){
        us=username;
        ps=pass;
        return userr;
    }

    public  LiveData<List<Keuangan>> getPemasukan(){
        return pemasukan;
    }

    public  LiveData<List<Keuangan>> getPengeluaran(){
        return pengeluaran;
    }
    public  LiveData<String []> getTahun(){
        return tahun;
    }

    public  LiveData<List<Keuangan>> getLaporan(){

        return laporan;
    }





}
