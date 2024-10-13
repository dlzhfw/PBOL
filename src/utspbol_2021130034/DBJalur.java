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
public class DBJalur {
    private JalurModel dt = new JalurModel();

    public JalurModel getJalurModel() {
        return (dt);
    }

    public void setJalurModel(JalurModel j) {
        dt = j;
    }
    public ObservableList<JalurModel> Load() {
        try {
            ObservableList<JalurModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select KdJalur, NamaJalur, Kuota from jalur");
            int i = 1;
            while (rs.next()) {
                JalurModel d = new JalurModel();
                d.setKdJalur(rs.getString("KdJalur"));
                d.setNamaJalur(rs.getString("NamaJalur"));
                d.setKuota(rs.getInt("Kuota"));

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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from jalur where KdJalur = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jalur (KdJalur, NamaJalur, Kuota) values (?,?,?)");
            con.preparedStatement.setString(1, getJalurModel().getKdJalur());
            con.preparedStatement.setString(2, getJalurModel().getNamaJalur());
            con.preparedStatement.setDouble(3, getJalurModel().getKuota());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jalur where KdJalur  = ? ");
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
                    "update jalur set NamaJalur = ?, Kuota = ? where KdJalur = ? ; ");
            con.preparedStatement.setString(1, getJalurModel().getNamaJalur());
            con.preparedStatement.setDouble(2, getJalurModel().getKuota());
            con.preparedStatement.setString(3, getJalurModel().getKdJalur());
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
