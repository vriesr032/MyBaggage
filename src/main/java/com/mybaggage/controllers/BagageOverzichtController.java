package com.mybaggage.controllers;

import com.mybaggage.Database;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author rickdevries
 */
public class BagageOverzichtController implements Initializable {

    @FXML
    private TableColumn kolomnaam;

    @FXML
    private TableColumn kolomkleur;

    @FXML
    private TableColumn kolomgrootte;

    @FXML
    private TableColumn kolomsoortbagage;

    @FXML
    private TableColumn kolomstatus;

    @FXML
    private TableColumn kolomRegistratieNummer;
//Gaat naar het scherm Bagage Toevoegen
    @FXML
    private Button btn_bagageToevoegen;

    @FXML
    private Button export;

    @FXML
    private void bagageToevoegen() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageToevoegen.fxml");
    }
//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageWijzigen;

    @FXML
    private void bagageWijzigen() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageWijzigen.fxml");
    }
//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageVerwijderen;

    @FXML
    private void bagageVerwijderen() {
        MainController.switchScherm("/com/mybaggage/controllers/BagageVerwijderen.fxml");
    }

    @FXML
    private void setCellTable() {
        kolomnaam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        kolomkleur.setCellValueFactory(new PropertyValueFactory<>("kleur"));
        kolomgrootte.setCellValueFactory(new PropertyValueFactory<>("grootte"));
        kolomsoortbagage.setCellValueFactory(new PropertyValueFactory<>("soortBagage"));
        kolomstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

//Laad alle data van de database in de tabel
    @FXML
    private ObservableList<com.mybaggage.models.BagageToevoegen> data;

    @FXML
    private TableView<com.mybaggage.models.BagageToevoegen> tabelBagage;

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @FXML
    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from bagage");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new com.mybaggage.models.BagageToevoegen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BagageOverzichtController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabelBagage.setItems(data);
    }

    public BagageOverzichtController() {
        conn = Database.connectdb();
    }

    @FXML
    private void exportExcel() {
        conn = Database.connectdb();
        try {
            String query = "Select * from bagage";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            // XSSF is voor 2007 of nieuwere versies voor excel. HSSF is voor 2006 en ouder.
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Bagage overzicht");// maakt een sheet aan
            XSSFRow header = sheet.createRow(0); // maakt rijen aan om de data in de database te krijgen
            header.createCell(0).setCellValue("Naam");
            header.createCell(1).setCellValue("Kleur");
            header.createCell(2).setCellValue("Grootte");
            header.createCell(3).setCellValue("SoortBagage");
            header.createCell(4).setCellValue("Status");
            header.createCell(5).setCellValue("RegistratieNummer");

            sheet.setColumnWidth(0, 256 * 25);
            sheet.setColumnWidth(1, 256 * 25);
            sheet.setColumnWidth(2, 256 * 25);
            sheet.setColumnWidth(3, 256 * 25);
            sheet.setColumnWidth(4, 256 * 25);
            sheet.setColumnWidth(5, 256 * 25);

            int index = 1;
            // Met deze code zet je pak je alleen de 1e rij van de excel bestand.
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("Naam"));
                row.createCell(1).setCellValue(rs.getString("Kleur"));
                row.createCell(2).setCellValue(rs.getString("Grootte"));
                row.createCell(3).setCellValue(rs.getString("SoortBagage"));
                row.createCell(4).setCellValue(rs.getString("Status"));
                row.createCell(5).setCellValue(rs.getString("RegistratieNummer"));
                index++; // Dit zorgt ervoor dat er meerdere rijen in de excel bestand worden opgepakt
            }

            FileOutputStream fileOut = new FileOutputStream("Bagage Overzicht.xlsx");
            wb.write(fileOut);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("BagageOverzicht");
            alert.setHeaderText(null);
            alert.setContentText("Overzicht succesvol geëxporteerd!");
            alert.showAndWait();

            pst.close();
            rs.close();

        } catch (SQLException | IOException ex) {
            Logger.getLogger(BagageOverzichtController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();

    }
}
