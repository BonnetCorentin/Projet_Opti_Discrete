import javax.xml.crypto.Data;
import java.util.ArrayList;

public class FirstFitDecreasing {
    public int firstFitDecreasing(DataSet dataset) {
        int tailleBin = dataset.getTailleBin();
        int nombreBin = 1;
        Bin bin = new Bin(tailleBin);
        for (int i = 0; i < dataset.getListItems().size(); i++) {
            Boolean bool = bin.addItem(dataset.getListItems().get(i));
            if (!bool) {
                dataset.addBin(bin);
                nombreBin++;
                bin = new Bin(tailleBin);
            }
        }
        return nombreBin;
    }

    public int changeItemToBin(DataSet dataset){

        int tailleBin = dataset.getTailleBin();
        int nombreBin = 1;
        int tempo=0;
        int nombreBinApresChangement = 0;
        boolean stop = false;
        ArrayList<Bin> listBins = new ArrayList<>();
        Bin bin = new Bin(tailleBin);
        for (int i = 0; i < dataset.getListItems().size(); i++) {
            Boolean bool = bin.addItem(dataset.getListItems().get(i));
            if (!bool) {
                dataset.addBin(bin);
                listBins.add(bin);
                nombreBin++;
                bin = new Bin(tailleBin);
            }
        }
        for(int j=0; j<nombreBin; j++){
            if(stop==false){
                if(listBins.get(j).getListItem().get(0).getTailleItem() <= listBins.get(j+1).getTailleBinRestant()){
                    if(listBins.get(j).getListItem()==null){
                        tempo++;
                    }
                    listBins.get(j+1).addItem(listBins.get(j).getListItem().get(0));
                    listBins.get(j).removeItem(listBins.get(j).getListItem().get(0));
                    System.out.println(listBins.get(j).getTailleBinRestant());
                    System.out.println("Item déplacé: "+ listBins.get(j).getListItem().get(0).getTailleItem());
                    System.out.println("Vers bin: "+ listBins.get(j+1).getListItem());
                    stop = true;
                }else{
                    stop=false;
                }
            }
        }
        System.out.println(tempo);
        nombreBinApresChangement = nombreBin - tempo;
        return nombreBinApresChangement;
    }
}