import java.util.List;

public class BinToBin {

    boolean binToBin(DataSet dataset, Items item1, Items item2) {

        List<Bin> binsList = dataset.getListBins();
        int i = 0;
        do {
            if (binsList.get(i).itemInBin(item1)) {
                int j = 0;
                do {
                    if (binsList.get(j).itemInBin(item2)) {
                        binsList.get(i).removeItem(item1);
                        binsList.get(j).removeItem(item2);
                        binsList.get(j).addItem(item1);
                        binsList.get(i).addItem(item2);
                        return true;
                    }
                    j++;

                } while (j < binsList.size());
            }
            i++;
        } while (i < binsList.size());
        return false;
    }
}
