package Service;

import Model.Account;
import DAO.AccountDAO;


public class AccountService {

    private AccountDAO accountDAO;
    

    /**
     * No-args constructor for a socialmediaService instantiates a plain flightDAO.
     * There is no need to modify this constructor.
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /**
     * Constructor for a SocialMediaService when a AccountDAO is provided.
     * This is used for when a mock AccountDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of SocialMediaService independently of AccountDAO.
     * There is no need to modify this constructor.
     * @param accountDAO
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    


    /**
     * Use the AccountDAO to add a new user to the database.
     *
     * This method should also return the added account (new user). A distinction should be made between *transient* and
     * *persisted* objects - the *transient* account Object given as the parameter will not contain the account's id,
     * because it is not yet a database record. When this method is used, it should return the full persisted account,
     * which will contain the account's id. This way, any part of the application that uses this method has
     * all information about the new account, because knowing the new account's ID is necessary. This means that the
     * method should return the Account returned by the AccountDAO's insertNewUserAccount method, and not the account provided by
     * the parameter 'account'.
     *
     * @param account an object representing a new Account.
     * @return the newly added flight if the add operation was successful, including the flight_id. We do this to
     *         inform our provide the front-end client with information about the added Flight.
     */
    public Account addAccount(Account account){

        if(account.getUsername() != "" && account.getPassword().length() >= 4 && accountDAO.getAccount(account) == null){
            return accountDAO.insertNewUserAccount(account);
        }
        return null;
    }


    /**
     * Use the AccountDAO to verify an existing account from the database.
     * check that the username and password already exists. To do this, use an if statement that checks
     * if accountDAO.getAccount returns null for the account object, as this would indicate that the account object does not
     * exist.
     *
     * @param account an object containing all data that should be used to query database.
     *         the account object does not contain a account ID.
     * @return information details of account if it is in the database. Return null if account not found in database
     *         We do this to inform our application about successful/unsuccessful operations. 
     */
    public Account verifyAccountLogin(Account account){
        
        if(accountDAO.getAccount(account) != null){
            
            return accountDAO.getAccount(account);
        }
            return null;
    }


}
