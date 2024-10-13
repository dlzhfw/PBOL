/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_2021130034;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_InputMapelController implements Initializable {

    @FXML
    private TextField txtkdmapel;
    @FXML
    private TextField txtnamamapel;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;

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
    private void simpanklik(ActionEvent event) { MapelModel m = new MapelModel();
        m.setKdMapel(txtkdmapel.getText());
        m.setNamaMapel(txtnamamapel.getText());
        FXMLDocumentController.dtmapel.setMapelModel(m);
        if (editdata) {
            if (FXMLDocumentController.dtmapel.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtkdmapel.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtmapel.validasi(m.getKdMapel()) <= 0) {
            if (FXMLDocumentController.dtmapel.insert()) {
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
            txtkdmapel.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) { 
        txtkdmapel.setText("");
        txtnamamapel.setText("");
        txtkdmapel.requestFocus();
    }
    
    
    public void execute(MapelModel d) {
        if (!d.getKdMapel().isEmpty()) {
            editdata = true;
            txtkdmapel.setText(d.getKdMapel());
            txtnamamapel.setText(d.getNamaMapel());
            txtkdmapel.setEditable(false);
            txtkdmapel.requestFocus();
        }
    }
}
