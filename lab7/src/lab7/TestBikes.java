package lab7;


public class TestBikes {
    
    public static void main(String[] args){
             
        //Try out your bikes here!
      //Bicycle b = new Bicycle(0,2);
      //b.printDescription();
      MountainBike mb = new MountainBike(0,1, SuspensionType.Cheap);
      mb.printDescription();
      Bicycle mb2 = new MountainBike(0,1, SuspensionType.Cheap);
      mb2.printDescription();
      Bicycle mb3 = new MountainBike(0,1, SuspensionType.Cheap);
      mb3.getSuspensionType();
      Bicycle a1, a2, a3 = a1 = new Bicycle(0,2);
      a2 = new MountainBike(0,5, SuspensionType.Cheap);
      a3 = new MoPed(0,3,100);
      a1.printDescription();
      a2.printDescription();
      a3.printDescription();
    }  
}
