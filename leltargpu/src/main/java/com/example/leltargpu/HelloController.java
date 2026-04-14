package com.example.leltargpu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloController {
    @FXML
    public ListView<Adat> balLista;

    @FXML
    public ListView<String> jobbLista;

    @FXML
    private Label infoLabel;

    @FXML
    private ComboBox<BeszerzesEve> evComboBox;

    public class Adat {
        public String megnevezes;
        public int beszerzesEve;
        public int darab;
        public int egysegAr;

        public Adat(String sor) {
            String[] split = sor.split(";");
            megnevezes = split[0];
            beszerzesEve = Integer.parseInt(split[1]);
            darab = Integer.parseInt(split[2]);
            egysegAr = Integer.parseInt(split[3]);
        }

        @Override
        public String toString() {
            return String.format(
                    "%d: %s (%d x %,d,-Ft)", beszerzesEve, megnevezes, darab, egysegAr);
        }
    }

    public class BeszerzesEve {
        public int ev;

        public BeszerzesEve(int _ev) {
            ev = _ev;
        }

        @Override
        public String toString() {
            return String.format("%d adatai:", ev);
        }
    }

    private FileChooser openChooser;
    private ArrayList<Adat> adatok = new ArrayList<>();

    public void initialize() {
        openChooser = new FileChooser();
        openChooser.setInitialDirectory(new File("./"));
        openChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv fájlok", "*.csv"));
        openChooser.setTitle("Megnyitás");
    }

    @FXML
    private void RenderJobb() {
        jobbLista.getItems().clear();
        int osszeg = 0;

        for (Adat adat : adatok) {
            if (adat.beszerzesEve == evComboBox.getSelectionModel().getSelectedItem().ev) {
                jobbLista.getItems().add(String.format(
                        "%d x %s = %,d,-Ft", adat.darab, adat.megnevezes, adat.egysegAr * adat.darab));
                osszeg += adat.egysegAr * adat.darab;
            }
        }
        infoLabel.setText(String.format("%d darab / %,d,-Ft", jobbLista.getItems().size(), osszeg));
    }

    @FXML
    public void Nevjegy() {
        Alert nevjegy = new Alert(Alert.AlertType.INFORMATION);
        nevjegy.setTitle("Névjegy");
        nevjegy.setContentText("Leltár v1.0.0\n(C) Kandó");
        nevjegy.setHeaderText(null);
        nevjegy.showAndWait();
    }

    @FXML
    public void Kilepes() {
        Platform.exit();
    }

    @FXML
    public void Megnyitas() {
        File f = openChooser.showOpenDialog(balLista.getScene().getWindow());
        if (f == null) return;

        Scanner scanner = null;
        try {
            scanner = new Scanner(f);

            scanner.nextLine();
            while (scanner.hasNextLine()) {
                adatok.add(new Adat(scanner.nextLine()));
            }

            balLista.getItems().addAll(adatok);

            int minEv = 9999;
            int maxEv = 0;
            for (Adat adat : adatok) {
                minEv = Math.min(minEv, adat.beszerzesEve);
                maxEv = Math.max(maxEv, adat.beszerzesEve);
            }

            evComboBox.getItems().clear();
            for (int i = minEv; i <= maxEv; i++) {
                evComboBox.getItems().add(new BeszerzesEve(i));
            }

            evComboBox.getSelectionModel().select(0);
            RenderJobb();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (scanner != null) scanner.close();
        }
    }
}