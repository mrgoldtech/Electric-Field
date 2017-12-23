import processing.core.*;
import processing.event.MouseEvent;
public class Main extends PApplet{

	public static void main(String[] args) {
		PApplet.main("Main");
	}
	
	final static int size = 500;
	Field hi;
	
	int chrg = 5;
	int mass = 1;
	int changer = 1;
	
	PVector initial = new PVector(0,0);
	PVector end = new PVector(0,0);
	
	public void settings(){
		size(size,size+45);
	}
	
	public void setup(){
		hi = new Field(this,50);
	}
	
	public void mousePressed(){
		initial = new PVector(mouseX,mouseY);
	}
	
	public void mouseReleased() {
		//System.out.println(new PVector(mouseX-initial.x,mouseY-initial.y));
		if(mouseButton==LEFT)hi.placeCharge(new Charge(this, new PVector(mouseX,mouseY),new PVector(-mouseX+initial.x,-mouseY+initial.y).div(15),chrg,mass));
	}
	
	public void mouseWheel(MouseEvent me) {
		if(changer==1)chrg -= 2*me.getCount();
		if(changer==-1)mass -= 2*me.getCount();
		if(mass < 1) mass=1;
	}
	
	public void keyPressed() {
		if(keyCode==CONTROL && changer == 1) {
			chrg*=-1;
		}
		if(keyCode==SHIFT) {
			changer *= -1;
		}
		if(keyCode==UP) {
			if(changer==1)chrg++;
			else mass++;
		}
		if(keyCode==DOWN) {
			if(changer==-1)chrg--;
			else mass--;
			if(mass<1)mass=1;
		}
	}
	
	public void gui() {
		fill(255);
		rect(0,size,size-2,43);
		fill(0);
		if(changer==1)text("->q = "+chrg,5,size+15);
		else text("q = "+chrg,5,size+15);
		if(changer==-1)text("->m = "+mass,5,size+30);
		else text("m = "+mass,5,size+30);
		
		text("Press SHIFT to change from charge to mass",size-245,size+15);
		text("Press CTRL to change from pos. to neg.",size-221,size+30);
		
		if(chrg > 0) fill(255,0,0,100);
		if(chrg < 0) fill(0,0,255,100);
		ellipse(mouseX,mouseY,(mass/(float)5.0+200)-180,(mass/(float)5.0+200)-180);
		
		strokeWeight(3);
		if(mousePressed)line(initial.x,initial.y,mouseX,mouseY);
	}
	
	public void draw(){
		background(227);
		strokeWeight(2);
		hi.draw();
		hi.update();
		gui();
	}

}
