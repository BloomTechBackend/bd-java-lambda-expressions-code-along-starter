import com.bloomtech.unit6.codealong.learnerscoreapp.LearnerScoreProcessingApplication;

import java.io.FileNotFoundException;
/********************************************************************************************
 * Application manager
 *
 * This is the application program because it has the main()
 ********************************************************************************************/
public class ApplicationProgram {
        public static void main(String args[]) throws FileNotFoundException {
                // instantiate an instance of the application to be run
                LearnerScoreProcessingApplication aLearnerScoreProcessingApplication = new LearnerScoreProcessingApplication();

                // invoke the application controller to start the application
                aLearnerScoreProcessingApplication.run();
        }
}
