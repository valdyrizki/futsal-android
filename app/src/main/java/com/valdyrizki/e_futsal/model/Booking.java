package com.valdyrizki.e_futsal.model;

public class Booking {
    String id_booking;
    String name;
    String nama_lapang;
    String jenis_lapang;
    String tgl_booking;
    String waktu_booking;
    String status_booking;
    String gambar_lapang;
    int harga_lapang;

    public Booking() {

    }

    public Booking(String id_booking, String name, String nama_lapang, String jenis_lapang, String tgl_booking, String waktu_booking, String status_booking, String gambar_lapang, int harga_lapang) {
        this.id_booking = id_booking;
        this.name = name;
        this.nama_lapang = nama_lapang;
        this.jenis_lapang = jenis_lapang;
        this.tgl_booking = tgl_booking;
        this.waktu_booking = waktu_booking;
        this.status_booking = status_booking;
        this.gambar_lapang = gambar_lapang;
        this.harga_lapang = harga_lapang;
    }

    public String getId_booking() {
        return id_booking;
    }

    public void setId_booking(String id_booking) {
        this.id_booking = id_booking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTgl_booking() {
        return tgl_booking;
    }

    public void setTgl_booking(String tgl_booking) {
        this.tgl_booking = tgl_booking;
    }

    public String getWaktu_booking() {
        return waktu_booking;
    }

    public void setWaktu_booking(String waktu_booking) {
        this.waktu_booking = waktu_booking;
    }

    public String getStatus_booking() {
        return status_booking;
    }

    public void setStatus_booking(String status_booking) {
        this.status_booking = status_booking;
    }

    public String getGambar_lapang() {
        return gambar_lapang;
    }

    public void setGambar_lapang(String gambar_lapang) {
        this.gambar_lapang = gambar_lapang;
    }

    public int getHarga_lapang() {
        return harga_lapang;
    }

    public void setHarga_lapang(int harga_lapang) {
        this.harga_lapang = harga_lapang;
    }
}
