/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneticAlgorithmTest;

import GeneticAlgorithm.GA;
import PaperGeneration.*;
import PaperGenerationTest.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.hamcrest.Matchers;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author MJHCHLOE
 */
public class GATest {
    static Rule paperRule = PopulationTest.getRule();
    static Population population = PopulationTest.getPopulation();
    static Random random = new Random();
    
    @Test
    public void test1_Selection(){
        Population pop = GA.selection(population);
        Paper p = pop.getBestFitnessPaper();
        for(int i = 0; i < 5; i++){
            assertThat(p.getAdaptationDegree(), Matchers.greaterThanOrEqualTo(pop.getPapers().get(i).getAdaptationDegree()));
        }
    }
    
    @Test
    public void test2_mutation(){
        Paper paper = population.getPapers().get(3);
        int num1 = paper.getProblemCount();
        double d1 = paper.getDifficulty();
        List<Integer> id1 = new ArrayList<>();
        for(Problem p : paper.getProblemList()){
            id1.add(p.getId());
        }
        
        GA.mutation(population, paper);
        int num2 = paper.getProblemCount();
        double d2 = paper.getDifficulty();
        List<Integer> id2 = new ArrayList<>();
        for(Problem p : paper.getProblemList()){
            id2.add(p.getId());
        }
        assertEquals(num1, num2);
        assertNotEquals(d1, d2);
    }
    
    @Test
    public void test3_crossover(){
        Paper paper1 = GA.select(population);
        Paper paper2 = GA.select(population);
        double d1 = paper1.getDifficulty();
        double d2 = paper2.getDifficulty();
        double f1 = paper1.getAdaptationDegree();
        double f2 = paper2.getAdaptationDegree();
        
        while (paper1.getId() == paper2.getId()) {
                paper2 = GA.select(population);
            }
        Paper child = GA.crossover(paper1, paper2, paperRule, population);
        double d3 = child.getDifficulty();
        double f3 = child.getAdaptationDegree();
        assertNotEquals(d1, d3);
        assertNotEquals(d2, d3);
        assertNotEquals(f1, f3);
        assertNotEquals(f2, f3);
        
    }
    
}
