package com.example;

import java.io.IOException;
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

    public List<Pair<Integer, Integer>> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }
}
