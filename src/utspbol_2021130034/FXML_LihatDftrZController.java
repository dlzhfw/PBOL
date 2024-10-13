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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_LihatDftrZController implements Initializable {

    @FXML
    private TableView<DftrZonaModel> tbvdftrzona;

    //private DBDftrZona dt = new DBDftrZona();
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
    @FXML
    private TextField txtavg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    public void showdata() {
        ObservableList<DftrZonaModel> data = FXMLDocumentController.dtdzona.Load();
        if (data != null) {
            tbvdftrzona.getColumns().clear();
            tbvdftrzona.getItems().clear();
            TableColumn col = new TableColumn("No Daftar");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("NoZona"));
            tbvdftrzona.getColumns().addAll(col);
            col = new TableColumn("NISN");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("NISN"));
            tbvdftrzona.getColumns().addAll(col);
            col = new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Nama"));
            tbvdftrzona.getColumns().addAll(col);
            col = new TableColumn("Tanggal Daftar");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("TglDaftar"));
            tbvdftrzona.getColumns().addAll(col);
            col = new TableColumn("Jarak");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Jarak"));
            tbvdftrzona.getColumns().addAll(col);
            col = new TableColumn("Status");
            col.setCellValueFactory(new PropertyValueFactory<DftrZonaModel, String>("Status"));
            tbvdftrzona.getColumns().addAll(col);
            tbvdftrzona.setItems(data);
            
            double total = 0;
            for (int i = 0; i < tbvdftrzona.getItems().size(); i++) {
                DftrZonaModel z = tbvdftrzona.getItems().get(i);
                total += z.getJarak();
                
            }
            double avgjarak= total/tbvdftrzona.getItems().size();
            txtavg.setText(String.valueOf(avgjarak));
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvdftrzona.getScene().getWindow().hide();
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvdftrzona.getSelectionModel().selectFirst();
        tbvdftrzona.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvdftrzona.getSelectionModel().selectAboveCell();
        tbvdftrzona.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvdftrzona.getSelectionModel().selectBelowCell();
        tbvdftrzona.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvdftrzona.getSelectionModel().selectLast();
        tbvdftrzona.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML_InputDfZona.fxml"));
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
        DftrZonaModel d = new DftrZonaModel();
        d = tbvdftrzona.getSelectionModel().getSelectedItem();
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("FXML_InputDfZona.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputDfZonaController isidt = (FXML_InputDfZonaController) loader.getController();
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
        DftrZonaModel d = new DftrZonaModel();
        d = tbvdftrzona.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtdzona.delete(d.getNoZona())) {
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
