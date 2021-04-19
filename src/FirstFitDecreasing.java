import java.util.Scanner;

public class FirstFitDecreasing {

    public static void binPacking(int[] a, int size, int n) {
        int binCount = 0;
        int[] binValues = new int[n];
        for (int i = 0; i < binValues.length; i++)
            binValues[i] = size;
        for (int i = 0; i < n; i++)

            for (int j = 0; j < binValues.length; j++)

            {

                if (binValues[j] - a[i] >= 0)

                {

                    binValues[j] -= a[i];

                    break;

                }

            }



        for (int i = 0; i < binValues.length; i++)
            if (binValues[i] != size) {
                binCount++;
            }
        System.out.println("Nombre de bins nécessaire: " + binCount);

    }



    static int[] sort(int[] sequence)

    {

        // Bubble Sort descending order

        for (int i = 0; i < sequence.length; i++)

            for (int j = 0; j < sequence.length - 1; j++)

                if (sequence[j] < sequence[j + 1])

                {

                    sequence[j] = sequence[j] + sequence[j + 1];

                    sequence[j + 1] = sequence[j] - sequence[j + 1];

                    sequence[j] = sequence[j] - sequence[j + 1];

                }

        return sequence;

    }

}