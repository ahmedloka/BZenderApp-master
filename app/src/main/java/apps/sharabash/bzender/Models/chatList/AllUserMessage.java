package apps.sharabash.bzender.Models.chatList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AllUserMessage {
    private List<ChatList> ChatList;

    public List<ChatList> getChatList() {
        return ChatList;
    }

    public void setChatList(List<ChatList> ChatList) {
        this.ChatList = ChatList;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassPojo [ChatList = " + ChatList + "]";
    }
}

