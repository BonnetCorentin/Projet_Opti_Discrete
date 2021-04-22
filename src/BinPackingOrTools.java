public class BinPackingOrTools {
    public final double[] weights;
    public final int numItems;
    public final int numBins;
    public final int binCapacity;

    public BinPackingOrTools(DataSet dataset) {
        weights = dataset.getWeights();
        numItems = dataset.getNbItems();
        numBins = dataset.getNbItems();
        binCapacity = dataset.getTailleBin();
    }
}
