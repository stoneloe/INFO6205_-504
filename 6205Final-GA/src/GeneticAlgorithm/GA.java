/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneticAlgorithm;

import PaperGeneration.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author MJHCHLOE
 */
public class GA {

    private static boolean isElitism = true;
    private static int tournamentSize = 5;
    private static double mutationRate = 0.00005;
    private static int elitism;
    private static Random random = new Random();

    public static Population evolvePopulation(Population pop, Rule rule) {
        Population newPopulation = new Population();
        newPopulation.setDB(pop.getDB());
        // Apply Elite Evolutionary Method
        if (isElitism) {
            elitism = 1;
            // Keep the best fitness paper of the last generation
            Paper bestFitnessPaper = pop.getBestFitnessPaper();
            newPopulation.addPaper(0, rule, bestFitnessPaper);
        }
        // Crossover to generate new generation
        for (int i = elitism; i < pop.getPapers().size(); i++) {
            // Select parents
            Paper parent1 = select(pop);
            Paper parent2 = select(pop);
            while (parent2.getId() == parent1.getId()) {
                parent2 = select(pop);
            }
            // Crossover
            Paper child = crossover(parent1, parent2, rule, newPopulation);
            newPopulation.addPaper(i, rule, child);
        }
        // Mutate
        Paper tmpPaper;
        for (int i = elitism; i < newPopulation.getPapers().size(); i++) {
            tmpPaper = newPopulation.getPapers().get(i);
            mutate(newPopulation, tmpPaper);
            // Calculate KP Coverage and Adaptation Degree
            tmpPaper.setKPCoverage(rule);
            tmpPaper.setAdaptationDegree(rule, GlobalWeight.KP_WEIGHT, GlobalWeight.DIFFCULTY_WEIGHt);
        }
        return newPopulation;
    }

    public static Paper select(Population population) {
        Population pop = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            pop.getPapers().add(population.getPapers().get((random.nextInt(population.getPapers().size()))));
        }
        return pop.getBestFitnessPaper();
    }

    public static Population selection(Population population) {
        Population pop = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            pop.getPapers().add(population.getPapers().get((random.nextInt(population.getPapers().size()))));
        }
        return pop;
    }

    public static int[] generatePoints(List<Problem> paper1, List<Problem> paper2) {
        // Choose two points randomly
        int[] points = new int[2];
        int s1 = random.nextInt(paper1.size());
        int s2 = random.nextInt(paper1.size());
        while (s1 == s2) {
            s2 = random.nextInt(paper1.size());
        }
        points[0] = s1 < s2 ? s1 : s2;
        points[1] = s1 > s2 ? s1 : s2;

        if (isScoreEqual(points, paper1, paper2)) {
            return points;
        } 
        return generatePoints(paper1, paper2);
    }

    public static boolean isScoreEqual(int[] points, List<Problem> paper1, List<Problem> paper2) {
        // ensure the score between s1 and s2 of parent1 is equal to that of parent2
        int total_p1 = 0;
        int total_p2 = 0;
        for (int i = points[0]; i < points[1]; i++) {
            total_p1 += paper1.get(i).getScore();
            total_p2 += paper2.get(i).getScore();
        }
        return total_p1 == total_p2;
    }

    public static Paper crossover(Paper parent1, Paper parent2, Rule rule, Population pop) {
        Paper child = new Paper();
        // Save problems into list
        List<Problem> paper1 = new ArrayList<>();
        for (Problem p : parent1.getProblemList()) {
            paper1.add(p);
        }
        List<Problem> paper2 = new ArrayList<>();
        for (Problem p : parent2.getProblemList()) {
            paper2.add(p);
        }
        paper1.sort((p1, p2) -> p1.getId() - p2.getId());
        paper2.sort((p1, p2) -> p1.getId() - p2.getId());

        int[] points = generatePoints(paper1, paper2);

        // Copy problems from paper1 except problems between this two points
        for (int i = 0; i < points[0]; i++) {
            child.getProblemList().add(paper1.get(i));
        }
        for (int i = points[1]; i < paper1.size(); i++) {
            child.getProblemList().add(paper1.get(i));
        }
        // Copy problems from paper2 between two points
        for (int i = points[0]; i < points[1]; i++) {
            Problem problem = paper2.get(i);
            if (!child.getProblemList().contains(problem)) {
                child.getProblemList().add(problem);
            } else {
                List<Problem> problemList = pop.getProblemTypeListWithoutItself(problem);
                if (problemList.size() > 0) {
                    // get a problem randomly                   
                    int index = random.nextInt(problemList.size());
                    Problem p = problemList.get(index);
                    while (child.getProblemList().contains(p)) {
                        index = random.nextInt(problemList.size());
                        p = problemList.get(index);
                    }
                    child.getProblemList().add(p);
                } else {
                    throw new UnknownError("Cannot find problem with same type and score when generate new paper. ");
                }
            }
        }
        return child;
    }

    public static void mutate(Population population, Paper paper) {
        List<Problem> temp1 = new ArrayList<>();
        List<Problem> temp2 = new ArrayList<>();
        for (Problem p : paper.getProblemList()) {
            if (Math.random() < mutationRate) {
                List<Problem> problemList = population.getProblemTypeListWithoutItself(p);
                if (problemList.size() > 0) {
                    // get a problem randomly                   
                    temp1.add(p);
                    int index = random.nextInt(problemList.size());
                    Problem problem = problemList.get(index);
                    while (paper.getProblemList().contains(problem)) {
                        index = random.nextInt(problemList.size());
                        problem = problemList.get(index);
                    }
                    temp2.add(problem);

                }
            }
        }
        paper.getProblemList().removeAll(temp1);
        paper.getProblemList().addAll(temp2);
    }
}
