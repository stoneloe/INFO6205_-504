/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaperGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author houxiaoge
 */
public class Population {

    private List<Paper> papers;
    
    public Population() {
        papers = new ArrayList<>();
    }

    public Population(int populationSize, boolean initFlag, Rule rule) {

        ProblemDB DB = new ProblemDB();
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
        papers.sort((Paper p1, Paper p2) -> (int)p2.getAdaptationDegree() - (int)p1.getAdaptationDegree());
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

    public List<Paper> getPapers() {
        return papers;
    }
    
    public void addPaper(int index, Paper paper){      
        paper.setId(index);
        papers.add(paper);
    }
       
}
