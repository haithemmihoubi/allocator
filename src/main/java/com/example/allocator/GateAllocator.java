package com.example.allocator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GateAllocator {

    public Optional<Gate> assignGate(List<Gate> gates) {
        //  1-find available Gate   -- filter
        // 2 is several are available FILTER   choose smallest id Min --- filter
        // 3-  return  optional .empty
        return gates.stream().filter(Gate::available).min(Comparator.comparingInt(Gate::id));
    }

    // Create a record Gate
    public record Gate(
            int id,
            boolean available
    ) {
    }


}
