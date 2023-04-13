package com.example.chatapplication.datamodel;

public class MessageData {
    private int id;
    private int senderId;
    private String content;

    public boolean isSelected()
    {
        return isSelected;
    }

    public void setSelected(boolean selected)
    {
        isSelected = selected;
    }

    private boolean isSelected;

    public MessageData(int id, int senderId, String content, boolean isSelected, long timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.isSelected = isSelected;
        this.timestamp = timestamp;
    }

    private long timestamp;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getSenderId()
    {
        return senderId;
    }

    public void setSenderId(int senderId)
    {
        this.senderId = senderId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }
}
