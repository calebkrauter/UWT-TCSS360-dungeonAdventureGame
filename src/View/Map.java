package View;

public class Map {

//        # = walls
//        | = yPath
//        - = xPath
//        O = intersection
//        S = start
//        E = end
//        [ = door
//        All are rooms, O, S, E and [ are functionally the same but have different
//        rules on whether they are locked

    // 5 x 5 room example
    protected final String[][] myMap = new String[][]{
        {"-", "-", "#", "#", "#"},
        {"#", "S", "O", "O", "#"},
        {"#", "#", "|", "|", "#"},
        {"#", "-", "O", "E", "#"},
        {"#", "#", "#", "#", "#"}
    };

    public Map(){

    }

    public String[][] getMap() {
        return myMap.clone();
    }



}
