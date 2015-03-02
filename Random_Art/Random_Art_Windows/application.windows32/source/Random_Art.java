import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import g4p_controls.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Random_Art extends PApplet {

// Need G4P library

PGraphics pg1;
PGraphics pg2;

//fonts
PFont myFont;
PFont myFont1;

public void setup(){
  size(1000, 600, P3D);
  pg1 = createGraphics(400, 600);
  pg2 = createGraphics(600,600);
//  if(frame != null){
//    frame.setResizable(true);
//  }
  createGUI();
  customGUI();
  // Place your setup code here
  background(230); 

// Uncomment the following two lines to see the available fonts 
  //String[] fontList = PFont.list();
  //println(fontList); 

 //pg1

}

public void draw(){
  
   pg1.beginDraw();
 pg1.background(200);
     image(pg1,600,0,400,600);
pg1.endDraw();
  
  myFont = createFont("Arial", 32);
  textFont(myFont);
  textAlign(CENTER, CENTER);
  fill(255);
 String s = "Welcome to Random Art Generator!";
 fill(255);
 text(s, 600, 0, 400, 100);  // Text wraps within text box

// Font text
textAlign(LEFT,CENTER);
    textSize(22);
     text("Choose filter:", 610,355, 400,100);
  
  
  //Objects text
  textAlign(LEFT,CENTER);
    textSize(22);
     text("Choose Objects:", 610,230, 400,100);
 
 
 
}

// Use this method to add additional statements
// to customise the GUI controls
public void customGUI(){

}


/* =========================================================
 * ====                   WARNING                        ===
 * =========================================================
 * The code in this tab has been generated from the GUI form
 * designer and care should be taken when editing this file.
 * Only add/edit code inside the event handlers i.e. only
 * use lines between the matching comment tags. e.g.

 void myBtnEvents(GButton button) { //_CODE_:button1:12356:
     // It is safe to enter your event code here  
 } //_CODE_:button1:12356:
 
 * Do not rename this tab!
 * =========================================================
 */
 
 
int st;

public void circle1_click1(GButton source, GEvent event) { //_CODE_:circle1:561316:
  println("Circle clicked at: " + millis());
 for(st = 0; st < 50; st++){
  noStroke ();
  fill (random (255), random (255), random(255), 170);
  ellipse (random (30, width/1.75f), random (0, height), 60, 60);
 }
 
} //_CODE_:circle1:561316:

public void button2_click1(GButton source, GEvent event) { //_CODE_:square1:656476:
  println("Square clicked at: " + millis());
  for(st = 0; st < 50; st++){
   noStroke ();
  fill (random (255), random (255), random(255), 170);
 // size(50,50,P3D);
 rect(random(0,width/1.86f), random(0,height), 60,60);
 // box(random(0,width), random(0,height), random(0,height));
  }
} //_CODE_:square1:656476:

public void button3_click1(GButton source, GEvent event) { //_CODE_:triangle1:298157:
  println("Triangle clicked at: " + millis());
  for(st = 0; st < 50; st++){
   noStroke ();
  fill (random (255), random (255), random(255), 200);
  triangle(random(0,width/1.7f), random(0,height), random(0,width/1.7f), random(0,height), width/3.2f, height/2);
  }
} //_CODE_:triangle1:298157:

public void button4_click1(GButton source, GEvent event) { //_CODE_:pause1:488433:
  println("Clear clicked at: " + millis());
  pg1.clear();
 pg1.beginDraw();
 pg1.background(200);
     image(pg1,600,0,400,600);
pg1.endDraw();

//  pg2
  pg2.beginDraw();
  pg2.background(230);
  image(pg2,0,0,600,600);
  pg2.endDraw();
  
 
  
} //_CODE_:pause1:488433:

public void button5_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Background clicked at: " + millis());
  pg2.beginDraw();
  pg2.background(random(255),random(255),random(255));
  image(pg2,0,0,600,600);
  pg2.endDraw();
 
 
  
} //_CODE_:resume1:284723:

public void button6_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Save clicked at: " + millis());
  PImage partialSave = get(0,0,600,565);
  //selectInput("Select ","file");
  partialSave.save("art-#####.jpeg");
  //saveFrame("art-#####.jpeg");
} //_CODE_:resume1:284723:

//filter button
public void button7_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Filer clicked at: " + millis());
  //filter(THRESHOLD);
  filter(BLUR,5);
} //_CODE_:resume1:284723:

//filter2 button
public void button8_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Filer2 clicked at: " + millis());
  filter(THRESHOLD);
  //filter(BLUR,5);
} //_CODE_:resume1:284723:

//filter3 button
public void button9_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Filer3 clicked at: " + millis());
  filter(GRAY);
} //_CODE_:resume1:284723:

//filter4 button
public void button10_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Filer4 clicked at: " + millis());
  filter(INVERT);
} //_CODE_:resume1:284723:

//filter5 button
public void button11_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Filer5 clicked at: " + millis());
  filter(POSTERIZE,3);
} //_CODE_:resume1:284723:

//filter6 button
public void button12_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Filer6 clicked at: " + millis());
  filter(ERODE);
} //_CODE_:resume1:284723:

//filter7 button
public void button13_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
  println("Filer7 clicked at: " + millis());
  filter(DILATE);
} //_CODE_:resume1:284723:





// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI(){
  G4P.messagesEnabled(false);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setCursor(ARROW);
  if(frame != null)
    frame.setTitle("Random Art Generator");
    
    //Circle button
  circle1 = new GButton(this, 790, 300, 80, 30);
  circle1.setText("Circle");
  circle1.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  circle1.addEventHandler(this, "circle1_click1");
  
  //Square button
  square1 = new GButton(this, 700, 300, 80, 30);
  square1.setText("Square");
  square1.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  square1.addEventHandler(this, "button2_click1");
  
  //Triangle button
  triangle1 = new GButton(this, 610, 300, 80, 30);
  triangle1.setText("Triangle");
  triangle1.setLocalColorScheme(GCScheme.CYAN_SCHEME);
  triangle1.addEventHandler(this, "button3_click1");
  
  //Clear Button
  clear1 = new GButton(this, 610, 570, 80, 30);
  clear1.setText("Clear");
  clear1.setLocalColorScheme(GCScheme.RED_SCHEME);
  clear1.addEventHandler(this, "button4_click1");
  
  //Background button
  background1 = new GButton(this, 830, 570, 80, 30);
  background1.setText("Background");
  background1.setLocalColorScheme(GCScheme.GREEN_SCHEME);
  background1.addEventHandler(this, "button5_click1");
  
  //Save button
  save1 = new GButton(this, 930, 570, 80, 30);
  save1.setText("Save");
  save1.setLocalColorScheme(GCScheme.GREEN_SCHEME);
  save1.addEventHandler(this, "button6_click1");
  
  
  //Filter
   filter1 = new GButton(this, 610, 420, 80, 30);
   filter1.setText("Blur");
   filter1.setLocalColorScheme(GCScheme.GREEN_SCHEME);
   filter1.addEventHandler(this, "button7_click1");
   
   //Filter2
   filter2 = new GButton(this, 700, 420, 80, 30);
   filter2.setText("Threshold");
   filter2.setLocalColorScheme(GCScheme.GREEN_SCHEME);
   filter2.addEventHandler(this, "button8_click1");
   
   //Filter3
   filter3 = new GButton(this, 790, 420, 80, 30);
   filter3.setText("Gray");
   filter3.setLocalColorScheme(GCScheme.GREEN_SCHEME);
   filter3.addEventHandler(this, "button9_click1");
   
   //Filter4
   filter4 = new GButton(this, 880, 420, 80, 30);
   filter4.setText("Invert");
   filter4.setLocalColorScheme(GCScheme.GREEN_SCHEME);
   filter4.addEventHandler(this, "button10_click1");
   
    //Filter5
   filter5 = new GButton(this, 610, 460, 80, 30);
   filter5.setText("Posterize");
   filter5.setLocalColorScheme(GCScheme.GREEN_SCHEME);
   filter5.addEventHandler(this, "button11_click1");
   
   //Filter6
   filter6 = new GButton(this, 700, 460, 80, 30);
   filter6.setText("Erode");
   filter6.setLocalColorScheme(GCScheme.GREEN_SCHEME);
   filter6.addEventHandler(this, "button12_click1");
   
   //Filter7
   filter7 = new GButton(this, 790, 460, 80, 30);
   filter7.setText("Dilate");
   filter7.setLocalColorScheme(GCScheme.GREEN_SCHEME);
   filter7.addEventHandler(this, "button13_click1");
  
}

// Variable declarations 
// autogenerated do not edit
GButton circle1; 
GButton square1; 
GButton triangle1; 
GButton clear1; 
GButton background1; 
GButton save1;
GButton filter1;
GButton filter2;
GButton filter3;
GButton filter4;
GButton filter5;
GButton filter6;
GButton filter7;


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Random_Art" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
