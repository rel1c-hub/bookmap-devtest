import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryReader {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            String[] firstLine = reader.readLine().split(" ");
            //int lengthOfString = Integer.parseInt(firstLine[0]);
            int numberOfQueries = Integer.parseInt(firstLine[1]);

            String baseString = reader.readLine();

            for (int i = 0; i < numberOfQueries; i++) {
                String[] queryLine = reader.readLine().split(" ");
                int leftIndex = Integer.parseInt(queryLine[0]);
                int rightIndex = Integer.parseInt(queryLine[1]);
                int targetMatch = Integer.parseInt(queryLine[2]);

                String substring = baseString.substring(leftIndex - 1, rightIndex);
                int result = getResult(substring, targetMatch);

                writer.write(Integer.toString(result));
                if (i < numberOfQueries - 1) {
                    writer.newLine();
                }
            }

            reader.close();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getResult(String substring, int targetMatch) {
        char kChar = substring.charAt(targetMatch - 1);

        List<Integer> aPositions = new ArrayList<>();
        List<Integer> bPositions = new ArrayList<>();

        for (int j = 0; j < substring.length(); j++) {
            if (substring.charAt(j) == 'A') {
                aPositions.add(j + 1);
            } else {
                bPositions.add(j + 1);
            }
        }

        int result = -1;
        if (kChar == 'A') {
            int aCount = 0;
            for (int j = 0; j < targetMatch; j++) {
                if (substring.charAt(j) == 'A') {
                    aCount++;
                }
            }
            if (aCount <= bPositions.size()) {
                result = bPositions.get(aCount - 1);
            }
        } else {
            int bCount = 0;
            for (int j = 0; j < targetMatch; j++) {
                if (substring.charAt(j) == 'B') {
                    bCount++;
                }
            }
            if (bCount <= aPositions.size()) {
                result = aPositions.get(bCount - 1);
            }
        }
        return result;
    }
}
