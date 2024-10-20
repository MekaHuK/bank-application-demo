package org.olsharabank.personalservice.service;

import lombok.RequiredArgsConstructor;
import org.olsharabank.personalservice.repository.PersonalRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ContributionService {

    private final PersonalRepository personalRepository;

    @Transactional
    @Scheduled(fixedRate = 300000) // Каждые 300 секунду
    public void paymentDividend() {
        int count = personalRepository.increaseBalanceByTwentyPercent();
        System.out.println("Dividends paid to " + count + " accounts");
    }
}
