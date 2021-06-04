import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Tabou {

    public DataSet methodeTabou(DataSet sol_initiale, int maxIter, char type_voisinage, int tailleListeTabou) {
        ArrayList<Dataset_item> voisinage = new ArrayList<>();
        ArrayList<Double> fitnessVoisin = new ArrayList<Double>();
        int[] listeTabou = null;
        int indice;

        for (int i = 0; i < maxIter; i++) {
            double fitnessVoisinageInitial = new FonctionObjective().fonctionObjective(sol_initiale);
            double maxF = 0;
            switch (type_voisinage) {
                case 'A':
                    voisinage = new Voisinage().voisinageA(sol_initiale);
                    break;
                case 'B':
                    //        voisinage = new Voisinage().voisinageB(sol_initiale);
                    break;
                default:
                    System.out.println("Veuillez choisir A ou B");
                    break;
            }

            for (int j = 0; j < voisinage.size(); j++) {
                double temp = new FonctionObjective().fonctionObjective(voisinage.get(j).getDataSet());
                fitnessVoisin.add(temp);
            }
            do {
                indice = ThreadLocalRandom.current().nextInt(0, fitnessVoisin.size());
            } while (!isMax(indice, fitnessVoisin));
            boolean isTabout = false;

            while (isTabout) {
                for (int p = 0; p < listeTabou.length; p++) {
                    if (voisinage.get(indice).getItem().getIdItem() == listeTabou[p]) {
                        isTabout = false;
                        do {
                            indice = ThreadLocalRandom.current().nextInt(0, fitnessVoisin.size());
                        } while (!isMax(indice, fitnessVoisin));
                    } else {
                        isTabout = true;
                    }
                }
            }

            maxF = new FonctionObjective().fonctionObjective(voisinage.get(indice).getDataSet());

            if (maxF > fitnessVoisinageInitial) {
                fitnessVoisinageInitial = maxF;
                sol_initiale = new DataSet(voisinage.get(indice).getDataSet());
            } else {
                int interdiction = voisinage.get(indice).getItem().getIdItem();
                sol_initiale = new DataSet(voisinage.get(indice).getDataSet());
                if (listeTabou == null)
                    listeTabou = new int[tailleListeTabou];

                listeTabou[0] = interdiction;
            }

            fitnessVoisin.clear();
        }
        return sol_initiale;
    }

    boolean isMax(int value, ArrayList<Double> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) > arrayList.get(value))
                return false;
        }


        return true;

    }

}
