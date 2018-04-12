/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paperGeneration_GA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author houxiaoge
 */
public class Population {

    private List<Paper> papers;

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
                        Problem temp = new Problem();
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
    public Paper getFitness() {
        Paper paper = papers.get(0);
        for (Paper p : papers) {
            if (paper.getAdaptationDegree() < p.getAdaptationDegree()) {
                paper = p;
            }
        }
        return paper;
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
}
