import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;


public class BinPackingOrTools {
    private DataSet dataSet;

    BinPackingOrTools(DataSet dataset) {
        this.dataSet = dataset;
    }

    static class DataModel {
        public final double[] weights;
        public final int numItems;
        public final int numBins;
        public final int binCapacity;

        DataModel(DataSet dataset) {
            weights = dataset.getWeights();
            numItems = dataset.getNbItems();
            numBins = dataset.getNbItems();
            binCapacity = dataset.getTailleBin();
        }

    }

    public String solutionOptimale() {
        Loader.loadNativeLibraries();

        final DataModel data = new DataModel(this.dataSet);

        MPSolver solver = MPSolver.createSolver("SCIP");
        MPVariable[][] x = new MPVariable[data.numItems][data.numBins];
        MPVariable[] y = new MPVariable[data.numBins];
        double infinity = java.lang.Double.POSITIVE_INFINITY;
        MPObjective objective = solver.objective();
        final MPSolver.ResultStatus resultStatus = solver.solve();

        if (solver == null) {
            System.out.println("Impossible de créer un solveur SCIP");
            return null;
        }

        for (int i = 0; i < data.numItems; ++i) {
            for (int j = 0; j < data.numBins; ++j) {
                x[i][j] = solver.makeIntVar(0, 1, "");
            }
        }
        for (int j = 0; j < data.numBins; ++j) {
            y[j] = solver.makeIntVar(0, 1, "");
        }

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

        for (int j = 0; j < data.numBins; ++j) {
            objective.setCoefficient(y[j], 1);
        }
        objective.setMinimization();

        if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
            System.out.println("Nombre de bin utilisé: " + objective.value());
            double totalWeight = 0;
            for (int j = 0; j < data.numBins; ++j) {
                if (y[j].solutionValue() == 1) {
                    System.out.println("\nBin " + j + "\n");
                    double binWeight = 0;
                    for (int i = 0; i < data.numItems; ++i) {
                        if (x[i][j].solutionValue() == 1) {
                            System.out.println("Item " + i + " - weight: " + data.weights[i]);
                            binWeight += data.weights[i];
                        }
                    }
                    System.out.println("Bin weight packed: " + binWeight);
                    totalWeight += binWeight;
                }
            }
            return ("\nTotal packed weight: " + totalWeight);
        } else {
            return ("Ce problème n'a pas de solution optimale.");
        }
    }
}
