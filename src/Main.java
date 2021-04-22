public class Main {
    public static void main(String[] args) {
        ListerRepertoire l = new ListerRepertoire();
        String[] listeFichier = l.ListerFichier("./data/");

        System.out.println("Borne inférieur :");
        for (int i = 0; i < listeFichier.length - 1; i++) {
            String fileDataName = listeFichier[i];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            int borne_inf = dataset.getBorneInf();
            System.out.println("Borne inférieur de " + listeFichier[i] + ": " + borne_inf);
        }

        System.out.println();
        System.out.println("First Fit Decreasing :");
        FirstFitDecreasing ft = new FirstFitDecreasing();
        for (int j = 0; j < listeFichier.length - 1; j++) {
            String fileDataName = listeFichier[j];
            ChargementData data = new ChargementData();
            DataSet dataset = data.loadFile("./data/" + fileDataName);
            System.out.println("Nombre de bin à utiliser avec la méthode firstFitDecreasing pour " + listeFichier[j] + ": " + ft.firstFitDecreasing(dataset));
        }

        System.out.println();
        System.out.println("Solution optimale du problème binpack1d_00.txt :");
        ChargementData data = new ChargementData();
        DataSet dataset = data.loadFile("./data/binpack1d_00.txt");
        BinPackingOrTools binPackingOrTools = new BinPackingOrTools(dataset);

        binPackingOrTools.solutionOptimale();


    }
}