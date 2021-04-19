import java.io.*;

public class ListerRepertoire {

    public String[] ListerFichier(String path){
        File repertoire = new File(path);
        String liste[] = repertoire.list();

        if (liste != null) {
            for (int i = 0; i < liste.length-1; i++) {
                System.out.println(liste[i]);
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
        return liste;
    }
}