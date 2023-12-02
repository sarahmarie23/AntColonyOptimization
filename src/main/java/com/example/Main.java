package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        int source = Integer.parseInt(args[0]);
        int destination = Integer.parseInt(args[1]);
        String fileName = args[2];

        Graph graph = new Graph(fileName);
        AdjacencyList adjacencyList = new AdjacencyList();
        Ant[] ants = new Ant[Constants.NUM_ANTS];

        for (int i = 0; i < Constants.NUM_ANTS; i++) {
            ants[i] = new Ant(graph, adjacencyList, source, destination);
        }

        // stopping criteria is in GraphTraversal
        
        // stopping criteria will be when a specified percentage of ants are on the best path
        /*
         * begin
         * initialize
         * 
         * While stopping criterion not satisfied do
         *      Position each ant in a starting node
         *      Repeat for each ant do
         *          Choose new node by applying the state transition
         *          Apply step by step local pheromone update
         *      End for
         *      Until every ant has built a solution
         *          Update best solution
         * Apply global pheromone update
         * End while
         * End
         * 
         */

    }
}