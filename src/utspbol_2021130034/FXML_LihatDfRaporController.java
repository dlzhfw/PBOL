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
public class FXML_LihatDfRaporController implements Initializable {

    @FXML
    private TableView<DfRaporModel> tbvdfrapor;
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

     public void showdata() {
        ObservableList<DfRaporModel> data = FXMLDocumentController.dtdfrapor.Load();
        if (data != null) {
            tbvdfrapor.getColumns().clear();
            tbvdfrapor.getItems().clear();
            TableColumn col = new TableColumn("NISN");
            col.setCellValueFactory(new PropertyValueFactory<DfRaporModel, String>("NISN"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<DfRaporModel, String>("Nama"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("KodeMapel");
            col.setCellValueFactory(new PropertyValueFactory<DfRaporModel, String>("KdMapel"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("Tanggal Daftar");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("TglDaftar"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("Nilai1");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Nilai1"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("Nilai2");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Nilai2"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("Nilai3");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Nilai3"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("Nilai4");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Nilai4"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("Nilai5");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Nilai5"));
            tbvdfrapor.getColumns().addAll(col);
            col = new TableColumn("Avg");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Avg"));
            tbvdfrapor.getColumns().addAll(col);
            tbvdfrapor.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvdfrapor.getScene().getWindow().hide();
        }
    }
    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvdfrapor.getSelectionModel().selectFirst();
        tbvdfrapor.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvdfrapor.getSelectionModel().selectAboveCell();
        tbvdfrapor.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvdfrapor.getSelectionModel().selectBelowCell();
        tbvdfrapor.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvdfrapor.getSelectionModel().selectLast();
        tbvdfrapor.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML_InputDfRapor.fxml"));
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
        DfRaporModel d = new DfRaporModel();
        d = tbvdfrapor.getSelectionModel().getSelectedItem();
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML_InputDfRapor.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputDfRaporController isidt = (FXML_InputDfRaporController) loader.getController();
            isidt.execute(d);
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
        DfRaporModel d = new DfRaporModel();
        d = tbvdfrapor.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtdfrapor.delete(d.getNISN(), d.getKdMapel())) {
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
