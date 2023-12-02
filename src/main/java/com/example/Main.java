package com.example;

public class Main {
    public static void main(String[] args) {

        int source = Integer.parseInt(args[0]);
        int destination = Integer.parseInt(args[1]);
        String fileName = args[2];
        
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