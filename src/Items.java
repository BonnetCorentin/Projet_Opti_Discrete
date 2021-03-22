public class Items {
    private int tailleItem;
    private int idItem;

    Items (int tailleItem, int idItem){
        this.tailleItem = tailleItem;
        this.idItem = idItem;
    }

    int getIdItem(){
        return this.idItem;
    }

    int getTailleItem(){
        return this.tailleItem;
    }
}
