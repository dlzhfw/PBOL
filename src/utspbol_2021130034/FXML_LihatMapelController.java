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
public class FXML_LihatMapelController implements Initializable {

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
    private Button btntambah;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnhapus;

    private DBMapel dt = new DBMapel();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    

    private void showdata() {
        ObservableList<MapelModel> data = dt.Load();
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
            tbvmapel.getScene().getWindow().hide();;
        }
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
    private void tambahklik(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMapel.fxml"));
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
        MapelModel m = new MapelModel();
        m = tbvmapel.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMapel.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputMapelController isidt = (FXML_InputMapelController) loader.getController();
            isidt.execute(m);
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
        
        MapelModel m = new MapelModel();
        m = tbvmapel.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtmapel.delete(m.getKdMapel())) {
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
