package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        int source = Integer.parseInt(args[0]);
        int destination = Integer.parseInt(args[1]);
        String fileName = "data/" + args[2];

        System.out.println(source);
        System.out.println(destination);
        System.out.println(fileName);

        System.out.println("File Path: " + fileName);
        /* 
        Graph graph = new Graph(fileName);
        
        AdjacencyList adjacencyList = new AdjacencyList();
        Ant[] ants = new Ant[Constants.NUM_ANTS];

        for (int i = 0; i < Constants.NUM_ANTS; i++) {
            ants[i] = new Ant(graph, adjacencyList, source, destination);
        }

        GraphTraversal graphTraversal = new GraphTraversal(graph, destination);
        PheromoneTrail pheromoneTrail = new PheromoneTrail(graph.getNumVertices());
        graphTraversal.sendAntsOnGraph(ants, pheromoneTrail);
        
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