/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol_2021130034;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXML_InputJalurController implements Initializable {

    @FXML
    private TextField txtidjalur;
    @FXML
    private TextField txtnamajalur;
    @FXML
    private TextField txtkuota;
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
    private void simpanklik(ActionEvent event) {
        JalurModel j = new JalurModel();
        j.setKdJalur(txtidjalur.getText());
        j.setNamaJalur(txtnamajalur.getText());
        j.setKuota(Integer.parseInt(txtkuota.getText()));
        FXMLDocumentController.dtjalur.setJalurModel(j);
        if (editdata) {
            if (FXMLDocumentController.dtjalur.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtidjalur.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtjalur.validasi(j.getKdJalur()) <= 0) {
            if (FXMLDocumentController.dtjalur.insert()) {
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
            txtidjalur.requestFocus();
        }

    }

    @FXML
    private void batalklik(ActionEvent event) {

        txtidjalur.setText("");
        txtnamajalur.setText("");
        txtkuota.setText("");
        txtidjalur.requestFocus();
    }
    
    
    public void execute(JalurModel d) {
        if (!d.getKdJalur().isEmpty()) {
            editdata = true;
            txtidjalur.setText(d.getKdJalur());
            txtnamajalur.setText(d.getNamaJalur());
            txtkuota.setText(String.valueOf(d.getKuota()));
            txtidjalur.setEditable(false);
            txtnamajalur.requestFocus();
        }
    }
}
