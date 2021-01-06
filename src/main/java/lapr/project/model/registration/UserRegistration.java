package lapr.project.model.registration;


public class UserRegistration {


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
