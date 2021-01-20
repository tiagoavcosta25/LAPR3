package lapr.project.model;

import java.security.NoSuchAlgorithmException;

public class Admin extends User {

    private Integer mId;

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
        return mId;
    }

    @Override
    public void setId(Integer intId) {
        this.mId = intId;
    }
}


