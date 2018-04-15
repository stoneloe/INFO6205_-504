# GA Implementation of Auto-Composing Test Paper
This project is going to figure out a way to `compose test paper automatically` based on genetic algorithm. Like extracting 40 problems randomly from problem database to compose a paper whose total score is 100, expect total difficulty is 0.72 and knowledge points coverage is 0.8.<br> 
In our project, entities in the process of composing paper like `problem`, `paper`, `population`(paper generation/a list of papers) are mapped into basic concept of genetic algorithm like `gene`, `chromsome`, `population`, then papers are generated and evolved based on specific rules and expectations. Finally, we will get the opitmized result. The following pictures are user interfaces to display input and output.
***
<p align="center">
<img src="https://github.com/stoneloe/INFO6205_-504/blob/master/images/pane1.png" width="49%" alt="Settings"  />
<img src="https://github.com/stoneloe/INFO6205_-504/blob/master/images/pane2.png" width="49%" alt="Result" />
</p>

***

## Classes design and explainations
* __Gene code: Problem class__<br> 
`Problem` is mapped to the `gene` in GA algorithm. 

* __Gene expression: Paper class__<br> 
`Paper` is mapped to the `chromsome` which is composed by a set of `problem(Genes)`. Its traits include `totalScore`, `diffculty`, `KPCoverage`(knowledge point coverage) and `adaptationDegree`(fitness).

* __Fitness function: adaptationDegree in Paper class__<br> 
In our project paper's fitness is defined by traits of `difficulty` and `KPCoverage`.<br> 
Unit adaptation: `f = 1-(1-M/N)*f1-|EP-P|*f2`<br> 
`M/N` is KPCoverage, `EP` is difficulty expectation, `P` is paper difficulty.<br> 
`f1` is KPCoverage weight, `f2` is difficulty weight

* __Sort function: getBestFitnessPaper() function in Population class__<br> 
`Population` class is the generation of papers, a list of papers. The `getBestFitnessPaper()` function is going to sort papers by fitness and get the best fitness candidate, which is also used in selection phase during evolution.

* __Evolution mechanism: GA class__<br> 
In `GA` class, we defined the process of evolution:<br> 
1. Initialize population based on given rules.
2. Elitism: the current fittest member of the population is always propagated to the next generation.
3. Select parents by select function(generate tournament array randomly and return the best fitness paper in tournament array)
4. Crossover process to generate new paper using multi points.(Select gene segments with the same score to swap between parents)
5. The gene mutate at specific mutatation rates.(A gene(problem) is replace by another which has the same score and type)
6. Repeat evolution and get best result.

## References
> https://towardsdatascience.com/introduction-to-genetic-algorithms-including-example-code-e396e98d8bf3<br>
> http://www.cnblogs.com/artwl/archive/2011/05/20/2052262.html<br>
> https://www.jianshu.com/p/7fe9d3bb00ac<br>
> https://github.com/jslixiaolin/GADemo<br>
> https://ieeexplore.ieee.org/document/5982470/?reload=true&arnumber=5982470
