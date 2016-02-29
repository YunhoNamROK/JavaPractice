package lab6;

import java.util.Hashtable;

public class executionProgram {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		filee[] fileArray;
		fileArray=new filee[10];
		
		//fileArray[0]=new openPDF();
		//fileArray[1]=new openPDF();
		//fileArray[2]=new OpenWord();
		//fileArray[3]=new openTxt();
		//fileArray[4]=new openPDF();
		//fileArray[5]=new OpenWord();
		//fileArray[6]=new openTxt();
		//fileArray[7]=new openTxt();
		//fileArray[8]=new openPDF();
		//fileArray[9]=new OpenWord();
		Hashtable<String, String> Hash = new Hashtable<String, String>();
		Hash.put("pdf", "openPDF");
		Hash.put("txt", "openTxt");
		Hash.put("doc", "OpenWord");

		Class x = Class.forName("openTxt");

		fileArray[0]= (filee) Class.forName(Hash.get("pdf")).newInstance();
		fileArray[1]= (filee) Class.forName(Hash.get("pdf")).newInstance();
		fileArray[2]= (filee) Class.forName(Hash.get("doc")).newInstance();
		fileArray[3]= (filee) Class.forName(Hash.get("txt")).newInstance();
		fileArray[4]= (filee) Class.forName(Hash.get("pdf")).newInstance();
		fileArray[5]= (filee) Class.forName(Hash.get("doc")).newInstance();
		fileArray[6]= (filee) Class.forName(Hash.get("txt")).newInstance();
		fileArray[7]= (filee) Class.forName(Hash.get("txt")).newInstance();
		fileArray[8]= (filee) Class.forName(Hash.get("pdf")).newInstance();
		fileArray[9]= (filee) Class.forName(Hash.get("doc")).newInstance();

		for (filee singleFile : fileArray)
		{   
			singleFile.NameOfFile();
			singleFile.readSingleLine();
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
}