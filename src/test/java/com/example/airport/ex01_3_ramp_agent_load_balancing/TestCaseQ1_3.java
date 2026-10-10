package com.example.airport.ex01_3_ramp_agent_load_balancing;

import com.example.airport.ex01_3_ramp_agent_load_balancing.RampAgentLoadBalancing.Agent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ1_3 {

    @Test
    public void lowestWorkloadWins() {
        Agent light = new Agent(1, 10);
        Agent heavy = new Agent(2, 100);

        assertEquals(1, RampAgentLoadBalancing.pickNext(List.of(heavy, light)).getId());
    }

    @Test
    public void tiedWorkload_smallestIdWins() {
        Agent five = new Agent(5, 50);
        Agent three = new Agent(3, 50);

        assertEquals(3, RampAgentLoadBalancing.pickNext(List.of(five, three)).getId());
    }

    @Test
    public void singleAgent_returnedTrivially() {
        Agent only = new Agent(1, 0);

        assertEquals(1, RampAgentLoadBalancing.pickNext(List.of(only)).getId());
    }

    @Test
    public void threeAgents_lowestNonTiedWorkloadWins() {
        Agent a = new Agent(1, 30);
        Agent b = new Agent(2, 10);
        Agent c = new Agent(3, 20);

        assertEquals(2, RampAgentLoadBalancing.pickNext(List.of(a, b, c)).getId());
    }
}
