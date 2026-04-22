package com.example.madarakgui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;


public class HelloController {
    private class Adat{
        public String mnev;
        public String lnev;
        public int atlagsuly;
        public int atlagmagas;
        public int repules;


        public Adat(String sor) {
            String[] s = sor.split(";");
            mnev = s[0];
            lnev = s[1];
            atlagsuly = Integer.parseInt(s[2]);
            atlagmagas = Integer.parseInt(s[3]);
            repules = Integer.parseInt(s[4]);


        }
    }

    private ArrayList<Adat> adatok = new ArrayList<>();
    @FXML
    public void Nevjegy()
    {
        Alert nevjegy = new Alert(Alert.AlertType.INFORMATION);
        nevjegy.setTitle("Madarak v1.0.0");
        nevjegy.setContentText("Madarak v1.0.0" + "(C) 2026");
        nevjegy.setHeaderText(null);


        nevjegy.showAndWait();
    }
}