package com.astontech.bo;

public class ClientContact extends Person {
    private int ClientContactId;
    //Client Id
    //Person Id


    public ClientContact() {}
    public ClientContact(int clientContactId, int entityTypeId) {
        ClientContactId = clientContactId;
    }

    public int getClientContactId() {
        return ClientContactId;
    }

    public void setClientContactId(int clientContactId) {
        ClientContactId = clientContactId;
    }

}
