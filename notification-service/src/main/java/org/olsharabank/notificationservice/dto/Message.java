package org.olsharabank.notificationservice.dto;

import java.util.UUID;

public record Message(
        UUID sender,
        Long amount
) {
}
