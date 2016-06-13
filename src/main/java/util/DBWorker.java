package util;


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


    private String getStringData(String query, String col) throws ClassNotFoundException, SQLException {
        String result;
        System.out.println("Executing query : " + query);
        rs = getRs(query);
        if (!rs.next()) {
            return null;
        } else {
            result = rs.getString(col);
        }
        closeDb();
        return result;
    }

    private Integer getIntData(String query, String column) throws SQLException, ClassNotFoundException {
        Integer result;
        System.out.println("Executing query = " + query);
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
     */

    private boolean isInteger(String col) {
        switch (col) {
            case "number":
                return false;
            case "owner":
                return false;
            case "description":
                return false;
            case "NOTES":
                return false;
            default:
                return true;
        }
    }


    /**
     * You can check in different tables
     * <p/>
     *
     * @param query which query should be
     * @return true if contains data
     */


    public Object getData(String query) {
        Object result;
        String col = query.split("\\s+")[1];//for get an a column
        System.out.println("the column is " + col);
        try {
            if (isInteger(col)) { // kind a switch if want integer or if want String
                result = getIntData(query, col);
            } else result = getStringData(query,col);


            if (result == null) {
                throw new NullPointerException("Database is empty");
            }
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }

    /**
     * For counting table "SELECT COUNT() FROM table
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




