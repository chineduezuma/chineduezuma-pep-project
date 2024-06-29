package DAO;

import Model.*;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

     /**
     * Retrieve an account using an message object.
     *
     * Remember that the format of a select where statement written as a Java String looks something like this:
     * String sql = "select * from TableName where ColumnName = ?";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from one) and the second argument identifies the value to be used:
     * preparedStatement.setString(1,string1);
     * preparedStatement.setString(2,string2);
     *
     * @param message an object modelling a Message. the message object does not contain a message ID.
     */
    public Account getAccountByPostBy(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE account_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, message.getPosted_by());

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    

    /**
     * Adding a new message record into the database which matches the values contained in the message object.
     * Using the getters already written in the account class to retrieve its values (getPosted_by(), getMessage_text(),
     * getTime_posted_epoch()). The message_id will be automatically generated by the SQL database, and JDBC will be able
     * to retrieve the generated ID automatically. That means that during insert the new message, only need
     * the posted_by, message_text, and time_posted_epoch values (three columns total!)
     *
     *
     * Remember that the format of a insert PreparedStatement written as a string works something like this:
     * String sql = "insert into TableName (ColumnName1, ColumnName2) values (?, ?);";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from one) and the second argument identifies the value to be used:
     * preparedStatement.setInt(1,int);
     * preparedStatement.setString(2,string);
     * preparedStatement.setLong(3,long);
     *
     * @param message an object modelling a Message. the message object does not contain a message ID.
     */
    public Message insertNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();

            try {
                //SQL logic. When inserting, defined the posted_by, message_text, and time_posted_epoch
                //values (three columns total!)
                String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                //write preparedStatement's setString and setInt methods here.
                preparedStatement.setInt(1, message.getPosted_by());
                preparedStatement.setString(2, message.getMessage_text());
                preparedStatement.setLong(3, message.getTime_posted_epoch());


                preparedStatement.executeUpdate();
                ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
                if(pkeyResultSet.next()){
                    int generated_message_id = (int) pkeyResultSet.getLong(1);
                    return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(),message.getTime_posted_epoch()) ;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        
        return null;
    }



    /**
     * Retrieve all messages from database.
     *
     * Remember that the format of a select where statement written as a Java String looks something like this:
     * String sql = "select * from TableName where ColumnName = ?";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from one) and the second argument identifies the value to be used:
     * preparedStatement.setString(1,string1);
     * preparedStatement.setString(2,string2);
     *
     * @return all messages from database.
     */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                        rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
  
    


     /**
     * Retrieve a specific message using its message ID.
     *
     *
     * Remember that the format of a select where statement written as a Java String looks something like this:
     * String sql = "select * from TableName where ColumnName = ?";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from one) and the second argument identifies the value to be used:
     * preparedStatement.setInt(1,int1);
     *
     * @param id a message ID.
     */
    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE message_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                return message;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



     /**
     * Delete a specific message using its message ID.
     *
     *
     * Remember that the format of a select where statement written as a Java String looks something like this:
     * String sql = "select * from TableName where ColumnName = ?";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from one) and the second argument identifies the value to be used:
     * preparedStatement.setInt(1,int1);
     *
     * @param id a message ID.
     */
    public void deleteMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "DELETE FROM message WHERE message_id = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }



     /**
     * Update the message identified by the message id to the values contained in the flight object.
     *
     *
     * Remember that the format of an update PreparedStatement written as a Java String looks something like this:
     * String sql = "update TableName set ColumnName1=?, ColumnName2=? where ColumnName3 = ?;";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from one) and the second argument identifies the value to be used:
     * preparedStatement.setInt(1,int);
     * preparedStatement.setString(2,string);
     * preparedStatement.setLong(3,long);
     *
     * @param id a message ID.
     * @param message a message object. the message object does not contain a message ID.
     */
    public void updateMessageById(int id, Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }



     /**
     * Retrieve all messages using a particular account ID.
     *
     * Remember that the format of a select where statement written as a Java String looks something like this:
     * String sql = "select * from TableName where ColumnName = ?";
     * The question marks will be filled in by the preparedStatement setString, setInt, etc methods. they follow
     * this format, where the first argument identifies the question mark to be filled (left to right, starting
     * from one) and the second argument identifies the value to be used:
     * preparedStatement.setInt(1,int);
     *
     * @param id a account ID.
     */
    public List<Message> getMessageByPostBy(int id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                        rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

}
