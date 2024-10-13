/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_2021130034;

import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_InputCalonSiswaController implements Initializable {

    @FXML
    private TextField txtnisn;
    @FXML
    private DatePicker dtlahir;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txtsekolahasal;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    
    boolean editdata = false;
    @FXML
    private Button btnkeluar;
    @FXML
    private RadioButton rbperempuan;
    @FXML
    private RadioButton rblaki;
    @FXML
    private ToggleGroup tggender;
    @FXML
    private RadioButton rbzona;
    @FXML
    private ToggleGroup tgjalur;
    @FXML
    private RadioButton rbrapor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void simpanklik(ActionEvent event) {
        CalonSiswaModel s = new CalonSiswaModel();
        s.setNISN(txtnisn.getText());
        s.setNama(txtnama.getText());
        s.setTglLahir(Date.valueOf(dtlahir.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        s.setAlamat(txtalamat.getText());
        if(rbperempuan.isSelected()){
            s.setJenisKelamin(rbperempuan.getText());
        } else if ( rblaki.isSelected()){
            s.setJenisKelamin(rblaki.getText());
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Data harus diisi", ButtonType.OK);
            a.showAndWait();
            txtnisn.requestFocus();
        }
        s.setSekolahAsal(txtsekolahasal.getText());
        if(rbrapor.isSelected()){
            s.setJalur(rbrapor.getText());
        } else if ( rbzona.isSelected()){
            s.setJalur(rbzona.getText());
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Pilih Jalur Pendaftaran", ButtonType.OK);
            a.showAndWait();
            txtnisn.requestFocus();
        }
        FXMLDocumentController.dtcalonsiswa.setCalonSiswaModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtcalonsiswa.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtnisn.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtcalonsiswa.validasi(s.getNISN()) <= 0) {
            if (FXMLDocumentController.dtcalonsiswa.insert()) {
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
        txtnama.setText("");
        dtlahir.getEditor().clear();
        txtalamat.setText("");
        rbperempuan.setSelected(false);
        rblaki.setSelected(false);
        txtsekolahasal.setText("");
        txtnisn.requestFocus();
        rbzona.setSelected(false);
        rbrapor.setSelected(false);
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
    public void execute(CalonSiswaModel d) {
        if (!d.getNISN().isEmpty()) {
            editdata = true;
            txtnisn.setText(d.getNISN());
            txtnama.setText(d.getNama());
            dtlahir.setValue(d.getTglLahir().toLocalDate());
            txtalamat.setText(d.getAlamat());
            txtsekolahasal.setText(d.getSekolahAsal());
            if(d.getJenisKelamin().equalsIgnoreCase("Perempuan")){
                rbperempuan.setSelected(true);
            } else if (d.getJenisKelamin().equalsIgnoreCase("Laki-Laki")){
                rblaki.setSelected(true);
            }
            if(d.getJalur().equalsIgnoreCase("Rapor")){
                rbrapor.setSelected(true);
            } else if (d.getJalur().equalsIgnoreCase("Zonasi")){
                rbzona.setSelected(true);
            }
            txtnisn.setEditable(false);
            txtnama.requestFocus();
        }
    }
}
