package com.unikom.go_cash.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.unikom.go_cash.Entity.Keuangan;

import java.util.List;

@Dao
public interface keuanganDAO {
    @Insert
    void insert(Keuangan keuangan);

    @Update
    void update(Keuangan keuangan);

    @Delete
    void delete(Keuangan keuangan);

    @Query("DELETE FROM Keuangan")
    void deleteAll();

    @Query("SELECT sum(uang) as SUM FROM Keuangan where type='Pemasukan'")
    LiveData<Integer> SumPemasukan();

    @Query("SELECT sum(uang) as SUM FROM Keuangan where type='Pengeluaran'")
    LiveData<Integer> SumPengeluaran();

    @Query("SELECT * FROM Keuangan where type='Pemasukan'")
    LiveData<List<Keuangan>> getPemasukan();

    @Query("SELECT * FROM Keuangan where type='Pengeluaran'")
    LiveData<List<Keuangan>> getPengeluaran();

    @Query("SELECT * FROM Keuangan where strftime('%Y',Tanggal) IN(:thn) AND strftime('%m',Tanggal) IN(:bln)")
    LiveData<List<Keuangan>> getlaporan(String thn , String bln);
}
