/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaperGeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author houxiaoge
 */
public class Population {

    private List<Paper> papers;
    private ProblemDB DB;
    
    public Population() {
        papers = new ArrayList<>();
    }

    public Population(int populationSize, boolean initFlag, Rule rule) {

        DB = new ProblemDB();
        papers = new ArrayList<>();
        int[] eachTypeCount = rule.getEachTypeCount();

        if (initFlag) {
            Paper p;
            Random random = new Random();
            for (int i = 0; i < populationSize; i++) {
                p = new Paper();
                p.setId(i + 1);
                //total constraint:
                while (p.getTotalScore() != rule.getTotal()) {
                    p.getProblemList().clear();
                    //each type number constraint:
                    for (int j = 0; j < eachTypeCount.length; j++) {
                        List<Problem> oneTypeProblem = getOneTypeProblems(DB, j);
                        Problem temp;
                        //generate problem without duplicates:
                        for (int k = 0; k < eachTypeCount[j]; k++) {
                            temp = generateProblem(oneTypeProblem, random);
                            if (!p.getProblemList().contains(temp)) {
                                p.getProblemList().add(temp);
                            } else {
                                k--;
                            }
                        }
                    }
                }
                p.setAdaptationDegree(rule, GlobalWeight.KP_WEIGHT, GlobalWeight.DIFFCULTY_WEIGHt);
                p.setKPCoverage(rule);
                papers.add(p);
            }
        }
    }

    //get the best fitness unit(paper):
    public Paper getBestFitnessPaper() {
        papers.sort(Comparator.comparingDouble(Paper::getAdaptationDegree).reversed());
        return papers.get(0);
    }

    private Problem generateProblem(List<Problem> oneTypeProblem, Random random) {
        int index = random.nextInt(1000);
        return oneTypeProblem.get(index);
    }

    private List<Problem> getOneTypeProblems(ProblemDB DB, int type) {

        List<Problem> oneTypeProblem = new ArrayList<>();
        int startIndex = 1000 * type;
        int finalIndex = startIndex + 1000;
        for (int i = startIndex; i < finalIndex; i++) {
            oneTypeProblem.add(DB.getProblemDB().get(i));
        }
        return oneTypeProblem;
    }
    
    public List<Problem> getProblemTypeListWithoutItself(Problem problem){
        List<Problem> p = new ArrayList<>();
        for(Problem prob : DB.getProblemDB()){
            if(prob.getType() == problem.getType() && prob.getScore() == problem.getScore()){
                p.add(prob);
            }
        }
        p.remove(problem);
        return p;
    }

    public List<Paper> getPapers() {
        return papers;
    }
    
    public void addPaper(int index, Rule rule, Paper paper){      
        paper.setId(index);
        paper.setKPCoverage(rule);
        paper.setAdaptationDegree(rule, GlobalWeight.KP_WEIGHT, GlobalWeight.DIFFCULTY_WEIGHt);
        papers.add(paper);
    }
       
}
