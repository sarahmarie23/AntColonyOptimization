package com.example;

import com.example.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Ant {
    private Graph graph;
    private List<Integer> visitedCities; // all the cities visited by the ant
    private Set<Integer> unvisitedCities; // all unvisited cities
    private Set<Integer> unvisitedNeighbors; // unvisited cities that are neighbors
    private int currCity; // ant's current location
    private HashMap<Integer, Integer> distances; // known cities and distances
    private PheromoneTrail trail; // pheromone trail that all ants update
    private List<Integer> path; // the chosen list of nodes the ant has taken 
    private int totalDistance; // the ant's total distance for that trail
    private Random random; // random number generator
    private double alpha; // pheromone influence factor
    private double beta; // distance influence factor
    private int globalBestDistance; // global best distance found so far
    private int currentBestDistance; // current best distance found by the ant


    public Ant(Graph graph, AdjacencyList adjacencyList, int initialCity) {
        this.graph = graph;
        visitedCities = new ArrayList<>();
        unvisitedCities = new HashSet<Integer>(graph.getCities());
        unvisitedCities.remove(Integer.valueOf(initialCity)); // don't need the first city
        unvisitedNeighbors = new HashSet<>();
        for (Integer city : unvisitedCities) {
            if (adjacencyList.isNeighbor(currCity, city)) {
                unvisitedNeighbors.add(city);
            }
        }
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

    public int getTotalDistance() {
        return totalDistance;
    }

    public void move(int node1, int node2) {
        totalDistance += graph.getDistance(node1, node2);
    }
    /* 
    // finish this next, still working on it
    public void selectNextCity() {
        double[] probabilities = new double[unvisitedNeighbors.size()];
        
        // Calculate probabilities for each unvisited neighbor
        int index = 0;
        for (Integer city : unvisitedNeighbors) {
            double pheromoneLevel = trail.getPheromoneLevel(currCity, city);
            double distance = graph.getDistance(currCity, city);
            
            double probability = Math.pow(pheromoneLevel, alpha) * Math.pow(1 / distance, beta);
            probabilities[index++] = probability;
        }

        // Normalize probabilities
        double sum = 0;
        for (double probability : probabilities) {
            sum += probability;
        }
        
        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= sum;
        }

        // Select the next city using weighted random selection
        int nextCityIndex = weightedRandomSelection(probabilities);
        Integer nextCity = unvisitedNeighbors.toArray()[nextCityIndex];

        // Update ant's state
        visitedCities.add(nextCity);
        unvisitedCities.remove(nextCity);
        unvisitedNeighbors.clear();
        for (Integer neighbor : graph.getNeighbors(nextCity)) {
            if (unvisitedCities.contains(neighbor)) {
                unvisitedNeighbors.add(neighbor);
            }
        }
        
    }
    */
}
