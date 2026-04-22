import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

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

    public Main() {
        betolt("madarak.csv");
        //1
        System.out.printf("1) Összesen %d madár adata van beolvasva.\n",adatok.size());
        //2
        Adat leg = adatok.get(0);
        for(Adat f : adatok) if(f.repules > leg.repules) leg = f;
        System.out.printf("2) A Legmesszebre repülő madár a %s, %d km \n", leg.mnev,leg.repules);
        //3
        int db =  0;
        for(Adat f : adatok) if (f.atlagsuly < 100) db++;
        System.out.printf("3) A 100g alatt lévő madarak db száma: %,d \n", db);

        //7
        int fecskedb =  0;
        for(Adat f : adatok) if (f.mnev.contains("fecske")) fecskedb++;
        System.out.printf("7) Darab fecske van: %,d \n", fecskedb);



    }
    private void betolt(String fajlnev){
        Scanner beolvasas = null;
        try {
            beolvasas = new Scanner(new File(fajlnev), "utf-8");
            beolvasas.nextLine();
            while(beolvasas.hasNextLine()) adatok.add(new Adat(beolvasas.nextLine()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(beolvasas != null) beolvasas.close();
        }

    }

    public static void main(String[] args) {
        new Main();
    }

}