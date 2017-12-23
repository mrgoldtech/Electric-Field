import java.util.ArrayList;

import processing.core.*;
public class Vector {

	PApplet p;
	PVector position;
	PVector magnitude;
	
	public Vector(PApplet parent, PVector pos){
		p = parent;
		position = pos;
		magnitude = new PVector(0,0);
	}
	
	public void draw(){
		p.stroke(0,200);
		p.point(position.x+15*magnitude.x, position.y+15*magnitude.y);
		p.stroke(0);
		p.strokeWeight(3);
		//p.point(position.x,position.y);
		//p.line(position.x,position.y,position.x+2*magnitude.x, position.y+2*magnitude.y);
	}
	
	public void updatePos(PVector pos) {
		position = pos;
	}
	
	public PVector getMag() {
		return magnitude;
	}
	
	
	public void update(ArrayList<Charge> c){
		magnitude.x = 0;
		magnitude.y = 0;
		for(int x=0; x<c.size(); x++){
			float rx = position.x-c.get(x).getPos().x;
			float ry = position.y-c.get(x).getPos().y;
			float r = p.sqrt(p.pow((position.x-c.get(x).getPos().x),2)+p.pow((position.y-c.get(x).getPos().y),2));
			magnitude.x += (c.get(x).getMag() / r)*(rx/r);
			magnitude.y += (c.get(x).getMag() / r)*(ry/r);			
		}	
	}	
	
	public void update(ArrayList<Charge> c, Charge exclude){
		magnitude.x = 0;
		magnitude.y = 0;
		for(int x=0; x<c.size(); x++){
			if(!c.get(x).equals(exclude)) {
				float rx = position.x-c.get(x).getPos().x;
				float ry = position.y-c.get(x).getPos().y;
				float r = p.sqrt(p.pow((position.x-c.get(x).getPos().x),2)+p.pow((position.y-c.get(x).getPos().y),2));
				magnitude.x += (c.get(x).getMag() / r)*(rx/r);
				magnitude.y += (c.get(x).getMag() / r)*(ry/r);	
			}
		}	
	}
	
	public void update(ArrayList<Charge> c, Charge exclude1, Charge exclude2){
		magnitude.x = 0;
		magnitude.y = 0;
		for(int x=0; x<c.size(); x++){
			if(!c.get(x).equals(exclude1) || !c.get(x).equals(exclude2)) {
				float rx = position.x-c.get(x).getPos().x;
				float ry = position.y-c.get(x).getPos().y;
				float r = p.sqrt(p.pow((position.x-c.get(x).getPos().x),2)+p.pow((position.y-c.get(x).getPos().y),2));
				magnitude.x += (c.get(x).getMag() / r)*(rx/r);
				magnitude.y += (c.get(x).getMag() / r)*(ry/r);	
			}
		}	
	}
	
	public String toString() {
		return "Vector at"+position+" of mag "+magnitude;
	}
	
}
