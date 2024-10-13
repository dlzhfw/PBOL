/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_2021130034;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_LihatCalonSiswaController implements Initializable {

    @FXML
    private TableView<CalonSiswaModel> tbvcalonsiswa;

    private DBCalonSiswa dt = new DBCalonSiswa();

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
    private Button btntambah;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnhapus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    private void showdata() {
        ObservableList<CalonSiswaModel> data = dt.Load();
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
    private void tambahklik(ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML_InputCalonSiswa.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);
    }

    @FXML
    private void editklik(ActionEvent event) {

        CalonSiswaModel s = new CalonSiswaModel();
        s = tbvcalonsiswa.getSelectionModel().getSelectedItem();
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML_InputCalonSiswa.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputCalonSiswaController isidt = (FXML_InputCalonSiswaController) loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        CalonSiswaModel s = new CalonSiswaModel();
        s = tbvcalonsiswa.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtcalonsiswa.delete(s.getNISN())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            awalklik(event);
        }
    }

}
