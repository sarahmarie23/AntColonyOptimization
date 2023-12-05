package com.example;

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
    private int currNode; // ant's current location
    private int numNodes;
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
            if (adjacencyList.isNeighbor(initialNode, city)) {
                unvisitedNeighbors.add(city);
            }
        }
        this.currNode = initialNode;
        this.numNodes = Constants.NUM_VERTICES;
        this.initialNode = initialNode;
        visitedCities.add(initialNode);
        this.destination = destination;
        this.distances = new HashMap<>();
        this.trail = new PheromoneTrail(numNodes); // need number of nodes
        this.path = new ArrayList<>();
        path.add(initialNode);
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

    public int getCurrNode() {
        return currNode;
    }

    public void clearUnvisitedNeighbors() {
        this.unvisitedNeighbors.clear();
    }

    public void clearVisitedCities() {
        this.visitedCities.clear();
    }

    public List<Pair<Integer, Integer>> getNeighborsAndDistances(int node) {
        System.out.println("the node is "+node);
        return graph.getNeighborsAndDistances(node);
    }

    public void move(int node1, int node2) { // moving from node1 to node2
        this.currNode = node2; // update currNode
        this.totalDistance += graph.getDistance(node1, node2); // update total distance
        visitedCities.add(node2); // add the next node to the visitedCities
        unvisitedCities.remove(node2); // remove from unvisitedCities
        updateBestPath(); // check if we have the best path so far
        path.add(node2); // add the next node to the path
    }
     
    public int selectNextCity(int currCity) {
        // get unvisited neighbors
        System.out.println("curr city "+currCity);
        List<Pair<Integer, Integer>> neighborsAndDistances = getNeighborsAndDistances(currCity);
        List<Pair<Integer, Integer>> unvisitedNeighbors = new ArrayList<>();
        

        for (Pair<Integer, Integer> pair : neighborsAndDistances) {
            Integer neighbor = pair.getKey();
            if (unvisitedCities.contains(neighbor)) { // Assuming unvisitedCities is the set of unvisited nodes
                unvisitedNeighbors.add(pair);
            }
        }
        System.out.println("size of unvisited neighbors " +unvisitedNeighbors.size());
        System.out.println("unvisited neighbors ");
        for (Pair<Integer, Integer> pair : unvisitedNeighbors) {
            Integer neighbor = pair.getKey();
            Integer distance = pair.getValue();
            System.out.println("Neighbor: " + neighbor + ", Distance: " + distance);
        }


        if (unvisitedNeighbors.isEmpty()) {
            // If no unvisited neighbors, return -1 or handle the case accordingly
            return -1;
        }

        unvisitedNeighbors.removeIf(pair -> visitedCities.contains(pair.getKey())); 

        // get probabilities
        Map<Integer, Double> nodeProbabilities = new HashMap<>(); 
        double probabilitySummation = 0.0;

        for (Pair<Integer, Integer> pair : unvisitedNeighbors) {
            Integer neighbor = pair.getKey();
            if (neighbor == destination) {
                return destination;
            }
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
        System.out.println("Node probabilities: " + nodeProbabilities);
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
                System.out.println("Selected next city: " + selectedCity);
                break;
            }
        }
        return selectedCity;
    }
    
    public boolean updateBestPath() {
        boolean updatedBestPath = false;

        if (totalDistance < globalBestDistance) {
            globalBestDistance = totalDistance;
            updatedBestPath = true;
        }

        if (totalDistance < currentBestDistance) {
            currentBestDistance = totalDistance;
            updatedBestPath = true;
        }

        return updatedBestPath;
    }
}
