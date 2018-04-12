/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paperGeneration_GA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author houxiaoge
 */
public class ProblemDB {

    private List<Problem> problemDB;

    public ProblemDB() {

        problemDB = new ArrayList<>();
        //New 5000 different problem:
        Problem p;
        for (int i = 1; i <= 5000; i++) {
            p = new Problem();
            p.setId(i);

            Random rd = new Random(1);
            //new a difficulty from 0.3~1
            Double d = (rd.nextInt(70) + 30) * 0.01;
            p.setDifficulty(d);

            //1000 type 1 problems: multiple choice 1 score
            if (i < 1001) {
                p.setType(1);
                p.setScore(1);
            }

            //1000 type 2 problems: multiple choice 2 score
            if (i > 1000 && i < 2001) {
                p.setType(2);
                p.setScore(2);
            }

            //1000 type 3 problems: true or false 2 score
            if (i > 2000 && i < 3001) {
                p.setType(3);
                p.setScore(2);
            }

            //1000 type 4 problems: blanks 1-4 score
            if (i > 3000 && i < 4001) {
                p.setType(4);
                int s = rd.nextInt(4) + 1;
                p.setScore(i);
            }

            //1000 type 5 problems: score is difficulty*10
            if (i > 4000 && i < 5001) {
                p.setType(5);
                int score = p.getDifficulty() > 0.3 ? (int) (p.getDifficulty() * 10) : 3;
                p.setScore(score);
            }

            //knowledge point count for problem from 1~4:
            Set<Integer> points = p.getPoints();
            int count = rd.nextInt(4) + 1;
            for (int j = 0; j < count; j++) {
                //100 points in total
                int point = rd.nextInt(100) + 1;
                if (!points.contains(point)) {
                    points.add(point);
                }else{
                    j--;
                }
            }

            p.setPoints(points);
            problemDB.add(p);
        }
    }

    public List<Problem> getProblemDB() {
        return problemDB;
    }
    
}
