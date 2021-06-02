import java.util.ArrayList;
import java.util.List;


public class Voisinage {
    public ArrayList<DataSet> voisinageA(DataSet sol_Initiale) {

        ArrayList<DataSet> voisin = new ArrayList<>();
        ChangeItemVersBin c = new ChangeItemVersBin();

        for (int i = 0; i < sol_Initiale.getListItems().size(); i++) {
            DataSet dataVoisin = new DataSet(sol_Initiale);
            Items itemAdeplacer = dataVoisin.getListItems().get(i);
            for (int j = 0; j < sol_Initiale.getListBins().size(); j++) {
                Bin binDestination = dataVoisin.getListBins().get(j);
                System.out.println("---------------------- Nouveau voisin");
                System.out.println("Bin avant ajout de l'item" + binDestination);
                System.out.println("Item à déplacer: " + itemAdeplacer);
                boolean tempo = c.changeItemToBin(dataVoisin, itemAdeplacer, binDestination);
                if (tempo == true) {
                    System.out.println("Bin destination: " + binDestination);
                    System.out.println("Voisin: " + dataVoisin);
                    voisin.add(dataVoisin);
                    dataVoisin = new DataSet(sol_Initiale);
                    itemAdeplacer = dataVoisin.getListItems().get(i);
//                    FonctionObjective ft = new FonctionObjective();
//                    System.out.println(ft.fonctionObjective(dataVoisin));
                }
            }
        }
        System.out.println("Voisin A de la solution initiale: " + voisin.size());
        System.out.println(voisin.toString());
        return voisin;
    }


    public ArrayList<DataSet> voisinageB(DataSet sol_Initiale) {
        ArrayList<DataSet> voisin = new ArrayList<>();
        BinToBin b = new BinToBin();

        for (int i = 0; i < sol_Initiale.getListItems().size(); i++) {

            DataSet dataVoisin = new DataSet(sol_Initiale);
            Items item1 = dataVoisin.getListItems().get(i);

            for (int j = 1; j < sol_Initiale.getListItems().size(); j++) {
                System.out.print("Item 1 à échanger : ");
                System.out.println(item1);
                System.out.println("---");
                System.out.print("Item 2 à échanger");

                Items item2 = dataVoisin.getListItems().get(j);
                System.out.println(item2);
                boolean tempo = b.binToBin(dataVoisin, item1, item2);

                if (tempo == true) {
                    System.out.println("----------------------Voisin :");
                    System.out.println(dataVoisin);
                    System.out.println("----------------------Fin Voisin :");
                    voisin.add(dataVoisin);
//                    FonctionObjective ft = new FonctionObjective();
//                    System.out.println(ft.fonctionObjective(dataVoisin));
                    dataVoisin = new DataSet(sol_Initiale);
                    item1 = dataVoisin.getListItems().get(i);
                } else
                    System.out.println("Impossible d'échanger les deux items");
            }
        }

        /*System.out.println("Voisin B de la solution initiale: " + voisin.size());
        System.out.println(voisin);*/


        return voisin;
    }
}
