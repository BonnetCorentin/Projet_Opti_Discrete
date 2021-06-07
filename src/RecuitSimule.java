import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RecuitSimule {

    DataSet recuitSimule(DataSet dataSet, double temperature, char voisinageChoix) {
        ArrayList<Double> arrayListTemperature = new ArrayList<>();
        arrayListTemperature.add(temperature);
        DataSet solutionMax = new DataSet(dataSet);
        ArrayList<DataSet> dataSetArrayList = new ArrayList<>();
        dataSetArrayList.add(dataSet);
        double fMax = new FonctionObjective().fonctionObjective(dataSet);
        boolean passe;
        ChangeItemVersBin c = new ChangeItemVersBin();
        BinToBin b = new BinToBin();
        DataSet voisinRandom = new DataSet();
        int i = 0;
        double u = 0.01;
        for (int k = 0; k < 100; k++) { //Iteration
            for (int j = 0; j < 10; j++) {
                switch (voisinageChoix) {
                    case 'A':
                        int compteur = 0;
                        do {
                            int randomBin = ThreadLocalRandom.current().nextInt(0, dataSet.getListBins().size());
                            Bin binDestination = dataSet.getListBins().get(randomBin);
                            int randomItem = ThreadLocalRandom.current().nextInt(0, dataSet.getNbItems());
                            Items itemADeplacer = dataSet.getListItems().get(randomItem);
                            boolean possible = c.changeItemToBin(dataSet, itemADeplacer, binDestination);
                            if (possible == true) {
                                passe = true;
                                voisinRandom = new DataSet(dataSet);
                            } else {
                                passe = false;
                            }
                            if (compteur > 10000)
                                return solutionMax;
                            compteur++;
                        } while (passe == false);
                        break;
                    case 'B':
                        do {
                            int randomItem = ThreadLocalRandom.current().nextInt(0, dataSet.getNbItems());
                            Items itemADeplacer = dataSet.getListItems().get(randomItem);
                            int randomItem2 = ThreadLocalRandom.current().nextInt(0, dataSet.getNbItems());
                            Items itemADeplacer2 = dataSet.getListItems().get(randomItem2);

                            boolean possible = b.binToBin(dataSet, itemADeplacer, itemADeplacer2);
                            if (possible == true) {
                                passe = true;
                                voisinRandom = new DataSet(dataSet);
                            } else {
                                passe = false;
                            }
                        } while (passe == false);
                        break;
                    default:
                        System.out.println("Veuillez choisir A ou B");
                        break;
                }

                double variationF = new FonctionObjective().fonctionObjective(voisinRandom) - fMax;

                if (variationF > 0) {
                    dataSetArrayList.add(voisinRandom);

                    if (new FonctionObjective().fonctionObjective(dataSetArrayList.get(i + 1)) > fMax) {
                        solutionMax = new DataSet(dataSetArrayList.get(i + 1));
                        fMax = new FonctionObjective().fonctionObjective(dataSetArrayList.get(i + 1));
                    }
                } else {
                    double p = ThreadLocalRandom.current().nextDouble(0, 1.01);

                    if (p <= Math.exp(-variationF / arrayListTemperature.get(k)))
                        dataSetArrayList.add(voisinRandom);
                    else
                        dataSetArrayList.add(dataSetArrayList.get(i));
                }
                i++;

            }
            arrayListTemperature.add(u * arrayListTemperature.get(k));
        }
        return solutionMax;
    }
}
