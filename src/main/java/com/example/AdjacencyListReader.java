package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class AdjacencyListReader {

    public static Map<Integer, List<Pair<Integer, Integer>>> readAdjacencyList(String filePath) throws IOException {
        Map<Integer, List<Pair<Integer, Integer>>> adjacencyList = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader("distances.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            int source = Integer.parseInt(parts[0]);
            int destination = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);

            if (!adjacencyList.containsKey(source)) {
                adjacencyList.put(source, new ArrayList<>());
            }
            List<Pair<Integer, Integer>> neighbors = adjacencyList.get(source);
            neighbors.add(new Pair<>(destination, weight));
        }

        reader.close();
        return adjacencyList;
    }
}