package lapr.project.controller;

import lapr.project.data.UserDB;

public class LoginController {

    private UserDB m_userReg;

    public LoginController(String jdbcUrl, String username, String password) {
        m_userReg = new UserDB(jdbcUrl, username, password);
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
        return m_userReg.login(email,password);
    }
}
