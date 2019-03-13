/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Kayttaja;
import Model.Resurssit;
import Model.Varaukset;
import Model.VarauksetAccessObject;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;

/**
 * Varausten tarkasteluun käytettävän näkymän toiminnot
 *
 * @author tmati
 */
public class VarausAdminController implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Label bizName;
    @FXML
    private TableView<Varaukset> varauksetTableView;
    @FXML
    private TableColumn nimiColumn;
    @FXML
    private TableColumn tavaraColumn;
    @FXML
    private TableColumn alkupvmColumn;
    @FXML
    private TableColumn paattymispvmColumn;
    @FXML
    private TableColumn kuvausColumn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button takaisinBtn;
    @FXML
    private Button hyvaksyBtn;
    @FXML
    private Button hylkaaBtn;
    @FXML
    private Button updateBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nimiColumn.setCellValueFactory(new PropertyValueFactory<Varaukset,Kayttaja>("kayttaja"));
        nimiColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Kayttaja>() {
            public String toString(Kayttaja k) {
               return k.getNimi();
            }

            @Override
            public Kayttaja fromString(String string) {
                Kayttaja kayttaja = (Kayttaja) nimiColumn.getCellData(this);
                kayttaja.setNimi(string);
                return kayttaja;
            }
        }));
        
        tavaraColumn.setCellValueFactory(new PropertyValueFactory<Varaukset,Resurssit>("resurssit"));
        tavaraColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Resurssit>() {
            public String toString (Resurssit r) {
                return r.getNimi();
            }
            
            public Resurssit fromString(String string) {
                Resurssit resurssit = (Resurssit) tavaraColumn.getCellData(this);
                resurssit.setNimi(string);
                return resurssit;
            }
        }));
        
        alkupvmColumn.setCellValueFactory(new PropertyValueFactory<Varaukset, Date>("alkupvm"));
        alkupvmColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        
        paattymispvmColumn.setCellValueFactory(new PropertyValueFactory<Varaukset, Date>("paattymispvm"));
        paattymispvmColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        
        kuvausColumn.setCellValueFactory(new PropertyValueFactory<Varaukset, String>("kuvaus"));
        kuvausColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        bizName.setText(View.BizName);
        usernameLabel.setText(View.loggedIn.getNimi());
        
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        Varaukset[] varaukset = VAO.readVaraukset();
        varauksetTableView.getItems().addAll(varaukset);

    }    

    /**
     * Päivittää napin ulkonäön.
     * @param event
     */
    public void updateBtnPainettu(MouseEvent event) {
        varauksetTableView.getItems().clear();
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        Varaukset[] varaukset = VAO.readVaraukset();
        varauksetTableView.getItems().addAll(varaukset);
    }
    
    /**
     * Kirjaa käyttäjän ulos.
     * @param event
     * @throws IOException 
     */
    public void logout(MouseEvent event) throws IOException {
        System.out.println("Logout");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Loginwindow.fxml"));
        Stage stage = (Stage) LogoutBtn.getScene().getWindow();
        Parent root = loader.load();
        stage.getScene().setRoot(root);
        View.loggedIn = null;
    }

    @FXML
    private void takaisinBtnPainettu(MouseEvent event) throws IOException {
        System.out.println("Logout");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nakyma.fxml"));
        Stage stage = (Stage) LogoutBtn.getScene().getWindow();
        Parent root = loader.load();
        stage.getScene().setRoot(root);
    }

    @FXML
    private void hyvaksyBtnPainettu(MouseEvent event) {
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        Varaukset V = varauksetTableView.getSelectionModel().getSelectedItem();
        V.setHyvaksytty(true);
        VAO.updateVaraus(V);
        System.out.println("Varaus hyväksytty!");
    }

    @FXML
    private void hylkaaBtnPainettu(MouseEvent event) {
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        Varaukset V = varauksetTableView.getSelectionModel().getSelectedItem();
        V.setHyvaksytty(false);
        VAO.updateVaraus(V);
        System.out.println("Varaus hylätty!");
    }
    
    @FXML
    private void nimiEditCommit(TableColumn.CellEditEvent<Varaukset, String> event) {
        Varaukset V = varauksetTableView.getSelectionModel().getSelectedItem();
        V.setNimi(event.getNewValue());
        System.out.println("Uusi nimi: " + V.getNimi());
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        VAO.updateVaraus(V);
    }
    
    @FXML
    private void tavaraEditCommit(TableColumn.CellEditEvent<Varaukset, Resurssit> event) {
        Varaukset V = varauksetTableView.getSelectionModel().getSelectedItem();
        V.setResurssit(event.getNewValue());
        System.out.println("Uusi tavara: " + V.getResurssit().getNimi());
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        VAO.updateVaraus(V);
    }

    @FXML
    private void alkupvmEditCommit(TableColumn.CellEditEvent<Varaukset, LocalDateTime> event) {
        Varaukset V = varauksetTableView.getSelectionModel().getSelectedItem();
        V.setAlkuAika(event.getNewValue());
        System.out.println("Uusi alkupvm: " + V.getAlkupvm().toString());
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        VAO.updateVaraus(V);
    }

    @FXML
    private void paattymispvmEditCommit(TableColumn.CellEditEvent<Varaukset, LocalDateTime> event) {
        Varaukset V = varauksetTableView.getSelectionModel().getSelectedItem();
        V.setLoppuAika(event.getNewValue());
        System.out.println("Uusi päättymispvm: " + V.getPaattymispvm().toString());
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        VAO.updateVaraus(V);
    }

    @FXML
    private void kuvausEditCommit(TableColumn.CellEditEvent<Varaukset, String> event) {
        Varaukset V = varauksetTableView.getSelectionModel().getSelectedItem();
        V.setKuvaus(event.getNewValue());
        System.out.println("Uusi Kuvaus: " + V.getKuvaus());
        VarauksetAccessObject VAO = new VarauksetAccessObject();
        VAO.updateVaraus(V);
    }
}
