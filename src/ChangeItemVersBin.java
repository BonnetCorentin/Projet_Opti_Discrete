public class ChangeItemVersBin {

    public boolean changeItemToBin(DataSet dataset, Items item, Bin binDestination) {
        int tempo = 0;

        if (binDestination.itemInBin(item) == true) {
            System.out.println("Impossible, l'item se trouve déjà dans ce bin");
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
        if (binDestination.getTailleBinRestant() >= item.getTailleItem()) {
            binDestination.addItem(item);       //On ajoute l'item dans le bin de destination
            return true;
        } else {
            dataset.getListBins().get(tempo).addItem(item); //Si place insuffisante, alors on remet l'item dans son bin d'origine
            return false;
        }
    }
}
