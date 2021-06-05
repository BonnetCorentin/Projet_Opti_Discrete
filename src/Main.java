import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) {

        ListerRepertoire l = new ListerRepertoire();
        String[] listeFichier = l.ListerFichier("./data/");

        //Question 1: Borne inférieure
//        borneInferieur(listeFichier);
        System.out.println();

        //Question 2: First Fit Decreasing
//        firstFitDecreasing(listeFichier);
        System.out.println();

        //Question 3.a: Solution optimale de « binpack1d_00.txt »
//        solutionOpti_Binpack1D_00();
        System.out.println();

        //Question 3.b: Limite d'exécution
        //Faire des tests avec plusieurs jeux de données et voir quand le temps d'éxécution
        //devient trop long (exemple: plus de 5min d'éxécution).
        //Faire des moyennes de temps d'éxécution entre les datas...


        //Question 4.a: Un item par bin
        //Etant donné que l'on place un item par bin, on peut conclure aisément qu'il y aura
        //autant de nombre de bin que d'item.
//        unItemParBin(listeFichier);
        System.out.println();

        //Question 4.b: firstFitAleatoire
//        firstFitAleatoire(listeFichier);
        System.out.println();

        //Question 5.a: Deplacer un item d'un bin vers un autre
        //A partir de la méthode First Fit Decreasing (question 2)
        //Exemple avec jeu de data 00.txt
        //On déplace l'item avec Id 0 dans bin n°8
//        voisinageA("binpack1d_00.txt");
        System.out.println();

        //Question 5.b: Echanger deux items de deux bins  différents
        //A partir de la méthode First Fit Decreasing (question 2)
        //On essaye de déplacer l'item id 0 et l'item id 3
//        voisinageB("binpack1d_00.txt");
        System.out.println();

        //Question 6: Recuit simulé
        //Pour changer les autres paramètres, aller dans la classe RecuitSimulé
//        recuitSimulé(listeFichier,'A',3);
        System.out.println();

        //Question 7 : Tabu search
        tabuSearch(listeFichier,'B',100);
    }
    static void borneInferieur(String[] listeFichier){
        System.out.println("Question 1");
        System.out.println("Borne inférieur :");
        for (int i = 0; i < listeFichier.length - 1; i++) {
            long chrono = startChrono();
            String fileDataName = listeFichier[i];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            int borne_inf = dataset.getBorneInf();
            System.out.println("Borne inférieur de " + listeFichier[i] + ": " + borne_inf);
            stopChrono(chrono);
        }
    }
    static void firstFitDecreasing(String[] listeFichier){
        System.out.println("Question 2");
        System.out.println("First Fit Decreasing :");
        FirstFitDecreasing ft = new FirstFitDecreasing();
        for (int j = 0; j < listeFichier.length - 1; j++) {
            long chrono = startChrono();
            String fileDataName = listeFichier[j];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            System.out.println("Nombre de bin à utiliser avec la méthode firstFitDecreasing pour " + listeFichier[j] + ": " + ft.firstFitDecreasing(dataset));
            stopChrono(chrono);
        }
    }
    static void solutionOpti_Binpack1D_00(){
        long chrono = startChrono();
        System.out.println("Question 3.a");
        System.out.println("Solution optimale du problème:");
        Loader.loadNativeLibraries();
        ChargementData datas = new ChargementData();
        DataSet dataset = datas.loadFile("./data/binpack1d_00.txt");
        System.out.println(solutionOptimale(dataset));
        stopChrono(chrono);
    }
    static void unItemParBin(String[] listeFichier){
        System.out.println("Question 4.a");
        for (int j = 0; j < listeFichier.length - 1; j++) {
            long chrono = startChrono();
            String fileDataName = listeFichier[j];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            System.out.println("Nombre de bin à utiliser avec la méthode 1 item par bin pour le fichier " + listeFichier[j] + " :" + dataset.getNbItems());
            stopChrono(chrono);
        }
    }
    static void firstFitAleatoire(String[] listeFichier){
        System.out.println("Question 4.b");
        FirstFitDecreasing ft = new FirstFitDecreasing();
        for (int j = 0; j < listeFichier.length - 1; j++) {
            long chrono = startChrono();
            String fileDataName = listeFichier[j];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            System.out.println("Nombre de bin à utiliser avec la méthode firstFitAleatoire pour " + listeFichier[j] + ": " + ft.firstFitAleatoire(dataset));
            stopChrono(chrono);
        }
    }
    static void voisinageA(String fileDataName){
        long chrono = startChrono();
        System.out.println("Question 5.a");
        fileDataName = "binpack1d_00.txt";
        ChargementData data = new ChargementData();
        DataSet dataset = data.loadFile("./data/" + fileDataName);
        System.out.println("Dataset de base :\n"+dataset.toString());
        System.out.print("f(x) de la solution de base :");
        fonctionObjective(dataset);
        System.out.println();
        Items itemaDeplacer = dataset.getListItems().get(0);
        Bin binDestination = dataset.getListBins().get(8);
        ChangeItemVersBin c = new ChangeItemVersBin();
        c.changeItemToBin(dataset, itemaDeplacer, binDestination);
        System.out.println("Dataset final :\n"+dataset.toString());
        stopChrono(chrono);
        System.out.print("f(x) de la solution finale :");
        fonctionObjective(dataset);
    }
    static void voisinageB(String fileDataName){
        long chrono = startChrono();
        System.out.println("Question 5.b");
        ChargementData data5 = new ChargementData();
        DataSet dataset = data5.loadFile("./data/" + fileDataName);
        System.out.println("Dataset de base :\n"+dataset.toString());
        System.out.print("f(x) de la solution de base ");
        fonctionObjective(dataset);
        System.out.println();
        Items itemaDeplacer2 = dataset.getListItems().get(0);
        Items itemaDeplacer3 = dataset.getListItems().get(3);
        BinToBin binToBin = new BinToBin();
        binToBin.binToBin(dataset, itemaDeplacer2, itemaDeplacer3);
        System.out.println("Dataset final :\n"+dataset.toString());
        stopChrono(chrono);
        System.out.print("f(x) de la solution finale :");
        fonctionObjective(dataset);
    }
    static void recuitSimulé(String[] listeFichier, char choix_voisinage,int temperature){
        System.out.println("Question 6: Recuit simulé");

        for (int i = 0; i < listeFichier.length; i++) {
            System.out.println(listeFichier[i]);
            long chrono = startChrono();
            ChargementData data3 = new ChargementData();
            DataSet dataset = data3.loadFile("./data/"+listeFichier[i]);
//            System.out.println("Dataset de base :\n"+dataset.toString());
            System.out.print("f(x) de la solution de base :");
            fonctionObjective(dataset);
            System.out.println("Avec un nombre de bin = "+dataset.getListBins().size());
            System.out.println();
            RecuitSimule recuitSimule = new RecuitSimule();
            System.out.println("Solution obtenue avec le recuit simulé :");
            DataSet d = recuitSimule.recuitSimule(dataset, temperature, choix_voisinage);
//            System.out.println("Dataset final :\n"+d);
            System.out.println("Avec un nombre de bin = " + d.getListBins().size());
            stopChrono(chrono);
            System.out.print("f(x) de la solution finale :");
            fonctionObjective(d);
            System.out.println("-------------------------Prochain fichier--------------------------------");
        }
    }
    static void tabuSearch(String[] listeFichier, char choix_voisinage, int nbIter){
        System.out.println("Question 7: tabou search");
        for(int i=0;i< listeFichier.length;i++){
            System.out.println(listeFichier[i]);
            long chrono = startChrono();
            ChargementData data2 = new ChargementData();
            DataSet dataset = data2.loadFile("./data/" + listeFichier[i]);
//            System.out.println("Dataset de base :\n"+dataset.toString());
            System.out.print("f(x) de la solution de base :");
            fonctionObjective(dataset);
            System.out.println("Nb bin initial = " + dataset.getListBins().size());
            System.out.println();
            Tabou t = new Tabou();
            DataSet d = t.methodeTabou(dataset, nbIter, choix_voisinage, 3);
//            System.out.println("Dataset final :\n"+d);
            System.out.println("Nb bin fin tabou = " + d.getListBins().size());
            stopChrono(chrono);
            System.out.print("f(x) de la solution finale :");
            fonctionObjective(d);
            System.out.println("-------------------------Prochain fichier--------------------------------");
        }
    }
    static void fonctionObjective(DataSet solution){
        FonctionObjective f = new FonctionObjective();
        System.out.println(f.fonctionObjective(solution));
    }

    static String solutionOptimale(DataSet dataSet) {
        final BinPackingOrTools data = new BinPackingOrTools(dataSet);
        MPSolver solver = MPSolver.createSolver("SCIP");
        if (solver == null) {
            return ("Impossible de créer un solveur SCIP");
        }
        MPVariable[][] x = new MPVariable[data.numItems][data.numBins];
        for (int i = 0; i < data.numItems; ++i) {
            for (int j = 0; j < data.numBins; ++j) {
                x[i][j] = solver.makeIntVar(0, 1, "");
            }
        }
        MPVariable[] y = new MPVariable[data.numBins];
        for (int j = 0; j < data.numBins; ++j) {
            y[j] = solver.makeIntVar(0, 1, "");
        }
        double infinity = java.lang.Double.POSITIVE_INFINITY;
        for (int i = 0; i < data.numItems; ++i) {
            MPConstraint constraint = solver.makeConstraint(1, 1, "");
            for (int j = 0; j < data.numBins; ++j) {
                constraint.setCoefficient(x[i][j], 1);
            }
        }
        for (int j = 0; j < data.numBins; ++j) {
            MPConstraint constraint = solver.makeConstraint(0, infinity, "");
            constraint.setCoefficient(y[j], data.binCapacity);
            for (int i = 0; i < data.numItems; ++i) {
                constraint.setCoefficient(x[i][j], -data.weights[i]);
            }
        }
        MPObjective objective = solver.objective();
        for (int j = 0; j < data.numBins; ++j) {
            objective.setCoefficient(y[j], 1);
        }
        objective.setMinimization();
        final MPSolver.ResultStatus resultStatus = solver.solve();
        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            double totalWeight = 0;
            int j, i;

            for (j = 0; j < data.numBins; ++j) {
                if (y[j].solutionValue() == 1) {
//                    System.out.println("\nBin n°" + j);
                    double binWeight = 0;
                    for (i = 0; i < data.numItems; ++i) {
                        if (x[i][j].solutionValue() == 1) {
//                            System.out.println("Item n°" + i + " avec un poids de : " + data.weights[i]);
                            binWeight += data.weights[i];
                        }
                    }
//                    System.out.println("Poids du bin: " + binWeight);
                    totalWeight += binWeight;
                }
            }
            return ("\nPoids total : " + totalWeight + "\nNombre total de bin utilisé: " + objective.value() + "\n");
        } else {
            return ("Aucune solution optimale possible.");
        }
    }
    static long startChrono(){
        long chrono=0;
        chrono = java.lang.System.currentTimeMillis() ;
        return chrono;
    }
    static void stopChrono(long chrono){
        long chrono2 = java.lang.System.currentTimeMillis() ;
        long temps = chrono2 - chrono ;
        System.out.println("Temps ecoule = " + temps + " ms") ;
    }
}