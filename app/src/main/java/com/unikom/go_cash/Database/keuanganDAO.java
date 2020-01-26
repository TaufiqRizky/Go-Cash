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
    void insert(Keuangan uang);

    @Update
    void update(Keuangan uang);

    @Delete
    void delete(Keuangan uang);

    @Query("DELETE FROM Keuangan")
    void deleteAll();

    @Query("SELECT * FROM Keuangan where type='Pemasukan'")
    LiveData<List<Keuangan>> getPemasukan();

    @Query("SELECT * FROM Keuangan where type='Pengeluaran'")
    LiveData<List<Keuangan>> getPengeluaran();

    @Query("SELECT * FROM Keuangan where strftime('%Y',Tanggal) IN(:thn) AND strftime('%m',Tanggal) IN(:bln)")
    LiveData<List<Keuangan>> getlaporan(String thn , String bln);
}
