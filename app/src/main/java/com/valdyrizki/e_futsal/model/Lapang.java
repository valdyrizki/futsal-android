package com.valdyrizki.e_futsal.model;

public class Lapang {
    private String nama_lapang;
    private String jenis_lapang;
    private int harga_lapang;
    private String gambar_lapang;

    public Lapang() {

    }

    public Lapang(String nama_lapang, String jenis_lapang, int harga_lapang, String gambar_lapang) {
        this.nama_lapang = nama_lapang;
        this.jenis_lapang = jenis_lapang;
        this.harga_lapang = harga_lapang;
        this.gambar_lapang = gambar_lapang;
    }

    public String getNama_lapang() {
        return nama_lapang;
    }

    public void setNama_lapang(String nama_lapang) {
        this.nama_lapang = nama_lapang;
    }

    public String getJenis_lapang() {
        return jenis_lapang;
    }

    public void setJenis_lapang(String jenis_lapang) {
        this.jenis_lapang = jenis_lapang;
    }

    public int getHarga_lapang() {
        return harga_lapang;
    }

    public void setHarga_lapang(int harga_lapang) {
        this.harga_lapang = harga_lapang;
    }

    public String getGambar_lapang() {
        return gambar_lapang;
    }

    public void setGambar_lapang(String gambar_lapang) {
        this.gambar_lapang = gambar_lapang;
    }
}
