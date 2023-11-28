package com.example;

import static com.example.Constants.*;

public class PheromoneTrail {
    private double[][] pheromoneMatrix;

    public double[][] pheromoneTrail(Graph graph) {
        pheromoneMatrix = new double[graph.getNumVertices()][graph.getNumVertices()];

        for (int i = 0; i < graph.getNumVertices(); i++) {
            for (int j = 0; j < graph.getNumVertices(); j++) {
                pheromoneMatrix[i][j] = INITIAL_PHEROMONE_VALUE;
            }
        }
        return pheromoneMatrix;
    }

    public double[][] getPheromoneMatrix() {
        return pheromoneMatrix;
    }
}
