package lapr.project.model.service;

import lapr.project.data.InvoiceDB;
import lapr.project.model.*;
import java.sql.Date;

public class InvoiceService {

    private InvoiceDB m_oInvoiceDB;

    public InvoiceService() {
        this.m_oInvoiceDB = new InvoiceDB();
    }

    public Invoice getInvoice(int strId) {
        return this.m_oInvoiceDB.getInvoice(strId);
    }

    public boolean removeInvoice(int intId) {
        return this.m_oInvoiceDB.removeInvoice(intId);
    }

    public boolean registerInvoice(Invoice oInvoice) {
        return this.m_oInvoiceDB .registerInvoice(oInvoice);
    }

    public Invoice newInvoice(Date dtInvoiceDate, float fltTotalPrice, Order oOrder) {
        return new Invoice(dtInvoiceDate, fltTotalPrice, oOrder);
    }
}
