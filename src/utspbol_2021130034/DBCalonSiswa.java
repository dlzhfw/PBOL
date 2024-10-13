/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol_2021130034;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class DBCalonSiswa {

    private CalonSiswaModel dt = new CalonSiswaModel();

    public CalonSiswaModel getCalonSiswaModel() {
        return (dt);
    }

    public void setCalonSiswaModel(CalonSiswaModel s) {
        dt = s;
    }

    public ObservableList<CalonSiswaModel> Load() {
        try {
            ObservableList<CalonSiswaModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select NISN, Nama, TglLahir, Alamat, JenisKelamin, SekolahAsal, Jalur from calon_siswa");
            int i = 1;
            while (rs.next()) {
                CalonSiswaModel d = new CalonSiswaModel();
                d.setNISN(rs.getString("NISN"));
                d.setNama(rs.getString("Nama"));
                d.setTglLahir(rs.getDate("TglLahir"));
                d.setAlamat(rs.getString("Alamat"));
                d.setJenisKelamin(rs.getString("JenisKelamin"));
                d.setSekolahAsal(rs.getString("SekolahAsal"));
                d.setJalur(rs.getString("Jalur"));

                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from calon_siswa where NISN = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into calon_siswa (NISN, Nama, TglLahir, Alamat, JenisKelamin, SekolahAsal, Jalur) values (?,?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getCalonSiswaModel().getNISN());
            con.preparedStatement.setString(2, getCalonSiswaModel().getNama());
            con.preparedStatement.setDate(3, getCalonSiswaModel().getTglLahir());
            con.preparedStatement.setString(4, getCalonSiswaModel().getAlamat());
            con.preparedStatement.setString(5, getCalonSiswaModel().getJenisKelamin());
            con.preparedStatement.setString(6, getCalonSiswaModel().getSekolahAsal());
            con.preparedStatement.setString(7, getCalonSiswaModel().getJalur());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from calon_siswa where NISN  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "update calon_siswa set Nama = ?, Alamat = ?, TglLahir = ?, JenisKelamin = ?, SekolahAsal = ?, Jalur = ? where  NISN = ? ; ");
            con.preparedStatement.setString(1, getCalonSiswaModel().getNama());
            con.preparedStatement.setString(2, getCalonSiswaModel().getAlamat());
            con.preparedStatement.setDate(3, getCalonSiswaModel().getTglLahir());
            con.preparedStatement.setString(4, getCalonSiswaModel().getJenisKelamin());
            con.preparedStatement.setString(5, getCalonSiswaModel().getSekolahAsal());
            con.preparedStatement.setString(6, getCalonSiswaModel().getJalur());
            con.preparedStatement.setString(7, getCalonSiswaModel().getNISN());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public ObservableList<CalonSiswaModel> LookUp(String fld, String dt) {
        try {
            ObservableList<CalonSiswaModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select NISN, Nama, TglLahir, Alamat, JenisKelamin, SekolahAsal, Jalur from calon_siswa where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                CalonSiswaModel d = new CalonSiswaModel();
                d.setNISN(rs.getString("NISN"));
                d.setNama(rs.getString("Nama"));
                d.setTglLahir(rs.getDate("TglLahir"));
                d.setAlamat(rs.getString("Alamat"));
                d.setJenisKelamin(rs.getString("JenisKelamin"));
                d.setSekolahAsal(rs.getString("SekolahAsal"));
                d.setJalur(rs.getString("Jalur"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
