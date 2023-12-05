package com.example;

public class Constants {
    public static final double INITIAL_PHEROMONE_VALUE = 0.01; // tau (inital pheromone level)
    public static final double PHEROMONE_CONCENTRATION = 1.0; // tau (pheromone intensity)
    public static final double DECOMPOSITION_CONSTANT = 1.0; // Q (pheromone evaporation constant)
    public static final double EVAPORATION_RATE = 0.5; // rho (pheromone evaporation rate)
    public static final double ALPHA = 1.0; // controls importance of pheromone trail
    public static final double BETA = 2.0; // controls importance of heuristics, i.e. shorter paths
    public static final int NUM_ANTS = 10;
    public static final int NUM_ITERATIONS = 10;
    public static final int NUM_VERTICES = 10;
}
