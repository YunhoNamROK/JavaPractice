package lab7;

public class Bicycle {

  protected int gear;
  protected long speed;

  // In TestBikes.main() we're calling the constructor Bicycle()
  // Where is this constructor method ?
  // Create another constructor method that initializes a bicycle with a
  // minimum and maximum gear. Basically, initialize these two variables:

  protected int minimumGear;
  protected int maximumGear;

  // Create getter and setter methods for all variables
  // See if you can get your IDE to do it automatically for you 
                  // (the cool IDEs do it)
  // Make sure to add the right checks into the setter!

  public Bicycle (int min, int max){
    gear = min;
    speed = max;
  }
  
  public void printDescription() {
    System.out.println("\nBike is " + "in gear " + this.gear
        + " and travelling at a speed of " + this.speed + ". "); 
 }

  final public void hitTheBreaks() {
      System.out.println("Break!");
  }
  
  public SuspensionType getSuspensionType(){
    return null;
  }

  public int getGear() {
    return gear;
  }

  public void setGear(int gear) {
    this.gear = gear;
  }

  public long getSpeed() {
    return speed;
  }

  public void setSpeed(long speed) {
    this.speed = speed;
  }

}
