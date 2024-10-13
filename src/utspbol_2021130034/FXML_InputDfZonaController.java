/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_2021130034;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_InputDfZonaController implements Initializable {

    @FXML
    private TextField txtnozona;
    @FXML
    private DatePicker dtdftr;
    @FXML
    private TextField txtnisn;
    @FXML
    private TextField txtjarak;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;

    boolean editdata = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        DftrZonaModel z = new DftrZonaModel();
        z.setNoZona(txtnozona.getText());
        z.setNISN(txtnisn.getText());
        z.setTglDaftar(Date.valueOf(dtdftr.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        z.setJarak(Double.parseDouble(txtjarak.getText()));
        FXMLDocumentController.dtdzona.setDftrZonaModel(z);
        if (editdata) {
            if (FXMLDocumentController.dtdzona.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtnozona.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtdzona.validasi(z.getNoZona()) <= 0) {
            if (FXMLDocumentController.dtdzona.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtnozona.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtnozona.setText("");
        txtnisn.setText("");
        dtdftr.getEditor().clear();
        txtjarak.setText("");
        txtnozona.requestFocus();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_PilihSiswa.fxml"));
            Parent root = (Parent) loader.load();
            FXML_PilihSiswaController isidt = (FXML_PilihSiswaController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtnisn.setText(isidt.getNisnhasil());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(DftrZonaModel d) {
        if (!d.getNoZona().isEmpty()) {
            editdata = true;
            txtnozona.setText(d.getNoZona());
            txtnisn.setText(d.getNISN());
            dtdftr.setValue(d.getTglDaftar().toLocalDate());
            txtjarak.setText(String.valueOf(d.getJarak()));
            txtnozona.setEditable(false);
            txtnisn.setEditable(false);
            dtdftr.requestFocus();
        }
    }

}
