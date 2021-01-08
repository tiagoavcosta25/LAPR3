package lapr.project.model;

import java.security.NoSuchAlgorithmException;

public class PharmacyManager extends User{

    public PharmacyManager() {
        super();
    }

    public PharmacyManager(int intId, String email, String password, Integer nif, String name) throws NoSuchAlgorithmException {
        super(intId, email, password,nif,name);
    }

    public PharmacyManager(String email, String password, Integer nif, String name) throws NoSuchAlgorithmException {
        super(email, password,nif,name);
    }
}
