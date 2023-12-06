package com.example;

public class GraphTraversal {
    private Graph graph;
    private int destination;

    public GraphTraversal(Graph graph, int destination) {
        this.graph = graph;
        this.destination = destination;
    }

    public void sendAntsOnGraph(Ant[] ants, PheromoneTrail trail) {
        int count = 0;
        
        for (Ant ant : ants) {
            int curr = ant.getCurrNode();
            ant.clearUnvisitedNeighbors();
            ant.clearVisitedCities();
            while (ant.getCurrNode() != destination) {
                int next = ant.selectNextCity(curr);
                ant.move(curr, next);
                curr = ant.getCurrNode();
            }
            count++;
            //ant.getTotalDistance(); might use it later
        }
    }
}
