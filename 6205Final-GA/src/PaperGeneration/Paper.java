/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaperGeneration;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author houxiaoge
 */
public class Paper {

    private int Id;

    private int totalScore;

    private double difficulty;

    private double KPCoverage;

    private double adaptationDegree;

    private Set<Problem> problemList;

    public Paper() {
        this.problemList = new HashSet<>();
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Set<Problem> getProblemList() {
        return problemList;
    }

    public void setProblemList(Set<Problem> problemList) {
        this.problemList = problemList;
    }

    public int getTotalScore() {
        int total = 0;
        for (Problem p : problemList) {
            total += p.getScore();
        }
        totalScore = total;
        return totalScore;
    }

    //sum(eachDif * eachScore)/totalScore
    public double getDifficulty() {
        double dif = 0;
        for (Problem p : problemList) {
            dif += p.getScore() * p.getDifficulty();
        }
        difficulty = dif / getTotalScore();
        return difficulty;
    }

    public int getProblemCount() {
        return problemList.size();
    }

    //caculate KPCoverage: unitCoverage/expectCoverage
    public void setKPCoverage(Rule rule) {
        Set<Integer> result = new HashSet<>();
        for (Problem p : problemList) {
            result.addAll(p.getPoints());
        }
        Set<Integer> expectation = rule.getPoints();
        //join:
        result.retainAll(expectation);
        KPCoverage = (double)result.size() / expectation.size();
    }

    public double getKPCoverage() {
        return KPCoverage;
    }

    // Set unit adaptation: f = 1-(1-M/N)*f1-|EP-P|*f2
    // M/N is KPCoverage, EP is difficulty expectation, P is unit difficulty
    // f1 is KPCoverage weight, f2 is difficulty weight
    public void setAdaptationDegree(Rule rule, double f1, double f2) {
        adaptationDegree = 1 - (1 - getKPCoverage()) * f1 - Math.abs(rule.getDifficulty() - getDifficulty()) * f2;
    }

    public double getAdaptationDegree() {
        return adaptationDegree;
    }

}
