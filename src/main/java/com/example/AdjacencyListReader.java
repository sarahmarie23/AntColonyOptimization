package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
/**
 * Reads an adjacency list from the specified input stream and constructs a corresponding adjacency list data structure
 * in memory. Each line in the input file represents an edge in the graph, represented by three space-separated integers:
 * start node, neighbor node, and edge weight. The adjacency list is represented as a HashMap, where the keys are node IDs
 * and the values are ArrayLists of adjacent nodes and their respective edge weights.
 *
 * @param fileName The input stream containing the adjacency list
 * @param adjacencyList The HashMap to store the constructed adjacency list
 * @throws IOException If an error occurs while reading the input file
 */
public class AdjacencyListReader {

    public static void readAdjacencyList(InputStream fileName, Map<Integer, List<Pair<Integer, Integer>>> adjacencyList) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileName))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                // parsing each line into a start node, neighbor node, and distance values
                String[] parts = line.split(" ");

                int startNode = Integer.parseInt(parts[0]);
                int neighbor = Integer.parseInt(parts[1]);
                int distance = Integer.parseInt(parts[2]);

                // creates a list of neighbors and distances for each node
                adjacencyList.putIfAbsent(startNode, new ArrayList<>());
                adjacencyList.get(startNode).add(new Pair<>(neighbor, distance));
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}