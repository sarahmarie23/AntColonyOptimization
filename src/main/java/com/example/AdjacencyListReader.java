package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class AdjacencyListReader {

    public static void readAdjacencyList(InputStream fileName, Map<Integer, List<Pair<Integer, Integer>>> adjacencyList) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileName))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                int startNode = Integer.parseInt(parts[0]);
                int neighbor = Integer.parseInt(parts[1]);
                int distance = Integer.parseInt(parts[2]);

                adjacencyList.putIfAbsent(startNode, new ArrayList<>());
                adjacencyList.get(startNode).add(new Pair<>(neighbor, distance));
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}