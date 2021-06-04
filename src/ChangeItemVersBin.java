public class ChangeItemVersBin {

    public boolean changeItemToBin(DataSet dataset, Items item, Bin binDestination) {
        int tempo = 0;

        if (binDestination.itemInBin(item) == true) {
            return false;
        }

        for (int i = 0; i < dataset.getListBins().size(); i++) {
            if (dataset.getListBins().get(i).getListItem().contains(item) == true) {
                tempo = i;
                dataset.getListBins().get(i).removeItem(item);
            }
        }

        if (!binDestination.addItem(item)) {
            dataset.getListBins().get(tempo).addItem(item);
            return false;
        }else{
            if (dataset.getListBins().get(tempo).getListItem().isEmpty()) {
                dataset.removeBin(dataset.getListBins().get(tempo));
            }
        }
        return true;
    }
}