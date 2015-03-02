// Need G4P library
import g4p_controls.*;
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

  
  //Setting the background color or the left side of the screen
  background(230); 
}

public void draw(){
//Creates the GUI window to the right
  
}

void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
  }
}

public void Gui(){
   pg1.beginDraw();
   pg1.background(200);
       image(pg1,600,0,400,600);
   pg1.endDraw();
  
//Setting the font, texts size and text of the right window.
   myFont = createFont("Arial", 32);
   textFont(myFont);
   textAlign(CENTER, CENTER);
   fill(255);
   String s = "Welcome to Random Art Generator!";
   fill(255);
   text(s, 600, 0, 400, 100);  // Text wraps within text box

//Font text
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


