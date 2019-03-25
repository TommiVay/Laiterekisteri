/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.Controller;
import Model.Kayttaja;
import Model.KayttajaAccessObject;
import Model.Varaukset;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Testi -luokka Controller -luokalle
 *
 * @author Tommi
 */
@Ignore
public class ControllerTest {

    Controller kont = new Controller();

    /**
     * Kayttaja -olion käsittelyyn liittyvät testit
     */
    @Test
    public void ControllerKayttajaTestit() {
        Kayttaja k = new Kayttaja("contTest", "contTest", "contTest", "contTest", 1);
        assertTrue("luoKayttaja: ei onnistunut",
                kont.luoKayttaja(k));
        assertTrue("readKayttaja(): Haku ei onnistunut",
                (k = kont.haeKayttaja(k.getId())) != null);
        assertFalse("tarkistaEmail: ei onnistunut",
                kont.tarkistaEmail("contTest"));
        assertFalse("tarkistaUsername: ei onnistunut",
                kont.tarkistaUsername("contTest"));
        k.setValtuudet(2);
        assertTrue("paivitaKayttaja: ei onnistunut",
                kont.paivitaKayttaja(k));
        assertTrue("poistaKayttaja: ei onnistunut",
                kont.poistaKayttaja(k.getId()));
        assertTrue("haeKaikkiKayttajat: ei onnistunut",
                kont.haeKaikkiKayttajat() != null);

    }

    @Test
    void ControllerVarausTestit() {
        Varaukset v = new Varaukset();
        assertTrue("luoVaraus: ei onnistunut",
                kont.luoVaraus(v));
        assertTrue("readVaraus(): Haku ei onnistunut",
                (v = kont.haeVaraus(v.getId())) != null);
        v.setKuvaus("asd");
        assertTrue("paivitaVaraus: ei onnistunut",
                kont.paivitaVaraus(v));
        assertTrue("poistaVaraus: ei onnistunut",
                kont.pa(k.getId()));
    }

}