package windchill;

public class MyTempConverter {
	
	public static long windchill(int speed, int temperature) throws BadInputException {
		if(speed<5)
			throw new BadInputException("Windchill not valid if speed<5");		
		double newTemp = 
				35.74 + 0.6125*temperature 
				- 35.75*Math.pow(speed,0.16)
				+ 0.4275*temperature*Math.pow(speed, 0.16);
		return Math.round(newTemp);
	}
	
	public static void main(String[] args){
		try{
			System.out.println(MyTempConverter.windchill(10, 30));
		} catch(BadInputException e){
			
		}
	}

}
