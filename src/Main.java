import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListerRepertoire l = new ListerRepertoire();
        String [] listefichier=l.ListerFichier("./data/");
        for(int i=0;i<listefichier.length-1;i++) {
            String fileDataName=listefichier[i];
            ChargementData data = new ChargementData();
            DataSet dataset = new DataSet();
            dataset = data.loadFile("./data/" + fileDataName);
            int borne_inf=dataset.getBorneInf();
            System.out.println("Borne inférieur de "+listefichier[i]+": "+borne_inf);

            /*public static void main(String args[])

            {

                System.out

                        .println("BIN - PACKING Algorithm 1D Objects(First Fit Decreasing)");

                System.out.println("Enter the number of items in Set: ");

                Scanner sc = new Scanner(System.in);

                int n = sc.nextInt();

                System.out.println("Enter " + n + " items:");

                int[] a = new int[n];

                for (int i = 0; i < n; i++)

                    a[i] = sc.nextInt();

                System.out.println("Enter the bin size: ");

                int size = sc.nextInt();

                int[] sequence = sort(a);

                binPacking(sequence, size, n);

                sc.close();

            }*/
        }
    }
}