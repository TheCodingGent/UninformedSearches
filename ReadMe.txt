/*************************
CSI4106 - Assignment #1
*************************/

/*************************
Author: Yasser Ghamlouch
Student Number: 6276898
*************************/

/*************************
	Description
*************************/
In this assignment we were given the map of romania with bidirectional links between different cities
and the cost to go from one city to another. 

The following program (contained in this source folder) provides: 
- A representation of the Map as an adjacency matrix.
 
- A representation of every city as a Node object which can expand into
children nodes and knows the cost of each path to every child.

- Implements three uninformed search algorithms: Breadth First Search, Depth First Search, Uniform Cost Search.
	- Each algorithm takes in a source node and a destination node and provide as feedback: 
		the path, the time cost and the space cost to travel from source to destination.

/*************************
	Usage
*************************/
The first step is to compile the file UninformedSearches.java, either using the IDE of your choice or through the
command line by calling javac command.

The program has a main method that contains some code to test each of the algorithms. 
In order to test the program there are two ways:

1) Open the source code and scroll to the bottom to find the main method, 
in the main method you can keep all three algorithms or comment out the ones you don't want to see the results for.
Change the arguments of the calls to bfs, dfs or ucs by inserting a source node and a destination node as following:
	us.<src>, us.<destination>
	for example: us.arad, us.timisora

2) Run the program (in your IDE or cmd) by providing three parameters to the main function as follows: java <src> <dest> <algorithm>
where src is an integer coresponding to the source city, dest is an integer coresponding to the destination city and the third argument
is the search algorithm to be applied "bfs", "dfs", "ucs" or "all" to run all three algorithms. 

/*************************
	Output
*************************/
Depending on what algorithm was chosen or if all three were chosen, the output of the program has three parts:
1) The first output printed out is the number of nodes generated (to represent time). To find this I count how
many nodes were expanded, which is the number of child nodes explored (same as the number of nodes visited).

2) The second output generated is the maximum number of expansions also known as the maximum depth. This is
to represent the space factor of what is the maximum space needed in memory at any point in time to store the
nodes.

3) The path from source to destination is visited by the algorithm, as a list of ordered integers where every
integer represents a city (node).

/*************************
	Thank you!
*************************/