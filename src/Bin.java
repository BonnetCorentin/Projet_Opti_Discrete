import java.util.ArrayList;

public class Bin {

    private ArrayList<Items> listItem;
    private int tailleBinRestant;

    Bin(Bin bin) {
        this.tailleBinRestant = bin.getTailleBinRestant();

        for (Items items : bin.getListItem()) {
            this.listItem.add(new Items(items));
        }
    }

    Bin(int tailleBin) {
        this.tailleBinRestant = tailleBin;
    }

    private Boolean verificationTaille(Items item) {

        this.tailleBinRestant = this.tailleBinRestant - item.getTailleItem();

        boolean tailleRestant = tailleBinRestant >= 0;
        return (tailleRestant);
    }

    public ArrayList<Items> getListItem() {
        return this.listItem;
    }


    public Boolean itemInBin(Items item) {
        return listItem.contains(item);
    }


    public int getSize() {
        return this.listItem.size();
    }

    public int getTailleBinRestant() {
        return this.tailleBinRestant;
    }

    public Boolean addItem(Items item) {
        if (this.listItem == null) {
            this.listItem = new ArrayList<Items>();
            this.tailleBinRestant = this.tailleBinRestant - item.getTailleItem();
        } else if (!verificationTaille(item)) {
            this.tailleBinRestant = this.tailleBinRestant + item.getTailleItem();
            return false;
        }
        this.listItem.add(item);
        return true;
    }

    public void removeItem(Items item) {
        this.listItem.remove(item);
        this.tailleBinRestant += item.getTailleItem();
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < listItem.size(); i++) {
            s += listItem.get(i).toString();
        }
        return s;
    }
}
