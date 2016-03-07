package lab7;

public class MountainBike extends Bicycle {

  // public MountainBike(int min, int max, SuspensionType t)
  // How can you reuse the constructor of the superclass?
  // You'll need to define a private variable to keep track of SuspensionType
  // don't forget to add getters/setters
  
  private SuspensionType s;
  
  public MountainBike(int minGear, int maxGear, SuspensionType suspType) {
    super(minGear, maxGear);
    s = suspType;
  }

  public void printDescription(){
    System.out.println("\nBike is " + "in gear " + gear
        + " and travelling at a speed of " + speed + ". Its suspension type is " + s + "."); 
  }

  public SuspensionType getSuspensionType(){
    return s;
  }
}
