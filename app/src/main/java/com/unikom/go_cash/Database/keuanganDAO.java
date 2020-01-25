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

    @Query("SELECT * FROM Keuangan where type='pemasukan'")
    LiveData<List<Keuangan>> getPemasukan();
}
