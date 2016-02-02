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
      // keep track of which row to iterate in the userMovieMatrix;
      // for ever new row on userUserMatrix, it is reset to 0
      int rowTracker = 0;
      // iterate column of userUserMatrix
      for (int b = 0; b < uuColumnNumber; b++) {
        // iterate column of userMovieMatrix
        for (int i = 0; i < columnNumber; i++) {
          // algorithm for adding the result of squaring the subtraction of a
          // number from one row to another number of another row from the same
          // column, or itself; added to userUserMatrix[e][i], which is the
          // current interation of the above two for loop iterations
          userUserMatrix[e][b] += Math
              .pow((userMovieMatrix[e][i] - userMovieMatrix[rowTracker][i]), 2);
        }
        // indicate the completed iteration of one row of userMovieMatrix
        rowTracker += 1;
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
    // this is the string that represents the userUserMatrix
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
    // assign the returning array of helper function userSingularScores
    float[] singularScores = userSingularScores();
    // assign the returning array of helper function userScoreLocations
    String[] scoreLocations = singularScoreLocations();
    // maximum score in singularScores
    float max = 0;
    // array to store multiples of maximum score
    float[] multipleMax = new float[singularScores.length];
    // keep track of multipleMax index
    int multiTracker = 0;
    // iterate singularScores array
    for (int item = 0; item < singularScores.length; item++) {
      // if the current item of singularScores is larger than max
      if (singularScores[item] > max) {
        // max is assigned the current item
        max = singularScores[item];
      }
      // if current item is maximum possible score 1.0
      if (singularScores[item] == 1.0) {
        // add 1.0 to first empty index of multipleMax
        multipleMax[multiTracker] = 1.0f;
        // pass the index onto the next one in multipleMax
        multiTracker++;
      }
    }
    // if maximum score is not 1.0
    if (max != 1.0) {
      // iterate singularScores array
      for (int item = 0; item < singularScores.length; item++) {
        // if a maximum score is a current item of singularScores
        if (singularScores[item] == max) {
          // the maximum score is added to first empty index of multipleMax
          multipleMax[multiTracker] = max;
          // pass the index onto the next one in multipleMax
          multiTracker++;
        }
      }
    }
    // index of maximum score in singularScores
    int index = 0;
    // array that stores multiple index in cases of multiple maximum scores
    int[] multiIndex = new int[multiTracker];
    // keep track of index of multiIndex
    int multiIndexTracker = 0;
    // if there is more than one maximum scores
    if (multiTracker > 1) {
      // iterate multipleMax array
      for (int i = 0; i < multiTracker; i++) {
        // index is reset to 0 per each i in multipleMax
        index = 0;
        // iterate singularScores array
        for (int item = 0; item < singularScores.length; item++) {
          // if current item in singularScores == maximum score
          if (singularScores[item] == max) {
            // replace the maximum score to not repeat counting current item
            singularScores[item] = 1300135;
            // break iteration and set index to current item
            break;
          } else {
            // maximum score is not found so search for next index
            index++;
          }
        }
        // add the index of current maximum score into first empty index
        multiIndex[multiIndexTracker] = index;
        // current index of multiIndex so pass the index onto the next one
        multiIndexTracker++;
      }
      // if there is just one maximum score
    } else {
      // iterate singularScores array
      for (int item = 0; item < singularScores.length; item++) {
        // if current item is equal maximum score
        if (max == singularScores[item]) {
          // break iteration and set index to current item
          break;
        } else {
          // maximum score is not found so search for next index
          index++;
        }
      }
    }
    // this is the final String representation of maximum score
    String similarScore;
    // this is the String representation of maximum score
    String stringMax;
    // create a new DecimalFormat object
    DecimalFormat Decimalnum = new DecimalFormat("0.0000");
    // format maximum score to four decimals
    stringMax = Decimalnum.format(max);
    // add formatted maximum score
    similarScore = "\nwith similarity score of " + stringMax;
    // this is the String representation of locations of maximum scores
    String similarLocations = "";
    // if there are multiples of maximum score
    if (multiTracker > 1) {
      // iterate multiIndex array
      for (int item = 0; item < multiIndex.length; item++) {
        // for each item in multiIndex, substring(0,1) represent first user
        // and substring (1,0) represent second user
        similarLocations += "\nUser"
            + scoreLocations[multiIndex[item]].substring(0, 1) + " and User"
            + scoreLocations[multiIndex[item]].substring(1, 2);
        // count down the number of pairs whose locations have been found
        --multiTracker;
        // if this is not the last pair of users
        if (multiTracker != 0) {
          // add comma at the end of current line
          similarLocations += ",";
        }
      }
      // if there is just one maximum score
    } else {
      similarLocations += "\nUser" + scoreLocations[index].substring(0, 1)
          + " and User" + scoreLocations[index].substring(1, 2);
    }
    // return the final output of the most similar score and their locations
    return "\nThe most similar pairs of users from above userUserMatrix are:"
        + similarLocations + similarScore;
  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   * @return return a string representation of most dissimilar pairs of users
   */
  public String findAndprintMostDissimilarPairOfUsers() {
    // assign the returning array of helper function userSingularScores
    float[] singularScores = userSingularScores();
    // assign the returning array of helper function singularScoreLocations
    String[] scoreLocations = singularScoreLocations();
    // minimum score in singularScores
    float min = 1;
    // array that stores duplicates of minimum score
    float[] multipleMin = new float[singularScores.length];
    // keep track of multipleMin index
    int multiTracker = 0;
    // iterate singularScores array
    for (int item = 0; item < singularScores.length; item++) {
      // if current item value is less than min and not equal 0 to avoid min
      // initiating an empty index in singularScores
      if (singularScores[item] != 0 & singularScores[item] < min) {
        // min is replaced with a new lower number
        min = singularScores[item];
      }
    }
    // iterate singularScores array
    for (int item = 0; item < singularScores.length; item++) {
      // if minimum number is found in singularScores
      if (singularScores[item] == min) {
        // add it to multipleMin array
        multipleMin[multiTracker] = min;
        // pass the index onto the new one in multipleMin
        // this way if there is more than one minimum score, they are all added
        multiTracker++;
      }
    }
    // index of minimum score in singularScores
    int index = 0;
    // array that stores multiple index in cases of multiple minimum scores
    int[] multiIndex = new int[multiTracker];
    // keep track of index of multiIndex
    int multiIndexTracker = 0;
    // if there is more than one minimum scores
    if (multiTracker > 1) {
      // iterate multiIndex array
      for (int i = 0; i < multiTracker; i++) {
        // index is reset to 0 for each minimum score in multiIndex
        index = 0;
        // iterate singularScores array
        for (int item = 0; item < singularScores.length; item++) {
          // if current item is equal minimum score
          if (singularScores[item] == min) {
            singularScores[item] = 1300135;
            // break iteration and set index to current item
            break;
          } else {
            // minimum score is not found so search for next index
            index++;
          }
        }
        // add the index of current minimum score into first empty index
        multiIndex[multiIndexTracker] = index;
        // current index of multiIndex so pass the index onto the next one
        multiIndexTracker++;
      }
      // if there is just one minimum score
    } else {
      // iterate singularScores array
      for (int item = 0; item < singularScores.length; item++) {
        // if current item is equal minimum score
        if (min == singularScores[item]) {
          // break iteration and set index to current item
          break;
        } else {
          // minimum score is not found so search for next index
          index++;
        }
      }
    }
    // this is the final String representation of minimum score
    String dissimilarScores;
    // this is the String representation of minimum score
    String stringMin;
    // create a new DecimalFormat object
    DecimalFormat Decimalnum = new DecimalFormat("0.0000");
    // format minimum score to four decimal points
    stringMin = Decimalnum.format(min);
    // add formatted minimum score
    dissimilarScores = "\nwith similarity score of " + stringMin;
    // this is the String representation of locations of minimum scores
    String dissimilarLocations = "";
    // if there are multiples of minimum score
    if (multiTracker > 1) {
      // iterate multiIndex array
      for (int item = 0; item < multiIndex.length; item++) {
        // for each item in multiIndex, substring(0,1) represent first user
        // and substring (1,0) represent second user
        dissimilarLocations += "\nUser"
            + scoreLocations[multiIndex[item]].substring(0, 1) + " and User"
            + scoreLocations[multiIndex[item]].substring(1, 2);
        // count down the number of pairs whose locations have been found
        --multiTracker;
        // if this is not the last pair of users
        if (multiTracker != 0) {
          // add comma at the end of current line
          dissimilarLocations += ",";
        }
      }
      // if there is just one minimum score
    } else {
      dissimilarLocations += "\nUser" + scoreLocations[index].substring(0, 1)
          + " and User" + scoreLocations[index].substring(1, 2);
    }
    // return the final output of the most dissimilar score and their locations
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
    // row number of the userUserMatrix
    int rowNumber = userUserMatrix.length;
    // column number of the userUserMatrix
    int columnNumber = userUserMatrix[0].length;
    // size of the array
    int arrNum = rowNumber * rowNumber;
    // the array of scores
    float[] arrScores = new float[arrNum];
    // tracks the array index
    int arrTracker = 0;
    // iterate row of userUserMatrix
    for (int row = 0; row < rowNumber; row++) {
      // iterate column of userUserMatrix
      for (int column = 0; column < columnNumber; column++) {
        // only adds score to the array past 1.0 self score column per row
        if (row < column) {
          // current index of the array is replaced with current iterated score
          arrScores[arrTracker] = userUserMatrix[row][column];
          // pass the index of the array into the next empty one
          arrTracker += 1;
        }
      }
    }
    // return the array
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
    // row number of the userUserMatrix
    int rowNumber = userUserMatrix.length;
    // column number of the userUserMatrix
    int columnNumber = userUserMatrix[0].length;
    // size of the array
    int arrNum = rowNumber * rowNumber;
    // the array of locations
    String[] arrLocations = new String[arrNum];
    // tracks the array index
    int arrTracker = 0;
    // iterate row of userUserMatrix
    for (int row = 0; row < rowNumber; row++) {
      // iterate column of userUserMatrix
      for (int column = 0; column < columnNumber; column++) {
        // only adds location to the array past 1.0 self score column per row
        if (row < column) {
          // current index of the array is replaced with current location;
          // each location is represented as "first user" + "second user"
          arrLocations[arrTracker] =
              // pass the index of the array into the next empty one
              Integer.toString(row + 1) + Integer.toString(column + 1);
          arrTracker += 1;
        }
      }
    }
    // return the array
    return arrLocations;
  }
}
