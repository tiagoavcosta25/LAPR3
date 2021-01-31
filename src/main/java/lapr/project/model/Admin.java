package lapr.project.model;

import java.security.NoSuchAlgorithmException;

/**
 * Admin.
 * <p>
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 *
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */
public class Admin extends User {

    /**
     * Admin ID
     */
    private Integer mId;

    /**
     * Empty constructor of Admin, which instantiates a new Admin
     * by calling it's superclass constructor
     */
    public Admin() {
        super();
    }

    /**
     * Constructor of Admin, which sets all the atributes to the ones
     * given by parameter
     *
     * @param strName     Admin name
     * @param strEmail    Admin email
     * @param strPassword Admin password
     * @param strNif      Admin NIF
     * @throws NoSuchAlgorithmException Exception raised when the encryptation of the password
     *                                  doesn't find an algorithm
     */
    public Admin(String strName, String strEmail, String strPassword, Integer strNif) throws NoSuchAlgorithmException {
        super(strEmail, strPassword, strNif, strName);
    }

    /**
     * Constructor of Admin, which sets all the atributes to the ones
     * given by parameter
     *
     * @param intId       Admin ID
     * @param strName     Admin name
     * @param strEmail    Admin email
     * @param strPassword Admin password
     * @param strNif      Admin NIF
     * @throws NoSuchAlgorithmException Exception raised when the encryptation of the password
     *                                  doesn't find an algorithm
     */
    public Admin(Integer intId, String strName, String strEmail, String strPassword, Integer strNif) throws NoSuchAlgorithmException {
        super(strEmail, strPassword, strNif, strName);
        this.setId(intId);
    }

    /**
     * Returns the Admin (/User) ID
     *
     * @return  Admin (/User) ID
     */
    @Override
    public Integer getId() {
        return mId;
    }

    /**
     * Sets the Admin ID to the one given by parameter
     *
     * @param intId new Admin ID
     */
    @Override
    public void setId(Integer intId) {
        this.mId = intId;
    }

    /**
     * Equals method, which compares two 'Admin's
     *
     * @param o Other Object
     * @return True if both 'Admin's are equal, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return super.equals(o);
    }



}


