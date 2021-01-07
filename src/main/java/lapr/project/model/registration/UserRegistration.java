package lapr.project.model.registration;


import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.UserSession;
import lapr.project.data.DataHandler;

public class UserRegistration extends DataHandler {

    public UserRegistration(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }


    /**
     * Logs a User in, by checking if there's any User in the DataBase that has
     * the 'email' and 'password' given by parameter
     *
     * @param email         User's email
     * @param password      User's password
     * @return              True if the login operation was successful, false if otherwise
     */
    public boolean login(String email, String password) {
        if (checkIfUserExistsInDB(email)) {
            ApplicationPOT app = ApplicationPOT.getInstance();
            UserSession session = new UserSession();
        }
        return false;
    }

    /**
     * Checks if the User that has the 'email' given by parameter
     * is registered in the DataBase
     *
     * @param email     User's email
     * @return          True if User exists in the DB, false if otherwise
     */
    public boolean checkIfUserExistsInDB(String email) {
        /**
         * db code
         */
        return true;
    }
    
}
