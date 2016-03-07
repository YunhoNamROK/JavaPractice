package lab7;

public class MoPed extends Bicycle {

  public int percentCharged;

  public MoPed(int minGear, int maxGear, int percent) {
    super(minGear, maxGear);
    percentCharged = percent;
  }


  public float getPercentCharged() {
    return percentCharged;
  }


  public void setPercentCharged(int percentCharged) {
    this.percentCharged = percentCharged;
  }
  
  public void printDescription(){
    System.out.println("\nBike is " + "in gear " + gear
        + " and travelling at a speed of " + speed + ". Its charge percentage is " + percentCharged + "."); 
  }

}
