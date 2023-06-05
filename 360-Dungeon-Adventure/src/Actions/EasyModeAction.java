package Actions;

public class EasyModeAction {
    private static boolean myEasyGameMode = false;
    public EasyModeAction() {

    }

    public void setEasyMode(boolean theMode) {
        myEasyGameMode = theMode;
        System.out.println("The mode is easy: " + myEasyGameMode);

    }
    public boolean getEasyMode() {
        return myEasyGameMode;
    }
}