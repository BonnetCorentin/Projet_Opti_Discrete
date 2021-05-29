import java.util.List;

public class BinToBin {

    void binToBin(DataSet dataset, Items item1, Items item2) {

        List<Bin> binsList = dataset.getListBins();

        Boolean stop = false;
        int i = 0;

        do {
            if (binsList.get(i).itemInBin(item1)) {
                int j = 0;
                do {
                    if (binsList.get(j).itemInBin(item2)) {
                        List<Items> listItems = binsList.get(i).getListItem();
                        List<Items> listItems2 = binsList.get(j).getListItem();

                        stop = true;
                    }

                } while (j < binsList.size() || !stop);
            }

        } while (!stop || i < binsList.size());


    }

}
