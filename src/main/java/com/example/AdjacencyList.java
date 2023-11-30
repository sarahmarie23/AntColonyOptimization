package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyList {
    private Map<Integer, List<Pair<Integer, Integer>>> adjacencyList;

    public AdjacencyList() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(int node1, int node2, int distance) {
        List<Pair<Integer, Integer>> neighbors = adjacencyList.getOrDefault(node1, new ArrayList<>());
        neighbors.add(new Pair<>(node2, distance));
        adjacencyList.put(node1, neighbors);
    }

    public List<Pair<Integer, Integer>> getNeighborsAndDistances(int node1) {
        return adjacencyList.getOrDefault(node1, new ArrayList<>());
    }

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

    public Map<Integer, List<Pair<Integer, Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

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

    public int getDegree(int node1) {
        int degree = adjacencyList.get(node1).size();
        return degree;
    }

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

    public boolean isEmpty() {
        if (adjacencyList.isEmpty()) {
            return true;
        }

        return false;
    }

}

