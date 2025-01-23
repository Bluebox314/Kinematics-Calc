package kinematics;

import java.util.ArrayList;

public class Solver {
	private ArrayList<Double> xVars = new ArrayList<Double>(); //stores all variables (x)
	private ArrayList<Double> yVars = new ArrayList<Double>(); //stores all variables (y)
	private double vix; //velocity (initial) (x)
	private double vfx; //velocity (final) (x)
	private double viy; //velocity (initial) (y)
	private double vfy; //velocity (final) (y)
	private double ax; //accel (x)
	private double ay; //accel (y)
	private double x; //displacement (x)
	private double y; //displacement (y)
	private double t; //time
	private double t2; //when a second time is needed
	
	//constructors (additional needed for testing and recursion)
	public Solver() { //sets all to a pseudo null value, never used by the computer
		vix = -.0001;
		viy = -.0001;
		vfx = -.0001;
		vfy = -.0001;
		ax = -.0001;
		ay = -.0001;
		x = -.0001;
		y = -.0001;
		t = -.0001;
	}
	public Solver(double vix, double viy, double vfx, double vfy, double ax, double ay, //input constructor
				  double x, double y, double t) {
		this.vix = vix;
		this.viy = viy;
		this.vfx = vfx;
		this.vfy = vfy;
		this.ax = ax;
		this.ay = ay;
		this.x = x;
		this.y = y;
		this.t = t;
		t2 = -.0001;
		makeXVars();
		makeYVars();
}
	
 	//getters setters
 	public double getVIX() { 
 		return vix;
 	}
 	public void setVIX(double vix) {
 		this.vix = vix;
 	}
 	public double getVIY() { 
 		return viy;
 	}
 	public void setVIY(double viy) {
 		this.viy = viy;
 	}
 	public double getVFX() { 
 		return vfx;
 	}
 	public void setVFX(double vfx) {
 		this.vfx = vfx;
 	}
 	public double getVFY() { 
 		return vfy;
 	}
 	public void setVFY(double vfy) {
 		this.vfy = vfy;
 	}
 	public double getAX() { 
 		return ax;
 	}
 	public void setAX(double ax) {
 		this.ax = ax;
 	}
 	public double getAY() { 
 		return ay;
 	}
 	public void setAY(double ay) {
 		this.ay = ay;
 	}
 	public double getX() { 
 		return x;
 	}
 	public void setX(double x) {
 		this.x = x;
 	}
 	public double getY() { 
 		return y;
 	}
 	public void setY(double y) {
 		this.y = y;
 	}
 	public double getT() { 
 		return t;
 	}
 	public void setT(double t) {
 		this.t = t;
 	}
 	//psudeo setters
 	public void makeXVars() { //returns ArrayList of all var values
		xVars.clear();
		xVars.add(vix);
		xVars.add(vfx);
		xVars.add(ax);
		xVars.add(x);

	}
	public void makeYVars() { //returns ArrayList of all var values
		yVars.clear();
		yVars.add(viy);
		yVars.add(vfy);
		yVars.add(ay);
		yVars.add(y);
	}
	public String toString() {
 		String str = "";
		if(vix==-.0001) {
			str += "Initial X Velocity = unknown\n";
		} else {
			str += "Initial X Velocity = "+vix+"\n";
		}
		if(vfx==-.0001) {
			str += "Final X Velocity = unknown\n";
		} else {
			str += "Final X Velocity = "+vfx+"\n";
		}
		if(ax==-.0001) {
			str += "X Acceleration = unknown\n";
		} else {
			str += "X Acceleration = "+ax+"\n";
		}
		if(x==-.0001) {
			str += "X Displacement = unknown\n";
		} else {
			str += "X Displacement = "+x+"\n";
		}
		if(viy==-.0001) {
			str += "Initial Y Velocity = unknown\n";
		} else {
			str += "Initial Y Velocity = "+viy+"\n";
		}
		if(vfy==-.0001) {
			str += "Final Y Velocity = unknown\n";
		} else {
			str += "Final Y Velocity = "+vfy+"\n";
		}
		if(ay==-.0001) {
			str += "Y Acceleration = unknown\n";
		} else {
			str += "Y Acceleration = "+ay+"\n";
		}
		if(y==-.0001) {
			str += "Y Displacement = unknown\n";
		} else {
			str += "Y Displacement = "+y+"\n";
		}
		if(t==-.0001) {
			str += "Time = unknown";
		} else if(t2==-.0001) {
			str += "Time = "+t;
		} else {
			str += "Time = "+t+", "+t2;
		}
		return str;
 	}
 
	
	
/* EQUATIONS
 * //Each equation solves for one var - without one other var (x or y)  	  t1 = ((-1*vf)+-Math.sqrt((vf*vf)-2*a*x))/(-1*a)
 * //Columns are the solved for var, rows are the missing var			      t2 = ((-1*vi)+-Math.sqrt((vi*vi)+2*a*x))/a
 *	   -------- vi -------   ======== vf ========   -------- a --------   ======== x ========   ------------- t ------------
 * vi:        N / A             (x+.5*a*t*t)/t      (2*x-2*vf*t)/(-t*t)     vf*t-.5*a*(t*t)           unneeded in code
 * vf:   (x-.5*a*(t*t))/t           N / A           (2*x-2*vi*t)/(t*t)      vi*t+.5*a*(t*t)           unneeded in code
 *  a:     (2*x)/t - vf          (2*x)/t - vi              N / A             .5*(vf+vi)*t               (2*x)/(vf+vi)
 *  x:        vf-a*t               vi + a*t              (vf-vi)/t               N / A                    (vf-vi)/a
 *  t: sqrt((vf*vf)+2*a*x)   sqrt((vi*vi)+2*a*x)  ((vf*vf)-(vi*vi))/(2*x) ((vf*vf)-(vi*vi))/(2*a)           N / A
 */
	public void solveProblem() { //sets correct values to all variables (WIP)
		int[] coords = nextToSolve();
		System.out.println(coords[0]+", "+coords[1]+", "+coords[2]);
		
		while(coords[1]>=0 && coords[2]>=0) {
			System.out.println("SOLVING NEXT VAR...");
			if(coords[0]==0) { 
				solveAtCoordsX(coords[1], coords[2]);
			} else {
				solveAtCoordsY(coords[1], coords[2]);
			}
			coords = nextToSolve();
			System.out.println(coords[0]+", "+coords[1]+", "+coords[2]);
		}
		
		roundAllVars();
	}
	public void solveAtCoordsX(int xc, int yc) {
		switch(xc) { //solving for...
		case 0: //initial velocity
			switch(yc) { //without...
			case 1: //final velocity
				vix = (x-.5*ax*(t*t))/t;
				break;
			case 2: //acceleration
				vix = (2*x)/t - vfx;
				break;
			case 3: //displacement
				vix = vfx-ax*t;
				break;
			case 4: //time
				vix = Math.sqrt((vfx*vfx)+2*ax*x);
				break;
			}
			break;
			
		case 1: //final velocity
			switch(yc) { //without...
			case 0: //initial velocity
				vfx = (x+.5*ax*t*t)/t;
				break;
			case 2: //acceleration
				vfx = (2*x)/t - vix;
				break;
			case 3: //displacement
				vfx = vix + ax*t;
				break;
			case 4: //time
				vfx = Math.sqrt((vix*vix)+2*ax*x);
				break;
			}
			break;
			
		case 2: //acceleration
			switch(yc) { //without...
			case 0: //initial velocity
				ax = (2*x-2*vfx*t)/(-t*t);
				break;
			case 1: //final velocity
				ax = (2*x-2*vix*t)/(t*t);
				break;
			case 3: //displacement
				ax = (vfx-vix)/t;
				break;
			case 4: //time
				ax = ((vfx*vfx)-(vix*vix))/(2*x);
				break;
			}
			break;
			
		case 3: //displacement
			switch(yc) { //without...
			case 0: //initial velocity
				x = vfx*t-.5*ax*(t*t);
				break;
			case 1: //final velocity
				x = vix*t+.5*ax*(t*t);
				break;
			case 2: //acceleration
				x = .5*(vfx+vix)*t;
				break;
			case 4: //time
				x = ((vfx*vfx)-(vix*vix))/(2*ax);
				break;
			}
			break;
			
		case 4: //time (funky)
			double time1 = ((-1*vix)-Math.sqrt((vix*vix)+2*ax*x))/ax;
			double time2 = ((-1*vix)+Math.sqrt((vix*vix)+2*ax*x))/ax;
			
			if(time1<0) {
				t = time2;
			} else if(time2<0) {
				t = time1;
			} else if(!Double.isNaN(time1) || !Double.isNaN(time2)) {
				t = time1;
				t2 = time2;
			} else {
				t = (vfx-vix)/ax;
			}
			break;
		case 5:
			x = vix*t;
			break;
		}
		makeXVars();
	}
	public void solveAtCoordsY(int xc, int yc) {
		switch(xc) { //solving for...
		case 0: //initial velocity
			switch(yc) { //without...
			case 1: //final velocity
				viy = (y-.5*ay*(t*t))/t;
				break;
			case 2: //acceleration
				viy = (2*y)/t - vfy;
				break;
			case 3: //displacement
				viy = vfy-ay*t;
				break;
			case 4: //time
				viy = Math.sqrt((vfy*vfy)+2*ay*y);
				break;
			}
			break;
			
		case 1: //final velocity
			switch(yc) { //without...
			case 0: //initial velocity
				vfy = (y+.5*ay*t*t)/t;
				break;
			case 2: //acceleration
				vfy = (2*y)/t - viy;
				break;
			case 3: //displacement
				vfy = viy + ay*t;
				break;
			case 4: //time
				vfy = Math.sqrt((viy*viy)+2*ay*y);
				break;
			}
			break;
			
		case 2: //acceleration
			switch(yc) { //without...
			case 0: //initial velocity
				ay = (2*y-2*vfy*t)/(-t*t);
				break;
			case 1: //final velocity
				ay = (2*y-2*viy*t)/(t*t);
				break;
			case 3: //displacement
				ay = (vfy-viy)/t;
				break;
			case 4: //time
				ay = ((vfy*vfy)-(viy*viy))/(2*y);
				break;
			}
			break;
			
		case 3: //displacement
			switch(yc) { //without...
			case 0: //initial velocity
				y = vfy*t-.5*ay*(t*t);
				break;
			case 1: //final velocity
				y = viy*t+.5*ay*(t*t);
				break;
			case 2: //acceleration
				y = .5*(vfy+viy)*t;
				break;
			case 4: //time
				y = ((vfy*vfy)-(viy*viy))/(2*ay);
				break;
			}
			break;
			
		case 4: //time
			double time1 = ((-1*viy)-Math.sqrt((viy*viy)+2*ay*y))/ay;
			double time2 = ((-1*viy)+Math.sqrt((viy*viy)+2*ay*y))/ay;
			
			if(time1<0) {
				t = time2;
			} else if(time2<0) {
				t = time1;
			} else if(!Double.isNaN(time1) || !Double.isNaN(time2)) {
				t = time1;
				t2 = time2;
			} else {
				t = (vfy-viy)/ay;
			}
			break;
		case 5:
			y = viy*t;
			break;
		}
		makeYVars();
	}
	
	public int[] nextToSolve() { //returns the grid location of the variable to solve for
		//first is direction, second describes the variable to solve for, the third describes the variable left out
		int[] returnList = {0, -1, -1};
		boolean isTimeKnown = false;
		if(t!=-.0001) isTimeKnown = true; //assigning
		//X portion
		
		switch(getNumOfKnownVars(1)) {
		case 4: //with all four, solve for time if needed
			if(!isTimeKnown && ax!=0) {
				returnList[1] = 4; //solve for time
				returnList[2] = 3; //without displacement (arbitrary pick)
				return returnList;
			}
			break;
		case 3: //we know all x variables but one, solve for remaining x var
			for(int i=0; i<xVars.size(); i++) { //find that missing var
				if(xVars.get(i)==-.0001) {
					if(ax==0 && i==3) {
						if(isTimeKnown) {
							returnList[1] = 5;
							returnList[2] = 1000;
							return returnList; 
						}
						break;
					}
					returnList[1] = i;
					returnList[2] = 4; //without time
					return returnList; //return it
				}
			}
			break;
		case 2: //not enough x vars to do x by itself, use time to solve for an x var if possible
			if(isTimeKnown) {
				boolean onSecond = false;
				for(int i=0; i<xVars.size(); i++) { //find 2 missing vars and solve for one without the other
					if(xVars.get(i)==-.0001) {
						if(onSecond) {
							returnList[2] = i; //second is without
						} else {
							returnList[1] = i; //first one found is solved for
							onSecond = true;
						}
					}
				}
				return returnList;
			}
			break;
		}
		
		//Y portion (identical)=============================================================================================
		returnList[0] = 1; //tell computer returned index is in y
		switch(getNumOfKnownVars(2)) {
		case 4:
			if(!isTimeKnown && ay!=0) {
				returnList[1] = 4;
				returnList[2] = 3;
				return returnList;
			}
			break;
		case 3:
			for(int i=0; i<yVars.size(); i++) {
				if(yVars.get(i)==-.0001) {
					if(ay==0 && i==3) {
						if(isTimeKnown) {
							returnList[1] = 5;
							returnList[2] = 1000;
							return returnList;
						}
						break;
					}
					returnList[1] = i;
					returnList[2] = 4;
					return returnList;
				}
			}
			break;
		case 2:
			if(isTimeKnown) {
				boolean onSecond = false;
				for(int i=0; i<yVars.size(); i++) {
					if(yVars.get(i)==-.0001) {
						if(onSecond) {
							returnList[2] = i;
						} else {
							returnList[1] = i;
							onSecond = true;
						}
					}
				}
				return returnList;
			}
			break;
		}
		return returnList;
	}
	public int getNumOfKnownVars(int direction) { //creates the list of known variables (excluding time) (1 for x, 2 for y)
		int numKnown = 0;
		if(direction == 1) {
			for(double var: xVars) {
				if(var!=-.0001) {
					numKnown++;
				}
			}
		} else {
			for(double var: yVars) {
				if(var!=-.0001) {
					numKnown++;
				}
			}
		}
		return numKnown;
	}
	
	public void roundAllVars() {
		this.vix = roundTo3Places(vix);
		this.viy = roundTo3Places(viy);
		this.vfx = roundTo3Places(vfx);
		this.vfy = roundTo3Places(vfy);
		this.ax = roundTo3Places(ax);
		this.ay = roundTo3Places(ay);
		this.x = roundTo3Places(x);
		this.y = roundTo3Places(y);
		this.t = roundTo3Places(t);
		if(t2!=-.0001) {
			t2 = roundTo3Places(t2);
		}
	}
	public static double roundTo3Places(double n) {
		String str = n+"";
		if(str.indexOf(".")+5>str.length()) {
			return n;
		}
		double roundVal = Double.valueOf(str.substring(str.indexOf(".")+4, str.indexOf(".")+5));
		if(roundVal>4) {
			roundVal = .001;
		}
		str = str.substring(0, str.indexOf(".")+4);
		double truncated = Double.valueOf(str);
		
		return truncated+roundVal;
	}
	
	
	
	
	
}