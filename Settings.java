import java.io.File;
import java.util.Scanner;

public class Settings {

   private static final int BIGS = 0;
   private static final int LITTLES = 1;

   private Scanner bigsCSV;
   private Scanner littlesCSV;
   private Favor favor;

   public Settings(String args[]) {
      if (args.length == 0) {
         Util.error("No arguments specified. Use the -h flag to view usage.");
      }
      favor = Favor.LITTLES;
      processSettings(args);
   }

   public Scanner bigs() {
      return bigsCSV;
   }

   public Scanner littles() {
      return littlesCSV;
   }

   public Favor favor() {
      return favor;
   }

   private void processSettings(String args[]) {
      for (int i = 0; i < args.length; i++) {

         if (args[i].equals("-h")) {
            printHelp();
            System.exit(0);
         }

         else if (args[i].equals("-f")) {
            if (i == args.length - 1) {
               Util.error("No favor flag value specified.");
            }
            processFavor(args[i++ + 1]);
         }

         else if (bigsCSV == null) {
            openFileScanner(args[i], BIGS);
         }

         else if (littlesCSV == null) {
            openFileScanner(args[i], LITTLES);
         }

         else {
            Util.error("Invalid argument '" + args[i] + "' specified.");
         }

      }
      if (bigsCSV == null || littlesCSV == null) {
         Util.error("Two CSV files were not specified.");
      }
   }

   private void printHelp() {
      System.out.println("java Main");
      System.out.println("  Arguments:");
      System.out.println("    * -h          Display this help message");
      System.out.print("    * -f flag     Choose whether to favor bigs ");
      System.out.println("(" + BIGS + ") or littles (" + LITTLES + ")");
      System.out.println("    * bigsCSV     The results of the bigs survey");
      System.out.println("    * littlesCSV  The results of the littles survey");
   }

   private void processFavor(String favorInput) {
      try {
         int favorFlag = Integer.parseInt(favorInput);
         if (favorFlag == BIGS) {
            favor = Favor.BIGS;
         }
         else if (favorFlag == LITTLES) {
            favor = Favor.LITTLES;
         }
         else {
            String message = "Invalid favor flag value specified (Must be ";
            message += "" + BIGS + " or " + LITTLES + ").";
            Util.error(message);
         }
      }
      catch (Exception e) {
         Util.error("Invalid favor flag value specified (Must be numeric).");
      }
   }

   private void openFileScanner(String filename, int identifier) {
      try {
         if (identifier == BIGS) {
            bigsCSV = new Scanner(new File(filename));
         }
         else {
            littlesCSV = new Scanner(new File(filename));
         }
      }
      catch (Exception e) {
         String fileFailed = (identifier == BIGS) ? "bigs" : "littles";
         Util.error("Invalid " + fileFailed + " file specified.");
      }
   }

}
