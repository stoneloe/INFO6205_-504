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
//Take the problem bean as gene repository:
public class Problem {
    
    private int id;
    
    private int type;
    
    private double difficulty;
    
    private int score;
    
    private Set<Integer> points;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Integer> getPoints() {
        return points;
    }

    public void setPoints(Set<Integer> points) {
        this.points = points;
    }
    
    
}
