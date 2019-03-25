/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Kayttaja;
import Model.KayttajaAccessObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

/**
 * Uuden käyttäjän luontipopupin toiminnallisuus
 * @author tmati
 */
public class UusiKayttajaController implements Initializable {

    @FXML
    private Button luokayttajaNappi;
    @FXML
    private Label titleLabel;
    @FXML
    private Button sulkuNappi;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label virheLabel;
    @FXML
    private TextField nimiTextField;
    @FXML
    private TextField salasanaTextField;
    @FXML
    private TextField kayttajatunnusTextField;
    @FXML
    private ChoiceBox<String> valtuudetChoiceBox;
    @FXML
    private TableView<Kayttaja> kayttajaTableView;
    
    /**
     * Controller-ilmentymä
     */
    private Controller controller;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        controller = View.controller;
    }

    /**
<<<<<<< HEAD
     * Käännetään choiceboxin arvo tietokantaan sopivaksi
     *
     * @param cb
     * @return
     */
    int tulkitseChoiceBox(ChoiceBox cb) {
        int selectedOption = -1;
        if (cb.getValue().equals("Työntekijä")) {
            selectedOption = 0;
        } else if (cb.getValue().equals("Esimies")) {
            selectedOption = 1;
        } else if (cb.getValue().equals("Ylläpitäjä")) {
            selectedOption = 2;
        }
        return selectedOption;
    }

    /**
=======
>>>>>>> 8a8731d5d2bfdebe1fcb3a056ddc07c316021857
     * Luo uuden käyttäjän annettujen ehtojen täsmätessä. Herjaa jos ehdot ei
     * täsmää. Tarkastaa myös sähköpostin ja käyttäjätunnuksen tietokantaa vasten (ei saa olla samat)
     * @param event Hiiren klikkaus painikkeesta.
     */
    @FXML
    private void luokayttajaNappiPainettu(MouseEvent event) {
        if (nimiTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || salasanaTextField.getText().isEmpty() || kayttajatunnusTextField.getText().isEmpty() || valtuudetChoiceBox.getValue().equals("Valitse...")) {
            virheLabel.setText("Tietoja puuttuu. Täytä kaikki kohdat ja yritä uudelleen.");
            virheLabel.setDisable(false);
            virheLabel.setOpacity(100);
        } else if (!controller.tarkistaUsername(kayttajatunnusTextField.getText())) {
            virheLabel.setText("Käyttäjätunnus on jo varattu. Kokeile toista tunnusta.");
            virheLabel.setDisable(false);
            virheLabel.setOpacity(100);
        } else if (!controller.tarkistaEmail(emailTextField.getText())) {
            virheLabel.setText("Sähköposti on jo varattu. Kokeile toista osoitetta.");
            virheLabel.setDisable(false);
            virheLabel.setOpacity(100);
        } else {
            Kayttaja J = new Kayttaja(nimiTextField.getText(), salasanaTextField.getText(), kayttajatunnusTextField.getText(), emailTextField.getText(), controller.readCb(valtuudetChoiceBox));
            System.out.println(J.getNimi() + " | " + J.getSalasana() + " | " + J.getKayttajatunnus() + " | " + J.getSahkoposti() + " | " + J.getValtuudet());
            controller.luoKayttaja(J);
            Popup popup = (Popup) sulkuNappi.getScene().getWindow();
            popup.hide();
        }
    }

    /**
     * Sulkee popupin.
     * @param event Hiiren klikkaus painikkeesta.
     */
    @FXML
    private void sulkuNappiPainettu(ActionEvent event) {
        Popup popup = (Popup) sulkuNappi.getScene().getWindow();
        popup.hide();
    }
}