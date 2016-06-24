import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Matrix {

   private ArrayList<ArrayList<Connection>> matrix;
   private HashMap<String, Integer> bigIndices, littleIndices;

   public Matrix(Scanner bigs, Scanner littles) {
      matrix = new ArrayList<ArrayList<Connection>>();
      bigIndices = new HashMap<String, Integer>();
      littleIndices = new HashMap<String, Integer>();
      parseBigs(bigs);
      parseLittles(littles);
   }

   private void parseBigs(Scanner bigs) {

      Scanner headers = new Scanner(bigs.nextLine());
      headers.useDelimiter("\",\"");

      int counter = 0;
      int nameIndex = readIndexContaining("name", headers, counter);
      counter += nameIndex + 1;
      int choiceIndex = readIndexContaining("little", headers, counter);
      counter += choiceIndex + 1;
      int numChoices = readIndexContaining("url redirect", headers, counter) -
         choiceIndex - 1;
      headers.close();

      counter = 0;
      while (bigs.hasNext()) {

         Scanner big = new Scanner(bigs.nextLine());
         big.useDelimiter("\",\"");
         matrix.add(new ArrayList<Connection>());

         skipForward(nameIndex, big);
         String bigName = big.next();
         bigIndices.put(bigName, counter);

         skipForward(choiceIndex - nameIndex, big);
         for (int col = 0; col < numChoices && bigs.hasNext(); col++) {

            String littleName = big.next();
            littleIndices.put(littleName, col);

            Connection c = new Connection(bigName, littleName);
            c.setBigOpinion(counter + 1);
            matrix.get(counter).set(col, c);

         }

         counter++;
      }

   }

   private void parseLittles(Scanner littles) {}

   private int readIndexContaining(String match, Scanner reader, int col) {
      int index = col, result = -1;
      while (reader.hasNext()) {
         if (reader.next().toLowerCase().contains(match)) {
            result = index;
            break;
         }
         index++;
      }
      if (result == -1) {
         Util.error("Invalid CSV file: Does not have a header containing '" +
            match + "'");
      }
      return result;
   }

   private void skipForward(int amount, Scanner s) {
      int index = 0;
      while (index != amount && s.hasNext()) {
         s.next();
         index++;
      }
      if (index != amount) {
         Util.error("Invalid CSV file: A row was not formatted to match the " +
            "provided headers.");
      }
   }

}
