import java.util.*;


public class DataSet {
    private List<Items> listItems;
    private int tailleBin;
    private int nbItems;
    private List<Bin> listBins;

    public DataSet() {
    }

    public DataSet(DataSet dataSet) {
        this.listBins = new ArrayList<>();
        this.listItems = new ArrayList<>();

        this.tailleBin = dataSet.getTailleBin();
        this.nbItems = dataSet.getNbItems();

        for (Bin bin : dataSet.getListBins()) {
            this.listBins.add(new Bin(bin));
        }

        for (Bin bin : this.getListBins()) {
            for (Items items : bin.getListItem()) {
                this.listItems.add(items);
            }
        }
    }

    public int getTailleBin() {
        return this.tailleBin;
    }

    public int getNbItems() {
        return this.nbItems;
    }

    public void reinitialisationBin() {
        this.listBins = null;
    }

    public List<Items> getListItems() {
        return this.listItems;
    }

    public List<Bin> getListBins() {
        return this.listBins;
    }

    public void addItems(Items items) {
        if (this.listItems == null) {
            this.listItems = new ArrayList<>();
        }
        this.listItems.add(items);
    }

    public void addBin(Bin bin) {
        if (this.listBins == null) {
            this.listBins = new ArrayList<>();
        }
        this.listBins.add(bin);
    }

    public void setTailleBin(int tailleBin) {
        this.tailleBin = tailleBin;
    }

    public void setNbItems(int nbItems) {
        this.nbItems = nbItems;
    }

    public double[] getWeights() {
        double[] weights = new double[this.listItems.size()];

        for (int i = 0; i < weights.length; i++)
            weights[i] = listItems.get(i).getTailleItem();
        return weights;
    }

    @Override
    public String toString() {

        String toString = "";

        for (int i = 0; i < this.listBins.size(); i++) {
            toString += "\nBin n° " + i + " avec items: " + listBins.get(i).toString() + "\n";
        }
        return toString;
    }

    public int getBorneInf() {
        int i = 0;
        double somme = 0;
        while (nbItems > i) {
            double poids = listItems.get(i).getTailleItem();
            somme += poids / tailleBin;
            i++;
        }
        return (int) somme + 1;
    }

    public boolean removeBin(Bin bin) {
        return this.listBins.remove(bin);
    }
}
