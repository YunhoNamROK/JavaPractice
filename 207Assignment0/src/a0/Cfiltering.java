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
package a0;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }

  /*
   * TODO:COMPLETE THIS I.E. APPROPRIATELY CREATE THE userMovieMatrix AND
   * userUserMatrix WITH CORRECT DIMENSIONS.
   */
  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    // this is 2d matrix of size users*movies
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    // this is 2d matrix of size users*users
    userUserMatrix = new float[numberOfUsers][numberOfUsers];
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {
    // populate the userMovieMatrix with ratingValue
    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC. Add/remove
   * 
   * @param AND
   * 
   * @return as required below.
   */
  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param rowNumber The row numer of the userMovieMatrix
   * @param columnNumber The column number of the userMovieMatrix
   * @return COMPLETE THIS IF NEEDED
   */
  public void calculateSimilarityScore(int rowNumber, int columnNumber) {
    // row number of the userUserMatrix
    int uuRowNumber = userUserMatrix.length;
    // column number of the userUserMatrix
    int uuColumnNumber = userUserMatrix[0].length;
    // iterate row of userUserMatrix
    for (int e = 0; e < uuRowNumber; e++) {
      // keep track of which row to iterate for next row of userMovieMatrix for
      // a new row on userUserMatrix, it is reset to 0
      int temprow = 0;
      // iterate column of userUserMatrix
      for (int b = 0; b < uuColumnNumber; b++) {
        // iterate column of userMovieMatrix
        for (int i = 0; i < columnNumber; i++) {
          // algorithm for adding the result of squaring the subtraction of a
          // number from one row to another number of another or same row from
          // the same column; added to userUserMatrix[e][i], which is the
          // current interation of the above two for loop iterations
          userUserMatrix[e][b] += Math
              .pow((userMovieMatrix[e][i] - userMovieMatrix[temprow][i]), 2);
        }
        // indicate the finished iteration of one row of userMovieMatrix
        temprow += 1;
        // complete the euclidean equation and acquire the similarity score
        userUserMatrix[e][b] =
            (float) (1 / (1 + (Math.sqrt(userUserMatrix[e][b]))));
        // assign a variable with the similarity score rounded to 4 decimmal
        // points
        float roundMatrix =
            (float) ((Math.round(userUserMatrix[e][b] * 10000.0000))
                / 10000.0000d);
        // reassign roundMatrix to the unit in userUserMatrix
        userUserMatrix[e][b] = roundMatrix;
      }
    }
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return return a string representation of userUserMatrix
   */

  public String printUserUserMatrix() {
    // row number of the userUserMatrix
    int rowNumber = userUserMatrix.length;
    // column number of the userUserMatrix
    int columnNumber = userUserMatrix[0].length;
    // this is a string that represent the userUserMatrix
    String stringArray = "";
    // this is a 1d array that represent a row in userUserMatrix
    String[] tempArray = new String[rowNumber];
    // iterate row of userUserMatrix
    for (int e = 0; e < rowNumber; e++) {
      // iterate column of userUserMatrix
      for (int i = 0; i < columnNumber; i++) {
        // create a new DecimalFormat object
        DecimalFormat Decimalnum = new DecimalFormat("0.0000");
        // assign each item of tempArray with formatted item of current row of
        // userUserMatrix
        tempArray[i] = Decimalnum.format(userUserMatrix[e][i]);
      }
      // add string conversion of tempArray for current row of userUserMatrix
      // with \newline to print the next row on next line
      stringArray += Arrays.deepToString(tempArray) + "\n";
      // clear the tempArray so it can be used for the next row of
      // userUserMatrix
      tempArray = new String[rowNumber];
    }
    // return the final string representation of userUserMatrix
    return "userUserMatrix is:\n\n\n" + stringArray;
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */

  public void findAndprintMostSimilarPairOfUsers() {

  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  public void findAndprintMostDissimilarPairOfUsers() {

  }
}

