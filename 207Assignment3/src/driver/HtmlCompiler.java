package driver;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;

public class HtmlCompiler extends MyParser {
  private String authorName;
  private String allCitations;
  private String i10Index;
  private ArrayList<String> fivePublications = new ArrayList<String>();
  
  
  public void extractHtml(String htmlAddress){
    try {
      String htmlString = super.getHTML(htmlAddress);

      String authorNameExtraction =
          "<span id=\"cit-name-display\" "
              + "class=\"cit-in-place-nohover\">(.*?)</span>";
      Pattern patternObject = Pattern.compile(authorNameExtraction);
      Matcher matcherObject = patternObject.matcher(htmlString);
      matcherObject.find();
      authorName = matcherObject.group(1);
      
      String citationsExtraction = "<td class=\"cit-borderleft cit-data\">(.*?)</td>";
      patternObject = Pattern.compile(citationsExtraction);
      matcherObject = patternObject.matcher(htmlString);
      matcherObject.find();
      allCitations = matcherObject.group(1);
      for (int i = 0; i < 5; i++){
        matcherObject.find();
      }
      i10Index = matcherObject.group(4);
      
      String publicationExtraction = "<a class=\"cit-dark-large-link\" href=\"(.*?)\">";
      patternObject = Pattern.compile(publicationExtraction);
      matcherObject = patternObject.matcher(htmlString);
      matcherObject.find();
      i10Index = matcherObject.group(1);
    } catch (Exception e) {
      System.out.println("DEBUG");
    }
  }
  
  public String toString(){
    return authorName + allCitations + i10Index;
  }
}
