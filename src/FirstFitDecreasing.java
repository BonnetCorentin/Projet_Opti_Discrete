import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;

public class FirstFitDecreasing {
    public int firstFitDecreasing(DataSet dataset) {
        int tailleBin = dataset.getTailleBin();
        int nombreBin = 1;
        Bin bin = new Bin(tailleBin);

        dataset.reinitialisationBin();

        Collections.sort(dataset.getListItems(), new ItemComparator());

        for (int i = 0; i < dataset.getListItems().size(); i++) {
            Boolean bool = bin.addItem(dataset.getListItems().get(i));
            if (!bool) {
                dataset.addBin(bin);
                nombreBin++;
                bin = new Bin(tailleBin);
                bin.addItem(dataset.getListItems().get(i));
            }
        }
        dataset.addBin(bin);
        return nombreBin;
    }


    public int firstFitAleatoire(DataSet dataset) {

        int tailleBin = dataset.getTailleBin();
        int nombreBin = 1;
        Bin bin = new Bin(tailleBin);

        dataset.reinitialisationBin();

        Collections.shuffle(dataset.getListItems());

        for (int i = 0; i < dataset.getListItems().size(); i++) {
            Boolean bool = bin.addItem(dataset.getListItems().get(i));
            if (!bool) {
                dataset.addBin(bin);
                nombreBin++;
                bin = new Bin(tailleBin);
                bin.addItem(dataset.getListItems().get(i));
            }
        }
        dataset.addBin(bin);
        return nombreBin;

    }
}