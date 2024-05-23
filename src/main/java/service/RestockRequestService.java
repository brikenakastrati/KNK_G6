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

    public void requestRestock(String user, String carName) {
        String date = LocalDate.now().format(DATE_FORMATTER);
        String message = "User " + user + " has requested to restock the car: " + carName;
        messageRepository.saveRestockRequest(user, carName, date);
    }

    public List<Message> getMessages(String firstNameFilter, String lastNameFilter) {
        return messageRepository.getMessages(firstNameFilter, lastNameFilter);
    }

    public List<RestockRequest> getRestockRequests() {
        return messageRepository.getRestockRequests();
    }
}
