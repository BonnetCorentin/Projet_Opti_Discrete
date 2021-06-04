import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Tabou {

    public DataSet methodeTabou(DataSet sol_initiale,int maxIter, char type_voisinage, int tailleListeTabou) {
        ArrayList<Dataset_item> voisinage = new ArrayList<>();
        ArrayList<Double> fitnessVoisin = new ArrayList<Double>();
        int[] listeTabou = new int[tailleListeTabou];
        int indice = 0;

        for (int i = 0; i < maxIter; i++) {
            double fitnessVoisinageInitial = new FonctionObjective().fonctionObjective(sol_initiale);
            double maxF = 0;
            switch (type_voisinage) {
                case 'A':
                    voisinage = new Voisinage().voisinageA(sol_initiale);
                    break;
                case 'B':
            //        voisinage = new Voisinage().voisinageB(sol_initiale);
                    break;
                default:
                    System.out.println("Veuillez choisir A ou B");
                    break;
            }

            for (int j = 0; j < voisinage.size(); j++) {
                double temp = new FonctionObjective().fonctionObjective(voisinage.get(j).getDataSet());
                if (temp > maxF) {
                    maxF = temp;
                    indice = j;
                }
                fitnessVoisin.add(temp);
            }

            System.out.println("Fitness voisin "+fitnessVoisin.toString());

            int idTabou = voisinage.get(indice).getItem().getIdItem();
            System.out.println("-------Liste tabou ----------------\n" +listeTabou[0]);
            System.out.println("Id tabou : \n"+idTabou);

            for(int z=0;z<listeTabou.length;z++){
                System.out.println(listeTabou[z]);
            }
            if(IntStream.of(listeTabou).anyMatch(x->x==idTabou)==false){
                if(maxF>fitnessVoisinageInitial){
                    fitnessVoisinageInitial=maxF;
                    sol_initiale= new DataSet(voisinage.get(indice).getDataSet());
                }else{
                    int interdiction = voisinage.get(indice).getItem().getIdItem();
                    sol_initiale= new DataSet(voisinage.get(indice).getDataSet());
                    listeTabou[0]=interdiction;
                }
            }
        }
        return sol_initiale;
    }
}
