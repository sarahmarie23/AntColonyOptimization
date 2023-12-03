package com.example;

import com.example.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Ant {
    private Graph graph;
    private List<Integer> visitedCities; // all the cities visited by the ant
    private Set<Integer> unvisitedCities; // all unvisited cities
    private Set<Integer> unvisitedNeighbors; // unvisited cities that are neighbors
    private int currCity; // ant's current location
    private int initialNode;
    private int destination;
    private HashMap<Integer, Integer> distances; // known cities and distances
    private PheromoneTrail trail; // pheromone trail that all ants update
    private List<Integer> path; // the chosen list of nodes the ant has taken 
    private int totalDistance; // the ant's total distance for that trail
    private Random random; // random number generator
    private double alpha; // pheromone influence factor
    private double beta; // distance influence factor
    private int globalBestDistance; // global best distance found so far
    private int currentBestDistance; // current best distance found by the ant


    public Ant(Graph graph, AdjacencyList adjacencyList, int initialNode, int destination) {
        this.graph = graph;
        this.visitedCities = new ArrayList<>();
        this.unvisitedCities = new HashSet<Integer>(graph.getCities());
        this.unvisitedCities.remove(Integer.valueOf(initialNode)); // don't need the first city
        this.unvisitedNeighbors = new HashSet<>();
        for (Integer city : unvisitedCities) {
            if (adjacencyList.isNeighbor(currCity, city)) {
                unvisitedNeighbors.add(city);
            }
        }
        this.currCity = initialNode;
        this.initialNode = initialNode;
        this.destination = destination;
        this.distances = new HashMap<>();
        this.trail = new PheromoneTrail(graph);
        this.path = new ArrayList<>();
        this.totalDistance = 0;

        this.random = new Random();
        this.alpha = Constants.ALPHA;
        this.beta = Constants.BETA;
        this.globalBestDistance = Integer.MAX_VALUE;
        this.currentBestDistance = Integer.MAX_VALUE;
    }

    public List<Integer> getPath() {
        return path;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getCurrCity() {
        return currCity;
    }

    public List<Pair<Integer, Integer>> getNeighborsAndDistances(int node) {
        return graph.getNeighborsAndDistances(node);
    }

    public void move(int node1, int node2) { // moving from node1 to node2
        this.currCity = node2; // update currCity
        this.totalDistance += graph.getDistance(node1, node2); // update total distance
        visitedCities.add(node2); // add the next node to the visitedCities
    }
     
    public int selectNextCity(int currCity) {
        // get probabilities
        Map<Integer, Double> nodeProbabilities = new HashMap<>(); 
        List<Pair<Integer, Integer>> neighborsAndDistances = getNeighborsAndDistances(currCity);

        double probabilitySummation = 0.0;

        for (Pair<Integer, Integer> pair : neighborsAndDistances) {
            Integer neighbor = pair.getKey();
            Integer distance = pair.getValue();

            double currPheromone = trail.getPheromoneLevel(currCity, neighbor); // tau
            double currQuality = 1.0 / distance; // eta; favors shorter paths
            double numerator = (Math.pow(currPheromone, alpha) * Math.pow(currQuality, beta));
            probabilitySummation += numerator;

            nodeProbabilities.put(neighbor, numerator);
        }

        for (Map.Entry<Integer, Double> entry : nodeProbabilities.entrySet()) {
            int neighbor = entry.getKey();
            double normalizedProbability = entry.getValue() / probabilitySummation;
            nodeProbabilities.put(neighbor, normalizedProbability);
        }

        // choose the next city, with a touch of randomness
        int selectedCity = -1;
        double randomValue = Math.random();
        double accumulatedProbability = 0.0;
        
        for (Map.Entry<Integer, Double> entry : nodeProbabilities.entrySet()) {
            int neighbor = entry.getKey();
            double probability = entry.getValue();

            accumulatedProbability += probability;

            if (randomValue <= accumulatedProbability) {
                selectedCity = neighbor;
                break;
            }
        }
        return selectedCity;
    }
    // TODO next
    public void updateBestPath()
}
