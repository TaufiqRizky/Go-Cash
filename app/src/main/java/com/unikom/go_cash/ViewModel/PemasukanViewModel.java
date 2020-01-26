package com.unikom.go_cash.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.unikom.go_cash.Database.KeuanganDatabase;
import com.unikom.go_cash.Database.keuanganDAO;
import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.KeuanganRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PemasukanViewModel extends AndroidViewModel {
    private KeuanganRepository repo;
    private  LiveData<List<Keuangan>> pemasukan,pengeluaran,laporan;
    public  String thn,bln;

    public PemasukanViewModel(@NonNull Application application) {
        super(application);
        repo = new KeuanganRepository(application);
        pemasukan = repo.getPemasukan();
        pengeluaran = repo.getPengeluaran();
        laporan = repo.getLaporan(thn,bln);
    }

    public  void  insert (Keuangan keuangan){
        repo.insert(keuangan);
    }

    public  void  update (Keuangan keuangan){
        repo.update(keuangan);
    }

    public  void  delete (Keuangan keuangan){
        repo.delete(keuangan);
    }
    public void deleteAll() {
        repo.deleteAll();
    }

    public  LiveData<List<Keuangan>> getPemasukan(){
        return pemasukan;
    }

    public  LiveData<List<Keuangan>> getPengeluaran(){
        return pengeluaran;
    }

    public  LiveData<List<Keuangan>> getLaporan(String thn, String bln){
        this.thn=thn;
        this.bln=bln;
        return laporan;
    }


}