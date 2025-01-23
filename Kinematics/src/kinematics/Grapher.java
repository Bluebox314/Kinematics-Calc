package kinematics;

import java.util.ArrayList;

public class Grapher {
	private int xDimension;
	private int yDimension;
	private double xUnit;
	private double yUnit;
	private String direction;
	private final Solver equation;
	private String graph;
	
	public Grapher(int xDimension, int yDimension, double xUnit, double yUnit, String direction, Solver equation) {
		this.xDimension = xDimension;
		this.yDimension = yDimension;
		this.xUnit = xUnit;
		this.yUnit = yUnit;
		this.direction = direction;
		this.equation = equation;
		makeGraph();
	}

	public int getXDimension() {
		return xDimension;
	}
	public void setXDimension(int xDimension) {
		this.xDimension = xDimension;
	}
	public int getYDimension() {
		return yDimension;
	}
	public void setYDimension(int yDimension) {
		this.yDimension = yDimension;
	}
	public double getXUnit() {
		return xUnit;
	}
	public void setXUnit(double xUnit) {
		this.xUnit = xUnit;
	}
	public double getYUnit() {
		return yUnit;
	}
	public void setYUnit(double yUnit) {
		this.yUnit = yUnit;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Solver getEquation() {
		return equation;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	
	public void makeGraph() {
		graph = "";
		ArrayList<String> transform = new ArrayList<String>();
		double vi;
		double a;
		if(direction.equals("x")) {
			vi = equation.getVIX();
			a = equation.getAX();
		} else {
			vi = equation.getVIY();
			a = equation.getAY();
		}
		
		for(int i=-1*yDimension; i<=yDimension; i++) {
			String slice = "";
			double t = xUnit*i;
			double mark = roundTo((vi*t + .5*a*t*t)*yUnit, xUnit);
			
			for(int k=-1*xDimension; k<=xDimension; k++) {
				if(k*xUnit==mark) {
					slice+= "X";
				} else if(k==0) {
					slice += "_";
				} else if(i==0) { 
					slice += "|";
				} else {
					slice += ".";
				}
			}
			transform.add(slice);
		}
		
		for(int i=transform.get(0).length()-1; i>=0; i--) {
			String slice = "";
			for(int k=0; k<transform.size(); k++) {
				slice += transform.get(k).substring(i,i+1)+" ";
			}
			graph += slice+"\n";
		}
		
		
	}
	public static double roundTo(double in, double to) {
		return Math.round(in/to)*to;
	}
	public static void overlayGraphs() {
		
	}

}
