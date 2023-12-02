package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class AdjacencyListReader {

    public static void readAdjacencyList(String fileName, Map<Integer, List<Pair<Integer, Integer>>> adjacencyList) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int startNode = Integer.parseInt(parts[0]);

                List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
                for (int i = 0; i < parts.length; i += 2) {
                    int neighbor = Integer.parseInt(parts[i]);
                    int distance = Integer.parseInt(parts[i + 1]);

                    neighbors.add(new Pair<>(neighbor, distance));
                }
                
                adjacencyList.put(startNode, neighbors);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}