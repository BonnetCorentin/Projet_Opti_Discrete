public class Dataset_item {
    private DataSet dataSet;
    private Items item;

    public Dataset_item(DataSet d, Items i){
        this.dataSet=d;
        this.item=i;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public Items getItem() {
        return item;
    }

    public String toString(){
        return "Dataset : "+this.dataSet.toString() + "\n" + "Item échangé : "+this.item+"\n";
    }
}
