# GA Implementation of Auto-Composing Test Paper
This project is going to figure out a way to `compose test paper automatically` based on genetic algorithm. Like extracting 40 problems randomly from problem database to compose a paper whose total score is 100, expect total difficulty is 0.75 and knowledge points coverage is 0.8.<br> 
In our project, entities in the process of composing paper like `problem`, `paper`, `population`(paper generation/a list of papers) are mapped into basic concept of genetic algorithm like `gene`, `unit`, `population`, then papers generated and envolved based on specific rules and expectations. Finally, the opitmized result would be got. The test final output is showed up below.
****
[output screenshot]
****
## Classes design and explainations
* __Gene code: `Problem` class__<br> 
`Problem` is mapped to the `gene` in GA algorithm. 

* __Gene expression: `Paper` class__<br> 
`Paper` is the unit which is composed by a set of `problem(Genes)`. Its traits include `totalScore`, `diffculty`, `KPCoverage`(knowledge point coverage) and `adaptationDegree`(fitness).

* __Fitness function: `adaptationDegree` in `Paper` class__<br> 
In our project paper's fitness is defined by traits of `difficulty` and `KPCoverage`.<br> 
Unit adaptation: `f = 1-(1-M/N)*f1-|EP-P|*f2`<br> 
`M/N` is KPCoverage, `EP` is difficulty expectation, `P` is unit difficulty.<br> 
`f1` is KPCoverage weight, `f2` is difficulty weight

* __Sort function: `getBestFitnessPaper()` function in `Population` class__<br> 
`Population` class is the generation of papers, a list of papers. The `getBestFitnessPaper()` function is going to sort papers by fitness and get the best fitness candidate. Which would be used in selection process in evolution.

* __Evolution mechanism: `GA` class__<br> 
In `GA` class, we defined the process of evolution:<br> 
1. Initialize population based on given rules.
2. Elitism: the best fitness paper could be added to the next generation directly.
3. Select 'father' and 'mother' by select function(select 5 papers randomly and return the best fitness paper among these 5 units)
4. Crossover process to generate new paper.(Select a section with the same score to exchange from father and mother)
5. The new generation mutate in specific mutateRate.(Select a gene/problem randomly to change with another gene had the same score)
6. Repeat revolution and get expect result
