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
public class DBDftrZona {

    private DftrZonaModel dt = new DftrZonaModel();

    public DftrZonaModel getDftrZonaModel() {
        return (dt);
    }

    public void setDftrZonaModel(DftrZonaModel dz) {
        dt = dz;
    }

    public ObservableList<DftrZonaModel> Load() {
        try {
            ObservableList<DftrZonaModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select d.NoZona, d.NISN, c.Nama, TglDaftar, Jarak "
                    + "from calon_siswa c inner join dftr_zonasi d on c.NISN=d.NISN");
            int i = 1;
            while (rs.next()) {
                DftrZonaModel d = new DftrZonaModel();
                d.setNoZona(rs.getString("NoZona"));
                d.setNISN(rs.getString("NISN"));
                d.setNama(rs.getString("Nama"));
                d.setTglDaftar(rs.getDate("TglDaftar"));
                d.setJarak(rs.getDouble("Jarak"));
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

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from dftr_zonasi where NoZona = '" + nomor + "'");
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
                    "insert into dftr_zonasi (NoZona,NISN,TglDaftar,Jarak) values (?,?,?,?)");
            con.preparedStatement.setString(1, getDftrZonaModel().getNoZona());
            con.preparedStatement.setString(2, getDftrZonaModel().getNISN());
            con.preparedStatement.setDate(3, getDftrZonaModel().getTglDaftar());
            con.preparedStatement.setDouble(4, getDftrZonaModel().getJarak());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from dftr_zonasi where NoZona  = ?");
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
                    "update dftr_zonasi set NISN = ?, TglDaftar = ?, Jarak = ?  where  NoZona = ?");
         
            con.preparedStatement.setString(1, getDftrZonaModel().getNISN());
            con.preparedStatement.setDate(2, getDftrZonaModel().getTglDaftar());
            con.preparedStatement.setDouble(3, getDftrZonaModel().getJarak());
            con.preparedStatement.setString(4, getDftrZonaModel().getNoZona());
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
