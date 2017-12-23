import processing.core.*;
public class Charge {

	protected PApplet p;
	protected PVector position;
	protected PVector velocity;
	
	protected Vector localVector;
	
	protected int magnitude;
	protected float radius;
	protected float mass;
	
	public Charge(PApplet parent, PVector pos, PVector vel, int mag, int mas){
		p = parent;
		position = pos;
		magnitude = mag;
		mass = mas/(float)5.0+200;
		velocity = vel;
		localVector = new Vector(p,position);
		radius = mass-180;
	}
	
	public Charge(){
		
	}
	
	public PVector getPos(){
		return position;
	}
	
	public void addPos(PVector v){
		position.add(v);
	}
	
	public int getMag(){
		return magnitude;
	}
	
	public Vector getVector(){
		return localVector;
	}
	
	public PVector getVel(){
		return velocity;
	}
	
	public float getMass() {
		return mass;
	}
	
	public float getRad() {
		return radius;
	}
	
	public void draw(){
		if(magnitude > 0) p.fill(255,0,0);
		if(magnitude < 0) p.fill(0,0,255);
		p.ellipse(position.x,position.y,radius,radius);
		//p.line(position.x, position.y, position.x+2*velocity.x, position.y+2*velocity.y);
	}
	
	public void update() {
		localVector.updatePos(position);
		localVector.update(Field.myCharges,this);
		for(int x=0; x<Field.myCharges.size(); x++) {
			if(magnitude > 0)velocity.add(localVector.getMag().x, localVector.getMag().y).div(mass/(float)200.0);
			if(magnitude < 0)velocity.sub(localVector.getMag().x, localVector.getMag().y).div(mass/(float)200.0);
		}
		position.add(velocity);
		
		if(position.x <= -radius){
			position.x = Main.size;
		}
		if(position.x >= Main.size+radius){
			position.x = 0;
		}
		if(position.y <= -radius){
			position.y = Main.size;
		}
		if(position.y >= Main.size+radius){
			position.y = 0;
		}
	}
	
	public String toString(){
		return "Charge at "+position+" with mag of "+magnitude;
	}
	
}
