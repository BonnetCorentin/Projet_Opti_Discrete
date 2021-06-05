import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Voisinage {
    public ArrayList<Dataset_item> voisinageA(DataSet sol_Initiale) {
        ArrayList<Dataset_item> listeVoisin = new ArrayList<>();
        ChangeItemVersBin c = new ChangeItemVersBin();

        for (int i = 0; i < sol_Initiale.getListItems().size(); i++) {
            DataSet dataVoisin = new DataSet(sol_Initiale);
            Items itemAdeplacer = dataVoisin.getListItems().get(i);

            for (int j = 0; j < sol_Initiale.getListBins().size(); j++) {
                Bin binDestination = dataVoisin.getListBins().get(j);

                boolean tempo = c.changeItemToBin(dataVoisin, itemAdeplacer, binDestination);

                if (tempo == true) {
                    Dataset_item temp = new Dataset_item(new DataSet(dataVoisin), new Items(itemAdeplacer));
                    listeVoisin.add(temp);
                }
                dataVoisin = new DataSet(sol_Initiale);
                itemAdeplacer = dataVoisin.getListItems().get(i);
            }
        }
        return listeVoisin;
    }


    public ArrayList<DatasetItemItem> voisinageB(DataSet sol_Initiale) {
        ArrayList<DatasetItemItem> voisin = new ArrayList<>();
        BinToBin b = new BinToBin();

        for (int i = 0; i < sol_Initiale.getListItems().size(); i++) {

            DataSet dataVoisin = new DataSet(sol_Initiale);
            Items item1 = dataVoisin.getListItems().get(i);

            for (int j = 1; j < sol_Initiale.getListItems().size(); j++) {

                Items item2 = dataVoisin.getListItems().get(j);
                boolean tempo = b.binToBin(dataVoisin, item1, item2);

                if (tempo == true) {
                    if (!voisin.contains(dataVoisin)) {
                        DatasetItemItem d = new DatasetItemItem(dataVoisin, item1, item2);
                        voisin.add(d);
                    }
                    dataVoisin = new DataSet(sol_Initiale);
                    item1 = dataVoisin.getListItems().get(i);
                }
            }
        }

        return voisin;
    }
}
