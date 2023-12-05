package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyList {
    private Map<Integer, List<Pair<Integer, Integer>>> adjacencyList;

    /**
    * Constructs an empty adjacency list.
    */
    public AdjacencyList() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a directed edge to the adjacency list, connecting `node1` to `node2` with a distance of `distance`.
     *
     * @param node1 The source node
     * @param node2 The destination node
     * @param distance The distance between the two nodes
     */
    public void addEdge(int node1, int node2, int distance) {
        List<Pair<Integer, Integer>> neighbors = adjacencyList.getOrDefault(node1, new ArrayList<>());
        neighbors.add(new Pair<>(node2, distance));
        adjacencyList.put(node1, neighbors);
    }

    /**
     * Retrieves a list of pairs representing the neighbors of `node1` and their corresponding distances.
     *
     * @param node1 The node for which to retrieve neighbors and distances
     * @return A list of pairs where each pair represents a neighbor and its distance from `node1`
     */
    public List<Pair<Integer, Integer>> getNeighborsAndDistances(int node1) {
        return adjacencyList.getOrDefault(node1, new ArrayList<>());
    }

    /**
     * Checks whether there is an edge directed from `node1` to `node2`.
     *
     * @param node1 The source node
     * @param node2 The destination node
     * @return True if there is an edge from `node1` to `node2`, false otherwise
     */
    public boolean hasEdge(int node1, int node2) {
        if (!adjacencyList.containsKey(node1)) {
            return false;
        }

        for (Pair<Integer, Integer> neighbor : adjacencyList.get(node1)) {
            if (neighbor.getKey() == node2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the adjacency list as a `HashMap` where keys represent node IDs and values represent lists of neighboring nodes
     * and their corresponding distances.
     *
     * @return The adjacency list as a `HashMap`
     */
    public Map<Integer, List<Pair<Integer, Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Retrieves a list of neighboring node IDs for `node1`.
     *
     * @param node1 The node for which to retrieve neighboring node IDs
     * @return A list of neighboring node IDs for `node1`
     */
    public List<Integer> getNeighbors(int node1) {
        if (!adjacencyList.containsKey(node1)) {
            return new ArrayList<>();
        }

        List<Pair<Integer, Integer>> neighbors = adjacencyList.get(node1);
        List<Integer> neighborIDs = new ArrayList<>();

        for (Pair<Integer, Integer> neighbor : neighbors) {
            int neighborID = neighbor.getKey();
            neighborIDs.add(neighborID);
        }

        return neighborIDs; 
    }

    /**
     * Retrieves the degree (number of neighbors) for a given node.
     *
     * @param node1 The node for which to calculate the degree
     * @return The degree (number of neighbors) for the specified node
     */
    public int getDegree(int node1) {
        int degree = adjacencyList.get(node1).size();
        return degree;
    }

    /**
     * Retrieves the distance between two nodes, if an edge exists between them.
     *
     * @param node1 The source node
     * @param node2 The destination node
     * @return The distance between the two nodes if an edge exists, -1 otherwise
     */
    public int getDistance(int node1, int node2) {
        if (!adjacencyList.containsKey(node1)) {
            return -1;
        }

        List<Pair<Integer, Integer>> neighbors = adjacencyList.get(node1);
        for (Pair<Integer, Integer> neighbor : neighbors) {
            int neighborID = neighbor.getKey();
            int distance = neighbor.getValue();

            if (neighborID == node2) {
                return distance;
            }
        }

        return -1;
    }

    /**
     * Checks whether two nodes are neighbors, i.e., if there is an edge connecting them.
     *
     * @param node1 The source node
     * @param node2 The destination node
     * @return True if the two nodes are neighbors, false otherwise
     */
    public boolean isNeighbor(int node1, int node2) {
        if (!adjacencyList.containsKey(node1)) {
            return false;
        }

        for (Pair<Integer, Integer> neighbor : adjacencyList.get(node1)) {
            int neighborID = neighbor.getKey();

            if (neighborID == node2) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether the adjacency list is empty, containing no nodes or edges.
     *
     * @return True if the adjacency list is empty, false otherwise
     */
    public boolean isEmpty() {
        if (adjacencyList.isEmpty()) {
            return true;
        }

        return false;
    }
}

