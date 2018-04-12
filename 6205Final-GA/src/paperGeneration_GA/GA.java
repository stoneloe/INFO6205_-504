/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paperGeneration_GA;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author houxiaoge
 */
public class GA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Rule rule = new Rule();
        rule.setId(1);
        int[] eachTypeCount = {10, 5, 10, 10, 5};
        rule.setEachTypeCount(eachTypeCount);
        rule.setTotal(100);
        Set<Integer> pointset = new HashSet<>();
        pointset.add((int) (Math.random() * 100 + 1));
        rule.setPoints(pointset);
        Population p = new Population(20, true, rule);

        for (Paper k : p.getPapers()) {
            System.out.println("ID:" + k.getId() + ", TotalScore:" + k.getTotalScore());
            System.out.println("***********************************************************");
            for (Problem m : k.getProblemList()) {
                System.out.println("ID: "+m.getId()+ ", Type: "+m.getType());
            }
        }
    }

}
