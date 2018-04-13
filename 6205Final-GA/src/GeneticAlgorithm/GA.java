/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneticAlgorithm;

import PaperGeneration.*;

/**
 *
 * @author MJHCHLOE
 */
public class GA {
    private static boolean elitism = true;
    private static int tournamentSize = 5;
    private static double mutationRate = 0.085;
    private static int elitismOffset;
    
    public static Population evolvePopulation(Population pop, Rule rule) {
        Population newPopulation = new Population();
        // Apply Elite Evolutionary Method
        if (elitism) {
            elitismOffset = 1;
            // Keep the best fitness paper of the last generation
            Paper bestFitnessPaper = pop.getBestFitnessPaper();
            newPopulation.addPaper(0, bestFitnessPaper);
        }
        // Crossover to generate new generation
        for (int i = elitismOffset; i < newPopulation.getPapers().size(); i++) {
            // Select parents
            Paper parent1 = select(pop);
            Paper parent2 = select(pop);
            while (parent2.getId() == parent1.getId()) {
                parent2 = select(pop);
            }
            // Crossover
            Paper child = crossover(parent1, parent2, rule);
            newPopulation.addPaper(i, child);
        }
        // Mutate
        Paper tmpPaper;
        for (int i = elitismOffset; i < newPopulation.getPapers().size(); i++) {
            tmpPaper = newPopulation.getPapers().get(i);
            mutate(tmpPaper);
            // Calculate KP Coverage and Adaptation Degree
            tmpPaper.setKPCoverage(rule);
            tmpPaper.setAdaptationDegree(rule, GlobalWeight.KP_WEIGHT, GlobalWeight.DIFFCULTY_WEIGHt);
        }
        return newPopulation;
    }

    private static Paper select(Population pop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Paper crossover(Paper parent1, Paper parent2, Rule rule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void mutate(Paper tmpPaper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
