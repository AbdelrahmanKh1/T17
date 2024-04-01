package src;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class stopwatch extends JPanel implements ActionListener
{
	int miliseconds=0;
	int seconds=0;
	int minutes=0;

	public Timer myTimer;

	public stopwatch()
	{
		myTimer=new Timer(10,this);
		myTimer.setInitialDelay(0);
		myTimer.start();
	}
	
	public void start(){
		if(myTimer.isRunning()){
			miliseconds=0;
			seconds = 0 ;
			minutes = 0 ;
			myTimer.restart();
		}else{
			myTimer.start();
		}
	}
	
	public void reset(){
		miliseconds=0;
		seconds = 0 ;
		minutes = 0 ;
	}
	
	public void stop(){
		myTimer.stop();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		miliseconds = miliseconds+1;
		if(miliseconds == 100)
		{
			miliseconds = 0;
			seconds = seconds+1;
		}
		if(seconds == 60)
		{
			seconds=0;
			minutes = minutes+1;
		}
	}
}