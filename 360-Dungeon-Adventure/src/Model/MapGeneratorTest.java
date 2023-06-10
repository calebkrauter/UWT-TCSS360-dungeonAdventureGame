package Model;

import static org.junit.jupiter.api.Assertions.*;

class MapGeneratorTest {

    MapGenerator mapGenerator = new MapGenerator(true);
    @org.junit.jupiter.api.Test
    void getMyStartCol() {
        assertEquals(mapGenerator.getMyStartCol(), 2);
    }

    @org.junit.jupiter.api.Test
    void getMyStartRow() {
        assertEquals(mapGenerator.getMyStartRow(), 2);
    }

    @org.junit.jupiter.api.Test
    void getMyEndCol() {
        assertEquals(mapGenerator.getMyEndCol(), 7);
    }

    @org.junit.jupiter.api.Test
    void getMyEndRow() {
        assertEquals(mapGenerator.getMyEndRow(), 7);
    }

    @org.junit.jupiter.api.Test
    void getMyCurIntersectionCol() {
        assertEquals(mapGenerator.getMyCurIntersectionCol(), 5);
    }

    @org.junit.jupiter.api.Test
    void getMap() {
    }

    @org.junit.jupiter.api.Test
    void getMyMaxRows() {
    }

    @org.junit.jupiter.api.Test
    void getMyMaxCols() {
    }
}