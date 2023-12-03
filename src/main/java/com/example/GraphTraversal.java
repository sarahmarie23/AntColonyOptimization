package com.example;

public class GraphTraversal {
    private Graph graph;
    private int destination;

    public GraphTraversal(Graph graph, int destination) {
        this.graph = graph;
        this.destination = destination;
    }

    public void sendAntsOnGraph(Ant[] ants, PheromoneTrail trail) {
        int bestPathCount = 0;

        while (true) {
            for (Ant ant : ants) {
                int curr = ant.getCurrCity();
                while (ant.getCurrCity() != destination) {
                    int next = ant.selectNextCity(curr);
                    ant.move(curr, next);
                    curr = ant.getCurrCity();
                }

                ant.getTotalDistance();
            }

            


            
        }
    }
}
