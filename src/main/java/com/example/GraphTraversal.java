package com.example;

public class GraphTraversal {
    private Graph graph;
    private int destination;

    public GraphTraversal(Graph graph, int destination) {
        this.graph = graph;
        this.destination = destination;
    }

    public void sendAntsOnGraph(Ant[] ants, PheromoneTrail trail) {
        int iterations = 0;

        while (iterations < Constants.NUM_ITERATIONS) {

            for (Ant ant : ants) {
                int curr = ant.getCurrNode();

                while (ant.getCurrNode() != destination) {
                    int next = ant.selectNextCity(curr);
                    ant.move(curr, next);
                    curr = ant.getCurrNode();
                }

                //ant.getTotalDistance(); might use it later
            }

            trail.updatePheromoneMatrix(ants);
            iterations++;
        }
    }
}
