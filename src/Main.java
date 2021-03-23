import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String fileDataName="binpack1d_00.txt";
        ChargementData.loadFile("./data/" + fileDataName);

        ChargementData data = new ChargementData();
    }
}
