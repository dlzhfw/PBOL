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
public class DBMapel {
    private MapelModel dt = new MapelModel();

    public MapelModel getMapelModel() {
        return (dt);
    }

    public void setMapelModel (MapelModel m) {
        dt = m;
    }
    public ObservableList<MapelModel> Load() {
        try {
            ObservableList<MapelModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select KdMapel, NamaMapel from mapel");
            int i = 1;
            while (rs.next()) {
                MapelModel d = new MapelModel();
                d.setKdMapel(rs.getString("KdMapel"));
                d.setNamaMapel(rs.getString("NamaMapel"));

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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from mapel where KdMapel = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into mapel (KdMapel, NamaMapel) values (?,?)");
            con.preparedStatement.setString(1, getMapelModel().getKdMapel());
            con.preparedStatement.setString(2, getMapelModel().getNamaMapel());
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
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from mapel where KdMapel  = ? ");
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
                    "update mapel set NamaMapel = ? where KdMapel = ? ; ");
            con.preparedStatement.setString(1, getMapelModel().getNamaMapel());
            con.preparedStatement.setString(2, getMapelModel().getKdMapel());
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
    
    public ObservableList<MapelModel> LookUp(String fld, String dt) {
        try {
            ObservableList<MapelModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select KdMapel, NamaMapel from mapel where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                MapelModel d = new MapelModel();
                d.setKdMapel(rs.getString("KdMapel"));
                d.setNamaMapel(rs.getString("NamaMapel"));
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
