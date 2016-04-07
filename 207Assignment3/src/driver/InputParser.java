package driver;

import java.util.ArrayList;
import exception.TooManyArgException;

public class InputParser {
  private String input;
  private String htmlAddress;
  private String fileName;

  public void parseInput(String s) {
    input = s;
    parseBlanks();
    parseArgs();
  }

  private void parseBlanks() {
    input = input.trim();

    while (input.contains("  ")) {
      input = input.replaceAll("  ", " ");
    }
  }

  private void parseArgs() {
    try {
      if (countArgs() == 2) {
        htmlAddress = input.substring(0, input.indexOf(" "));
        fileName = input.substring(input.indexOf(" ") + 1, input.length());
      } else {
        htmlAddress = input;
      }
    } catch (TooManyArgException e) {
      System.out.println(e.toString());
    }
  }

  public ArrayList<String> getHtml(){
    ArrayList<String> htmlArray = new ArrayList<String>();
    
    if (checkQuotes()){
      int loopTracker = 1;
      
      for (int i = 0; i < htmlAddress.length(); i++){
        if (htmlAddress.substring(i, i + 1).equals(",")){
          htmlArray.add(htmlAddress.substring(loopTracker, i));
          loopTracker = i + 1;
        }
        if (htmlAddress.length() == i + 1){
          htmlArray.add(htmlAddress.substring(loopTracker, i));
        }
      }
      return htmlArray;
    } else {
      System.out.println("HTML argument must be surrounded with double quotes.");
      return null;
    }
  }
  
  private boolean checkQuotes() {

    if (htmlAddress.substring(0, 1).equals("\"") && htmlAddress
        .substring(htmlAddress.length() - 1, htmlAddress.length()).equals("\"")) 
      return true;    
    return false; 
  }
  
  private int countArgs() throws TooManyArgException {
    int count = 0;

    for (int i = 0; i < input.length(); i++) {
      if (input.substring(i, i + 1).equals(" ")) {
        count++;
      }
    }
    count++;
    if (count > 2) {
      throw new TooManyArgException(count);
    }
    return count;
  }
  
  public String toString(String type){
    if (type.equals("file"))
      return fileName;
    return htmlAddress;
  }
}
