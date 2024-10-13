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
public class DBDfRapor {

    private DfRaporModel dt = new DfRaporModel();

    public DfRaporModel getDfRaporModel() {
        return (dt);
    }

    public void setDfRaporModel(DfRaporModel dr) {
        dt = dr;
    }

    public ObservableList<DfRaporModel> Load() {
        try {
            ObservableList<DfRaporModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select d.NISN, c.Nama, d.KdMapel, TglDaftar, Nilai1, Nilai2, Nilai3, Nilai4, Nilai5 "
                    + "from calon_siswa c inner join dftr_rapor d on c.NISN=d.NISN");
            int i = 1;
            while (rs.next()) {
                DfRaporModel d = new DfRaporModel();
                d.setNISN(rs.getString("NISN"));
                d.setNama(rs.getString("Nama"));
                d.setKdMapel(rs.getString("KdMapel"));
                d.setTglDaftar(rs.getDate("TglDaftar"));
                d.setNilai1(rs.getDouble("Nilai1"));
                d.setNilai2(rs.getDouble("Nilai2"));
                d.setNilai3(rs.getDouble("Nilai3"));
                d.setNilai4(rs.getDouble("Nilai4"));
                d.setNilai5(rs.getDouble("Nilai5"));
                
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor, String kode) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from dftr_rapor where NISN = '" + nomor + "' and KdMapel='" + kode + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "insert into dftr_rapor (NISN,KdMapel,TglDaftar,Nilai1,Nilai2,Nilai3,Nilai4,Nilai5) values (?,?,?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getDfRaporModel().getNISN());
            con.preparedStatement.setString(2, getDfRaporModel().getKdMapel());
            con.preparedStatement.setDate(3, getDfRaporModel().getTglDaftar());
            con.preparedStatement.setDouble(4, getDfRaporModel().getNilai1());
            con.preparedStatement.setDouble(5, getDfRaporModel().getNilai2());
            con.preparedStatement.setDouble(6, getDfRaporModel().getNilai3());
            con.preparedStatement.setDouble(7, getDfRaporModel().getNilai4());
            con.preparedStatement.setDouble(8, getDfRaporModel().getNilai5());
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

    public boolean delete(String nomor, String kode) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from dftr_rapor where NISN  = ? and KdMapel = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
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
                    "update dftr_rapor set TglDaftar = ?, Nilai1 = ?, Nilai2 = ?,Nilai3 = ?,Nilai4 = ?,Nilai5 = ?  where  NISN = ? and KdMapel = ?" );
         
            con.preparedStatement.setDate(1, getDfRaporModel().getTglDaftar());
            con.preparedStatement.setDouble(2, getDfRaporModel().getNilai1());
            con.preparedStatement.setDouble(3, getDfRaporModel().getNilai2());
            con.preparedStatement.setDouble(4, getDfRaporModel().getNilai3());
            con.preparedStatement.setDouble(5, getDfRaporModel().getNilai4());
            con.preparedStatement.setDouble(6, getDfRaporModel().getNilai5());
            con.preparedStatement.setString(7, getDfRaporModel().getNISN());
            con.preparedStatement.setString(8, getDfRaporModel().getKdMapel());
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
    
}
