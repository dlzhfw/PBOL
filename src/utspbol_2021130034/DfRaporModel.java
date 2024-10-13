/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol_2021130034;

import java.sql.Date;

/**
 *
 * @author User
 */
public class DfRaporModel {
    private String NISN, KdMapel, Nama, NamaMapel;
    private double Nilai1, Nilai2, Nilai3, Nilai4, Nilai5, Avg, total;

    private Date TglDaftar;
    public String getNISN() {
        return NISN;
    }

    public void setNISN(String NISN) {
        this.NISN = NISN;
    }

    public String getKdMapel() {
        return KdMapel;
    }

    public void setKdMapel(String KdMapel) {
        this.KdMapel = KdMapel;
    }


    public double getNilai1() {
        return Nilai1;
    }

    public void setNilai1(double Nilai1) {
        this.Nilai1 = Nilai1;
    }

    public double getNilai2() {
        return Nilai2;
    }

    public void setNilai2(double Nilai2) {
        this.Nilai2 = Nilai2;
    }

    public double getNilai3() {
        return Nilai3;
    }

    public void setNilai3(double Nilai3) {
        this.Nilai3 = Nilai3;
    }

    public double getNilai4() {
        return Nilai4;
    }

    public void setNilai4(double Nilai4) {
        this.Nilai4 = Nilai4;
    }

    public double getNilai5() {
        return Nilai5;
    }

    public void setNilai5(double Nilai5) {
        this.Nilai5 = Nilai5;
    }

    public double getAvg() {
        total = Nilai1+Nilai2+Nilai3+Nilai4+Nilai5;
        Avg = total/5;
        return Avg;
    }

    public void setAvg(double Avg) {
        this.Avg = Avg;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getNamaMapel() {
        return NamaMapel;
    }

    public void setNamaMapel(String NamaMapel) {
        this.NamaMapel = NamaMapel;
    }

    public Date getTglDaftar() {
        return TglDaftar;
    }

    public void setTglDaftar(Date TglDaftar) {
        this.TglDaftar = TglDaftar;
    }
    
    
    
    
}
