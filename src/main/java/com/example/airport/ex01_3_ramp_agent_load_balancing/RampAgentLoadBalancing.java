package com.example.airport.ex01_3_ramp_agent_load_balancing;

import java.util.List;

public class RampAgentLoadBalancing {

    /**
     * See exercises/ex01.3-ramp-agent-load-balancing/README.md.
     * TODO: implement.
     */
    public static Agent pickNext(List<Agent> agents) {
        return null;
    }

    public static class Agent {
        private final int id;
        private final int workloadMinutes;

        public Agent(int id, int workloadMinutes) {
            this.id = id;
            this.workloadMinutes = workloadMinutes;
        }

        public int getId() {
            return id;
        }

        public int getWorkloadMinutes() {
            return workloadMinutes;
        }
    }
}
