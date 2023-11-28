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

    public void addEdge(int u, int v, int distance) {
        List<Pair<Integer, Integer>> neighbors = adjacencyList.getOrDefault(u, new ArrayList<>());
        neighbors.add(new Pair<>(v, distance));
        adjacencyList.put(u, neighbors);
    }

    public List<Pair<Integer, Integer>> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

}
