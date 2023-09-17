package com.ecommerce.chatservice.models;



public class ChatMessage {
    private String id;
    private String content;
    private String senderId;

    public ChatMessage(String id, String content, String senderId) {
        this.id = id;
        this.content = content;
        this.senderId = senderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    enum MessageType{
        JOIN,
        CHAT,
        LEAVE;
    }

}
