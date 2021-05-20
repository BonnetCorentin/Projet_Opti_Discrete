import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ListerRepertoire l = new ListerRepertoire();
        String[] listeFichier = l.ListerFichier("./data/");


        //Question 1: Borne inférieure

        System.out.println("Question 1");
        System.out.println("Borne inférieur :");
        for (int i = 0; i < listeFichier.length - 1; i++) {
            String fileDataName = listeFichier[i];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            int borne_inf = dataset.getBorneInf();
            System.out.println("Borne inférieur de " + listeFichier[i] + ": " + borne_inf);
        }

        //Question 2: First Fit Decreasing

        System.out.println();
        System.out.println("Question 2");
        System.out.println("First Fit Decreasing :");
        FirstFitDecreasing ft = new FirstFitDecreasing();
        for (int j = 0; j < listeFichier.length - 1; j++) {
            String fileDataName = listeFichier[j];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            Collections.sort(dataset.getListItems(), new ItemComparator());
            System.out.println("Nombre de bin à utiliser avec la méthode firstFitDecreasing pour " + listeFichier[j] + ": " + ft.firstFitDecreasing(dataset));
        }

        //Question 3.a: Solution optimale de « binpack1d_00.txt »

        System.out.println();
        System.out.println("Question 3.a");
        System.out.println("Solution optimale du problème binpack1d_00.txt :");

        Loader.loadNativeLibraries();
        ChargementData datas = new ChargementData();
        DataSet dataset = datas.loadFile("./data/binpack1d_00.txt");
        System.out.println(solutionOptimale(dataset));

        //Question 3.b: Limite d'exécution
        //Faire des tests avec plusieurs jeux de données et voir quand le temps d'éxécution
        //devient trop long (exemple: plus de 5min d'éxécution).
        //Faire des moyennes de temps d'éxécution entre les datas...


        //Question 4.a: Un item par bin
        //Etant donné que l'on place un item par bin, on peut conclure aisément qu'il y aura
        //autant de nombre de bin que d'item.
        System.out.println("Question 4.a");
        for (int j = 0; j < listeFichier.length - 1; j++) {
            String fileDataName = listeFichier[j];
            ChargementData data = new ChargementData();
            DataSet dataset2 = data.loadFile("./data/" + fileDataName);
            Collections.shuffle(dataset2.getListItems());
            System.out.println("Nombre de bin à utiliser avec la méthode 1 item par bin pour :"+dataset2.getNbItems());
        }
        System.out.println();

        //Question 4.b: firstFitAleatoire

        System.out.println("Question 4.b");
        for (int j = 0; j < listeFichier.length - 1; j++) {
            String fileDataName = listeFichier[j];
            ChargementData data = new ChargementData();
            DataSet dataset2 = data.loadFile("./data/" + fileDataName);
            Collections.shuffle(dataset2.getListItems());
            System.out.println("Nombre de bin à utiliser avec la méthode firstFitAleatoire pour " + listeFichier[j] + ": " + ft.firstFitDecreasing(dataset2));
        }
        System.out.println();



        //Question 5.a: Deplacer un item d'un bin vers un autre
        //A partir de la méthode First Fit Decreasing (question 2)

        //Exemple avec jeu de data 00.txt

        System.out.println("Question 5.a");

        FirstFitDecreasing ft2 = new FirstFitDecreasing();
        String fileDataName = "binpack1d_00.txt";
        ChargementData data = new ChargementData();

        DataSet dataset2 = data.loadFile("./data/" + fileDataName);
        ft2.firstFitDecreasing(dataset2);

        System.out.println(dataset2.toString()+"\n");

        Items itemaDeplacer = dataset2.getListItems().get(0);
        Bin binDestination = dataset2.getListBins().get(5);

        System.out.println(ft2.changeItemToBin(dataset2,itemaDeplacer,binDestination));
        System.out.println(dataset2.toString());

        //Question 5.b: Echanger deux items de deux bins  différents
        //A partir de la méthode First Fit Decreasing (question 2)

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
                    System.out.println("\nBin n°" + j );
                    double binWeight = 0;
                    for (i = 0; i < data.numItems; ++i) {
                        if (x[i][j].solutionValue() == 1) {
                            System.out.println("Item n°" + i + " avec un poids de : " + data.weights[i]);
                            binWeight += data.weights[i];
                        }
                    }
                    System.out.println("Poids du bin: " + binWeight);
                    totalWeight += binWeight;
                }
            }
            return ("\nPoids total : " + totalWeight + "\nNombre total de bin utilisé: " + objective.value()+"\n");
        } else {
            return ("Aucune solution optimale possible.");
        }
    }
}