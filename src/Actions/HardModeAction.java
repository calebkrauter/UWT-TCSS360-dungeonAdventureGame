
package Actions;

public class HardModeAction {
    static boolean myHardGameMode = false;
    public HardModeAction() {

    }

    public void setHardMode(boolean theMode) {
        myHardGameMode = theMode;
        System.out.println("The mode is hard: " + myHardGameMode);
    }
    public boolean getHardMode() {
        return myHardGameMode;
    }

}