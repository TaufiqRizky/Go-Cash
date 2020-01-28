package com.unikom.go_cash.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import  java.io.Serializable;

@Entity(tableName = "Keuangan")
public class Keuangan implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Tanggal")
    private String tgl;

    @ColumnInfo(name = "uang")
    private int uang;

    @ColumnInfo(name = "keterangan")
    private String desc;

    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "type")
    private String type;

    public Keuangan( String tgl, int uang, String desc, String nama, String type) {

        this.tgl = tgl;
        this.uang = uang;
        this.desc = desc;
        this.nama = nama;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

