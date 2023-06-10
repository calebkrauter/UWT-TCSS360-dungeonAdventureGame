package Controller.DB;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * @author Caleb Krauter
 * @version 1.0
 */

/**
 * Sets up the monster database.
 */
public class MonsterStatsDB {
    /**
     * The data source.
     */
    private SQLiteDataSource myDS;
    /**
     * The initial query.
     */
    private String initialQuery;
    /**
     * Monsters.
     */
    private ArrayList<ArrayList<Object>> myMonsters;
    /**
     * Stats for monsters.
     */
    private ArrayList<Object> myMonsterStats;
    /**
     * Initial monsters.
     */
    private ArrayList<ArrayList<Object>> initMonsters;

    // These fields are made public because they are codes
    // that are intended to be used outside of this class. They have a single
    // purpose which is clearly defined as being an index to arraylists.
    // Because they are necessary outside of this class and misusing them would not
    // likely result in misuse of the program functionality, they are made public to be
    // used more conveniently outside of this class.
    /**
     * Stat code for MONSTER_TYPE.
     */
    public static final int MONSTER_TYPE = 0;
    /**
     * Stat code for IMAGE_FILE.
     */
    public static final int IMAGE_FILE = 1;
    /**
     * Stat code for HIT_CHANCE.
     */
    public static final int HIT_CHANCE = 2;
    /**
     * Stat code for MIN_DAMAGE.
     */
    public static final int MIN_DAMAGE = 3;
    /**
     * Stat code for MAX_DAMAGE.
     */
    public static final int MAX_DAMAGE = 4;
    /**
     * Stat code for DEFAULT_HEALTH.
     */
    public static final int DEFAULT_HEALTH = 5;
    /**
     * Stat code for GREMLIN.
     */
    public static final int GREMLIN = 0;
    /**
     * Stat code for SKELTON.
     */
    public static final int SKELTON = 1;
    /**
     * Stat code for OGRE.
     */
    public static final int OGRE = 2;

    /**
     * Constructor
     */
    public MonsterStatsDB() {
        setupInitTableQuery();
        myDS = null;
        getDBConnection();
        updateTableWithQuery("DELETE FROM monsterData");
        updateTableWithQuery(initialQuery);
        initStatsForDB();
        fillTable();
        retrieveDataFromDB();
    }

    /**
     * Sets up table.
     */
    private void setupInitTableQuery() {
        initialQuery = "CREATE TABLE IF NOT EXISTS monsterData ( " +
                "MONSTER_TYPE TEXT NOT NULL, " +
                "IMAGE_FILE NAME TEXT NOT NULL, " +
                "HIT_CHANCE TEXT NOT NULL, " +
                "MIN_DAMAGE TEXT NOT NULL, " +
                "MAX_DAMAGE TEXT NOT NULL, " +
                "DEFAULT_HEALTH TEXT NOT NULL)";
    }

    /**
     * Gets the connection.
     */
    private void getDBConnection() {
        try {
            myDS = new SQLiteDataSource();
            myDS.setUrl("jdbc:sqlite:src/Controller/DB/SQLRes/monsterData.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Updates the table and tests that it is set up.
     * @param theQuery
     */
    private void updateTableWithQuery(String theQuery) {
        try (Connection connection = myDS.getConnection();
             Statement statement = connection.createStatement()) {
            int returnValue = statement.executeUpdate(theQuery);
            System.out.println("executeUpdate() returned " + returnValue);
        } catch (SQLException e) {
             e.printStackTrace();
             System.exit(0);
        }
        System.out.println( "Created table successfully" );
    }

    /**
     * Fills the table.
     */
    private void fillTable() {;
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

    /**
     * Default data for monsters.
     */
    private void initStatsForDB() {
        initMonsters = new ArrayList<>();
        ArrayList<Object> gremlin = new ArrayList<>();
        ArrayList<Object> skeleton = new ArrayList<>();
        ArrayList<Object> ogre = new ArrayList<>();
        gremlin.add("Gremlin");
        gremlin.add("res/Monster/Gremlin.png");
        gremlin.add(80);
        gremlin.add(25);
        gremlin.add(35);
        gremlin.add(150);
        initMonsters.add(gremlin);
        skeleton.add("Skeleton");
        skeleton.add("res/Monster/skeleton.png");
        skeleton.add(50);
        skeleton.add(40);
        skeleton.add(60);
        skeleton.add(200);
        initMonsters.add(skeleton);
        ogre.add("Ogre");
        ogre.add("res/Monster/Ogre.png");
        ogre.add(80);
        ogre.add(15);
        ogre.add(25);
        ogre.add(100);
        initMonsters.add(ogre);

        myMonsters = initMonsters;
        for (ArrayList<Object> monster: myMonsters) {
            myMonsterStats = monster;
        }
    }

    /**
     * Prints table.
     */
    public void printMonsterData() {
        for (ArrayList<Object> monster: myMonsters) {
            System.out.println();
            for (Object stat: monster) {
                System.out.print(stat + ", ");
            }
        }
    }

    /**
     * Sets monster stat.
     * @param theMonsterCode
     * @param theStatCode
     * @param theStat
     */
    public void setMonsterStat(int theMonsterCode, int theStatCode, Object theStat) {
        myMonsters.get(theMonsterCode).set(theStatCode, theStat);
        updateTableWithQuery("DELETE FROM monsterData");
        fillTable();
    }
    /**
     * Gets monster stat.
     * @param theMonsterCode
     * @param theStatCode
     * @return
     */
    public Object getMonsterStat(int theMonsterCode, int theStatCode) {
        return myMonsters.get(theMonsterCode).get(theStatCode);
    }

    /**
     * Retrieves data from DB.
     */
    private void retrieveDataFromDB() {
        initialQuery = "SELECT * FROM monsterData";
        myMonsters = new ArrayList<>();
        try (Connection connection = myDS.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(initialQuery);
            System.out.println("MONSTER_TYPE, IMAGE_FILE, HIT_CHANCE, MIN_DAMAGE, MAX_DAMAGE, DEFAULT_HEALTH");
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        printMonsterData();
    }
}
