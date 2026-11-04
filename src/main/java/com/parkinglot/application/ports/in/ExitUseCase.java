package com.parkinglot.application.ports.in;

import com.parkinglot.application.model.ExitResponse;

public interface ExitUseCase {
    ExitResponse exit(Long ticketId);
}
