/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaperGeneration;

import java.util.List;
import java.util.Set;

/**
 *
 * @author houxiaoge
 */
//The expectation of final paper:
public class Rule {
    
    private long id;
    
    private static long count = 1;
    
    private int total;
    
    private double difficulty;
    
    private Set<Integer> points;
    
    //5 types of problems in total,lenghth would be 5
    private int[] eachTypeCount;
    
    public Rule(){
        id = count++;
    }

    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Rule{" + "id=" + id + ", total=" + total + ", difficulty=" + difficulty + '}';
    }

    
    
    
}
