package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Ant {
    Set<Integer> visitedCities = new HashSet<>(); // all the cities visited by the ant
    Set<Integer> unvisitedCities; // all unvisited cities
    int curr; // ant's current location
    HashMap<Integer, Integer> distances = new HashMap<>(); // known cities and distances
    PheromoneTrail pt = new PheromoneTrail(); // pheromone trail that all ants update

    public Ant(Graph graph, int initialCity) {
        unvisitedCities = new Set<>(graph.adjacencyList.keySet());
        unvisitedCities.remove(Integer.valueOf(initialCity)); // don't need the first city
        curr = initialCity;
    }
}
