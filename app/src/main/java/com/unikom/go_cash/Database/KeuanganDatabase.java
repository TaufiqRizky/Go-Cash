package com.unikom.go_cash.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import  com.unikom.go_cash.Entity.Keuangan;

import java.util.Date;

@Database(entities = {Keuangan.class}, version = 1, exportSchema = false)
public abstract class KeuanganDatabase extends RoomDatabase{
    private static KeuanganDatabase instance;
    public abstract keuanganDAO keuanganDAO();

    //private static final Object sLock = new Object();

    public static synchronized KeuanganDatabase getInstance(Context context) {

            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        KeuanganDatabase.class, "Keuangan.db")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
            }
            return instance;
    }
    private static  RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private  static  class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private keuanganDAO dao;

        private  PopulateDbAsyncTask(KeuanganDatabase db){
            dao = db.keuanganDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new Keuangan(new Date(100,8,23 ), 20000, "kas", "Taufiq Rizky" ,"Pemasukan"));
            dao.insert(new Keuangan(new Date(100,8,19 ), 30000, "kas", "Taufiq Rizkyyy" ,"Pemasukan"));
            dao.insert(new Keuangan(new Date(100,8,18 ), 10000, "kas", "Taufiq Rizkyyy D S" ,"Pengeluaran"));
//            dao.deleteAll();
            return null;
        }
    }
}
