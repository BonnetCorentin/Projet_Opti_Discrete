import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

public class Main {
    public static void main(String[] args) {
        ListerRepertoire l = new ListerRepertoire();
        String[] listefichier = l.ListerFichier("./data/");

        System.out.println("Borne inférieur :");
        for (int i = 0; i < listefichier.length - 1; i++) {
            String fileDataName = listefichier[i];
            ChargementData data = new ChargementData();
            DataSet dataset = new DataSet();
            dataset = data.loadFile("./data/" + fileDataName);
            int borne_inf = dataset.getBorneInf();
            System.out.println("Borne inférieur de " + listefichier[i] + ": " + borne_inf);
        }

        System.out.println();
        System.out.println("First Fit Decreasing :");
        FirstFitDecreasing ft = new FirstFitDecreasing();
        for (int j = 0; j < listefichier.length - 1; j++) {
            String fileDataName = listefichier[j];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            System.out.println("Nombre de bin à utiliser avec la méthode firstFitDecreasing pour " + listefichier[j] + ": " + ft.firstFitDecreasing(dataset));
        }
    }
}