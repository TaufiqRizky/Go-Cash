package com.unikom.go_cash.Database;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.unikom.go_cash.Model.User;


@Dao
public interface UserDao {
    @Query("SELECT * FROM User where username in (:us) and password= :password")
    User getUser(String us, String password);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}

