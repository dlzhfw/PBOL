/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_2021130034;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_PilihSiswaController implements Initializable {

    @FXML
    private TableView<CalonSiswaModel> tbvcalonsiswa;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btncari;
    @FXML
    private ComboBox<String> cmbfield;
    @FXML
    private TextField txtisi;

    private int hasil = 0;
    private String nisnhasil = "";

    public int getHasil() {
        return (hasil);
    }

    public String getNisnhasil() {
        return (nisnhasil);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbfield.setItems(FXCollections.observableArrayList(
                "NISN", "Nama", "Alamat", "Jalur"));
        cmbfield.getSelectionModel().select(0);
        showdata("NISN", "");
    }

    public void showdata(String f, String i) {
        ObservableList<CalonSiswaModel> data = FXMLDocumentController.dtcalonsiswa.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXMLDocumentController.dtcalonsiswa.Load();
            txtisi.setText("");
        }
        if (data != null) {
            tbvcalonsiswa.getColumns().clear();
            tbvcalonsiswa.getItems().clear();
            TableColumn col = new TableColumn("NISN");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("NISN"));
            tbvcalonsiswa.getColumns().addAll(col);
            col = new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("Nama"));
            tbvcalonsiswa.getColumns().addAll(col);
            col = new TableColumn("TanggalLahir");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("TglLahir"));
            tbvcalonsiswa.getColumns().addAll(col);
            col = new TableColumn("Alamat");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("Alamat"));
            tbvcalonsiswa.getColumns().addAll(col);
            col = new TableColumn("Jenis Kelamin");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("JenisKelamin"));
            tbvcalonsiswa.getColumns().addAll(col);
            col = new TableColumn("Sekolah Asal");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("SekolahAsal"));
            tbvcalonsiswa.getColumns().addAll(col);
            col = new TableColumn("Jalur Daftar");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("Jalur"));
            tbvcalonsiswa.getColumns().addAll(col);
            tbvcalonsiswa.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvcalonsiswa.getScene().getWindow().hide();
        }
        awalklik(null);
        txtisi.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvcalonsiswa.getSelectionModel().selectFirst();
        tbvcalonsiswa.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvcalonsiswa.getSelectionModel().selectAboveCell();
        tbvcalonsiswa.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvcalonsiswa.getSelectionModel().selectBelowCell();
        tbvcalonsiswa.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvcalonsiswa.getSelectionModel().selectLast();
        tbvcalonsiswa.requestFocus();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbvcalonsiswa.getSelectionModel().getSelectedCells().get(0).getRow();
        nisnhasil = tbvcalonsiswa.getItems().get(pilihan).getNISN();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showdata(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }

}
