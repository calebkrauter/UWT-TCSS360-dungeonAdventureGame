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
    // Ogre, skeleton, gremlin
// Should I make these static?
    private ArrayList<ArrayList<Object>> myMonsters;
    private ArrayList<Object> myMonsterStats;
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
    public SQLStuff() {
        setupTable();
        myDS = null;
        getDBConnection();
        // DELETES the table contents so that it doesn't duplicate.
        updateTableWithQuery("DELETE FROM monsterData");
        updateTableWithQuery(initialQuery);
        initTable();
        fillTable();
        displayTable();
//        printTestData();
        // For testing
        setMonsterData(SKELTON, IMAGE_FILE, "LOLOL");
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

    public void fillTable() {
        // This setter is used here to prove that data from the arraylist
        // is getting put in the data base so that when it prints it should update to HERO.
//        setMonsterData(GREMLIN, MONSTER_TYPE, "HERO");
        String gremlinQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('" + getMonsterData(GREMLIN, MONSTER_TYPE) + "', " +
                "'" + getMonsterData(GREMLIN, IMAGE_FILE) + "', " +
                "'" + getMonsterData(GREMLIN, HIT_CHANCE) + "', " +
                "'" + getMonsterData(GREMLIN, MIN_DAMAGE) + "', " +
                "'" + getMonsterData(GREMLIN, MAX_DAMAGE) + "', " +
                "'" + getMonsterData(GREMLIN, DEFAULT_HEALTH) + "'" +
                ")";
        String skeletonQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('" + getMonsterData(SKELTON, MONSTER_TYPE) + "', " +
                "'" + getMonsterData(SKELTON, IMAGE_FILE) + "', " +
                "'" + getMonsterData(SKELTON, HIT_CHANCE) + "', " +
                "'" + getMonsterData(SKELTON, MIN_DAMAGE) + "', " +
                "'" + getMonsterData(SKELTON, MAX_DAMAGE) + "', " +
                "'" + getMonsterData(SKELTON, DEFAULT_HEALTH) + "'" +
                ")";
        String ogreQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('" + getMonsterData(OGRE, MONSTER_TYPE) + "', " +
                "'" + getMonsterData(OGRE, IMAGE_FILE) + "', " +
                "'" + getMonsterData(OGRE, HIT_CHANCE) + "', " +
                "'" + getMonsterData(OGRE, MIN_DAMAGE) + "', " +
                "'" + getMonsterData(OGRE, MAX_DAMAGE) + "', " +
                "'" + getMonsterData(OGRE, DEFAULT_HEALTH) + "'" +
                ")";

        updateTableWithQuery(gremlinQuery);
        updateTableWithQuery(skeletonQuery);
        updateTableWithQuery(ogreQuery);

    }

    ArrayList<ArrayList<Object>> initMonsters;
    private void initTable() {
        initMonsters = new ArrayList<>();
        ArrayList<Object> gremlin = new ArrayList<>();
        ArrayList<Object> skeleton = new ArrayList<>();
        ArrayList<Object> ogre = new ArrayList<>();
        gremlin.add("Gremlin");
        gremlin.add("res/Monster/Gremlin.png");
        gremlin.add(80);
        gremlin.add(30);
        gremlin.add(60);
        gremlin.add(150);
        initMonsters.add(gremlin);
        skeleton.add("Skeleton");
        skeleton.add("res/Monster/skeleton.png");
        skeleton.add(50);
        skeleton.add(50);
        skeleton.add(65);
        skeleton.add(200);
        initMonsters.add(skeleton);
        ogre.add("Ogre");
        ogre.add("res/Monster/Ogre.png");
        ogre.add(80);
        ogre.add(30);
        ogre.add(55);
        ogre.add(100);
        initMonsters.add(ogre);

        myMonsters = initMonsters;
        for (ArrayList<Object> monster: myMonsters) {
            myMonsterStats = monster;
        }

//        System.out.println(myMonsters.get(GREMLIN).get(IMAGE_FILE));
    }

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
        updateTableWithQuery("DELETE FROM monsterData");
        fillTable();
    }
    public Object getMonsterData(int theMonsterCode, int theStatCode) {
        return myMonsters.get(theMonsterCode).get(theStatCode);
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
                        myMonsters.get(theMonster).get(MONSTER_TYPE) + ", "
                        + myMonsters.get(theMonster).get(IMAGE_FILE) + ", "
                        + myMonsters.get(theMonster).get(HIT_CHANCE) + ", "
                        + myMonsters.get(theMonster).get(MIN_DAMAGE) + ", "
                        + myMonsters.get(theMonster).get(MAX_DAMAGE) + ", "
                        + myMonsters.get(theMonster).get(DEFAULT_HEALTH));
                System.out.println();
                theMonster++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        monsterData();
    }
}
