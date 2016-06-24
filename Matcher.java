import java.util.ArrayList;

public class Matcher {

   private Matrix matrix;

   public Matcher(Settings settings) {
      matrix = new Matrix(settings.bigs(), settings.littles());
   }

}
