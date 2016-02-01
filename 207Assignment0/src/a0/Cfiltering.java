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

  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param rowNumber The row numer of the userMovieMatrix
   * @param columnNumber The column number of the userMovieMatrix
   */
  public void calculateSimilarityScore(int rowNumber, int columnNumber) {
    // row number of the userUserMatrix
    int uuRowNumber = userUserMatrix.length;
    // column number of the userUserMatrix
    int uuColumnNumber = userUserMatrix[0].length;
    // iterate row of userUserMatrix
    for (int e = 0; e < uuRowNumber; e++) {
      // keep track of which row to iterate for next row of userMovieMatrix;
      // for a new row on userUserMatrix, it is reset to 0
      int temprow = 0;
      // iterate column of userUserMatrix
      for (int b = 0; b < uuColumnNumber; b++) {
        // iterate column of userMovieMatrix
        for (int i = 0; i < columnNumber; i++) {
          // algorithm for adding the result of squaring the subtraction of a
          // number from one row to another number of another row from the same
          // column, or itself; added to userUserMatrix[e][i], which is the
          // current interation of the above two for loop iterations
          userUserMatrix[e][b] += Math
              .pow((userMovieMatrix[e][i] - userMovieMatrix[temprow][i]), 2);
        }
        // indicate the completed iteration of one row of userMovieMatrix
        temprow += 1;
        // complete the euclidean equation and acquire the similarity score
        userUserMatrix[e][b] =
            (float) (1 / (1 + (Math.sqrt(userUserMatrix[e][b]))));
        // assign a variable with the similarity score rounded to 4 decimmal
        // points
        float roundMatrix =
            (float) ((Math.round(userUserMatrix[e][b] * 10000.0000))
                / 10000.0000d);
        // reassign roundMatrix to the current interation of userUserMatrix
        userUserMatrix[e][b] = roundMatrix;
      }
    }
  }

  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   * 
   * @return return a string representation of userUserMatrix
   */

  public String printUserUserMatrix() {
    // row number of the userUserMatrix
    int rowNumber = userUserMatrix.length;
    // column number of the userUserMatrix
    int columnNumber = userUserMatrix[0].length;
    // this is the string that represent the userUserMatrix
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
    return "\n\nuserUserMatrix is:\n" + stringArray;
  }

  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   * 
   * @return return a string representation of most similar pairs of users
   */

  public String findAndprintMostSimilarPairOfUsers() {
    float[] singularScores = userSingularScores();
    String[] scoreLocations = singularScoreLocations();
    float max = 0;
    float[] multipleMax = new float[singularScores.length];
    int multiCounter = 0;
    for (int item = 0; item < singularScores.length; item++) {
      if (singularScores[item] > max) {
        max = singularScores[item];
      }
      if (singularScores[item] == 1.0) {
        multipleMax[multiCounter] = 1.0f;
        multiCounter++;
      }
    }
    if (max != 1.0) {
      for (int item = 0; item < singularScores.length; item++) {
        if (singularScores[item] == max) {
          multipleMax[multiCounter] = max;
          multiCounter++;
        }
      }
    }
    int index = 0;
    int[] multiIndex = new int[multiCounter];
    int multiIndexCounter = 0;
    if (multiCounter > 1) {
      for (int i = 0; i < multiCounter; i++) {
        index = 0;
        for (int item = 0; item < singularScores.length; item++) {
          if (singularScores[item] == max) {
            singularScores[item] = 1300135;
            break;
          } else {
            index++;
          }
        }
        multiIndex[multiIndexCounter] = index;
        multiIndexCounter++;
      }
    } else {
      for (int item = 0; item < singularScores.length; item++) {
        if (max == singularScores[item]) {
          break;
        } else {
          index++;
        }
      }
    }
    String similarScores;
    String stringMax;
    DecimalFormat Decimalnum = new DecimalFormat("0.0000");
    stringMax = Decimalnum.format(max);
    similarScores = "\nwith similarity score of " + stringMax;
    String similarLocations = "";
    if (multiCounter > 1) {
      for (int item = 0; item < multiIndex.length; item++) {
        similarLocations += "\nUser"
            + scoreLocations[multiIndex[item]].substring(0, 1) + " and User"
            + scoreLocations[multiIndex[item]].substring(1, 2);
        --multiCounter;
        if (multiCounter != 0) {
          similarLocations += ",";
        }
      }
    } else {
      similarLocations += "\nUser" + scoreLocations[index].substring(0, 1)
          + " and User" + scoreLocations[index].substring(1, 2);
    }
    return "\nThe most similar pairs of users from above userUserMatrix are:"
        + similarLocations + similarScores;
  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   * @return return a string representation of most dissimilar pairs of users
   */
  public String findAndprintMostDissimilarPairOfUsers() {
    float[] singularScores = userSingularScores();
    String[] scoreLocations = singularScoreLocations();
    float min = 1;
    float[] multipleMin = new float[singularScores.length];
    int multiCounter = 0;
    for (int item = 0; item < singularScores.length; item++) {
      if (singularScores[item] != 0 & singularScores[item] < min) {
        min = singularScores[item];
      }
    }
    for (int item = 0; item < singularScores.length; item++) {
      if (singularScores[item] == min) {
        multipleMin[multiCounter] = min;
        multiCounter++;
      }
    }
    int index = 0;
    int[] multiIndex = new int[multiCounter];
    int multiIndexCounter = 0;
    if (multiCounter > 1) {
      for (int i = 0; i < multiCounter; i++) {
        index = 0;
        for (int item = 0; item < singularScores.length; item++) {
          if (singularScores[item] == min) {
            singularScores[item] = 1300135;
            break;
          } else {
            index++;
          }
        }
        multiIndex[multiIndexCounter] = index;
        multiIndexCounter++;
      }
    } else {
      for (int item = 0; item < singularScores.length; item++) {
        if (min == singularScores[item]) {
          break;
        } else {
          index++;
        }
      }
    }
    String dissimilarScores;
    String stringMin;
    DecimalFormat Decimalnum = new DecimalFormat("0.0000");
    stringMin = Decimalnum.format(min);
    dissimilarScores = "\nwith similarity score of " + stringMin;
    String dissimilarLocations = "";
    if (multiCounter > 1) {
      for (int item = 0; item < multiIndex.length; item++) {
        dissimilarLocations += "\nUser"
            + scoreLocations[multiIndex[item]].substring(0, 1) + " and User"
            + scoreLocations[multiIndex[item]].substring(1, 2);
        --multiCounter;
        if (multiCounter != 0) {
          dissimilarLocations += ",";
        }
      }
    } else {
      dissimilarLocations += "\nUser" + scoreLocations[index].substring(0, 1)
          + " and User" + scoreLocations[index].substring(1, 2);
    }
    return "\n\n"
        + "The most dissimilar pairs of users from above userUserMatrix are:"
        + dissimilarLocations + dissimilarScores;
  }

  /**
   * This HELPER function is used to extract all the scores from userUserMatrix
   * that aren't comparing a user by itself, or a duplicate of a score between
   * pair of users who already have an assigned score.
   * 
   * @return return an array of scores without duplicates or self scores
   */
  public float[] userSingularScores() {
    int rowNumber = userUserMatrix.length;
    int columnNumber = userUserMatrix[0].length;
    int arrNum = rowNumber * rowNumber;
    float[] arrScores = new float[arrNum];
    int arrTracker = 0;
    for (int row = 0; row < rowNumber; row++) {
      for (int column = 0; column < columnNumber; column++) {
        if (row < column) {
          arrScores[arrTracker] = userUserMatrix[row][column];
          arrTracker += 1;
        }
      }
    }
    return arrScores;
  }

  /**
   * This HELPER function returns an array whose indexes are assigned with
   * locations of singular scores in userUserMatrix; the index of the array
   * correspond with the index of the array from the userSingularScores
   * function.
   * 
   * @return return an array of locations of singular scores
   */
  public String[] singularScoreLocations() {
    int rowNumber = userUserMatrix.length;
    int columnNumber = userUserMatrix[0].length;
    int arrNum = rowNumber * rowNumber;
    String[] arrLocations = new String[arrNum];
    int arrTracker = 0;
    for (int row = 0; row < rowNumber; row++) {
      for (int column = 0; column < columnNumber; column++) {
        if (row < column) {
          arrLocations[arrTracker] =
              Integer.toString(row + 1) + Integer.toString(column + 1);
          arrTracker += 1;
        }
      }
    }
    return arrLocations;
  }
}
