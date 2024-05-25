package service;

import Repository.MessageRepository;
import model.Message;
import model.RestockRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RestockRequestService {

    private final MessageRepository messageRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RestockRequestService() {
        this.messageRepository = new MessageRepository();
    }

    public void requestRestock(String username, String carName, String carType) {
        messageRepository.saveRestockRequest(username, carName, carType);
    }


    public List<Message> getMessages(String firstNameFilter, String lastNameFilter) {
        return messageRepository.getMessages(firstNameFilter, lastNameFilter);
    }

    public List<RestockRequest> getRestockRequests() {
        return messageRepository.getRestockRequests();
    }
}
