package com.example.allocator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
public class InterviewQuestion {
    /**
     * @param parkPositions park positions requested. 0,1 or more park position can be available
     * @param parkPositionRequest request for the park position
     * @return
     */
    public static Optional<ParkPosition> question1(List<ParkPosition> parkPositions, ParkPositionRequest parkPositionRequest) {
        return  null;
    }

 

    /**
     * contains location of the park position and the request has been accepted before
     */
    public static class ParkPosition {
        private int location;
        private Set<ParkPositionRequest> accepted;

        public ParkPosition(int location, Set<ParkPositionRequest> accepted) {
            this.location = location;
            this.accepted = accepted;
        }

        public int getLocation() {
            return  location;
        }
    }

    /**
     * request for the park position , contains location of the requested park position and the date interval (start and end times)
     */
    public static class ParkPositionRequest {
        private int location;
        private LocalDateTime start;
        private LocalDateTime end;

        public ParkPositionRequest(int location, LocalDateTime start, LocalDateTime end) {
            this.location = location;
            this.start = start;
            this.end = end;
        }

        public int getLocation() {
            return location;
        }
    }
}
