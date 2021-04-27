import java.util.*;


public class DataSet {
    private List<Items> listItems;
    private int tailleBin;
    private int nbItems;
    private List<Bin> listBins;

    public int getTailleBin() {
        return this.tailleBin;
    }

    public int getNbItems() {
        return this.nbItems;
    }

    public List<Items> getListItems() {
        return this.listItems;
    }

    public List<Bin> getListBins() {
        return this.listBins;
    }

    public void addItems(Items items) {
        if (this.listItems == null) {
            this.listItems = new ArrayList<Items>();
        }
        this.listItems.add(items);
    }

    public void addBin(Bin bin) {
        if (this.listBins == null) {
            this.listBins = new ArrayList<Bin>();
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
        return "Nombre d'items: " + nbItems + " et taille: " + tailleBin + "\n Liste des items: " + listItems.toString();
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
}
