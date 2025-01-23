package kinematics;


import java.util.Scanner;

public class Kinematics {	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("press enter to start");
		if("dev".equals(scan.nextLine())) { //dev tests
			Solver s = new Solver(20,44.1,20,0,0,-9.8,-.0001,-.0001,-.0001);
			s.solveProblem();
			
			Grapher g = new Grapher(50, 50, .2, .1, "y", s);
			System.out.println(g.getGraph());
			System.out.println("\n"+s);
		} else {
			System.out.println("Welcome to Kinematics Solver\n");
			System.out.println("How many objects are in motion?");
			int objectNum = Integer.valueOf(scan.nextLine());
			
			Solver[] solverList = new Solver[objectNum];
			for(int i=0; i<objectNum; i++) {
				solverList[i] = new Solver();
				System.out.println("Object #"+(i+1));
				objectInfoInput(solverList[i]);
			}
			
			solveAndPrint(solverList);
			
		}
		
		scan.close();
	}
	public static void objectInfoInput(Solver solve) { // (WIP)
		System.out.println("Input known variables");
		System.out.println("No units, 3 decimal max");
		System.out.println("Please leave \"n/a\" for unknown variables");
		
		System.out.println("Initial X Velocity: (vix) (m/s)");
		String currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setVIX(Double.valueOf(currentScan)); //set vix input
		}
		System.out.println("Initial Y Velocity: (viy) (m/s)");
		currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setVIY(Double.valueOf(currentScan)); //set viy input
		}
		System.out.println("Final X Velocity: (vfx) (m/s)");
		currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setVFX(Double.valueOf(currentScan)); //set vfx input
		}
		System.out.println("Final Y Velocity: (vfy) (m/s)");
		currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setVFY(Double.valueOf(currentScan)); //set vfy input
		}
		System.out.println("X Accel: (ax) (m/s^2)");
		currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setAX(Double.valueOf(currentScan)); //set ax input
		}
		System.out.println("Y Accel: (ay) (m/s^2)");
		currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setAY(Double.valueOf(currentScan)); //set ay input
		}
		System.out.println("X Displacement: (x) (m)");
		currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setX(Double.valueOf(currentScan)); //set x input
		}
		System.out.println("Y Displacement: (y) (m)");
		currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setY(Double.valueOf(currentScan)); //set y input
		}
		System.out.println("Time: (t) (s)");
		currentScan = scan.nextLine();
		if(!currentScan.equals("n/a")) {
			solve.setT(Double.valueOf(currentScan)); //set t input
		}
		System.out.println();
		
	}
	public static void doGraphs(Solver solve) {
		String currentScan;
		
		System.out.println("\nWould you like to graph this problem? (y/n)");
		currentScan = scan.nextLine();
		while(currentScan.equals("y")) {
			Grapher g;
			System.out.println("Graphs are displacement over time. Graph Y or X displacement?");
			String direction = scan.nextLine();
			System.out.println("X Max/Min (graph bounds)");
			int xDimension = Integer.valueOf(scan.nextLine());
			System.out.println("Y Max/Min (graph bounds)");
			int yDimension = Integer.valueOf(scan.nextLine());
			System.out.println("Graph interval: ");
			Double xUnit = Double.valueOf(scan.nextLine());
			System.out.println("Y scale factor: ");
			Double yUnit = Double.valueOf(scan.nextLine());
			System.out.println("GRAPHING...");
			g = new Grapher(xDimension, yDimension, xUnit, yUnit, direction, solve);
			System.out.println(g.getGraph());
			System.out.println("\n"+solve);
			
			
			System.out.println("Regraph problem? (y/n)");
			currentScan = scan.nextLine();
		}
	}
	public static void solveAndPrint(Solver[] list) {
		for(Solver solve: list) {
			solve.makeXVars();
			solve.makeYVars();
			solve.solveProblem();
			
			System.out.println(solve+"\n");
			
		}
	}
	
	
	
	
	
	
	
	
	
}