import javax.xml.crypto.Data;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Tabou {

    public ArrayList<Bin> methodeTabou(DataSet sol_initiale,int maxIter, String type_voisinage){
        DataSet xmin = sol_initiale;
        FonctionObjective f = new FonctionObjective();
        double fx0 = f.fonctionObjective(sol_initiale);
        ArrayList<DataSet> listeVoisinInitial = new ArrayList<>();
        DataSet voisin = new DataSet();
        ArrayList<DataSet> listeTabou = new ArrayList<>();
        ArrayList<Double> fitness = new ArrayList<>();
        ArrayList<Double> fitnessParIteration = new ArrayList<>();
        double meilleurFitness=fx0;

        if(type_voisinage == "A"){ //Voisinage par déplacement d'item
            Voisinage vA = new Voisinage();
            listeVoisinInitial = vA.voisinageA(sol_initiale);
//            System.out.println("Voisin début "+listeVoisinInitial);

            for(int j=0;j<maxIter;j++){

                for(int i=0;i<listeVoisinInitial.size();i++){  //Pour tout les voisins, on calcule la fitness
                    fitness.add(f.fonctionObjective(listeVoisinInitial.get(i)));
//                    System.out.println("Fitness voisin n°"+i+" : "+fitness.get(i));
                }
                for(int z=0;z<listeVoisinInitial.size();z++){
                    if(meilleurFitness<fitness.get(z)){
                        voisin = listeVoisinInitial.get(z);
                        meilleurFitness=fitness.get(z);
                    }
                }
                if(meilleurFitness<fx0){
                    listeTabou.add(voisin);
                }
                fitnessParIteration.add(meilleurFitness);
                listeVoisinInitial.clear();
                listeVoisinInitial=vA.voisinageA(voisin);
                fitness.clear();
//                System.out.println("Voisin fin "+listeVoisinInitial);
//                System.out.println(fitness);
//                System.out.println(fitnessParIteration);
            }
        }

        return null;
    }
}
