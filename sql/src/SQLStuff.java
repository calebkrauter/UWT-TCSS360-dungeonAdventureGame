import org.sqlite.SQLiteDataSource;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLStuff {
    private SQLiteDataSource myDS;
    private String initialQuery;
    public SQLStuff() {
        setupTable();
        myDS = null;
        getDBConnection();
        // DELETES the table contents so that it doesn't duplicate.
        updateTableWithQuery("DELETE FROM monsterData");
        updateTableWithQuery(initialQuery);
        fillTable();
        displayTable();
        printTestData();
    }
    private void setupTable() {
        initialQuery = "CREATE TABLE IF NOT EXISTS monsterData ( " +
                "MONSTER_TYPE TEXT NOT NULL, " +
                "IMAGE_FILE NAME TEXT NOT NULL, " +
                "HIT_CHANCE TEXT NOT NULL, " +
                "MIN_DAMAGE TEXT NOT NULL, " +
                "MAX_DAMAGE TEXT NOT NULL, " +
                "DEFAULT_HEALTH TEXT NOT NULL)";
    }

    public void getDBConnection() {
        try {
            myDS = new SQLiteDataSource();
            myDS.setUrl("jdbc:sqlite:src/SQLRes/monsterData.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
// Ogre, skeleton, gremlin

    private void updateTableWithQuery(String theQuery) {
        // TEST Table build
        try (Connection connection = myDS.getConnection();
             Statement statement = connection.createStatement()) {
            int returnValue = statement.executeUpdate(theQuery);
//            System.out.println("executeUpdate() returned " + returnValue);
        } catch (SQLException e) {
             e.printStackTrace();
             System.exit(0);
        }
//        System.out.println( "Created table successfully" );
    }

    private void fillTable() {
        String gremlinQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('Gremlin', " +
                "'res/Monster/Gremlin.png', " +
                "'80', " +
                "'30', " +
                "'60', " +
                "'150')";
        String skeletonQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('Skeleton', " +
                "'res/Monster/Skeleton.png', " +
                "'50', " +
                "'50', " +
                "'65', " +
                "'200')";
        String ogreQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('Ogre', " +
                "'res/Monster/Ogre.png', " +
                "'80', " +
                "'30', " +
                "'55', " +
                "'100')";

        updateTableWithQuery(gremlinQuery);
        updateTableWithQuery(skeletonQuery);
        updateTableWithQuery(ogreQuery);

    }

    ArrayList<ArrayList<Object>> myMonsters;
    ArrayList<Object> myMonsterStats;
    // These fields are made public because they are codes
    // that are intended to be used outside of this class. They have a single
    // purpose which is clearly defined as being an index to arraylists.
    // Because they are necessary outside of this class and misusing them would not
    // likely result in misuse of the program functionality, they are made public to be
    // used more conveniently outside of this class.
    public static final int MONSTER_TYPE = 0;
    public static final int IMAGE_FILE = 1;
    public static final int HIT_CHANCE = 2;
    public static final int MIN_DAMAGE = 3;
    public static final int MAX_DAMAGE = 4;
    public static final int DEFAULT_HEALTH = 5;
    public static final int GREMLIN = 0;
    public static final int SKELTON = 1;
    public static final int OGRE = 2;

    private void monsterData() {
        for (ArrayList<Object> monster: myMonsters) {
            System.out.println();
            for (Object stat: monster) {
                System.out.print(stat + ", ");
            }
        }
    }

    public void setMonsterData(int theMonsterCode, int theStatCode, Object theStat) {
        myMonsters.get(theMonsterCode).set(theStatCode, theStat);
    }
    public Object getMonsterData(int theMonsterCode, int theStatCode) {
        return myMonsters.get(theMonsterCode).get(theStatCode);
    }

    // This method is to test that the correct data is being accessed acurately.
    public void printTestData() {
        System.out.println();
        System.out.println();

        String theStat = getMonsterData(SKELTON, MIN_DAMAGE).toString();
        System.out.println("Stat from getter SKELTON, MIN_DAMAGE : " + theStat);

        System.out.println();
        System.out.println();
        setMonsterData(SKELTON, MIN_DAMAGE, 10);
        theStat = getMonsterData(SKELTON, MIN_DAMAGE).toString();
        System.out.println("updated stat SKELTON, MIN_DAMAGE, : " + theStat);
    }
    private void displayTable() {
        System.out.println("TABLE");
        initialQuery = "SELECT * FROM monsterData";
        myMonsters = new ArrayList<>();
        try (Connection connection = myDS.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(initialQuery);
            System.out.println("MONSTER_TYPE, IMAGE_FILE, HIT_CHANCE, MIN_DAMAGE, MAX_DAMAGE, DEFAULT_HEALTH");
            int theMonster = 0;
            while (resultSet.next()) {
                myMonsterStats = new ArrayList<>();
                String monsterType = resultSet.getString("MONSTER_TYPE");
                String imageFile = resultSet.getString("IMAGE_FILE");
                String hitChance = resultSet.getString("HIT_CHANCE");
                String minDamage = resultSet.getString("MIN_DAMAGE");
                String maxDamage = resultSet.getString("MAX_DAMAGE");
                String defaultHealth = resultSet.getString("DEFAULT_HEALTH");

                myMonsterStats.add(monsterType);
                myMonsterStats.add(imageFile);
                myMonsterStats.add(hitChance);
                myMonsterStats.add(minDamage);
                myMonsterStats.add(maxDamage);
                myMonsterStats.add(defaultHealth);

                myMonsters.add(myMonsterStats);
                System.out.print(
                        monsterType + ", "
                        + imageFile + ", "
                        + hitChance + ", "
                        + minDamage + ", "
                        + maxDamage + ", "
                        + defaultHealth);
                System.out.println();
                theMonster++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        monsterData();
    }
}
