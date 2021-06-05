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
        ArrayList<DatasetItemItem> voisinageB = new ArrayList<>();
        ArrayList<Double> fitnessVoisin = new ArrayList<>();
        List<Integer> listeTabou = null;
        List<int[]> listeTabouB = null;
        int indice;

        for (int i = 0; i < maxIter; i++) {
            double fitnessVoisinageInitial = new FonctionObjective().fonctionObjective(sol_initiale);
            double maxF = 0;
            switch (type_voisinage) {
                case 'A':
                    voisinage = new Voisinage().voisinageA(sol_initiale);
                    break;
                case 'B':
                    voisinageB = new Voisinage().voisinageB(sol_initiale);
                    break;
                default:
                    System.out.println("Veuillez choisir A ou B");
                    break;
            }
            if (voisinageB.size() == 0 && voisinage.size() == 0)
                return sol_initiale;


            double temp;

            if (type_voisinage == 'A')
                for (int j = 0; j < voisinage.size(); j++) {
                    temp = new FonctionObjective().fonctionObjective(voisinage.get(j).getDataSet());
                    fitnessVoisin.add(temp);

                }
            else
                for (int j = 0; j < voisinageB.size(); j++) {
                    temp = new FonctionObjective().fonctionObjective(voisinageB.get(j).getDataSet());
                    fitnessVoisin.add(temp);
                }
            do {
                indice = ThreadLocalRandom.current().nextInt(0, fitnessVoisin.size());
            } while (!isMax(indice, fitnessVoisin));
            boolean isTabout = false;


            while (isTabout) {
                if (type_voisinage == 'A') {
                    if (listeTabou.contains(voisinage.get(indice).getItem().getIdItem())) {
                        isTabout = false;
                        do {
                            indice = ThreadLocalRandom.current().nextInt(0, fitnessVoisin.size());
                        } while (!isMax(indice, fitnessVoisin));
                    } else {
                        isTabout = true;
                    }
                } else {
                    for (int y = 0; y < listeTabouB.size(); y++) {
                        if (Arrays.asList(listeTabouB.get(y)).contains(voisinageB.get(indice).getItem1().getIdItem()) && Arrays.asList(listeTabouB.get(y)).contains(voisinageB.get(indice).getItem2().getIdItem())) {
                            isTabout = false;
                            do {
                                indice = ThreadLocalRandom.current().nextInt(0, fitnessVoisin.size());
                            } while (!isMax(indice, fitnessVoisin));
                            y = 0;
                        } else {
                            isTabout = true;
                        }
                    }
                }
            }

            if (type_voisinage == 'A') {
                maxF = new FonctionObjective().fonctionObjective(voisinage.get(indice).getDataSet());

                if (maxF > fitnessVoisinageInitial) {
                    fitnessVoisinageInitial = maxF;
                    sol_initiale = new DataSet(voisinage.get(indice).getDataSet());
                } else {
                    int interdiction = voisinage.get(indice).getItem().getIdItem();
                    sol_initiale = new DataSet(voisinage.get(indice).getDataSet());
                    if (listeTabou == null)
                        listeTabou = new ArrayList<>();

                    if (listeTabou.size() == tailleListeTabou)
                        listeTabou.remove(0);


                    listeTabou.add(interdiction);
                }
            } else {
                maxF = new FonctionObjective().fonctionObjective(voisinageB.get(indice).getDataSet());

                if (maxF > fitnessVoisinageInitial) {
                    fitnessVoisinageInitial = maxF;
                    sol_initiale = new DataSet(voisinageB.get(indice).getDataSet());
                } else {
                    int interdiction = voisinageB.get(indice).getItem1().getIdItem();
                    int interdiction2 = voisinageB.get(indice).getItem2().getIdItem();
                    sol_initiale = new DataSet(voisinageB.get(indice).getDataSet());
                    if (listeTabouB == null)
                        listeTabouB = new ArrayList<>();

                    if (listeTabouB.size() == tailleListeTabou)
                        listeTabouB.remove(0);

                    int[] tab = {interdiction, interdiction2};

                    listeTabouB.add(tab);
                }
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
