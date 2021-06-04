import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RecuitSimule {

    DataSet recuitSimule(DataSet dataSet, double temperature, char voisinageChoix) {

        ArrayList<Dataset_item> voisinage = new ArrayList<>();

        ArrayList<Double> arrayListTemperature = new ArrayList<>();

        arrayListTemperature.add(temperature);

        DataSet solutionMax = new DataSet(dataSet);

        ArrayList<DataSet> dataSetArrayList = new ArrayList<>();

        dataSetArrayList.add(dataSet);

        double fMax = new FonctionObjective().fonctionObjective(dataSet);

        System.out.println("FMax = " + fMax);

        int i = 0;

        double u = 0.01;

        int n1 = (int) (Math.log(Math.log(0.8) / Math.log(0.01)) / Math.log(u));

        System.out.println("n1 = " + n1);
        for (int k = 0; k < 100; k++) {
            for (int j = 0; j < 10; j++) {
                switch (voisinageChoix) {
                    case 'A':
                        voisinage = new Voisinage().voisinageA(dataSetArrayList.get(i));
                        break;
                    case 'B':
//                        voisinage = new Voisinage().voisinageB(dataSetArrayList.get(i));
                        break;
                    default:
                        System.out.println("Veuillez choisir A ou B");
                        break;
                }

                if (voisinage.size() == 0) {
                    return dataSetArrayList.get(i);
                }

                int random = ThreadLocalRandom.current().nextInt(0, voisinage.size());

                DataSet voisinRandom = new DataSet(voisinage.get(random).getDataSet());

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
