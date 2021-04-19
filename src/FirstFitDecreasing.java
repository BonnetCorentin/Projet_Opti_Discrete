import java.util.*;

public class FirstFitDecreasing {
    public int firstFitDecreasing(DataSet dataset) {
        Collections.sort(dataset.getListItems(), new ItemComparator());
        int tailleBin = dataset.getTailleBin();
        int tailleBinTempo = tailleBin;
        int nombreBin = 1;
        for (int i = 0; i < dataset.getListItems().size(); i++) {
            if (dataset.getListItems().get(i).getTailleItem() >= tailleBinTempo) {
                nombreBin++;
                tailleBinTempo = tailleBin;
            }
            tailleBinTempo = tailleBinTempo - dataset.getListItems().get(i).getTailleItem();
        }
        return nombreBin;
    }
}