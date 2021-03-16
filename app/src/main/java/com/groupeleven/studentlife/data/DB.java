/*
 * THIS DATABASE IS FOR ITERATION 2
 */

package com.groupeleven.studentlife.data;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
import com.groupeleven.studentlife.domainSpecificObjects.Link;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

public class DB implements IDatabase {
    private Connection connection;
    private Activity context;
    private final String path = "/data/data/com.groupeleven.studentlife/";

    public DB() {
        this("storage", "file");
    }

    public DB(String name) {
        this(name, "mem");
    }

    public DB(String name, String type) {
        try {;
            Class.forName("org.hsqldb.jdbcDriver");
            context = new Activity();
            connection = DriverManager.getConnection("jdbc:hsqldb:" + type + ":"+path+ name, "SA", "");
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
        String links = "CREATE TABLE IF NOT EXISTS links( " +
                " linkAddress varchar(50) PRIMARY KEY," +
                " linkName varchar(20)" +
                ");";
        try {
            connection.createStatement().executeUpdate(tasks);
            connection.createStatement().executeUpdate(links);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

    private Task[] getTasksWhere(String where, String[] args) {
        Task[] out = null;
        int i = 0;
        String startTime, endTime;
        try {
            PreparedStatement cmd = connection.prepareStatement("SELECT COUNT(*) as count FROM tasks WHERE "+where+";");
            for(int j = 0; j<args.length; j++) {
                cmd.setString(j+1, args[j]);
            }
            ResultSet resultSet = cmd.executeQuery();
            if (resultSet.next()) {
                out = new Task[resultSet.getInt("count")];

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

    public boolean insertTask(Task t) {
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

    public boolean updateTask(Task t) {
        return updateTask(t, -1);
    }

    public boolean updateTask(Task t, int tid) {
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

    public boolean deleteTask(Task t) {
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

    public int getSize() {
        int out = -1;
        try {
            PreparedStatement cmd = connection.prepareStatement("SELECT count(*) FROM tasks;");
            ResultSet resultSet = cmd.executeQuery();
            out = resultSet.getInt(0);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return out;
    }


    public Task[] getTasks(){
        return getTasksWhere("completed = FALSE", new String[0]);
    }

    public Task[] getTasks(String startTime, String endTime) {
        String[] args = {startTime, endTime};

        return getTasksWhere("startTime>=(? AS DATETIME) AND startTime<=(? AS DATETIME)", args);
    }

    public Task[] getTasksCompleted(){
        return getTasksWhere("completed = TRUE", new String[0]);
    }

    public Task[] getTask(int tid){
        return getTasksWhere("tid = "+tid, new String[0]);
    }

    public Link[] getLinks(){
        Link[] out = null;
        int i = 0;
        String startTime, endTime;
        try {
            PreparedStatement cmd = connection.prepareStatement("SELECT COUNT(*) as count FROM links;");
            ResultSet resultSet = cmd.executeQuery();
            if (resultSet.next()) {
                out = new Link[resultSet.getInt("count")];

                cmd = connection.prepareStatement("SELECT * FROM tasks ORDER BY tid;");
                resultSet = cmd.executeQuery();
                while (resultSet.next()) {
                    startTime = resultSet.getString("startTime");
                    if (startTime != null)
                        startTime = startTime.substring(0, 19);
                    endTime = resultSet.getString("endTime");
                    if (endTime != null)
                        endTime = endTime.substring(0, 19);
                    out[i++] = new Link(resultSet.getString("linkName"), resultSet.getString("linkAddress"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return out;
    }
}
