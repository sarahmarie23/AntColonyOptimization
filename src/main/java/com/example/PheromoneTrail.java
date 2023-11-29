package com.example;

import static com.example.Constants.*;

public class PheromoneTrail {
    private double[][] pheromoneMatrix;

    public PheromoneTrail(Graph graph) {
        initializePheromoneMatrix(graph);
    }

    private void initializePheromoneMatrix(Graph graph) {
        pheromoneMatrix = new double[graph.getNumVertices()][graph.getNumVertices()];

        for (int i = 0; i < graph.getNumVertices(); i++) {
            for (int j = 0; j < graph.getNumVertices(); j++) {
                pheromoneMatrix[i][j] = INITIAL_PHEROMONE_VALUE;
            }
        }
    }

    public double[][] getPheromoneMatrix() {
        return pheromoneMatrix;
    }

    public void updatePheromoneMatrix(Ant ant) { // do this when the ant reaches the destination
        
        
        // depositing pheromone
        for (int i = 0; i < ant.getPath().size() - 1; i++) {
            int node1 = ant.getPath().get(i);
            int node2 = ant.getPath().get(i + 1);
            pheromoneMatrix[node1][node2] += Constants.PHEROMONE_CONCENTRATION;
            pheromoneMatrix[node2][node1] += Constants.PHEROMONE_CONCENTRATION;
        }

    }
}
