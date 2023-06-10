package Components;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Modifies the insets.
 */
public class ModifyInsets {

    /**
     * An inset.
     */
    private int myInsetTop = 0;
    /**
     * An inset.
     */
    private int myInsetLeft = 0;
    /**
     * An inset.
     */
    private int myInsetBottom = 0;
    /**
     * An inset.
     */
    private int myInsetRight = 0;

    /**
     * A constructor.
     */
    public ModifyInsets() {
    }

    /**
     * Sets the insets.
     * @param theInsetTop
     * @param theInsetLeft
     * @param theInsetBottom
     * @param theInsetRight
     */
    public void setInsets(int theInsetTop, int theInsetLeft, int theInsetBottom, int theInsetRight) {
        this.setMyInsetTop(theInsetTop);
        this.setMyInsetLeft(theInsetLeft);
        this.setMyInsetBottom(theInsetBottom);
        this.setMyInsetRight(theInsetRight);
    }

    /**
     * Sets theInsetTop.
     * @param theInsetTop
     */
    private void setMyInsetTop(int theInsetTop) {
        this.myInsetTop = theInsetTop;
    }
    /**
     * Sets theInsetLeft.
     * @param theInsetLeft
     */
    private void setMyInsetLeft(int theInsetLeft) {
        this.myInsetLeft = theInsetLeft;
    }
    /**
     * Sets theInsetBottom.
     * @param theInsetBottom
     */
    private void setMyInsetBottom(int theInsetBottom) {
        this.myInsetBottom = theInsetBottom;
    }
    /**
     * Sets theInsetRight.
     * @param theInsetRight
     */
    private void setMyInsetRight(int theInsetRight) {
        this.myInsetRight = theInsetRight;
    }
    /**
     * Gets myInsetTop
     * @return myInsetTop
     */
    public int getMyInsetTop() {
        return this.myInsetTop;
    }
    /**
     * Gets myInsetLeft
     * @return myInsetLeft
     */
    public int getMyInsetLeft() {
        return this.myInsetLeft;
    }
    /**
     * Gets myInsetBottom
     * @return myInsetBottom
     */
    public int getMyInsetBottom() {
        return this.myInsetBottom;
    }
    /**
     * Gets myInsetRight
     * @return myInsetRight
     */
    public int getMyInsetRight() {
        return this.myInsetRight;
    }
}
