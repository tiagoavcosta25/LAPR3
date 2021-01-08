package lapr.project.model;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class PharmacyManager extends User{

    public PharmacyManager() {
        super();
    }

    public PharmacyManager(String name, Integer nif, String email, String password) throws NoSuchAlgorithmException {
        super(email, password,nif,name);
    }
}
