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
            System.out.println("ANT NUMBER " + count);
            ant.clearUnvisitedNeighbors();
            ant.clearVisitedCities();
            System.out.println("curr in send ants "+curr);
            while (ant.getCurrNode() != destination) {
                int next = ant.selectNextCity(curr);
                System.out.println("next is "+next);
                ant.move(curr, next);
                curr = ant.getCurrNode();
                System.out.println("new curr "+curr);
                System.out.println("Current node: " + curr);
                System.out.println("Destination node: " + destination);
            }
            System.out.println("Ant finished");
            count++;
            //ant.getTotalDistance(); might use it later
        }
    }
}
