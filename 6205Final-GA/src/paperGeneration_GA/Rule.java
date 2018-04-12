/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paperGeneration_GA;

import java.util.List;
import java.util.Set;

/**
 *
 * @author houxiaoge
 */
//The expectation of final paper:
public class Rule {
    
    private long id;
    
    private int total;
    
    private double difficulty;
    
    private Set<Integer> points;
    
    //5 types of problems in total
    private int[] eachTypeCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Integer> getPoints() {
        return points;
    }

    public void setPoints(Set<Integer> points) {
        this.points = points;
    }

    public int[] getEachTypeCount() {
        return eachTypeCount;
    }

    public void setEachTypeCount(int[] eachTypeCount) {
        this.eachTypeCount = eachTypeCount;
    }
    
    
}
