package com.antonid.chat;

public class Message {

    private String senderUsername;
    private String text;

    public Message() {

    }

    public Message(String senderUsername, String text) {
        this.senderUsername = senderUsername;
        this.text = text;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
