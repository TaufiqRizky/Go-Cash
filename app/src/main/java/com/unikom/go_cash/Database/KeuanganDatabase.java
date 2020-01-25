package com.unikom.go_cash.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import  com.unikom.go_cash.Entity.Keuangan;

@Database(entities = {Keuangan.class}, version = 1, exportSchema = false)
public abstract class KeuanganDatabase extends RoomDatabase{
    private static KeuanganDatabase INSTANCE;
    public abstract keuanganDAO keuanganDAO();

    //private static final Object sLock = new Object();

    public static synchronized KeuanganDatabase getInstance(Context context) {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        KeuanganDatabase.class, "Keuangan.db")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
            }
            return INSTANCE;
    }
    private static  RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };
    private  static  class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private keuanganDAO dao;

        private  PopulateDbAsyncTask(KeuanganDatabase db){
            dao = db.keuanganDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new Keuangan("19/08/2000", 20000, "kas", "Taufiq Rizky" ,"Pemasukan"));
            dao.insert(new Keuangan("19/08/2000", 30000, "kas", "Taufiq Rizkyyy" ,"Pemasukan"));
            dao.insert(new Keuangan("19/08/2000", 10000, "kas", "Taufiq Rizkyyy" ,"Pengeluaran"));
            return null;
        }
    }
}
