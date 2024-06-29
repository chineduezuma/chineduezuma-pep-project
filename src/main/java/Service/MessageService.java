package Service;


import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {

    private MessageDAO messageDAO;

    /**
     * No-args constructor for a socialmediaService instantiates a plain flightDAO.
     * There is no need to modify this constructor.
     */
    public MessageService(){
        
         messageDAO = new MessageDAO();

    }

   

    /**
     * Constructor for a SocialMediaService when a MessageDAO is provided.
     * This is used for when a mock MessageDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of SocialMediaService independently of MessageDAO.
     * There is no need to modify this constructor.
     * @param messageDAO
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }



    /**
     * Use the MessageDAO to add a new message to the database.
     *
     * This method should also return the added new message. A distinction should be made between *transient* and
     * *persisted* objects - the *transient* message Object given as the parameter will not contain the message's id,
     * because it is not yet a database record. When this method is used, it should return the full persisted account,
     * which will contain the message's id. This way, any part of the application that uses this method has
     * all information about the new message, because knowing the new message's ID is necessary. This means that the
     * method should return the Messeage returned by the MessageDAO's insertNewMessage method, and not the message provided by
     * the parameter 'message'.
     *
     * @param message an object representing a new Account.
     * @return the newly added message if the add operation was successful, including the message_id. We do this to
     *         inform our provide the front-end client with information about the added Message.
     */
    public Message addMessage(Message message){

        if(message.getMessage_text() != "" && message.getMessage_text().length() <= 255 && messageDAO.getAccountByPostBy(message) != null){
           
            return messageDAO.insertNewMessage(message);
        }
        return null;
    }



     /**
     * Use the MessageDAO to retrieve a List containing all messages.
     * Use the MessageDAO.getAllMessages method.
     *
     * @return all messages in the database.
     */
    public List<Message> getAllMessages() {
        
        return messageDAO.getAllMessages();
    }


    /**
     * Use the MessageDAO to retrieve a message by message ID.
     * First check that the message ID already exists. To do this, use an if statement that checks
     * if MessageDAO.getMessageById returns null for the message's ID, as this would indicate that the message id does not
     * exist.
     *
     * @param message_id the ID of the messaget to be retrieved.
     * @return the details of the message object by message ID 
     */
    public Message getMessageById(int message_id){
         
            return messageDAO.getMessageById(message_id);
    }


    
     /**
     * Use the MessageDAO to delete a message by message ID.
     * First check that the message ID already exists. To do this, use an if statement that checks
     * if MessageDAO.deleteMessageById returns null for the message's ID, as this would indicate that the message id does not
     * exist.
     *
     * @param message_id the ID of the messaget to be retrieved.
     * @return the details of the message object deleted by message ID 
     */
    public Message deleteMessageById(int message_id){
        
        if(messageDAO.getMessageById(message_id) != null){
            Message message = messageDAO.getMessageById(message_id);
            messageDAO.deleteMessageById(message_id);
            return message;
        }

            
         return null;
            
    }



    /**
     * Use the MessageDAO to update an existing message from the database.
     * Check that the message ID already exists. To do this, use an if statement that checks
     * if messageDAO.updateMessageById returns null for the null's ID, as this would indicate that the message id does not
     * exist.
     *
     * @param message_id the ID of the flight to be modified.
     * @param message an object containing all data that should replace the values contained by the existing message_id.
     *         the message object does not contain a message ID.
     * @return the newly updated message if the update operation was successful. Return null if the update operation was
     *         unsuccessful. We do this to inform our application about successful/unsuccessful operations. (eg, the
     *         user should have some insight if they attempted to edit a nonexistent flight.)
     */
    public Message updateMessageById(int message_id, Message message){
        
        if(messageDAO.getMessageById(message_id) != null && message.getMessage_text() != "" && message.getMessage_text().length() <= 255){
            
            messageDAO.updateMessageById(message_id, message);
            return messageDAO.getMessageById(message_id);
        }
        
            return null;
    }


     /**
     * Use the MessageDAO to retrieve a all message by an account ID.
     * First check that the message ID already exists. To do this, use an if statement that checks
     * if MessageDAO.getMessageByPostBy returns null for the account's ID, as this would indicate that the account id does not
     * exist or have any message.
     *
     * @param account_id the ID of the account to retrieve all its' messages.
     * @return the list of messages object by account ID 
     */
    public List<Message> getMessageByPostBy(int account_id){
             
            return messageDAO.getMessageByPostBy(account_id);
       
    }



}
