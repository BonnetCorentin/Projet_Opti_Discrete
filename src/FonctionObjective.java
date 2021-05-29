public class FonctionObjective {
    public double fonctionObjective(DataSet dataSet){
        double sommeParBin=0;
        double sommeFinale=0;
        int i,j=0;

        for(i=0; i<dataSet.getListBins().size();i++){
            sommeParBin=0;
            for(j=0; j<dataSet.getListBins().get(i).getListItem().size();j++){
                sommeParBin += dataSet.getListBins().get(i).getListItem().get(j).getTailleItem();
            }
            sommeFinale+=Math.pow(sommeParBin,2);
        }
        return sommeFinale;
    }
}
