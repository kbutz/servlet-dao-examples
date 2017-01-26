package com.astontech.bo;

import java.util.Date;

public class Client extends BaseBO{
    private int ClientId;
    private String ClientName;
    private Date CreateDate;
    private ClientContact Contact;

    public Client(){
        this.Contact = new ClientContact();
    }

    public Client(int clientId, String clientName, Date createDate) {
        ClientId = clientId;
        ClientName = clientName;
        CreateDate = createDate;
        this.Contact = new ClientContact();
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public ClientContact getContact() {
        return Contact;
    }

    public void setContact(ClientContact contact) {
        Contact = contact;
    }
}
