import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLStuff {
    private SQLiteDataSource myDS;
    private String initialQuery;
    public SQLStuff() {
        setupTable();
        myDS = null;
        getDBConnection();
        updateTableWithQuery(initialQuery);
        fillTable();
        displayTable();
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
            System.out.println("executeUpdate() returned " + returnValue);
        } catch (SQLException e) {
             e.printStackTrace();
             System.exit(0);
        }
        System.out.println( "Created table successfully" );
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
        updateTableWithQuery(gremlinQuery);
    }

    private void displayTable() {
        System.out.println("TABLE");
        initialQuery = "SELECT * FROM monsterData";

        try (Connection connection = myDS.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(initialQuery);
            System.out.println("MONSTER_TYPE, IMAGE_FILE, HIT_CHANCE, MIN_DAMAGE, MAX_DAMAGE, DEFAULT_HEALTH");
            while (resultSet.next()) {
                String monsterType = resultSet.getString("MONSTER_TYPE");
                String imageFile = resultSet.getString("IMAGE_FILE");
                String hitChance = resultSet.getString("HIT_CHANCE");
                String minDamage = resultSet.getString("MIN_DAMAGE");
                String maxDamage = resultSet.getString("MAX_DAMAGE");
                String defaultHealth = resultSet.getString("DEFAULT_HEALTH");

                System.out.print(
                        monsterType + ", "
                        + imageFile + ", "
                        + hitChance + ", "
                        + minDamage + ", "
                        + maxDamage + ", "
                        + defaultHealth);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
