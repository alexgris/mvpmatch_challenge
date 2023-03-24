package ax.generic;

import init.settings.SeleniumSetUp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;

public class FileProcessing extends SeleniumSetUp {

    final static Logger logger = Logger.getLogger(FileProcessing.class);


    public static void deleteFilesFromFolder(String file_path){

        //Deleting old image files from 'current_images' folder
        File file = new File (file_path);
        String[] myFiles;
        if (file.isDirectory ( )) {

            try {
                myFiles = file.list ( );
                for (int i = 0; i < myFiles.length; i++) {
                    File myFile = new File (file, myFiles[i]);
                    myFile.delete ( );
                }
            } catch (Exception e) {
                logger.error ("Unable to delete old image files from '"+ file_path +"' folder: " + e.getMessage ( ));
            }
        }

    }


    public static void deleteOld_Files(String filepath, String filename) {

        //Deleting old files
        File file = new File(filepath);

        String[] myFiles;
        if (file.isDirectory()) {
            try {
                LogManager.resetConfiguration();
                myFiles = file.list();
                for (int l = 0; l < myFiles.length; l++) {
                    File myFile4 = new File(file, myFiles[l]);
                    myFile4.delete();
                }
            } catch (Exception e) {
                logger.error("Unable to delete old " + filename + " " + e.getMessage());
            }
        } else {

            logger.error("Unable to find old " + filename);

        }
    }

}