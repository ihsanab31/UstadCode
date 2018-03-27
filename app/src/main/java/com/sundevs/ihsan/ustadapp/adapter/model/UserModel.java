package com.sundevs.ihsan.ustadapp.adapter.model;

/**
 * Created by iihsa on 12/01/2018.
 */

public class UserModel {

    String nip;
    String nama;
    String ttl;
    String jurusan;
    String univ;
    String nohp;
    String user;
    String password;
    String gambar;

    public UserModel() {
    }

    public UserModel(String nip,String gambar, String nama, String ttl, String jurusan, String univ, String nohp, String user, String password) {
        this.nip = nip;
        this.nama = nama;
        this.ttl = ttl;
        this.gambar = gambar;
        this.jurusan = jurusan;
        this.univ = univ;
        this.nohp = nohp;
        this.user = user;
        this.password = password;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
