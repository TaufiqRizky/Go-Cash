package com.unikom.go_cash.Database;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.unikom.go_cash.Model.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)

public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

}
