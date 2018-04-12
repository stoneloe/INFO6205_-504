
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import paperGeneration_GA.Paper;
import paperGeneration_GA.Population;
import paperGeneration_GA.Rule;

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
    public void testPaperScore() {
        //new a rule for population:
        Rule rule = new Rule();
        rule.setId(1);
        int[] eachTypeCount = {10, 5, 10, 10, 5};
        rule.setEachTypeCount(eachTypeCount);
        rule.setTotal(100);
        Set<Integer> pointset = new HashSet<>();
        pointset.add((int) (Math.random() * 100 + 1));
        rule.setPoints(pointset);
        Population p = new Population(20, true, rule);
        
        for (Paper k : p.getPapers()){
            assertEquals(k.getTotalScore(),rule.getTotal());
        }
    }
}
