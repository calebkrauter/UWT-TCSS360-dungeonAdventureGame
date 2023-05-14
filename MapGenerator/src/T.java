public class T {
    /*
                neg x
                    - distance uses MIN COL and theCol
                    - randomIntersect uses theCol - distance and the col
                    - the for loop uses theCol - distance and thecol
                    - mapLayout uses theRow and i
                    - randomDoorSpace uses theCol - distance and thecol
                    - setmyintersection uses therow and randomintersect

                        randomDoorSpace = random.nextInt(theCol - distance, theCol);
                        setMyCurDoorRoom(theRow, randomDoorSpace);
                 */
                /*
                pos X
                    - distance uses theCol + 1 and Max Col in bounds
                    - randomIntersect uses theCol and the distance
                    - the for loop uses theCol + 1 and the distance
                    - mapLayout uses theRow and i
                    - randomDoorSpace uses theCol and the distance
                    - setmyintersection uses the row and randomintersect

                    randomDoorSpace = random.nextInt(theCol, distance);
                    setMyCurDoorRoom(theRow, randomDoorSpace);
                 */
                /*
                neg Y
                    - distance uses MIN_ROW_IN_BOUNDS and theRow
                    - randomIntersect uses theRow - distance and theRow
                    - the for loop uses theRow - distance and theRow
                    - mapLayout uses i and theCol
                    - randomDoorSpace uses theRow - distance and theRow
                    - setmyintersection uses randomIntersect and  theCol

                    randomDoorSpace = random.nextInt(theRow - distance, theRow);
                    setMyCurDoorRoom(randomDoorSpace, theCol);
                 */
                /*
                pos Y
                    - distance uses theRow + 1 and MAX_ROW_IN_BOUNDS
                    - randomIntersect uses theRow and distance
                    - the for loop uses theRow + 1 and distance
                    - mapLayout uses i and theCol
                    - randomDoorSpace uses theRow and distance
                    - setmyintersection uses randomIntersect and theCol

                        randomDoorSpace = random.nextInt(theRow, distance);
                        setMyCurDoorRoom(randomDoorSpace, theCol);

                 */
}
