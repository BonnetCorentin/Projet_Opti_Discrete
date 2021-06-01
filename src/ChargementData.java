import javax.xml.crypto.Data;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;


public class ChargementData {

    public static DataSet loadFile(String filePath) {
        DataSet dataSet = new DataSet();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            String data = myReader.nextLine();
            String[] line = data.split(" ");
            dataSet.setTailleBin(Integer.parseInt(line[0]));
            dataSet.setNbItems(Integer.parseInt(line[1]));
            Bin bin = new Bin(Integer.parseInt(line[0]));
            while (myReader.hasNextLine()) {
                String data2 = myReader.nextLine();
                String[] line2 = data2.split(" ");
                Items item = new Items(Integer.parseInt(line2[0]), i);
                dataSet.addItems(item);

                Boolean bool = bin.addItem(item);
                if (!bool)
                    dataSet.addBin(bin);
                bin = new Bin(Integer.parseInt(line[0]));
                bin.addItem(item);

                i++;
            }
            dataSet.addBin(bin);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur lors de la lecture du fichier: " + filePath);
            e.printStackTrace();
        }
        return dataSet;
    }
}