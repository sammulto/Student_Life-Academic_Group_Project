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

public class DB {
    private Connection connection;

    public DB(){
        this("db");
    }

    public DB(String name){
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:"+name, "SA", "");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace(System.out);
        }
        catch (SQLException e){
            e.printStackTrace(System.out);
        }

        String tasks = "CREATE TABLE IF NOT EXISTS tasks( "+
                            " tid INTEGER IDENTITY PRIMARY KEY,"+
                            " taskName VARCHAR(256) NOT NULL,"+
                            " priority TINYINT, "+
                            " startTime DATETIME, "+
                            " endTime DATETIME, "+
                            " status TINYINT NOT NULL,"+
                            " type VARCHAR(50),"+
                            " quantity int,"+
                            " quantityUnit VARCHAR(50)"+
                        ");";
        try {
            connection.createStatement(). executeUpdate(tasks);
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
                                            resultSet.getInt("priority"),
                                            startTime,
                                            endTime,
                                            resultSet.getInt("status"),
                                            resultSet.getString("type"),
                                            resultSet.getInt("quantity"),
                                            resultSet.getString("quantityUnit"));
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
            PreparedStatement cmd = connection.prepareStatement("INSERT INTO tasks(taskName, priority, startTime, endTime, status, type, quantity, quantityUnit)"+
                                                                        " values(?, ?, ?, ?, ?, ?, ?, ?)");
            cmd.setString(1, t.getTaskName());
            cmd.setInt(2, t.getPriority());
            cmd.setString(3, t.getStartTime());
            cmd.setString(4, t.getEndTime());
            cmd.setInt(5, t.getStatus());
            cmd.setString(6, t.getType());
            cmd.setInt(7, t.getQuantity());
            cmd.setString(8, t.getQuantityUnit());

            cmd.executeUpdate();
        }
        catch(SQLException e){
            out = false;
            e.printStackTrace(System.out);
        }

        return out;
    }

    public boolean updateTask(Task t){
        boolean out = true;
        try{
            PreparedStatement cmd = connection.prepareStatement("UPDATE tasks SET taskName=?, priority=?, startTime=?, endTime=?, status=?, type=?"+
                                                                ", quantity=?, quantityUnit=? WHERE tid = ?");
            cmd.setString(1, t.getTaskName());
            cmd.setInt(2, t.getPriority());
            cmd.setString(3, t.getStartTime());
            cmd.setString(4, t.getEndTime());
            cmd.setInt(5, t.getStatus());
            cmd.setString(6, t.getType());
            cmd.setInt(7, t.getQuantity());
            cmd.setString(8, t.getQuantityUnit());
            cmd.setInt(9, t.getTid());

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






}
