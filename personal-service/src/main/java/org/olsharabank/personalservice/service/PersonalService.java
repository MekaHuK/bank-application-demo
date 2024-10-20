package org.olsharabank.personalservice.service;

import lombok.RequiredArgsConstructor;
import org.olsharabank.personalservice.dto.Message;
import org.olsharabank.personalservice.entity.UserEntity;
import org.olsharabank.personalservice.exception.InsufficientFundsException;
import org.olsharabank.personalservice.exception.UserNotFoundException;
import org.olsharabank.personalservice.repository.PersonalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonalService {

    private final PersonalRepository personalRepository;
    private final MessageProducer messageProducer;

    @Transactional
    public Long putMoney(UUID userId, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        UserEntity user = personalRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " is not exist"));

        Long oldBalance = user.getBalance();
        Long newBalance = oldBalance + amount;
        user.setBalance(newBalance);
        personalRepository.save(user);

        return newBalance;
    }

    @Transactional
    public Long withdrawMoney(UUID userId, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        UserEntity user = personalRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " does not exist"));

        Long oldBalance = user.getBalance();
        if (oldBalance < amount) {
            throw new InsufficientFundsException("Insufficient funds for user with id: " + userId);
        }

        Long newBalance = oldBalance - amount;
        user.setBalance(newBalance);
        personalRepository.save(user);

        return newBalance;
    }

    @Transactional
    public Long transferMoney(UUID senderId, String receiverPhoneNumber, Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        UserEntity sender = personalRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException("Sender with id: " + senderId + " does not exist"));

        UserEntity receiver = personalRepository.findByPhoneNumber(receiverPhoneNumber)
                .orElseThrow(() -> new UserNotFoundException("Receiver with phone number: " + receiverPhoneNumber + " does not exist"));

        Long senderBalance = sender.getBalance();
        if (senderBalance < amount) {
            throw new InsufficientFundsException("Insufficient funds for user with id: " + senderId);
        }

        sender.setBalance(senderBalance - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        personalRepository.save(sender);
        personalRepository.save(receiver);

        messageProducer.sendMessage(new Message(senderId, amount));

        return sender.getBalance();
    }
}
