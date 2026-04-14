import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private class Adat{
        public String kod;
        public String ido;
        public int esemeny;

        public Adat (String sor){
            String[] s = sor.split(" ");
            kod = s[0];
            ido = s[1];
            esemeny = Integer.parseInt(s[2]);

        }
    }
    private ArrayList<Adat> adatok = new ArrayList<>();
    private final int BELEP = 1;
    private final int EBED = 3;
    private final int KOLCSON = 4;
    public Main(){
        //1
        betolt("bedat.txt");
        System.out.printf("1 feladat %d sor beolvasva\n",adatok.size());

        //2
        System.out.printf("2 feladat\n");
        System.out.printf("Az első tanuló lépett %s be a főkapun.", adatok.getFirst().ido);
        System.out.printf("Az utolsó tanulú %s-kor lépett ki",adatok.getLast().ido);

        //3
        PrintWriter ki = null;
        try {
            ki= new PrintWriter(new File("kesok.txt"),"utf-8");
            for (Adat a: adatok){
                if (a.ido.compareTo("7:50") > 0 && a.ido.compareTo("08:15") <= 0){
                    ki.printf("%s %s\r\n", a.ido,a.kod);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        // 4
        int ebedDb = 0; for (Adat a : adatok) if (a.esemeny == EBED) ebedDb++;
        System.out.printf("4. feladat\n");
        System.out.printf("A menzán aznap %d tanuló ebédelt.\n",ebedDb);

        //5
        int kolcsonDb= 0;
        TreeSet<String> mar  = new TreeSet<>();
        for (Adat a : adatok){
            if (a.esemeny == KOLCSON && !mar.contains(a.kod)){
                System.out.printf("Többen voltak,mint a menzán");
            }else{
                System.out.printf("Nem voltak a többen, mint a menzán");
            }
        }
        TreeMap<String, Boolean> bent = new TreeMap<>();
        for (Adat a : adatok){
            if (a.esemeny == BELEP && !bent.containsKey(a.kod)){
               bent.put(a.kod,true);
            } else if (a.esemeny == BELEP) {

            }
        }

    }
    private void betolt(String fajlnev){
        Scanner be = null;
        try{
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) adatok.add(new Adat(be.nextLine()));
        } catch (FileNotFoundException e){
            throw  new RuntimeException(e);
        } finally {
            {
                if (be != null ) be.close();;
            }
        }
    }
    public static void main(String[] args) {
        new Main();

    }
}