package PaperGenerationTest;


import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
import PaperGeneration.Paper;
import PaperGeneration.Population;
import PaperGeneration.Rule;
import org.hamcrest.Matchers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author houxiaoge
 */
public class PopulationTest {  
    @Test
    public void test6_PaperScore() {      
        for (Paper p : population.getPapers()){
            assertEquals(p.getTotalScore(),paperRule.getTotal());
        }
    }
    
    @Test
    public void test7_Sort(){
        Paper bestPaper = population.getBestFitnessPaper();
        assertThat(bestPaper.getAdaptationDegree(), Matchers.greaterThan(population.getPapers().get(1).getAdaptationDegree()));
    }
    
    static Rule paperRule = getRule();
    static Population population = getPopulation();
    
    
    public static Rule getRule(){
        Rule rule = new Rule();
        int[] eachTypeCount = {10, 5, 10, 10, 5};
        rule.setEachTypeCount(eachTypeCount);
        rule.setTotal(100);
        rule.setDifficulty(0.89);
        Set<Integer> pointset = new HashSet<>();
        for(int i = 0; i < 20; i++){
            pointset.add((int) (Math.random() * 100 + 1));
        }        
        rule.setPoints(pointset);
        return rule;
    }
    
    public static Population getPopulation(){       
        Population p = new Population(20, true, paperRule);
        return p;
    }
}
