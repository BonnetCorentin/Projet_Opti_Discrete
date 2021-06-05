public class DatasetItemItem {
    private DataSet dataSet;
    private Items item1;
    private Items item2;

    public DatasetItemItem(DataSet d, Items i, Items i2) {
        this.dataSet = d;
        this.item1 = i;
        this.item2 = i2;
    }

    public Items getItem1() {
        return item1;
    }

    public Items getItem2() {
        return item2;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public String toString() {
        return this.dataSet.toString();
    }

}
