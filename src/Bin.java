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

    public boolean contains(Items item){
        if(this.getListItem().contains(item)){
            return true;
        }else{
            return false;
        }
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
        } else if (!verificationTaille(item)) {
            return false;
        }
        this.listItem.add(item);
        return true;
    }

    public void removeItem(Items item){
        this.listItem.remove(item);
        this.tailleBinRestant+=item.getTailleItem();
    }

    public String toString(){
        String s="";
        for(int i=0; i<listItem.size();i++){
            s += listItem.get(i).toString()+" , ";
        }
        return s;
    }
}
