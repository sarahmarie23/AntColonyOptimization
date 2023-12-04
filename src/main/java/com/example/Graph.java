package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final Map<Integer, List<Pair<Integer, Integer>>> adjacencyList; // [node, <neighbor, distance>]
    private final String fileName;

    public Graph(String fileName) throws IOException {
        this.fileName = fileName;
        this.adjacencyList = new HashMap<>();

        try {
            System.out.println(fileName);
            loadAdjacencyList();
            System.out.println("size is " + adjacencyList.size());
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    private void loadAdjacencyList() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }

            AdjacencyListReader.readAdjacencyList(inputStream, adjacencyList);
        }
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
