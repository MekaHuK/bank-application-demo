package org.olsharabank.personalservice.repository;

import org.olsharabank.personalservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonalRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query("UPDATE UserEntity SET balance = balance * 12 / 10")
    int increaseBalanceByTwentyPercent();
}
