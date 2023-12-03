package com.example;

import java.util.List;


public class PheromoneTrail {
    private int numVertices;
    private double[][] pheromoneMatrix;

    public PheromoneTrail(int numVertices) {
        this.numVertices = numVertices;
        pheromoneMatrix = new double[numVertices][numVertices];
        initializePheromoneMatrix();
    }

    private void initializePheromoneMatrix() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                pheromoneMatrix[i][j] = Constants.INITIAL_PHEROMONE_VALUE;
            }
        }
    }

    public double getPheromoneLevel(int node1, int node2) {
        return pheromoneMatrix[node1][node2];
    }

    public double[][] getPheromoneMatrix() {
        return pheromoneMatrix;
    }

    public void updatePheromoneMatrix(Ant[] ants) { // do this when all ants have reached the end
        // pheromone evaporation
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                pheromoneMatrix[i][j] *= (1 - Constants.EVAPORATION_RATE);
            }
        }

        // pheromone deposition
        for (Ant ant : ants) {
            double deposition = Constants.DECOMPOSITION_CONSTANT/ant.getTotalDistance(); // TODO: calc getTotalDistance during ant traversal

            List<Integer> path = ant.getPath();
        
            // update pheromone matrix for edges that ant visited
            for (int i = 0; i < path.size() - 1; i++) {
                int node1 = path.get(i);
                int node2 = path.get(i + 1);

                pheromoneMatrix[node1][node2] += deposition;
                pheromoneMatrix[node2][node1] += deposition;
            }
        }
    }
}
