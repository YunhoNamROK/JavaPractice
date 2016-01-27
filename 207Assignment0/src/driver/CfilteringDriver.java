// **********************************************************
// Assignment0:
// CDF user_name: c4namyun
// UT Student #: 999186474
// Author: Yunho Nam
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores and
   * prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());


      /*
       * create a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      // this is a blankline being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      // keep track of row being used to populate the USER_MOVIE MATRIX
      int rowtracker = 0;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        // keep track of colum being used and repeat for each row
        int coltracker = 0;
        for (String singleRating : allRatings) {
          // make the String number into an integer
          // populate userMovieMatrix
          // TODO: COMPLETE THIS I.E. POPULATE THE USER_MOVIE MATRIX
          int rate = Integer.parseInt(singleRating);
          cfObject.populateUserMovieMatrix(rowtracker, coltracker, rate);
          // add 1 every time a column is used to move onto next column
          coltracker++;
        }
        // add 1 every time a row is used to move onto next row
        rowtracker++;
      }
      // close the file
      fStream.close();

      /*
       * COMPLETE THIS ( I.E. CALL THE APPROPRIATE FUNCTIONS THAT DOES THE
       * FOLLOWING)
       */
      // TODO:1.) CALCULATE THE SIMILARITY SCORE BETWEEN USERS.
      // TODO:2.) PRINT OUT THE userUserMatrix
      // TODO:3.) PRINT OUT THE MOST SIMILAR PAIRS OF USER AND THE MOST
      // DISSIMILAR
      // PAIR OF USERS.
      // calculate the similarity score between users
      cfObject.calculateSimilarityScore(numberOfUsers, numberOfMovies);
      // print out the userUserMatrix
      System.out.println(cfObject.printUserUserMatrix());
    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
