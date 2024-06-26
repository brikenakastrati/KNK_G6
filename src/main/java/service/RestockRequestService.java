package service;

import Repository.MessageRepository;
import model.Message;
import model.RestockRequest;

import java.util.List;

public class RestockRequestService {

    private final MessageRepository messageRepository;

    public RestockRequestService() {
        this.messageRepository = new MessageRepository();
    }

    public void requestRestock(String username, String carName, String carType) {
        messageRepository.saveRestockRequest(username, carName, carType);
    }

    public void requestRestockWithDescription(String username, String carName, String carType, String carDescription) {
        messageRepository.saveRestockRequestWithDescription(username, carName, carType, carDescription);
    }

    public List<Message> getMessages(String firstNameFilter, String lastNameFilter) {
        return messageRepository.getMessages(firstNameFilter, lastNameFilter);
    }

    public List<RestockRequest> getRestockRequests() {
        return messageRepository.getRestockRequests();
    }
    public List<RestockRequest> getRestockRequestsForUser(String username) {
        return messageRepository.getRestockRequestsForUser(username);
    }
    public int getNumberOfCarRequestsFromUser(String username) {
        return messageRepository.getNumberOfCarRequestsFromUser(username);
    }
    public void saveMessage(String firstName, String lastName, String message){
         messageRepository.saveMessage(firstName,lastName,message);
    }
}
