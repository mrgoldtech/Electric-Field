import java.util.ArrayList;

import processing.core.*;
public class Field {

	PApplet p;
	Vector[][] myField;
	static ArrayList<Charge> myCharges = new ArrayList<Charge>();
	
	int scale;
	
	public Field(PApplet parent, int scl){
		p = parent;
		myField = new Vector[scl+1][scl+1];
		scale = scl;
		createMap();
	}
	
	public void createMap(){
		for(int r=0; r<myField.length; r++){
			for(int c=0; c<myField[0].length; c++){
				myField[r][c] = new Vector(p, new PVector(r*Main.size/scale,c*Main.size/scale));
			}
		}
		
	}
	
	public void placeCharge(Charge chg){
		myCharges.add(chg);	
	}
	
	public void draw(){
		for(int r=0; r<myField.length; r++){
			for(int c=0; c<myField[0].length; c++){
				myField[r][c].draw();
			}
		}
		
		for(int x=0; x<myCharges.size(); x++){
			myCharges.get(x).draw();
		}
		
	}
	
	public void update() {
		for(int r=0; r<myField.length; r++){
			for(int c=0; c<myField[0].length; c++){
				myField[r][c].update(myCharges);
			}
		}
		
		for(int x=0; x<myCharges.size(); x++) {
			myCharges.get(x).update();
		}
	}
	
}
