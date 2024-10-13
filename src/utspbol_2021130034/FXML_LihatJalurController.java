/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_2021130034;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class FXML_LihatJalurController implements Initializable {

    @FXML
    private TableView<JalurModel> tbvjalur;

    private DBJalur dt = new DBJalur();
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
        ObservableList<JalurModel> data = dt.Load();
        if (data != null) {
            tbvjalur.getColumns().clear();
            tbvjalur.getItems().clear();
            TableColumn col = new TableColumn("Kode Jalur");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("KdJalur"));
            tbvjalur.getColumns().addAll(col);
            col = new TableColumn("Nama Jalur");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("NamaJalur"));
            tbvjalur.getColumns().addAll(col);
            col = new TableColumn("Kuota");
            col.setCellValueFactory(new PropertyValueFactory<CalonSiswaModel, String>("Kuota"));
            tbvjalur.getColumns().addAll(col);
            tbvjalur.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvjalur.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvjalur.getSelectionModel().selectFirst();
        tbvjalur.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvjalur.getSelectionModel().selectAboveCell();
        tbvjalur.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvjalur.getSelectionModel().selectBelowCell();
        tbvjalur.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvjalur.getSelectionModel().selectLast();
        tbvjalur.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputJalur.fxml"));
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
        JalurModel j = new JalurModel();
        j = tbvjalur.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputJalur.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputJalurController isidt = (FXML_InputJalurController) loader.getController();
            isidt.execute(j);
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
        JalurModel j = new JalurModel();
        j = tbvjalur.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtjalur.delete(j.getKdJalur())) {
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
