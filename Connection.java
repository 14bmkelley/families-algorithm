public class Connection {

   private String bigName, littleName;
   private int bigOpinion, littleOpinion;

   public Connection(String bigName, String littleName) {
      this.bigName = bigName;
      this.littleName = littleName;
   }

   public String big() {
      return bigName;
   }

   public String little() {
      return littleName;
   }

   public int bigOpinion() {
      return bigOpinion;
   }

   public int littleOpinion() {
      return littleOpinion;
   }

   public void setBigOpinion(int opinion) {
      bigOpinion = opinion;
   }

   public void setLittleOpinion(int opinion) {
      littleOpinion = opinion;
   }

}
