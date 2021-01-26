package lapr.project.model.service;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.UserDB;
import lapr.project.model.UserSession;
import lapr.project.utils.EncryptPassword;
import lapr.project.utils.WriteFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private UserDB moUserDB;

    public UserDB getUserDB() {
        return moUserDB;
    }

    public void setUserDB(UserDB oUserDB) {
        this.moUserDB = oUserDB;
    }

    public UserService() {
        moUserDB = new UserDB();
    }

    public boolean login(String email, String password){
        try {
            String encryptedPassword = EncryptPassword.encryptPasswordMD5(password);
            if (moUserDB.login(email,encryptedPassword)) {
                LOGGER.log(Level.INFO,"Logged In!");
                String body = String.format("You are now logged in with the following account:\n\n-Email: %s\n-Password: %s\n-Encrypted" +
                        "Password: %s\n",email,password,encryptedPassword);
                WriteFile.write("LoggedIn_" + email,body);
                return true;
            }else return false;
        }catch (Exception e) {
            LOGGER.log(Level.WARNING,"Encryptation Algorithm failed!");
            return false;
        }
    }

    public boolean newUserSession() {
        String body = String.format("You are now logged out of your account with the corresponding email: %s!",
                ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail());
        WriteFile.write("LoggedOut_" + ApplicationPOT.getInstance().getCurrentSession().getCurrentUserEmail(),
                body);
        new UserSession();
        LOGGER.log(Level.INFO,"Logged Out!");
        return true;
    }
}
