package com.example.airport.ex08_shared_stand_clusters;

import com.example.airport.ex08_shared_stand_clusters.StandClusterIndex.SharedLink;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ8 {

    @Test
    public void directlyLinkedStands_areInSameCluster() {
        StandClusterIndex index = new StandClusterIndex(3, List.of(new SharedLink(0, 1)));

        assertTrue(index.sameCluster(0, 1));
    }

    @Test
    public void transitivelyLinkedStands_areInSameCluster() {
        StandClusterIndex index = new StandClusterIndex(3, List.of(new SharedLink(0, 1), new SharedLink(1, 2)));

        assertTrue(index.sameCluster(0, 2));
    }

    @Test
    public void unrelatedStands_areNotInSameCluster() {
        StandClusterIndex index = new StandClusterIndex(4, List.of(new SharedLink(0, 1)));

        assertFalse(index.sameCluster(0, 3));
    }

    @Test
    public void standIsAlwaysInSameClusterAsItself() {
        StandClusterIndex index = new StandClusterIndex(2, List.of());

        assertTrue(index.sameCluster(0, 0));
    }

    @Test
    public void outOfRangeLinkInConstructor_throws() {
        assertThrows(IllegalArgumentException.class, () -> new StandClusterIndex(2, List.of(new SharedLink(0, 5))));
    }

    @Test
    public void outOfRangeQuery_throws() {
        StandClusterIndex index = new StandClusterIndex(2, List.of());

        assertThrows(IllegalArgumentException.class, () -> index.sameCluster(0, 9));
    }
}
