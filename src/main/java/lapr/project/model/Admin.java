package lapr.project.model;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(mId, admin.mId);
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


