package apps.sharabash.bzender.activities.chatRoom;

import apps.sharabash.bzender.Models.singleChat.SingleChatResponse;

public interface ChatRoomInterface {
    void getChatRoomData(SingleChatResponse singleChatResponse);
    void finishGetDate();
}
