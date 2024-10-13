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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_InputDfRaporController implements Initializable {

    @FXML
    private DatePicker dtdftr;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private TextField txtn1;
    @FXML
    private TextField txtn2;
    @FXML
    private TextField txtn3;
    @FXML
    private TextField txtn4;
    @FXML
    private TextField txtn5;
    @FXML
    private Button btnpilihnisn;
    @FXML
    private TextField txtnisn;
    @FXML
    private Button btnpilihmp;
    @FXML
    private TextField txtkdmapel;
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
        DfRaporModel r = new DfRaporModel();
        r.setNISN(txtnisn.getText());
        r.setKdMapel(txtkdmapel.getText());
        r.setTglDaftar(Date.valueOf(dtdftr.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        r.setNilai1(Double.parseDouble(txtn1.getText()));
        r.setNilai2(Double.parseDouble(txtn2.getText()));
        r.setNilai3(Double.parseDouble(txtn3.getText()));
        r.setNilai4(Double.parseDouble(txtn4.getText()));
        r.setNilai5(Double.parseDouble(txtn5.getText()));
        FXMLDocumentController.dtdfrapor.setDfRaporModel(r);
        if (editdata) {
            if (FXMLDocumentController.dtdfrapor.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtnisn.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtdfrapor.validasi(r.getNISN(), r.getKdMapel()) <= 0) {
            if (FXMLDocumentController.dtdfrapor.insert()) {
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
            txtnisn.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtnisn.setText("");
        txtkdmapel.setText("");
        txtn1.setText("");
        txtn2.setText("");
        txtn3.setText("");
        txtn4.setText("");
        txtn5.setText("");
        dtdftr.getEditor().clear();
        txtnisn.requestFocus();
    }

    @FXML
    private void pilihnisnklik(ActionEvent event) {
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

    @FXML
    private void pilihmpklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_PilihMapel.fxml"));
            Parent root = (Parent) loader.load();
            FXML_PilihMapelController isidt = (FXML_PilihMapelController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtkdmapel.setText(isidt.getKdmapelhasil());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(DfRaporModel d) {
        if (!d.getNISN().isEmpty() && !d.getKdMapel().isEmpty()) {
            editdata = true;
            txtnisn.setText(d.getNISN());
            txtkdmapel.setText(d.getKdMapel());
            dtdftr.setValue(d.getTglDaftar().toLocalDate());
            txtn1.setText(String.valueOf(d.getNilai1()));
            txtn2.setText(String.valueOf(d.getNilai2()));
            txtn3.setText(String.valueOf(d.getNilai3()));
            txtn4.setText(String.valueOf(d.getNilai4()));
            txtn5.setText(String.valueOf(d.getNilai5()));
            txtnisn.setEditable(false);
            txtkdmapel.setEditable(false);
            dtdftr.requestFocus();
        }
    }

}
