package Controller.DB;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * @author Caleb Krauter
 */
public class MonsterStatsDB {
    private SQLiteDataSource myDS;
    private String initialQuery;
    // Ogre, skeleton, gremlin
// Should I make these static?
    private ArrayList<ArrayList<Object>> myMonsters;
    private ArrayList<Object> myMonsterStats;
    private ArrayList<ArrayList<Object>> initMonsters;
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

    public MonsterStatsDB() {
        setupInitTableQuery();
        myDS = null;
        getDBConnection();
        // DELETES the table contents so that it doesn't duplicate.
        updateTableWithQuery("DELETE FROM monsterData");
        updateTableWithQuery(initialQuery);
        initStatsForDB();
        fillTable();
        retrieveDataFromDB();
//        printTestData();
        // For testing
//        setMonsterStat(SKELTON, IMAGE_FILE, "LOLOL");
    }

    private void setupInitTableQuery() {
        initialQuery = "CREATE TABLE IF NOT EXISTS monsterData ( " +
                "MONSTER_TYPE TEXT NOT NULL, " +
                "IMAGE_FILE NAME TEXT NOT NULL, " +
                "HIT_CHANCE TEXT NOT NULL, " +
                "MIN_DAMAGE TEXT NOT NULL, " +
                "MAX_DAMAGE TEXT NOT NULL, " +
                "DEFAULT_HEALTH TEXT NOT NULL)";
    }
    private void getDBConnection() {
        try {
            myDS = new SQLiteDataSource();
            myDS.setUrl("jdbc:sqlite:src/Controller/DB/SQLRes/monsterData.db");
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

    private void fillTable() {
        // This setter is used here to prove that data from the arraylist
        // is getting put in the data base so that when it prints it should update to HERO.
//        setMonsterStat(GREMLIN, MONSTER_TYPE, "HERO");
        String gremlinQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('" + getMonsterStat(GREMLIN, MONSTER_TYPE) + "', " +
                "'" + getMonsterStat(GREMLIN, IMAGE_FILE) + "', " +
                "'" + getMonsterStat(GREMLIN, HIT_CHANCE) + "', " +
                "'" + getMonsterStat(GREMLIN, MIN_DAMAGE) + "', " +
                "'" + getMonsterStat(GREMLIN, MAX_DAMAGE) + "', " +
                "'" + getMonsterStat(GREMLIN, DEFAULT_HEALTH) + "'" +
                ")";
        String skeletonQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('" + getMonsterStat(SKELTON, MONSTER_TYPE) + "', " +
                "'" + getMonsterStat(SKELTON, IMAGE_FILE) + "', " +
                "'" + getMonsterStat(SKELTON, HIT_CHANCE) + "', " +
                "'" + getMonsterStat(SKELTON, MIN_DAMAGE) + "', " +
                "'" + getMonsterStat(SKELTON, MAX_DAMAGE) + "', " +
                "'" + getMonsterStat(SKELTON, DEFAULT_HEALTH) + "'" +
                ")";
        String ogreQuery = "INSERT INTO monsterData (" +
                "MONSTER_TYPE, " +
                "IMAGE_FILE, " +
                "HIT_CHANCE, " +
                "MIN_DAMAGE, " +
                "MAX_DAMAGE, " +
                "DEFAULT_HEALTH)" +
                " VALUES " +
                "('" + getMonsterStat(OGRE, MONSTER_TYPE) + "', " +
                "'" + getMonsterStat(OGRE, IMAGE_FILE) + "', " +
                "'" + getMonsterStat(OGRE, HIT_CHANCE) + "', " +
                "'" + getMonsterStat(OGRE, MIN_DAMAGE) + "', " +
                "'" + getMonsterStat(OGRE, MAX_DAMAGE) + "', " +
                "'" + getMonsterStat(OGRE, DEFAULT_HEALTH) + "'" +
                ")";

        updateTableWithQuery(gremlinQuery);
        updateTableWithQuery(skeletonQuery);
        updateTableWithQuery(ogreQuery);

    }

    private void initStatsForDB() {
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

    public void printMonsterData() {
        for (ArrayList<Object> monster: myMonsters) {
            System.out.println();
            for (Object stat: monster) {
                System.out.print(stat + ", ");
            }
        }
    }

    public void setMonsterStat(int theMonsterCode, int theStatCode, Object theStat) {
        myMonsters.get(theMonsterCode).set(theStatCode, theStat);
        updateTableWithQuery("DELETE FROM monsterData");
        fillTable();
    }
    public Object getMonsterStat(int theMonsterCode, int theStatCode) {
        return myMonsters.get(theMonsterCode).get(theStatCode);
    }

    private void retrieveDataFromDB() {
//        System.out.println("TABLE");
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
//                System.out.print(
//                        myMonsters.get(theMonster).get(MONSTER_TYPE) + ", "
//                        + myMonsters.get(theMonster).get(IMAGE_FILE) + ", "
//                        + myMonsters.get(theMonster).get(HIT_CHANCE) + ", "
//                        + myMonsters.get(theMonster).get(MIN_DAMAGE) + ", "
//                        + myMonsters.get(theMonster).get(MAX_DAMAGE) + ", "
//                        + myMonsters.get(theMonster).get(DEFAULT_HEALTH));
//                System.out.println();
                theMonster++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        printMonsterData();
    }
}
