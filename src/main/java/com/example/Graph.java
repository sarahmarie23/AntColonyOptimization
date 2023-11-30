package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<Integer, List<Pair<Integer, Integer>>> adjacencyList; // [node, <neighbor, distance>]

    String fileName = "distances.txt";

    public Graph(String fileName) throws IOException {
        this.adjacencyList = AdjacencyListReader.readAdjacencyList(fileName);
    }

    public int getNumVertices() {
        return adjacencyList.size();
    }

    public Set<Integer> getCities() {
        return adjacencyList.keySet();
    }

    public List<Pair<Integer, Integer>> getNeighbors(int node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }

    public List<Pair<Integer, Integer>> getNeighborsAndDistances(int node) {
        return adjacencyList.get(node);
    }

    public int getDistance(int node1, int node2) {
        List<Pair<Integer, Integer>> neighbors = adjacencyList.get(node1);
        if (neighbors != null) {
            for (Pair<Integer, Integer> neighbor : neighbors) {
                if (neighbor.getKey() == node2) {
                    return neighbor.getValue();
                }
            }
        }
        return -1;
    }
}
