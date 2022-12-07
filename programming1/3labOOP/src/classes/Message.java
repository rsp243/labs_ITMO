package src.classes;

import src.enums.MessageType;

public class Message {
    private String content;
    private MessageType messageType;

    public Message(String someText, MessageType isMessageTruly) {
        this.content = someText;
        this.messageType = isMessageTruly;
    }

    public String getContent() {
        return content;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getMessageTypeDescription() {
        return messageType.getDescription();
    }

    public boolean getIsMessageTruly() {
        return messageType == MessageType.TRULY;
    }

    @Override
    public String toString() {
        return "Message [content=" + content + ", isMessageTruly=" + messageType.getDescription() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + messageType.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (messageType != other.messageType)
            return false;
        return true;
    }
}
