import java.util.*;

public class DataSet {
    private List<Items> listItems;
    private int tailleBin;
    private int nbItems;

    public int getTailleBin() {
        return this.tailleBin;
    }

    public int getNbItems(){
        return this.nbItems;
    }

    public List<Items>getListItems(){
        return this.listItems;
    }

    public void addItems(Items items){
        if(this.listItems==null){
            this.listItems = new ArrayList<Items>();
        }else{
            this.listItems.add(items);
        }
    }

    public void setTailleBin (int tailleBin){
        this.tailleBin = tailleBin;
    }

    public void setNbItems (int nbItems){
        this.nbItems = nbItems;
    }

}
