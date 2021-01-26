package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.controller.ApplicationPOT;
import lapr.project.controller.RegisterDeliveryRunController;
import lapr.project.controller.RegisterProductController;
import lapr.project.model.*;
import lapr.project.model.service.GraphService;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.*;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //FileReader.readFiles();
        RegisterProductController ctrl = new RegisterProductController();
        ApplicationPOT.getInstance().setCurrentSession(new UserSession("asdas", UserSession.Role.ADMIN));
        ctrl.registerProduct("New Product", "Description", 123d, 123d);
    }
}
