package zhdanboro.database;

import zhdanboro.generation.sequence.Sequence;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseService {
    public static boolean saveToDatabase(boolean binary, String polynomial, Sequence[] sequences) {
        String pathToFile;
        if (binary) {
            pathToFile = "C:\\Users\\lucif\\Desktop\\DiplomFinal\\database\\binary.txt";
        }else {
            pathToFile = "C:\\Users\\lucif\\Desktop\\DiplomFinal\\database\\database.txt";
        }

        try (FileWriter writer = new FileWriter(pathToFile, true)){

            //writer.write("Polynomial: " + polynomial + "\n");
            for (Sequence sequence : sequences) {
                String toWrite = sequence.toString();
                writer.write(toWrite);
                writer.append("\n");
            }
        } catch (IOException ex) {
            System.out.println("Cannot open file");
            return false;
        }

        return true;
    }

    public static Sequence[] loadFromDatabase(boolean binary) {
        HashMap<String, Sequence[]> readedInfo = new HashMap<>();
        String pathToFile;
        if (binary) {
            pathToFile = "C:\\Users\\lucif\\Desktop\\DiplomFinal\\database\\binary.txt";
        }else {
            pathToFile = "C:\\Users\\lucif\\Desktop\\DiplomFinal\\database\\database.txt";
        }

        ArrayList<Sequence> seqList = new ArrayList<>();
        try(FileReader reader = new FileReader(pathToFile)) {
            BufferedReader buffer = new BufferedReader(reader);

            String line = buffer.readLine();

            while (line != null) {
                seqList.add(createSequence(line));
                line = buffer.readLine();
            }


        } catch (IOException ex) {
            System.out.println("Cannot open file");
        }
        return seqList.toArray(new Sequence[0]);
    }

    private static Sequence createSequence(String line) {
        String[] args = line.split(" ");
        double[] array = new double[args.length];
        for (int i = 0; i<args.length; i++)
            array[i] = Double.parseDouble(args[i]);

        return new Sequence(array);
    }
}
