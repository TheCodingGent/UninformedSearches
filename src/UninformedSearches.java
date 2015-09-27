/**
 * Uninformed Search Algorithms.
 * 
 * Check ReadMe for details on this program and on how to use it.
 * 
 * Author: Yasser Ghamlouch
 * Student Number: 6276898
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class UninformedSearches {

	public final static int NUM_OF_CITIES = 20;

	// Every city is assigned an integer
	private final static int ORADEA = 0;
	private final static int ZERIND = 1;
	private final static int ARAD = 2;
	private final static int TIMISORA = 3;
	private final static int LUGOJ = 4;
	private final static int MEHADIA = 5;
	private final static int DROBETA = 6;
	private final static int CRAIOVA = 7;
	private final static int RIMNICU_VILCEA = 8;
	private final static int SIBIU = 9;
	private final static int FAGARAS = 10;
	private final static int PITESTI = 11;
	private final static int BUCHAREST = 12;
	private final static int GIURGIU = 13;
	private final static int URZICENI = 14;
	private final static int VASLUI = 15;
	private final static int IASI = 16;
	private final static int NEAMT = 17;
	private final static int HIRSOVA = 18;
	private final static int EFORIE = 19;

	// Every city is represented by a Node instance
	private Node oradea;
	private Node zerind;
	private Node arad;
	private Node timisora;
	private Node lugoj;
	private Node mehadia;
	private Node drobeta;
	private Node craiova;
	private Node rimnicuVilcea;
	private Node sibiu;
	private Node fagaras;
	private Node pitesti;
	private Node bucharest;
	private Node giurgiu;
	private Node urziceni;
	private Node vaslui;
	private Node iasi;
	private Node neamt;
	private Node hirsova;
	private Node eforie;

	// This is an adjacency matrix that defines what cities connect
	private Integer[][] map = new Integer[NUM_OF_CITIES][NUM_OF_CITIES];
	// This is a map that stores each city as a node with a key value
	// corresponding to the integer associated to that city.
	private HashMap<Integer, Node> cities = new HashMap<Integer, UninformedSearches.Node>(); 
	
	// Search algorithms parameters
	//Breadth First Search
	private int bfsNumOfNodesGenerated = 0;
	private int bfsMaxNumOfNodesInMemory = 0;
	
	//Depth First Search
	private int dfsNumOfNodesGenerated = 0;
	private int dfsMaxNumOfNodesInMemory = 0;
	
	//Uniform Cost Search
	private int ucsNumOfNodesGenerated = 0;
	private int ucsMaxNumOfNodesInMemory = 0;
	
	/**
	 * Constructor: Initializes the adjacency matrix and creates the nodes
	 */
	public UninformedSearches(){
		initData(this.map);
		createNodes();
		initHashMap();
	}

	/**
	 * This initializes the adjacency matrix with all the connected cities
	 * and the cost of going from one city to another this can be written into
	 * a file and read into the map as well.
	 * @param map: a 2x2 Array
	 */
	private void initData(Integer[][] map){
		map[ORADEA][ZERIND] = 71;
		map[ORADEA][SIBIU] = 151;
		map[ZERIND][ORADEA] = 71;
		map[ZERIND][ARAD] = 75;
		map[ARAD][ZERIND] = 75;
		map[ARAD][SIBIU] = 140;
		map[ARAD][TIMISORA] = 118;
		map[TIMISORA][ARAD] = 118;
		map[TIMISORA][LUGOJ] = 111;
		map[LUGOJ][TIMISORA] = 111;
		map[LUGOJ][MEHADIA] = 70;
		map[MEHADIA][LUGOJ] = 70;
		map[MEHADIA][DROBETA] = 75;
		map[DROBETA][MEHADIA] = 75;
		map[DROBETA][CRAIOVA] = 120;
		map[CRAIOVA][RIMNICU_VILCEA] = 146;
		map[CRAIOVA][DROBETA] = 120;
		map[CRAIOVA][PITESTI] = 138;
		map[RIMNICU_VILCEA][SIBIU] = 80;
		map[RIMNICU_VILCEA][PITESTI] = 97;
		map[RIMNICU_VILCEA][CRAIOVA] = 146;
		map[SIBIU][ORADEA] = 151;
		map[SIBIU][ARAD] = 140;
		map[SIBIU][FAGARAS] = 99;
		map[SIBIU][RIMNICU_VILCEA] = 80;
		map[FAGARAS][SIBIU] = 99;
		map[FAGARAS][BUCHAREST] = 211;
		map[PITESTI][RIMNICU_VILCEA] = 87;
		map[PITESTI][CRAIOVA] = 138;
		map[PITESTI][BUCHAREST] = 101;
		map[BUCHAREST][PITESTI] = 101;
		map[BUCHAREST][FAGARAS] = 211;
		map[BUCHAREST][GIURGIU] = 90;
		map[BUCHAREST][URZICENI] = 85;
		map[GIURGIU][BUCHAREST] = 90;
		map[URZICENI][BUCHAREST] = 85;
		map[URZICENI][VASLUI] = 142;
		map[URZICENI][HIRSOVA] = 98;
		map[VASLUI][IASI] = 92;
		map[VASLUI][URZICENI] = 142;
		map[IASI][NEAMT] = 87;
		map[IASI][VASLUI] = 92;
		map[NEAMT][IASI] = 87;
		map[HIRSOVA][URZICENI] = 98;
		map[HIRSOVA][EFORIE] = 86;
		map[EFORIE][HIRSOVA] = 86;
	}
	
	/**
	 * This method is called by the constructor to initialize the node
	 * corresponding to each city
	 */
	private void createNodes(){
		oradea = new Node(ORADEA);
		zerind = new Node(ZERIND);
		arad = new Node(ARAD);
		timisora = new Node(TIMISORA);
		lugoj = new Node(LUGOJ);
		mehadia = new Node(MEHADIA);
		drobeta = new Node(DROBETA);
		craiova = new Node(CRAIOVA);
		rimnicuVilcea = new Node(RIMNICU_VILCEA);
		sibiu = new Node(SIBIU);
		fagaras = new Node(FAGARAS);
		pitesti = new Node(PITESTI);
		bucharest = new Node(BUCHAREST);
		giurgiu = new Node(GIURGIU);
		urziceni = new Node(URZICENI);
		vaslui = new Node(VASLUI);
		iasi = new Node(IASI);
		neamt = new Node(NEAMT);
		hirsova = new Node(HIRSOVA);
		eforie = new Node(EFORIE);
	}
	
	/**
	 * This method initializes the values of the hashmap with the cities and
	 * their corresponding integers
	 */
	private void initHashMap(){
		this.cities.put(ORADEA, oradea);
		this.cities.put(ZERIND, zerind);
		this.cities.put(ARAD, arad);
		this.cities.put(TIMISORA, timisora);
		this.cities.put(LUGOJ, lugoj);
		this.cities.put(MEHADIA, mehadia);
		this.cities.put(DROBETA, drobeta);
		this.cities.put(CRAIOVA, craiova);
		this.cities.put(RIMNICU_VILCEA, rimnicuVilcea);
		this.cities.put(SIBIU, sibiu);
		this.cities.put(FAGARAS, fagaras);
		this.cities.put(PITESTI, pitesti);
		this.cities.put(BUCHAREST, bucharest);
		this.cities.put(GIURGIU, giurgiu);
		this.cities.put(URZICENI, urziceni);
		this.cities.put(VASLUI, vaslui);
		this.cities.put(IASI, iasi);
		this.cities.put(NEAMT, neamt);
		this.cities.put(HIRSOVA, hirsova);
		this.cities.put(EFORIE, eforie);
	}
	
	/**
	 * This method finds all the nodes that are adjacent to the provided node
	 * and returns them in a list
	 * @param n
	 * @return adjNodes: an array list holding the adjacent nodes to the
	 * provided node
	 */
	private ArrayList<Node> findAdjNodes(int n){
		ArrayList<Node> adjNodes = new ArrayList<UninformedSearches.Node>();
		for(int i = 0; i < NUM_OF_CITIES; i++){
			if(map[n][i] != null){
				// the child is at the same index in the list as the corresponding edge
				adjNodes.add(cities.get(i));
			}
		}
		return adjNodes;
	}

	/**
	 * This method finds all the edges that are adjacent to the provided node
	 * and returns them in a list
	 * @param n
	 * @return adjEdges: an array list holding the adjacent edges to the
	 * provided node
	 */
	private ArrayList<Edge> findAdjEdges(int n){
		ArrayList<Edge> adjEdges = new ArrayList<UninformedSearches.Edge>();
		for(int i = 0; i < NUM_OF_CITIES; i++){
			if(map[n][i] != null){
				Node child = new Node(i);
				// the edge is at the same index in the list as the corresponding child
				adjEdges.add(new Edge(cities.get(n), child, map[n][i]));
			}
		}
		return adjEdges;
	}
	
	private void resetParameters(){
		bfsNumOfNodesGenerated = 0;
		bfsMaxNumOfNodesInMemory = 0;
		
		dfsNumOfNodesGenerated = 0;
		dfsMaxNumOfNodesInMemory = 0;
		
		ucsNumOfNodesGenerated = 0;
		ucsMaxNumOfNodesInMemory = 0;
		
	}

	/*--------------------------------------------------------------------------
	 * Search Algorithms
	 -------------------------------------------------------------------------*/
	/**
	 * This method represents the breadth first algorithm takes in a start and an end node
	 * then finds the path between them based on a breadth first approach.
	 * Returns the list of visited cities from source to destination.
	 * @param src
	 * @param dest
	 * @return visited
	 */
	public ArrayList<Node> bfs(Node src, Node dest){

		// a queue to hold the nodes that are to be visited in order
		Queue<Node> frontier = new LinkedList<Node>();
		
		frontier.add(src);

		// a list to keep track of the visited nodes
		ArrayList<Node> visited = new ArrayList<Node>();

		while(!frontier.isEmpty()){
			Node elem = frontier.remove();

			visited.add(elem);
			
			// if destination has been reached
			if(elem.city == dest.city) break;

			elem.expandNode(findAdjNodes(elem.city), findAdjEdges(elem.city));
			bfsMaxNumOfNodesInMemory++;
			
			ArrayList<Node> childNodes = elem.adjNodes;
			if(!childNodes.isEmpty()){
				for(int i = 0; i < childNodes.size(); i++){
					Node child = childNodes.get(i);
					if(!visited.contains(child) && !frontier.contains(child)){
						frontier.add(child);
					}
				}
			}
		}
		bfsNumOfNodesGenerated = visited.size();
		return visited;
	}

	/**
	 * This method represents the depth first algorithm takes in a start and an end node
	 * then finds the path between them based on a depth first approach.
	 * Returns the list of visited cities from source to destination.
	 * @param src
	 * @param dest
	 * @return visited
	 */
	public ArrayList<Node> dfs(Node src, Node dest)
	{
		//DFS uses Stack data structure
		// a stack to hold the nodes that are to be visited in order
		Stack<Node> frontier =new Stack<Node>();

		frontier.push(src);

		// a list to keep track of the visited nodes
		ArrayList<Node> visited = new ArrayList<Node>();

		while(!frontier.isEmpty())
		{
			Node elem = frontier.pop();

			visited.add(elem);

			// if destination has been reached
			if(elem.city == dest.city) break;

			elem.expandNode(findAdjNodes(elem.city), findAdjEdges(elem.city));
			dfsMaxNumOfNodesInMemory++;
			
			ArrayList<Node> childNodes = elem.adjNodes;
			if(!childNodes.isEmpty()){
				for(int i = 0; i < childNodes.size(); i++){
					Node child = childNodes.get(i);
					if(!visited.contains(child) && !frontier.contains(child)){
						frontier.push(child);
					}
				}
			}

		}
		dfsNumOfNodesGenerated = visited.size();
		return visited;
	}

	/**
	 * This method represents the uniform cost search algorithm takes in a start and an end node
	 * then finds the path between them based on the lowest cost.
	 * Returns the list of visited cities from source to destination.
	 * @param src
	 * @param dest
	 * @return visited
	 */
	public ArrayList<Node> ucs(Node src, Node dest){

		Comparator<Node> comparator = new NodeComparator();
		// a queue to hold the nodes that are to be visited in order
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(NUM_OF_CITIES, comparator);

		frontier.add(src);

		// a list to keep track of the visited nodes
		ArrayList<Node> visited = new ArrayList<Node>();

		while(!frontier.isEmpty()){
			Node elem = frontier.remove();

			visited.add(elem);

			// if destination has been reached
			if(elem.city == dest.city) break;

			elem.expandNode(findAdjNodes(elem.city), findAdjEdges(elem.city));
			ucsMaxNumOfNodesInMemory++;
			
			ArrayList<Node> childNodes = elem.adjNodes;
			if(!childNodes.isEmpty()){
				for(int i = 0; i < childNodes.size(); i++){
					Node child = childNodes.get(i);
					if(!visited.contains(child) && !frontier.contains(child)){
						child.updateCost(elem.getCost()+map[elem.city][child.city]);
						//System.out.println("Cost of Node: "+child.city+" is: "+child.getCost());
						frontier.add(child);
					}
				}
			}
		}
		ucsNumOfNodesGenerated = visited.size();
		return visited;
	}


	/*--------------------------------------------------------------------------
	 * Inner Classes
	 -------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------
	 * Class Node to represent a city and its adjacent cities
	 -------------------------------------------------------------------------*/
	/**
	 * Class Node which is an inner class of the main class UninformedSearches,
	 * a node is used to represent a city, it holds the value of the city as an
	 * integer, the list of adjacent nodes and the list of adjacent edges to
	 * that node
	 * 
	 * @author Yasser
	 *
	 */
	
	private class Node{
		private int city;
		private ArrayList<Node> adjNodes;
		private ArrayList<Edge> adjEdges;
		private int tempCost = 0;

		public Node(int value){
			this.city = value;
		}
		
		// update the value of the cost
		public void updateCost(int cost){
			tempCost += cost;
		}
		
		// get the value of the cost
		public int getCost(){
			return this.tempCost;
		}
		
		// reset the cost to zero
		@SuppressWarnings("unused")
		public void resetCost(){
			tempCost = 0;
		}

		// expand the node and find its children
		public void expandNode(ArrayList<Node> adjNodes, 
				ArrayList<Edge> adjEdges){
			this.adjNodes = adjNodes;
			this.adjEdges = adjEdges;

		}
		
		// Print the node: value, children and cost of edges
		@SuppressWarnings("unused")
		public void print(){
			String s = "";
			s = s + ("Node: "+this.city);
			if(adjNodes != null){
				s = s + " --> Children --> ";
				for(int i = 0; i < adjNodes.size(); i++){
					s = s + "Node "+adjNodes.get(i).city + ": ";
					s = s + adjEdges.get(i).cost + ", ";
				}
			}

			System.out.println(s);
		}
	}

	private class Edge{
		@SuppressWarnings("unused")
		private Node src;
		@SuppressWarnings("unused")
		private Node dest;
		private int cost;

		public Edge(Node src, Node dest, int cost){
			this.src = src;
			this.dest = dest;
			this.cost = cost;
		}
	}
	
	/**
	 * This class is a comparator that defines how to compare two nodes based
	 * on the cost difference between them.
	 * @author Yasser
	 *
	 */
	private class NodeComparator implements Comparator<Node>
	{
		@Override
		public int compare(Node x, Node y)
		{
			// Assume neither string is null. Real code should
			// probably be more robust
			// You could also just return x.length() - y.length(),
			// which would be more efficient.
			if (x.getCost() < y.getCost())
			{
				return -1;
			}
			if (x.getCost() > y.getCost())
			{
				return 1;
			}
			return 0;
		}
	}

	/*--------------------------------------------------------------------------
	 * Main method to test the program
	 -------------------------------------------------------------------------*/
	public static void main(String[] args) {
		UninformedSearches us = new UninformedSearches();
		
		Node src = us.cities.get(Integer.parseInt(args[0]));
		Node dest = us.cities.get(Integer.parseInt(args[1]));
		
		if(args[2].equals("bfs")){
			ArrayList<Node> bfsPath = us.bfs(src, dest);
			System.out.println("***************Breadth First Search***************");
			System.out.println("The number of nodes generated is: "+us.bfsNumOfNodesGenerated);
			System.out.println("The maximum number of nodes that existed in memory is: "+us.bfsMaxNumOfNodesInMemory);
			System.out.print("The path from source to destination is: ");
			for(int i = 0; i < bfsPath.size(); i++){
				System.out.print(bfsPath.get(i).city+", ");
			}
		}else if(args[2].equals("dfs")){
			ArrayList<Node> dfsPath = us.dfs(src, dest);
			System.out.println("***************Depth First Search***************");
			System.out.println("The number of nodes generated is: "+us.dfsNumOfNodesGenerated);
			System.out.println("The maximum number of nodes that existed in memory is: "+us.dfsMaxNumOfNodesInMemory);
			System.out.print("The path from source to destination is: ");
			for(int i = 0; i < dfsPath.size(); i++){
				System.out.print(dfsPath.get(i).city+", ");
			}
			
		}else if(args[2].equals("ucs")){
			ArrayList<Node> ucsPath = us.ucs(src, dest);
			System.out.println("**************Uniform Cost Search***************");
			System.out.println("The number of nodes generated is: "+us.ucsNumOfNodesGenerated);
			System.out.println("The maximum number of nodes that existed in memory is: "+us.ucsMaxNumOfNodesInMemory);
			System.out.print("The path from source to destination is: ");
			for(int i = 0; i < ucsPath.size(); i++){
				System.out.print(ucsPath.get(i).city+", ");
			}
		}else if(args[2].equals("all")){
			ArrayList<Node> bfsPath = us.bfs(src, dest);
			ArrayList<Node> dfsPath = us.dfs(src, dest);
			ArrayList<Node> ucsPath = us.ucs(src, dest);
			
			System.out.println("***************Breadth First Search***************");
			System.out.println("The number of nodes generated is: "+us.bfsNumOfNodesGenerated);
			System.out.println("The maximum number of nodes that existed in memory is: "+us.bfsMaxNumOfNodesInMemory);
			System.out.print("The path from source to destination is: ");
			for(int i = 0; i < bfsPath.size(); i++){
				System.out.print(bfsPath.get(i).city+", ");
			}

			System.out.println("\n\n***************Depth First Search***************");
			System.out.println("The number of nodes generated is: "+us.dfsNumOfNodesGenerated);
			System.out.println("The maximum number of nodes that existed in memory is: "+us.dfsMaxNumOfNodesInMemory);
			System.out.print("The path from source to destination is: ");
			for(int i = 0; i < dfsPath.size(); i++){
				System.out.print(dfsPath.get(i).city+", ");
			}

			System.out.println("\n\n**************Uniform Cost Search***************");
			System.out.println("The number of nodes generated is: "+us.ucsNumOfNodesGenerated);
			System.out.println("The maximum number of nodes that existed in memory is: "+us.ucsMaxNumOfNodesInMemory);
			System.out.print("The path from source to destination is: ");
			for(int i = 0; i < ucsPath.size(); i++){
				System.out.print(ucsPath.get(i).city+", ");
			}
		}
		
		/*
		 * For automatic testing purpose without interaction of user
		 */
		/*ArrayList<Node> bfsPath = us.bfs(us.oradea, us.mehadia);
		ArrayList<Node> dfsPath = us.dfs(us.oradea, us.mehadia);
		ArrayList<Node> ucsPath = us.ucs(us.oradea, us.mehadia);

		System.out.println("***************Breadth First Search***************");
		for(int i = 0; i < bfsPath.size(); i++){
			bfsPath.get(i).print();
		}

		System.out.println("\n\n***************Depth First Search***************");
		for(int i = 0; i < dfsPath.size(); i++){
			dfsPath.get(i).print();
		}

		System.out.println("\n\n**************Uniform Cost Search***************");
		for(int i = 0; i < ucsPath.size(); i++){
			ucsPath.get(i).print();
		}*/
		us.resetParameters();
	}

}
