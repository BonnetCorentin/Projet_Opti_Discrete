public class Tabou {
    public int methodeTabou(DataSet sol_Initiale){
        double fInitiale = fonctionObjective(sol_Initiale);
        
        int i = 0;

        return 0;
    }

    public double fonctionObjective(DataSet dataSet){
        double sommeParBin=0;
        double sommeFinale=0;
        int i,j=0;
        for(i=0; i<dataSet.getListBins().size();i++){
            sommeParBin=0;
            for(j=0; j<dataSet.getListBins().get(i).getListItem().size();j++){
                sommeParBin += dataSet.getListBins().get(i).getListItem().get(j).getTailleItem();
            }
            System.out.println(sommeParBin);
            sommeFinale+=Math.pow(sommeParBin,2);
        }
        return sommeFinale;
    }
}
