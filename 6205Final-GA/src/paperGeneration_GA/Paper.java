/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paperGeneration_GA;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author houxiaoge
 */
public class Paper {

    private int Id;

    private double totalScore;

    private double difficulty;

    private double KPCoverage;

    private double adaptationDegree;

    private List<Problem> problemList;

    public Paper() {
        this.problemList = new ArrayList<>();
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
    }

    public double getTotalScore() {
        if (totalScore == 0) {
            int total = 0;
            for (Problem p : problemList) {
                total += p.getScore();
            }
            totalScore = total;
        }
        return totalScore;
    }
    
    //eachDif*eachScore/totalScore
    public double getDifficulty() {
        if (difficulty == 0) {
            double dif = 0;
            for (Problem p : problemList) {
                dif += p.getScore() * p.getDifficulty();
            }
            difficulty = dif/getTotalScore();
        }
        return difficulty;
    }
    
}
