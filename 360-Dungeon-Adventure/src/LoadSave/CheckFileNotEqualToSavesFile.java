package LoadSave;

public class CheckFileNotEqualToSavesFile {

    static int i = 0;

    public String checkThatFileIsNotSavesFile(String theGameStateFile) {
        StringBuilder sb = new StringBuilder();
        if (theGameStateFile.equals("game-saves.ser")) {
            i++;
            sb.append(theGameStateFile);
            int indexOfDot = theGameStateFile.lastIndexOf('.');
            sb.insert(indexOfDot, i);
            theGameStateFile = sb.toString();
//            System.out.println(theGameStateFile);
        }
        return theGameStateFile;
    }
}
