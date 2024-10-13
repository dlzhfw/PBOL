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
public class FXML_PilihMapelController implements Initializable {

    @FXML
    private TableView<MapelModel> tbvmapel;
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
    private String kdmapelhasil = "";

    public int getHasil() {
        return (hasil);
    }

    public String getKdmapelhasil() {
        return (kdmapelhasil);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbfield.setItems(FXCollections.observableArrayList(
                "KdMapel", "NamaMapel"));
        cmbfield.getSelectionModel().select(0);
        showdata("KdMapel", "");
    }

    public void showdata(String f, String i) {
        ObservableList<MapelModel> data = FXMLDocumentController.dtmapel.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXMLDocumentController.dtmapel.Load();
            txtisi.setText("");
        }
        if (data != null) {
            tbvmapel.getColumns().clear();
            tbvmapel.getItems().clear();
            TableColumn col = new TableColumn("Kode Mata Pelajaran");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("KdMapel"));
            tbvmapel.getColumns().addAll(col);
            col = new TableColumn("Nama Mata Pelajaran");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("NamaMapel"));
            tbvmapel.getColumns().addAll(col);
            tbvmapel.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvmapel.getScene().getWindow().hide();
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
        tbvmapel.getSelectionModel().selectFirst();
        tbvmapel.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvmapel.getSelectionModel().selectAboveCell();
        tbvmapel.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvmapel.getSelectionModel().selectBelowCell();
        tbvmapel.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvmapel.getSelectionModel().selectLast();
        tbvmapel.requestFocus();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbvmapel.getSelectionModel().getSelectedCells().get(0).getRow();
        kdmapelhasil = tbvmapel.getItems().get(pilihan).getKdMapel();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showdata(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }

}
