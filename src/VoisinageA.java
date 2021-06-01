import java.util.ArrayList;

public class VoisinageA {
    public void voisinageA(DataSet sol_Initiale) {
        ArrayList<DataSet> voisin = new ArrayList<>();
        int nbvoisin = (sol_Initiale.getListBins().size() - 1) * sol_Initiale.getNbItems();
        int blblablbla = 0;
        System.out.println("Nb voisins = " + nbvoisin);

        ChangeItemVersBin c = new ChangeItemVersBin();
        for (int i = 0; i < sol_Initiale.getListItems().size(); i++) {
            DataSet dataVoisin = new DataSet(sol_Initiale);
            Items itemAdeplacer = dataVoisin.getListItems().get(i);
            for (int j = 0; j < sol_Initiale.getListBins().size(); j++) {
                dataVoisin = new DataSet(sol_Initiale);
                Bin binDestination = dataVoisin.getListBins().get(j);
                boolean tempo = c.changeItemToBin(dataVoisin, itemAdeplacer, binDestination);
                if (tempo == true) {
                    FonctionObjective ft = new FonctionObjective();
                    voisin.add(dataVoisin);
//                    System.out.println(ft.fonctionObjective(dataVoisin));
                }
            }
        }

        if (voisin.get(0).equals(voisin.get(1))) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        System.out.println(voisin.size());
    }

    public void voisinageB(DataSet sol_Initiale){

    }
}
