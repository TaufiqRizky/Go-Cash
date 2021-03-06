package com.unikom.go_cash.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.Model.User;

import java.util.List;

@Dao
public interface keuanganDAO {
    @Query("SELECT * FROM User where username in (:us) and password in (:pass)")
    User getUser(String us, String pass);
    @Insert
    void insert(Keuangan keuangan);

    @Insert
    void insertUser(User user);

    @Update
    void update(Keuangan keuangan);

    @Delete
    void  deleteKeuangan(Keuangan keuangan);

    @Query("DELETE FROM Keuangan")
    void deleteAll();

    @Query("DELETE FROM Keuangan where id = :id")
    void deleteTest(int id);

    @Query("SELECT sum(uang) as SUM FROM Keuangan where type='Pemasukan'")
    LiveData<Integer> SumPemasukan();

    @Query("SELECT sum(uang) as SUM FROM Keuangan where type='Pengeluaran'")
    LiveData<Integer> SumPengeluaran();

    @Query("SELECT * FROM Keuangan where type='Pemasukan'")
    LiveData<List<Keuangan>> getPemasukan();

    @Query("SELECT * FROM Keuangan where type='Pengeluaran'")
    LiveData<List<Keuangan>> getPengeluaran();

    @Query("SELECT * FROM Keuangan where substr(Tanggal,4,5)= :bln AND substr(Tanggal,7,10) = :thn")
    LiveData<List<Keuangan>> getlaporanBlnThn(String thn , String bln);

    @Query("SELECT * FROM Keuangan where substr(Tanggal,4,5)= :bln ")
    LiveData<List<Keuangan>> getlaporanBln(String bln);

    @Query("SELECT * FROM Keuangan where substr(Tanggal,7,10) = :thn")
    LiveData<List<Keuangan>> getlaporanThn(String thn );

    @Query("SELECT substr(Tanggal,7,10) as Tahun FROM Keuangan group by  substr(Tanggal,7,10)")
    LiveData<String[]> getTahun();

    @Query("SELECT * FROM Keuangan ")
    LiveData<List<Keuangan>> getlaporan();
}
