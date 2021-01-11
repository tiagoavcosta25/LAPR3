package lapr.project.model;

import java.security.NoSuchAlgorithmException;

public class Admin extends User {

    private Integer id;

    public Admin() {
        super();
    }

    public Admin(String strName, String strEmail, String strPassword, Integer strNif) throws NoSuchAlgorithmException {
        super(strEmail, strPassword, strNif, strName);
    }

    public Admin(Integer intId,String strName, String strEmail, String strPassword, Integer strNif) throws NoSuchAlgorithmException {
        super(strEmail, strPassword, strNif, strName);
        this.setId(intId);
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}


