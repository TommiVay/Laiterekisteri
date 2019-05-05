/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import controller.Controller;

/**
 *
 * @author Tommi
 */
public class SalasananPalautus {
    private Controller controller;
    private static final String merkit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
    private final StringBuilder sb = new StringBuilder(8);
    
    public SalasananPalautus(Controller c){
        this.controller = c;
    }
    
    public boolean palautaSalasana(String email){
        Kayttaja[] kayttajat = controller.haeKaikkiKayttajat();
        for(Kayttaja k : kayttajat){
            if (k.getSahkoposti().equals(email)){
                String salasana = this.getRandomSalasana();
                k.setSalasana(controller.salasananCryptaus(salasana));
                controller.paivitaKayttaja(k);
                controller.lahetaSahkoposti(k.getSahkoposti(), "Hei,\n\nSalasanasi on resetoitu.\nUusi salasanasi on: " + salasana
                + "\nMuistathan vaihtaa salasanasi, kun kirjaudut sisään.\n\nTämä on automaattinen viesti, johon ei tarvitse vastata.");
                return true;
            }
        }
    return false;
}
    
    private String getRandomSalasana(){
         for (int i = 0; i < 9; i++) { 
            int index = (int)(merkit.length() * Math.random()); 
            sb.append(merkit.charAt(index)); 
        } 
        return sb.toString(); 
    }
    
}
