package org.olsharabank.personalservice.dto;

import java.util.UUID;

public record Message(
        UUID sender,
        Long amount
) {
}
