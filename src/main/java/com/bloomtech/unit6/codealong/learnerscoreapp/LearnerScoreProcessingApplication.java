package com.bloomtech.unit6.codealong.learnerscoreapp;

import com.bloomtech.unit6.codealong.emojis.Emogis;
import com.bloomtech.unit6.codealong.exceptions.DataFileErrorException;
import com.bloomtech.unit6.codealong.exceptions.InvalidMenuResponseException;
import com.bloomtech.unit6.codealong.types.Learner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
/********************************************************************************************
 * Class representing the Addams Family members and various manipulation methods
 ********************************************************************************************/
public class LearnerScoreProcessingApplication {

        /********************************************************************************************
        *  Keyboard object to get user input
        ********************************************************************************************/
        private static Scanner userKeyboardDevice = new Scanner(System.in);

        /********************************************************************************************
        * Constants representing menu options
        ********************************************************************************************/
        private static final String DISPLAY_ALL_LEARNERS_OPTION     = "Display all Learners";
        private static final String REFRESH_DATA_FROM_FILE          = "Refresh data from file";
        private static final String SORT_BY_ID                      = "Sort by Id";
        private static final String SORT_BY_NAME                    = "Sort by Name";
        private static final String SORT_BY_TRACK                   = "Sort by Track";
        private static final String SORT_BY_TRACK_AND_NAME          = "Sort by Track and Name";
        private static final String SORT_BY_TOTAL_SCORE             = "Sort by Total Score";
        private static final String SORT_BY_AVG_SCORE               = "Sort by Average Score";
        private static final String EXIT_OPTION                     = "Exit";

        /********************************************************************************************
        * Array of menu options display to users as needed
        ********************************************************************************************/
        private static final String[] mainMenuOptions = { DISPLAY_ALL_LEARNERS_OPTION
                                                        , SORT_BY_NAME
                                                        , SORT_BY_ID
                                                        , SORT_BY_TRACK
                                                        , SORT_BY_TRACK_AND_NAME
                                                        , SORT_BY_TOTAL_SCORE
                                                        , SORT_BY_AVG_SCORE
                                                        , REFRESH_DATA_FROM_FILE
                                                        , EXIT_OPTION
                                                        };
        /********************************************************************************************
         * List of Addams Family members
         ********************************************************************************************/
        private List<Learner> learners;

        /********************************************************************************************
         * Constructor for this application
         ********************************************************************************************/
        public LearnerScoreProcessingApplication() throws FileNotFoundException {
                // Note: use of LinkedList rather than ArrayList due to efficiency when adding/removing
                learners = new LinkedList<>();  // Instantiate structure to hold learners
                loadLearnerDataFromFile();      // Load data structure with learners in a file
        }
        /********************************************************************************************
         * Application controller
         *
         * This is the method called to actually run the application
         ********************************************************************************************/
        public void run() throws FileNotFoundException {

                startOfApplicationProcessing();           // Display greeting
                String whatTheyChose = new String("");    // Hold response from user prompt
                boolean shouldLoop = true;                // Main processing loop control variable
                /********************************************************************************************
                 * main processing loop
                 ********************************************************************************************/
                while (shouldLoop) {
                    try {
                        whatTheyChose = displayMenuAndGetResponse();          // Display main menu and get response
                        System.out.println("\nYou chose: " + whatTheyChose);  // Display menu option chosen

                        switch (whatTheyChose) {                              // Process based on menu option chosen
                               case DISPLAY_ALL_LEARNERS_OPTION: {
                                    displayAllLearners();
                                    break;
                               }
                               case SORT_BY_NAME: {
                                    learners.sort(Learner::compareByName);
                                    displayAllLearners();
                                    break;
                               }
                               case SORT_BY_ID: {
                                    learners.sort(Learner::compareById);
                                    displayAllLearners();
                                    break;
                               }
                               case SORT_BY_TRACK: {
                                    learners.sort(Learner::compareByTrack);
                                    displayAllLearners();
                                    break;
                               }
                               case SORT_BY_TRACK_AND_NAME: {
                                    learners.sort(Learner::compareByTrackAndName);
                                    displayAllLearners();
                                    break;
                               }
                               case SORT_BY_TOTAL_SCORE: {
                                    learners.sort(Learner::compareByTotalScore);
                                    displayAllLearners();
                                    break;
                               }
                               case SORT_BY_AVG_SCORE:_SCORE: {
                                    learners.sort(Learner::compareByAvgScore);
                                    displayAllLearners();
                                    break;
                               }
                               case REFRESH_DATA_FROM_FILE: {
                                    learners.removeAll(learners);  // Remove all app data structure current entries
                                    loadLearnerDataFromFile();                 // Reload app data structure
                                    break;
                               }
                               case EXIT_OPTION: {
                                    shouldLoop = false;
                                    break;
                               }
                               default: {    // if somehow an unexpected option was returned - throw an exception
                                    throw new InvalidMenuResponseException("Invalid menu option " + whatTheyChose + " entered: ");
                               }
                          } // end of switch
                      } // end of try
                      catch(InvalidMenuResponseException exceptionObject){
                            System.out.println("\nUh-Oh, Looks like you entered an invalid response, please try again");
                      }
                } // end of while
                endOfApplicationProcessing();     // Perform any clean up at end of the application
        }  // End of main processing method - run()

/**********************************************************************************************************
 * main processing helper methods
 *********************************************************************************************************/

        /********************************************************************************************
         * Display main menu, get response and return response
         ********************************************************************************************/
        public String displayMenuAndGetResponse () {

                int response = -1;  // initialze response to invalid value to be sure we store what user enters

                System.out.println("\nHello! WattaYaWannaDo? (enter number of option)\n");

                for (int i = 0; i < mainMenuOptions.length; i++) {              // Loop through menu option array
                        System.out.println(i + 1 + ". " + mainMenuOptions[i]);  //     display a menu option
                }
                System.out.print("\nYour choice: ");                               // Ask user for choice
                try {
                        response = Integer.parseInt(userKeyboardDevice.nextLine());// Get user choice and convert to int value
                        return mainMenuOptions[response - 1];                      // Return menu option from option array
                } catch (NumberFormatException exceptionObject) {
                        throw new InvalidMenuResponseException("Invalid menu option " + response + " entered: ");
                } catch (ArrayIndexOutOfBoundsException exceptionObject) {
                        throw new InvalidMenuResponseException("Invalid menu option " + response + " entered");
                }
        }  // End of displayMenuAndGetResponse()

         /********************************************************************************************
          * Starting of application setup processing - display welcome screen
          ********************************************************************************************/
         public void startOfApplicationProcessing () throws FileNotFoundException {

            // Send any error messages to a file rather than screen
            // 1. Instantiate a PrintStream object for the file to hold error messdage
            PrintStream fileProcessingErrorLogFile = new PrintStream("fileProcessingError.log");
            // 2. Tell Java to send all error message to the PrintStream file created in step 1 using setErr()
            System.setErr(fileProcessingErrorLogFile);

            // Display a welcome message
            System.out.println(Emogis.CLOSED_BOOK.repeat(40));
            System.out.printf("%1s %s \n", Emogis.CLOSED_BOOK, "Welcome to the Learner Score Processing app!");
            System.out.println(Emogis.CLOSED_BOOK.repeat(40));
         }  // end of startOfApplicationProcessing

         /********************************************************************************************
          * End of application takedown processing - display goodbye message
          ********************************************************************************************/
         public void endOfApplicationProcessing () {
                System.out.println(Emogis.WAVING_HAND_SIGN.repeat(25) + "\n"+Emogis.WAVING_HAND_SIGN+"Thank you for using our app!\n" + Emogis.WAVING_HAND_SIGN.repeat(25));
         }

         /********************************************************************************************
          * Display a entries in data stucture holding application data
          ********************************************************************************************/
         public void displayAllLearners() {
            int LearnerCount = 0;
            String borderIcon = Emogis.WOMAN_STUDENT;

            System.out.println("\n"+ (borderIcon+" ").repeat(25)) ;

            System.out.printf("%s %7s %-18s %5s %-20s %10s %10s\n", borderIcon
                                                                  , "  ID "
                                                                  , "     Name"
                                                                  , "Track"
                                                                  , "      Scores      "
                                                                  , "Tot Score"
                                                                  , "Avg Score");
            Iterator<Learner> allLearnerIterator = learners.iterator();
            while(allLearnerIterator.hasNext()) {            // Loop while the iterator has a next element to process
                 Learner aLearner = allLearnerIterator.next(); // Get the next element from the iterator
                 LearnerCount++;
                 System.out.printf("%s %7d %-18s %-6s %-20s %9d %10.2f",borderIcon
                                                                       ,aLearner.getId()
                                                                       ,aLearner.getName()
                                                                       ,aLearner.getTrack()
                                                                       ,aLearner.getScoresAsString()
                                                                       ,aLearner.getTotalScore()
                                                                       ,aLearner.getAverageScore()
                                                                       );
                 if (LearnerCount != learners.size())  {
                     System.out.println("");
                 }
            }  // end of while
            System.out.println("\n"+ (borderIcon + " ").repeat(25)) ;
        } // end of displayAllLearners
       
        /********************************************************************************************
         * Load application data structure from data in a file
         ********************************************************************************************/
        private void loadLearnerDataFromFile() throws FileNotFoundException, DataFileErrorException {

            String aLine = null;                              // Hold a line from the file
            String LEARNERS_FILE_NAME = "LearnerData.txt";    // Name of file holding data to be loaded
            File learnersFile = null;                         // File object to represent file to be loaded
            Scanner learnerFileReader  = null;                // Scanner object to read the file

            try {
                learnersFile       = new File(LEARNERS_FILE_NAME);    // Instantiate File object resprenenting data
                learnerFileReader  = new Scanner(learnersFile);       // Instantiate Scanner to read file
                while (learnerFileReader.hasNextLine()) {             // Loop as long as there is data in the file
                      aLine = learnerFileReader.nextLine().strip(); //      Get a line from the file and store it
                      String[] learnerData = aLine.split(",");      //      Parse values in line based on ,
                      learners.add(new Learner(learnerData[0]       //      Add to List with  input track
                                              ,Integer.parseInt(learnerData[1]) //      and  input id
                                              ,learnerData[2]                   //      and  input name and
                                              ,Arrays.asList(Integer.parseInt(learnerData[3]) // input scores
                                              ,Integer.parseInt(learnerData[4])               //    converted
                                              ,Integer.parseInt(learnerData[5])               //       to a
                                              ,Integer.parseInt(learnerData[6])))             //         list
                                   );   // end of .add for learners list
                }  // end of while
            }  // end of try
            catch(FileNotFoundException exceptionObj) {
                 System.err.println(exceptionObj.getMessage());
                 exceptionObj.printStackTrace();
                 throw new DataFileErrorException(LEARNERS_FILE_NAME + " not found - see error log for details");
            }
            catch (IllegalStateException exceptionObject) {
                  System.err.println("Error processing learners file: " + LEARNERS_FILE_NAME);
                  System.err.println("Call stack:");
                  exceptionObject.printStackTrace();
            }
            finally {   // Whether there is an exception or not...
                  learnerFileReader.close();
            }
        }  // End of loadMembers()
} // end of AddamsFamilyApplication class
