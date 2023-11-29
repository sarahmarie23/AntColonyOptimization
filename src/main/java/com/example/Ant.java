package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Ant {
    private List<Integer> visitedCities; // all the cities visited by the ant
    private Set<Integer> unvisitedCities; // all unvisited cities
    private int currCity; // ant's current location
    private HashMap<Integer, Integer> distances; // known cities and distances
    private PheromoneTrail trail; // pheromone trail that all ants update
    private List<Integer> path; // the chosen list of nodes the ant has taken 
    private int totalDistance; // the ant's total distance
    private Random random; // random number generator
    private double alpha; // pheromone influence factor
    private double beta; // distance influence factor
    private int globalBestDistance; // global best distance found so far
    private int currentBestDistance; // current best distance found by the ant


    public Ant(Graph graph, int initialCity) {
        visitedCities = new ArrayList<>();
        unvisitedCities = new HashSet<Integer>(graph.getCities());
        unvisitedCities.remove(Integer.valueOf(initialCity)); // don't need the first city
        currCity = initialCity;
        
        distances = new HashMap<>();
        trail = new PheromoneTrail(graph);
        path = new ArrayList<>();
        totalDistance = 0;

        random = new Random();
        alpha = Constants.ALPHA;
        beta = Constants.BETA;
        globalBestDistance = Integer.MAX_VALUE;
        currentBestDistance = Integer.MAX_VALUE;
    }

    public List<Integer> getPath() {
        return path;
    }
}
