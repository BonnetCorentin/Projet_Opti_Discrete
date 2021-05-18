import java.util.ArrayList;

public class Bin {

    private ArrayList<Items> listItem;
    private int tailleBinRestant;

    Bin(int tailleBin) {
        this.tailleBinRestant = tailleBin;
    }

    private Boolean verificationTaille(Items item) {
        this.tailleBinRestant = this.tailleBinRestant - item.getTailleItem();
        return (tailleBinRestant < 0);
    }

    public ArrayList<Items> getListItem() {
        return this.listItem;
    }

    public int getSize(){
        return this.listItem.size();
    }

    public int getTailleBinRestant() {
        return this.tailleBinRestant;
    }

    public Boolean addItem(Items item) {
        if (this.listItem == null) {
            this.listItem = new ArrayList<Items>();
            this.listItem.add(item);
        } else if (!verificationTaille(item)) {
            return false;
        }
        this.listItem.add(item); //Pq on add deux fois ???
        return true;
    }

    public void removeItem(Items item){
        this.listItem.remove(item);
    }
}
