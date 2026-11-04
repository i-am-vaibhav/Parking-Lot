package com.parkinglot.application.ports.in;

import com.parkinglot.application.model.EntryRequest;
import com.parkinglot.application.model.EntryResponse;

public interface EntryUseCase {
    EntryResponse entry(EntryRequest entryRequest);
}
