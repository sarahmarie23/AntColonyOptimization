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

    public List<Pair<Integer, Integer>> getNeighborsAndDistances(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public boolean hasEdge(int node1, int node2) {
        if (adjacencyList.containsValue(node1)) {
            if (adjacencyList.getValue(node1).stream().anyMatch(e -> getKey() == node2)) {
                return true;
            }
        }
        return false;
    }

    public Map<Integer, List<Pair<Integer, Integer>>> getAdjacencyList() {
        return adjacencyList;
    }

}

