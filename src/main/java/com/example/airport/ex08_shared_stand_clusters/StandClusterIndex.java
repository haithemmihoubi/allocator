package com.example.airport.ex08_shared_stand_clusters;

import java.util.List;

public class StandClusterIndex {

    /**
     * See exercises/ex08-shared-stand-clusters/README.md.
     * TODO: implement.
     */
    public StandClusterIndex(int totalStands, List<SharedLink> links) {
    }

    /**
     * TODO: implement.
     */
    public boolean sameCluster(int standA, int standB) {
        throw new UnsupportedOperationException("TODO");
    }

    public record SharedLink(int standA, int standB) {
    }
}
