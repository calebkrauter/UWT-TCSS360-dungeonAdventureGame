
package Components;

public class ModifyInsets {
    private int myIPadX = 50;
    private int myIPadY = 10;
    private int myInsetTop = 0;
    private int myInsetLeft = 0;
    private int myInsetBottom = 0;
    private int myInsetRight = 0;

    public ModifyInsets() {
    }

    public void setInsets(int theInsetTop, int theInsetLeft, int theInsetBottom, int theInsetRight) {
        this.setMyInsetTop(theInsetTop);
        this.setMyInsetLeft(theInsetLeft);
        this.setMyInsetBottom(theInsetBottom);
        this.setMyInsetRight(theInsetRight);
    }

    private void setMyInsetTop(int theInsetTop) {
        this.myInsetTop = theInsetTop;
    }

    private void setMyInsetLeft(int theInsetLeft) {
        this.myInsetLeft = theInsetLeft;
    }

    private void setMyInsetBottom(int theInsetBottom) {
        this.myInsetBottom = theInsetBottom;
    }

    private void setMyInsetRight(int theInsetRight) {
        this.myInsetRight = theInsetRight;
    }

    public int getMyInsetTop() {
        return this.myInsetTop;
    }

    public int getMyInsetLeft() {
        return this.myInsetLeft;
    }

    public int getMyInsetBottom() {
        return this.myInsetBottom;
    }

    public int getMyInsetRight() {
        return this.myInsetRight;
    }
}
