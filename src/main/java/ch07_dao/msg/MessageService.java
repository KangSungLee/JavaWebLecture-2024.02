package ch07_dao.msg;

import java.util.List;

public interface MessageService {
	int DELTED = 1;
	
	Message getMessageByMid(int mid);
	
	List<Message> getMessageList();
	
	List<Message> getMessageListBySearch(String searchList, String search);
	
	void insertMessage (Message message);

	void updateMessage (Message message);
	
	void deleteMessage (int mid);
}
