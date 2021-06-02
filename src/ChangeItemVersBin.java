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
                if (dataset.getListBins().get(i).getListItem().isEmpty()) {
                    dataset.removeBin(dataset.getListBins().get(i));
                }
            }
        }
        if (!binDestination.addItem(item)) {
            if (dataset.getListBins().size() <= tempo) {
                Bin bin = new Bin(dataset.getTailleBin());
                bin.addItem(item);
                dataset.addBin(bin);
            } else
                dataset.getListBins().get(tempo).addItem(item);
            return false;
        }
        return true;
    }
}
