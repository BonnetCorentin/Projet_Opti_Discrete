
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;


public class BinPackingOrTools {
    private DataSet dataSet;

    BinPackingOrTools(DataSet dataset) {
        this.dataSet = dataset;
    }

    public String solutionOptimale(){
        double[] weights = dataSet.getWeights();
        int numItems = dataSet.getNbItems();
        int numBins = dataSet.getNbItems();
        int binCapacity = dataSet.getTailleBin();

        MPSolver solver = MPSolver.createSolver("SCIP");
        MPVariable[][] x = new MPVariable[numItems][numBins];
        MPVariable[] y = new MPVariable[numBins];
        double infinity = java.lang.Double.POSITIVE_INFINITY;
        MPObjective objective = solver.objective();
        final MPSolver.ResultStatus resultStatus = solver.solve();

        if (solver == null) {
            System.out.println("Impossible de créer un solveur SCIP");
            return null;
        }

        for (int i = 0; i < numItems; ++i) {
            for (int j = 0; j < numBins; ++j) {
                x[i][j] = solver.makeIntVar(0, 1, "");
            }
        }
        for (int j = 0; j < numBins; ++j) {
            y[j] = solver.makeIntVar(0, 1, "");
        }

        for (int i = 0; i < numItems; ++i) {
            MPConstraint constraint = solver.makeConstraint(1, 1, "");
            for (int j = 0; j < numBins; ++j) {
                constraint.setCoefficient(x[i][j], 1);
            }
        }

        for (int j = 0; j < numBins; ++j) {
            MPConstraint constraint = solver.makeConstraint(0, infinity, "");
            constraint.setCoefficient(y[j], binCapacity);
            for (int i = 0; i < numItems; ++i) {
                constraint.setCoefficient(x[i][j], -weights[i]);
            }
        }

        for (int j = 0; j < numBins; ++j) {
            objective.setCoefficient(y[j], 1);
        }
        objective.setMinimization();

        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            System.out.println("Nombre de bin utilisé: " + objective.value());
            double totalWeight = 0;
            for (int j = 0; j < numBins; ++j) {
                if (y[j].solutionValue() == 1) {
                    System.out.println("\nBin " + j + "\n");
                    double binWeight = 0;
                    for (int i = 0; i < numItems; ++i) {
                        if (x[i][j].solutionValue() == 1) {
                            System.out.println("Item " + i + " - weight: " + weights[i]);
                            binWeight += weights[i];
                        }
                    }
                    System.out.println("Bin weight packed: " + binWeight);
                    totalWeight += binWeight;
                }
            }
            return("\nTotal packed weight: " + totalWeight);
        } else {
            return("Ce problème n'a pas de solution optimale.");
        }
    }
}
