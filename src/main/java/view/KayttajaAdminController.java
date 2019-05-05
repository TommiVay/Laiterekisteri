/**
 *
 */
package View;

import Controller.Controller;
import Model.Kayttaja;
import Model.LuvanvaraisuusConverter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Käyttäjänäkymään liittyvät toiminnot.
 *
 * @author tmati
 */
public class KayttajaAdminController implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Label bizName;
    @FXML
    private TableView<Kayttaja> kayttajaTableView;
    @FXML
    private TableColumn nimiColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn valtuudetColumn;
    @FXML
    private Button takaisinBtn;
    @FXML
    private Button lisaaBtn;
    @FXML
    private Button poistaBtn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private GridPane bgPane;
    @FXML
    private TableColumn kayttajatunnusColumn;
    @FXML
    private Label bizName1;
    
    Popup popup;
 

    private Controller kontrolleri;
    
    @FXML
    private Button kayttajanvarauksetNappi;

    /**
     * Initializes the controller class.
     *
     * @param url url
     * @param rb resourcebundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Kontrollerin ilmentymä
         */
        kontrolleri = View.controller;
        LuvanvaraisuusConverter KayLC = new LuvanvaraisuusConverter(kontrolleri, kontrolleri.getConfigTeksti("freeUse"), kontrolleri.getConfigTeksti("supApproved"), kontrolleri.getConfigTeksti("adApproved"));
        //NÄISSÄ TUON STRING-PARAMETRIN PITÄÄ VASTATA OLION PARAMETRIÄ. MUUTEN EI NÄY!
        nimiColumn.setCellValueFactory(new PropertyValueFactory<Kayttaja, String>("nimi"));
        nimiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nimiColumn.setText(kontrolleri.getConfigTeksti("name").toUpperCase());
        
        emailColumn.setCellValueFactory(new PropertyValueFactory<Kayttaja, String>("sahkoposti"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setText(kontrolleri.getConfigTeksti("emailLabel").toUpperCase());
        
        ChoiceBoxTableCell CC = new ChoiceBoxTableCell();
        CC.setConverter(KayLC);
        valtuudetColumn.setCellValueFactory(new PropertyValueFactory<Kayttaja, Integer>("valtuudet"));
        valtuudetColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(CC.getConverter(), 0,1,2));
        valtuudetColumn.setText(kontrolleri.getConfigTeksti("authorization").toUpperCase());
        
        kayttajatunnusColumn.setCellValueFactory(new PropertyValueFactory<Kayttaja, String>("kayttajatunnus"));
        kayttajatunnusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        kayttajatunnusColumn.setText(kontrolleri.getConfigTeksti("accountName").toUpperCase());
        
        kayttajaTableView.getItems().addAll(kontrolleri.haeKaikkiKayttajat());
        bizName.setText(View.BizName);
        usernameLabel.setText(View.loggedIn.getNimi());
        
        lisaaBtn.setText(kontrolleri.getConfigTeksti("newUser").toUpperCase());
        kayttajanvarauksetNappi.setText(kontrolleri.getConfigTeksti("userReservation").toUpperCase());
        poistaBtn.setText(kontrolleri.getConfigTeksti("removeUser").toUpperCase());
        takaisinBtn.setText(kontrolleri.getConfigTeksti("back").toUpperCase());
        LogoutBtn.setText(kontrolleri.getConfigTeksti("Logout").toUpperCase());
        bizName1.setText(kontrolleri.getConfigTeksti("user").toUpperCase());
        
        this.LogoutBtn.setTooltip(new Tooltip(kontrolleri.getConfigTeksti("LogoutInfo")));
        this.lisaaBtn.setTooltip(new Tooltip(kontrolleri.getConfigTeksti("addUser")));
        this.poistaBtn.setTooltip(new Tooltip(kontrolleri.getConfigTeksti("removeUser")));
        this.takaisinBtn.setTooltip((new Tooltip(kontrolleri.getConfigTeksti("returnButton"))));
        this.kayttajanvarauksetNappi.setTooltip(new Tooltip(kontrolleri.getConfigTeksti("userReservationTooltip")));
       
    }

    /**
     * Päivittää käyttäjä -taulun.
     *
     */
    @FXML
    public void updateBtnPainettu() {
        kayttajaTableView.getItems().clear();
        kayttajaTableView.getItems().addAll(kontrolleri.haeKaikkiKayttajat());
    }

    /**
     * Logout. Palauttaa käyttäjän kirjautumisnäkymään ja kirjaa tämän ulos.
     *
     * @param event MouseEvent
     * @throws IOException IOException
     */
    @FXML
    public void logout(MouseEvent event) throws IOException {
        System.out.println("Logout");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Loginwindow.fxml"));
        Stage stage = (Stage) LogoutBtn.getScene().getWindow();
        Parent root = loader.load();
        stage.getScene().setRoot(root);
        View.loggedIn = null;
    }
    
    /**
     * Käyttäjän painaessa takaisin - painiketta tämä palautetaan takaisin
     * päänäkymään.
     *
     * @param event MouseEvent
     * @throws IOException IOException
     */
    @FXML
    private void takaisinBtnPainettu(MouseEvent event) throws IOException {
        System.out.println("Logout");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nakyma.fxml"));
        Stage stage = (Stage) LogoutBtn.getScene().getWindow();
        Parent root = loader.load();
        stage.getScene().setRoot(root);
    }

    /**
     * Avaa uuden käyttäjän lisäämisnäkymän.
     *
     * @param event Hiiren klikkaus painikkeeseen.
     * @throws IOException Tiedostosta lukemisen vuoksi varauduttava poikkeus
     */
    @FXML
    private void lisaaBtnPainettu(MouseEvent event) throws IOException {
        if (popup == null || !popup.isShowing()) {
            popup = new Popup();
            Object source = event.getSource();
            Node node = (Node) source;
            Scene scene = node.getScene();
            Window window = scene.getWindow();
            Stage stage = (Stage) window;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/uusiKayttaja.fxml"));
            popup.getContent().add((Parent) loader.load());
            popup.show(window);
        }
    }

    /**
     * Poistaa valitun rivin tietokannasta.
     *
     * @param event Hiiren klikkaus painikkeeseen.
     */
    @FXML
    private void poistaBtnPainettu(MouseEvent event) {
        Kayttaja K = kayttajaTableView.getSelectionModel().getSelectedItem();
        if (K != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, kontrolleri.getConfigTeksti("confDeleteUser") , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                kontrolleri.poistaKayttaja(K.getId());
                this.updateBtnPainettu();
                System.out.println("Poistetaan rivi");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, kontrolleri.getConfigTeksti("chooseUser"));
            alert.showAndWait();
        }
    }
    
    @FXML private void kayttajanvarauksetNappiPainettu(MouseEvent event) throws IOException {
    Kayttaja K = kayttajaTableView.getSelectionModel().getSelectedItem();
        if (K != null) {
            View.selected = K;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/KayttajanVaraukset.fxml"));
            Stage stage = (Stage) LogoutBtn.getScene().getWindow();
            Parent root = loader.load();
            stage.getScene().setRoot(root);
    }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, kontrolleri.getConfigTeksti("chooseUser"));
            alert.showAndWait();
        }
    }
    
    /**
     * Toiminnallisuus nimi-columnin muokkaamisen päättyessä.
     *
     * @param event Toiminta tapahtuu taulukon solun muokkauksen varmistuessa
     * ENTER - painalluksella.
     */
    @FXML
    private void nimiEditCommit(TableColumn.CellEditEvent<Kayttaja, String> event) {
        Kayttaja J = kayttajaTableView.getSelectionModel().getSelectedItem();
        J.setNimi(event.getNewValue());
        System.out.println("Uusi nimi: " + J.getNimi());
        kontrolleri.paivitaKayttaja(J);
    }

    /**
     * Toiminnalisuus sähköposticolumnin muokkaamisen päättyessä.
     *
     * @param event Toiminta tapahtuu taulukon solun muokkauksen varmistuessa
     * ENTER - painalluksella.
     */
    @FXML
    private void emailEditCommit(TableColumn.CellEditEvent<Kayttaja, String> event) {
        if (!kontrolleri.tarkistaEmail(event.getNewValue())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, kontrolleri.getConfigTeksti("emailDublicate"));
            alert.showAndWait();
            kayttajaTableView.getItems().clear();
            kayttajaTableView.getItems().addAll(kontrolleri.haeKaikkiKayttajat());
        } else {
            Kayttaja J = kayttajaTableView.getSelectionModel().getSelectedItem();
            J.setSahkoposti(event.getNewValue());
            System.out.println("Uusi email: " + J.getSahkoposti());
            kontrolleri.paivitaKayttaja(J);
        }
    }

    /**
     * Toiminnallisuus valtuudet-columnin muokkaamisen päättyessä.
     *
     * @param event Toiminta tapahtuu taulukon solun muokkauksen varmistuessa
     * ENTER - painalluksella.
     */
    @FXML
    private void valtuudetEditCommit(TableColumn.CellEditEvent<Kayttaja, Integer> event) {
        Kayttaja J = kayttajaTableView.getSelectionModel().getSelectedItem();
        J.setValtuudet(event.getNewValue());
        System.out.println("Uudet valtuudet: " + J.getValtuudet());
        kontrolleri.paivitaKayttaja(J);
    }

    /**
     * Toiminnallisuus käyttäjätunnus-columnin muokkaamisen päättyessä.
     *
     * @param event Toiminta tapahtuu taulukon solun muokkauksen varmistuessa
     * ENTER - painalluksella
     */
    @FXML
    private void kayttajatunnusEditCommit(TableColumn.CellEditEvent<Kayttaja, String> event) {
        if (!kontrolleri.tarkistaUsername(event.getNewValue())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Käyttäjätunnus on jo käytössä!");
            alert.showAndWait();
            kayttajaTableView.getItems().clear();
            kayttajaTableView.getItems().addAll(kontrolleri.haeKaikkiKayttajat());
        } else {
            Kayttaja J = kayttajaTableView.getSelectionModel().getSelectedItem();
            J.setKayttajatunnus(event.getNewValue());
            System.out.println("Uusi tunnus: " + J.getKayttajatunnus());
            kontrolleri.paivitaKayttaja(J);
        }
    }
}