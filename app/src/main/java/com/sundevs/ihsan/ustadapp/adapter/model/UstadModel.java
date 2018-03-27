package com.sundevs.ihsan.ustadapp.adapter.model;

/**
 * Created by iihsa on 15/01/2018.
 */

public class UstadModel {
    String id;
    String nama;
    String alamat;
    double lat;
    double lng;
    String nohp;
    String email;
    String gambar;
    String bidang;
    int harga;
    public UstadModel() {

    }

    public UstadModel(String id, String nama, String alamat, double lat, double lng, String nohp, String email, String gambar,int harga, String bidang) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.lat = lat;
        this.lng = lng;
        this.nohp = nohp;
        this.email = email;
        this.gambar = gambar;
        this.bidang = bidang;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }



    public void setLat(int lat) {
        this.lat = lat;
    }



    public void setLng(int lng) {
        this.lng = lng;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
