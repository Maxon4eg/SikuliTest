package utils;

import java.sql.*;

public class DBWorker {

    private final String JDBC_DRIVER = "org.sqlite.JDBC";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    private ResultSet getRs(String query) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection("jdbc:sqlite:" + Props.get("numberokData.path") + "NumberOk.db");
        stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }


    private String getStringData(String query, String column) throws ClassNotFoundException, SQLException {
        String result;
        System.out.println("Executing query :" + query);
        rs = getRs(query);
        if (!rs.next()) {
            return null;
        } else {
            result = rs.getString(column);
        }
        closeDb();
        return result;
    }

    private Integer getIntData(String query, String column) throws SQLException, ClassNotFoundException {
        Integer result;
        System.out.println("Executing query :" + query);
        rs = getRs(query);
        if (!rs.next()) {
            return null;
        } else {
            result = rs.getInt(column);
        }
        closeDb();
        return result;
    }


    private void closeDb() throws SQLException {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }


    /**
     * The query is "SELECT * FROM cars WHERE number LIKE \'" + number + "\'"
     * <p/>
     * number/owner/description/NOTES
     * allowMode
     * ref_Group
     * access
     * DURATIONUSE
     * DURATIONTIME
     * COUNTUSE
     * COUNTVALUE
     *
     * @param column in which column the data expects to be
     * @param number by number
     * @param data   data is must be.
     * @return true if contains data
     */

    public boolean isDataInCarsCorrect(String column, String number , Object data) {
        return isDataPresentIn("SELECT * FROM cars WHERE number LIKE \'" + number + "\'", column, data);
    }

    /**
     * "SELECT * FROM groups WHERE groupName LIKE 'gropName'"
     * <p/>
     * <br> groupName
     * <br>allowMode
     * <br>timetable_enabled
     * <br>DFROM
     * <br>DTO
     * <br>TFROM
     * <br>TTO
     * <br>DDAYS
     * <br>DURATIONUSE
     * <br>DURATIONTIME
     * <br>COUNTUSE
     * <br>COUNTVALUE
     * <br>DRANGE_ENABLED
     * <br>TRANGE_ENABLED
     *
     * @param data
     */
    public boolean isDataInGroupsCorrect(String gropName, String column, Integer data) {
        return isDataPresentIn("SELECT * FROM groups WHERE groupName LIKE \'" + gropName + "\'", column, data);
    }

    /**
     * You can check in different tables
     * <p/>
     *
     * @param query        which query should be
     * @param column       wich column of Cars table
     * @param ExpectedData which data to search in db
     * @return true if contains data
     */


    private boolean isDataPresentIn(String query, String column, Object ExpectedData) {
        Object result;
        try {
            if (ExpectedData.getClass().getCanonicalName().contains("String")) { // kind a switch if want integer or if want String
                result = getStringData(query, column);
            } else result = getIntData(query, column);
            if (result == null) {
                throw new NullPointerException("Database is empty");
            }
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        return result.equals(ExpectedData);
    }

    /**
     * For counting table "SELECT COUNT() FROM table
     *
     * @param table which table we will count
     */

    public int countTable(String table) {
        int count;
        try {
            rs = getRs("SELECT COUNT() FROM " + table);
            count = rs.getInt(1);
            closeDb();
            return count;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}




