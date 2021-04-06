/*
 * THIS DATABASE IS FOR ITERATION 2
 */

package com.groupeleven.studentlife.data;

import android.content.Context;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

public class DB implements IDatabase {
    private Connection connection;
    private static String path = "/data/data/com.groupeleven.studentlife/";
    private static DB db;

    public static DB getDB(){
        if(db == null){
            db = new DB();
        }
        return db;
    }

    public DB() {
        this("storage", "file");
    }

    public static void setDBPath(Context context){
        File dbPath =  context.getDir("db", Context.MODE_PRIVATE);
        path = dbPath.toString();
    }

    public static void setDBPath(String filePath){
        path = filePath;
    }

    public DB(String name, String type) {
        try {;
            Class.forName("org.hsqldb.jdbcDriver");
            if(type.equals("file")) {
                connection = DriverManager.getConnection("jdbc:hsqldb:" + type + ":" + DB.path + name, "SA", "");
            }
            else if(type.equals("mem")){
                connection = DriverManager.getConnection("jdbc:hsqldb:" + type + ":" + name, "SA", "");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        String tasks = "CREATE TABLE IF NOT EXISTS tasks( " +
                " tid INTEGER IDENTITY PRIMARY KEY," +
                " taskName VARCHAR(20) NOT NULL," +
                " priority VARCHAR(10), " +
                " startTime DATETIME, " +
                " endTime DATETIME, " +
                " status TINYINT NOT NULL," +
                " type VARCHAR(50)," +
                " quantity int," +
                " quantityUnit VARCHAR(50)," +
                " completed BOOLEAN" +
                ");";
        try {
            connection.createStatement().executeUpdate(tasks);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

    private ITaskObject[] getTasksWhere(String where, String[] args) {
        ITaskObject[] out = null;
        int i = 0;
        String startTime, endTime;
        try {
            PreparedStatement cmd = connection.prepareStatement("SELECT COUNT(*) as count FROM tasks WHERE "+where+";");
            for(int j = 0; j<args.length; j++) {
                cmd.setString(j+1, args[j]);
            }
            ResultSet resultSet = cmd.executeQuery();
            if (resultSet.next()) {
                out = new ITaskObject[resultSet.getInt("count")];

                cmd = connection.prepareStatement("SELECT * FROM tasks WHERE "+where+" ORDER BY tid;");
                for(int j = 0; j<args.length; j++) {
                    cmd.setString(j+1, args[j]);
                }
                resultSet = cmd.executeQuery();
                while (resultSet.next()) {
                    startTime = resultSet.getString("startTime");
                    if (startTime != null)
                        startTime = startTime.substring(0, 19);
                    endTime = resultSet.getString("endTime");
                    if (endTime != null)
                        endTime = endTime.substring(0, 19);
                    out[i++] = new Task(resultSet.getInt("tid"),
                            resultSet.getString("taskName"),
                            resultSet.getString("priority")!= null ? ITaskObject.Priority.valueOf(resultSet.getString("priority")):null,
                            startTime,
                            endTime,
                            resultSet.getInt("status"),
                            resultSet.getString("type"),
                            resultSet.getInt("quantity"),
                            resultSet.getString("quantityUnit"),
                            resultSet.getBoolean("completed"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return out;
    }

    public boolean insertTask(ITaskObject t) {
        boolean out = true;
        try {
            PreparedStatement cmd = connection.prepareStatement("INSERT INTO tasks(taskName, priority, startTime, endTime, status, type, quantity, quantityUnit, completed)" +
                    " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            cmd.setString(1, t.getTaskName());
            cmd.setString(2, t.getPriority()!=null ? t.getPriority().name():null);
            cmd.setString(3, t.getStartTime());
            cmd.setString(4, t.getEndTime());
            cmd.setInt(5, t.getStatus());
            cmd.setString(6, t.getType());
            cmd.setInt(7, t.getQuantity());
            cmd.setString(8, t.getQuantityUnit());
            cmd.setBoolean(9, t.isCompleted());

            cmd.executeUpdate();
        } catch (SQLException e) {
            out = false;
            e.printStackTrace(System.out);
        }

        return out;
    }

    public boolean updateTask(ITaskObject t) {
        return updateTask(t, -1);
    }

    public boolean updateTask(ITaskObject t, int tid) {
        boolean out = true;
        try {
            PreparedStatement cmd = connection.prepareStatement("UPDATE tasks SET taskName=?, priority=?, startTime=?, endTime=?, status=?, type=?" +
                    ", quantity=?, quantityUnit=?, completed=? WHERE tid = ?");
            cmd.setString(1, t.getTaskName());
            cmd.setString(2, t.getPriority() != null ? t.getPriority().name():null);
            cmd.setString(3, t.getStartTime());
            cmd.setString(4, t.getEndTime());
            cmd.setInt(5, t.getStatus());
            cmd.setString(6, t.getType());
            cmd.setInt(7, t.getQuantity());
            cmd.setString(8, t.getQuantityUnit());
            cmd.setBoolean(9, t.isCompleted());
            cmd.setInt(10, t.getTid());

            cmd.executeUpdate();
        } catch (SQLException e) {
            out = false;
            e.printStackTrace(System.out);
        }

        return out;
    }

    public boolean deleteTask(ITaskObject t) {
        boolean out = true;
        try {
            PreparedStatement cmd = connection.prepareStatement("DELETE FROM tasks WHERE tid = ?");
            cmd.setInt(1, t.getTid());

            cmd.executeUpdate();
        } catch (SQLException e) {
            out = false;
            e.printStackTrace(System.out);
        }

        return out;
    }

    public boolean deleteAllTask() {
        boolean out = true;
        try {
            PreparedStatement cmd = connection.prepareStatement("DELETE FROM tasks");
            cmd.executeUpdate();
        } catch (SQLException e) {
            out = false;
            e.printStackTrace(System.out);
        }

        return out;
    }

    public int getSize() {
        int out = -1;
        try {
            PreparedStatement cmd = connection.prepareStatement("SELECT count(*) AS total FROM tasks;");
            ResultSet resultSet = cmd.executeQuery();
            while(resultSet.next())
                out = resultSet.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return out;
    }


    public ITaskObject[] getTasks(){
        return getTasksWhere("completed = FALSE OR completed = TRUE", new String[0]);
    }

    public ITaskObject[] getTasks(String startTime, String endTime) {
        String[] args = {startTime, endTime};

        return getTasksWhere("startTime>=(? AS DATETIME) AND startTime<=(? AS DATETIME)", args);
    }

    public ITaskObject[] getTasksUncompleted(){
        return getTasksWhere("completed = FALSE", new String[0]);
    }

    public ITaskObject[] getTasksCompleted(){
        return getTasksWhere("completed = TRUE", new String[0]);
    }

    public ITaskObject[] getTask(int tid){
        return getTasksWhere("tid = "+tid, new String[0]);
    }

    public ITaskObject[] getTasks(String day) {
        String[] args = {day};

        return getTasksWhere("CAST(endTime AS DATE)=?", args);
    }

}
