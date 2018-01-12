package com.mybaggage.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mybaggage.Database;
import com.mybaggage.Utilities;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane rootAnchorPane;

    @FXML
    private TableColumn type;

    @FXML
    private TableColumn formuliernummer;

    @FXML
    private TableColumn lostandfoundID;

    @FXML
    private TableColumn kenmerken;

    @FXML
    private TableColumn labelnummer;

    @FXML
    private TableColumn luchthaven;
//Gaat naar het scherm Bagage Toevoegen
    @FXML
    private Button btn_bagageToevoegen;

    @FXML
    private Button export;

    @FXML
    private Button pdf;

    @FXML
    private void bagageToevoegen() throws IOException {
        Utilities.switchSchermNaarFXML("BagageToevoegen.fxml", rootAnchorPane);
    }
//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageWijzigen;

    @FXML
    private void bagageWijzigen() throws IOException {
//MainController.switchScherm("/com/mybaggage/controllers/BagageWijzigen.fxml");
        Utilities.switchSchermNaarFXML("BagageWijzigen.fxml", rootAnchorPane);
    }
//Gaat naar het scherm Bagage Wijzigen
    @FXML
    private Button btn_bagageVerwijderen;

    @FXML
    private void bagageVerwijderen() throws IOException {
//MainController.switchScherm("/com/mybaggage/controllers/BagageVerwijderen.fxml");
        Utilities.switchSchermNaarFXML("BagageVerwijderen.fxml", rootAnchorPane);
    }

    @FXML
    private void setCellTable() {
        formuliernummer.setCellValueFactory(new PropertyValueFactory<>("formuliernummer"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        lostandfoundID.setCellValueFactory(new PropertyValueFactory<>("lostandfoundID"));
        kenmerken.setCellValueFactory(new PropertyValueFactory<>("kenmerken"));
        labelnummer.setCellValueFactory(new PropertyValueFactory<>("labelnummer"));
        luchthaven.setCellValueFactory(new PropertyValueFactory<>("luchthaven"));
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
            pst = conn.prepareStatement("Select * from registratie");
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new com.mybaggage.models.BagageToevoegen(rs.getString(1), rs.getString(2), rs.getString(12), rs.getString(4), rs.getString(6), rs.getString(11)));
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
            String query = "Select * from registratie";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

// XSSF is voor 2007 of nieuwere versies voor excel. HSSF is voor 2006 en ouder.
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Bagage overzicht");// maakt een sheet aan
            XSSFRow header = sheet.createRow(0); // maakt rijen aan om de data in de database te krijgen
            header.createCell(0).setCellValue("formuliernummer");
            header.createCell(1).setCellValue("type");
            header.createCell(2).setCellValue("lostandfoundID");
            header.createCell(3).setCellValue("kenmerken");
            header.createCell(4).setCellValue("labelnummer");
            header.createCell(5).setCellValue("luchthaven");

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
                row.createCell(0).setCellValue(rs.getString("formuliernummer"));
                row.createCell(1).setCellValue(rs.getString("type"));
                row.createCell(2).setCellValue(rs.getString("lostandfoundID"));
                row.createCell(3).setCellValue(rs.getString("kenmerken"));
                row.createCell(4).setCellValue(rs.getString("labelnummmer"));
                row.createCell(5).setCellValue(rs.getString("luchthaven"));
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

    @FXML
    public void pdfs()
            throws Exception {

        conn = Database.connectdb();
        try {
            Statement stmt = conn.createStatement();
            /* SQL query */
            ResultSet query_set = stmt.executeQuery("SELECT * From registratie");
            /* Step-2: PDF document laden */
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("BagageOverzicht.pdf"));
            my_pdf_report.open();
//Hoeveel tabellen ?
            PdfPTable my_report_table = new PdfPTable(4);
//cell object maken
            PdfPCell table_cell;

            while (query_set.next()) {

                Paragraph preface = new Paragraph();
                preface.add(new Paragraph("PDF Bagage Overzicht - Corendon"));
                table_cell = new PdfPCell(new Phrase("formuliernummer"));
                String RegistratieNummer = query_set.getString("formuliernummer");
                table_cell = new PdfPCell(new Phrase(RegistratieNummer));
                my_report_table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase("type"));
                String Kleur = query_set.getString("type");
                table_cell = new PdfPCell(new Phrase(Kleur));
                my_report_table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase("lostandfoundID"));
                String Grootte = query_set.getString("lostandfoundID");
                table_cell = new PdfPCell(new Phrase(Grootte));
                my_report_table.addCell(table_cell);
                table_cell = new PdfPCell(new Phrase("kenmerken"));
                String Status = query_set.getString("kenmerken");
                table_cell = new PdfPCell(new Phrase(Status));
                my_report_table.addCell(table_cell);
            }
            /* tabel naar PDF */
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Bagage Overzicht");
            alert.setHeaderText(null);
            alert.setContentText("Overzicht succesvol geëxporteerd!");
            alert.showAndWait();

            /* Alles sluiten */
            query_set.close();
            stmt.close();
            conn.close();

        } catch (FileNotFoundException | DocumentException e) {
// Auto-generated catch block

        }

    }
}
