/*
 * THIS DATABASE IS FOR ITERATION 2
 */

package com.groupeleven.studentlife.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

public class DB implements IDatabase{
    private Connection connection;

    public DB(){
        this("db", "file");
    }

    public DB(String name){
        this(name, "mem");
    }

    public DB(String name, String type){
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:"+type+":"+name, "SA", "");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace(System.out);
        }
        catch (SQLException e){
            e.printStackTrace(System.out);
        }

        String tasks = "CREATE TABLE IF NOT EXISTS tasks( "+
                            " tid INTEGER IDENTITY PRIMARY KEY,"+
                            " taskName VARCHAR(20) NOT NULL,"+
                            " priority VARCHAR(1), "+
                            " startTime DATETIME, "+
                            " endTime DATETIME, "+
                            " status TINYINT NOT NULL,"+
                            " type VARCHAR(50),"+
                            " quantity int,"+
                            " quantityUnit VARCHAR(50),"+
                            " completed BOOLEAN"+
                        ");";
        String links = "CREATE TABLE IF NOT EXISTS links( "+
                            " linkAddress varchar(50) PRIMARY KEY,"+
                            " linkName varchar(20)"+
                        ");";
        try {
            connection.createStatement().executeUpdate(tasks);
            connection.createStatement().executeUpdate(links);
        }
        catch (SQLException e){
            e.printStackTrace(System.out);
        }

    }

    public Task[] getTasks(){
        Task[] out = null;
        int i = 0;
        String startTime, endTime;
            try{
                PreparedStatement cmd = connection.prepareStatement("SELECT COUNT(*) as count FROM tasks;");
                ResultSet resultSet = cmd.executeQuery();
                if(resultSet.next()){
                    out = new Task[resultSet.getInt("count")];

                    cmd = connection.prepareStatement("SELECT * FROM tasks ORDER BY tid;");
                    resultSet = cmd.executeQuery();
                    while(resultSet.next()){
                        startTime = resultSet.getString("startTime");
                        if(startTime != null)
                            startTime = startTime.substring(0,19);
                        endTime = resultSet.getString("endTime");
                        if(endTime != null)
                            endTime = endTime.substring(0,19);
                        out[i++]= new Task( resultSet.getInt("tid"),
                                            resultSet.getString("taskName"),
                                            resultSet.getString("priority"),
                                            startTime,
                                            endTime,
                                            resultSet.getInt("status"),
                                            resultSet.getString("type"),
                                            resultSet.getInt("quantity"),
                                            resultSet.getString("quantityUnit"),
                                            resultSet.getBoolean("completed"));
                    }
                }
            }
            catch(SQLException e){
                e.printStackTrace(System.out);
            }
        return out;
    }

    public boolean insertTask(Task t){
        boolean out = true;
        try{
            PreparedStatement cmd = connection.prepareStatement("INSERT INTO tasks(taskName, priority, startTime, endTime, status, type, quantity, quantityUnit, completed)"+
                                                                        " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            cmd.setString(1, t.getTaskName());
            cmd.setString(2, t.getPriority());
            cmd.setString(3, t.getStartTime());
            cmd.setString(4, t.getEndTime());
            cmd.setInt(5, t.getStatus());
            cmd.setString(6, t.getType());
            cmd.setInt(7, t.getQuantity());
            cmd.setString(8, t.getQuantityUnit());
            cmd.setBoolean(9, t.isCompleted());

            cmd.executeUpdate();

            cmd = connection.prepareStatement("SELECT tid FROM tasks ORDER BY tid DESC");

            ResultSet resultSet = cmd.executeQuery();
            if(resultSet.next()) {
                t.setTid(resultSet.getInt("tid"));
            }
        }
        catch(SQLException e){
            out = false;
            e.printStackTrace(System.out);
        }

        return out;
    }

    public boolean updateTask(Task t){
        return updateTask(t, -1);
    }

    public boolean updateTask(Task t, int tid){
        boolean out = true;
        try{
            PreparedStatement cmd = connection.prepareStatement("UPDATE tasks SET taskName=?, priority=?, startTime=?, endTime=?, status=?, type=?"+
                                                                ", quantity=?, quantityUnit=?, completed=? WHERE tid = ?");
            cmd.setString(1, t.getTaskName());
            cmd.setString(2, t.getPriority());
            cmd.setString(3, t.getStartTime());
            cmd.setString(4, t.getEndTime());
            cmd.setInt(5, t.getStatus());
            cmd.setString(6, t.getType());
            cmd.setInt(7, t.getQuantity());
            cmd.setString(8, t.getQuantityUnit());
            cmd.setBoolean(9,  t.isCompleted());
            cmd.setInt(10, t.getTid());

            cmd.executeUpdate();
        }
        catch(SQLException e){
            out = false;
            e.printStackTrace(System.out);
        }

        return out;
    }

    public boolean deleteTask(Task t){
        boolean out = true;
        try{
            PreparedStatement cmd = connection.prepareStatement("DELETE FROM tasks WHERE tid = ?");
            cmd.setInt(1, t.getTid());

            cmd.executeUpdate();
        }
        catch(SQLException e){
            out = false;
            e.printStackTrace(System.out);
        }

        return out;
    }

    public int getSize(){
        int out = -1;
        try {
            PreparedStatement cmd = connection.prepareStatement("SELECT count(*) FROM tasks;");
            ResultSet resultSet = cmd.executeQuery();
            out = resultSet.getInt(0);
        }
        catch(SQLException e) {
            e.printStackTrace(System.out);
        }

        return out;
    }






}
