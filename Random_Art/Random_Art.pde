// Need G4P library
import g4p_controls.*;
PGraphics pg1;
PGraphics pg2;
PImage img;

//fonts
PFont myFont;
PFont myFont1;


public void setup(){
  
  size(displayWidth, displayHeight, P3D);
  
  
  pg1 = createGraphics(displayWidth/4, displayHeight); //GUI
  pg2 = createGraphics(displayWidth-displayWidth/4,displayHeight);
  
  

  if(frame != null){
    frame.setResizable(true);
  }
  
  createGUI();
  customGUI();
  
  //Setting the background color or the left side of the screen
  background(230); 


 Gui();

}

public void draw(){
//Creates the GUI window to the right
 if ( img != null )
  {
    image( img, 0, 0,displayWidth-displayWidth/4,displayHeight);
  }

}


public void Gui(){
   pg1.beginDraw();
   pg1.background(200);
       image(pg1,displayWidth-displayWidth/4,displayHeight-displayHeight);
   pg1.endDraw();
  
//Setting the font, texts size and text of the right window.
   myFont = createFont("Arial", 25);
   textFont(myFont);
   textAlign(CENTER, CENTER);
   fill(255);
   String s = "Welcome to Random Art Generator!";
   fill(255);
   text(s, displayWidth-displayWidth/4, displayHeight/40, displayWidth/4, displayHeight/12);  // Text wraps within text box

//Font text
    textAlign(LEFT,CENTER);
    textSize(22);
    text("Choose filter:", displayWidth-displayWidth/4.1,displayHeight/2.55, 400,100);
  
  
//Objects text
     textAlign(LEFT,CENTER);
     textSize(22);
     text("Choose Objects:", displayWidth-displayWidth/4.1,displayHeight/3.8, 400,100); 
  
}

// Use this method to add additional statements
// to customise the GUI controls

void imageChosen( File f )
{
  if( f.exists() )
  {
     img = loadImage( f.getAbsolutePath() ); 
  }
}


 

public void customGUI(){

}


