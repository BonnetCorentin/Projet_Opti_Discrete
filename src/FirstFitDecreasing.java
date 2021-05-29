import javax.xml.crypto.Data;
import java.util.ArrayList;

public class FirstFitDecreasing {
    public int firstFitDecreasing(DataSet dataset) {
        int tailleBin = dataset.getTailleBin();
        int nombreBin = 1;
        Bin bin = new Bin(tailleBin);

        for (int i = 0; i < dataset.getListItems().size(); i++) {
            Boolean bool = bin.addItem(dataset.getListItems().get(i));
            if (!bool) {
                dataset.addBin(bin);
                nombreBin++;
                bin = new Bin(tailleBin);
                bin.addItem(dataset.getListItems().get(i));
            }
        }
        return nombreBin;
    }
}