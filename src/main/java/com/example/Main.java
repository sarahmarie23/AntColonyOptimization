package com.example;

import java.io.IOException;
/**
 * The main entry point of the application. Parses command-line arguments, constructs a graph from the specified file,
 * and runs the ant colony optimization algorithm to find the shortest path between a source node and a destination node.
 *
 * @param args The command-line arguments
 * @throws IOException If an error occurs while reading the input file or generating the output
 */
public class Main {
    public static void main(String[] args) throws IOException {

        // parsing command line arguments: source node, destination node, distances file
        int source = Integer.parseInt(args[0]);
        int destination = Integer.parseInt(args[1]);
        String fileName = "data/" + args[2];
        
        // generate graph and pheromone trail
        Graph graph = new Graph(fileName);

        GraphTraversal graphTraversal = new GraphTraversal(graph, destination);
        PheromoneTrail pheromoneTrail = new PheromoneTrail(graph.getNumVertices());

        int iterations = 0;

        while (iterations < Constants.NUM_ITERATIONS) {
            System.out.print("ITERATION #"+iterations);
            // generate array of ants
            Ant[] ants = new Ant[Constants.NUM_ANTS];
            AdjacencyList adjacencyList = new AdjacencyList();

            for (int i = 0; i < Constants.NUM_ANTS; i++) {
                ants[i] = new Ant(graph, adjacencyList, source, destination);
            }

            // send ants on graph and update pheromone matrix after all ants are done
            graphTraversal.sendAntsOnGraph(ants, pheromoneTrail);
            pheromoneTrail.updatePheromoneMatrix(ants);

            // get average ant distance
            double avgDistance = 0.0;
            for (Ant ant : ants) {
                avgDistance += ant.getTotalDistance();
            }
            avgDistance /= Constants.NUM_ANTS;
            System.out.println(" avg distance = " + avgDistance);
            iterations++;
        }
        
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