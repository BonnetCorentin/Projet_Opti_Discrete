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

    public boolean changeItemToBin(DataSet dataset, Items item, Bin binDestination){

        for(int i=0; i<dataset.getListBins().size(); i++){
            if(dataset.getListBins().get(i).getListItem().contains(item)==true){
                dataset.getListBins().get(i).removeItem(item);
            }
        }
        if(binDestination.getTailleBinRestant()>=item.getTailleItem()){
            binDestination.addItem(item);
            return true;
        }else{
            return false;
        }
    }
}