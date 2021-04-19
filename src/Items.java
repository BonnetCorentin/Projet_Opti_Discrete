public class Items {
    private int tailleItem;
    private int idItem;

    Items (int tailleItem, int idItem){
        this.tailleItem = tailleItem;
        this.idItem = idItem;
    }

    public int getIdItem(){
        return this.idItem;
    }

    public int getTailleItem(){
        return this.tailleItem;
    }

    public String toString(){
        return "\n Id: "+ idItem+" taille : "+ tailleItem;
    }
}
