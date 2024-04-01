package src;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

public class StartTrail extends JPanel implements ActionListener
{
	int miliseconds=100;
	int seconds=3;
	int minutes=0;
	String trial;
	String newTrial;

	Timer myTimer;
	static JFrame myFrame;
	static boolean f = true;

	Font timerFont=new Font("Verdana",Font.BOLD,24);
	Font speedFont=new Font("Verdana",Font.BOLD,36);
	Font font=new Font("Verdana",Font.BOLD,18);
	
	public static void main(String[] args) {
		PartInfo.maxSpeed = 7;
		PartInfo.userid = 5;

		PartInfo.sblock = Trial.getSpeedBlocks(PartInfo.userid, PartInfo.maxSpeed);
		PartInfo.block1 = Trial.getBlockTrials(PartInfo.sblock[0]);
		PartInfo.block2 = Trial.getBlockTrials(PartInfo.sblock[1]);
		PartInfo.block3 = Trial.getBlockTrials(PartInfo.sblock[2]);
		PartInfo.block4 = Trial.getBlockTrials(PartInfo.sblock[3]);
		Experiment.counter = 20;
		new StartTrail(1, 28);
	}
	
	public StartTrail(String trial,int seconds)
	{
		this.trial = trial;
		this.seconds = seconds;
		myTimer=new Timer(10,this);

		myFrame = new JFrame();


		myFrame.add(this);


		myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		myFrame.setSize(300,170);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);

		myTimer.setInitialDelay(0);
		

		myTimer.start();
	}
	
	public StartTrail(int block , int seconds)
	{
		this.trial = "Next Trial";
		this.seconds = seconds;
		
		//assign next trial according to the block
		if(block==1){
			newTrial = Trial.getInfo(PartInfo.block1[Experiment.counter-1]);
		}else {
			if(block==2){
				newTrial = Trial.getInfo(PartInfo.block2[Experiment.counter-1]);
			}else{
				if(block==3){
					newTrial = Trial.getInfo(PartInfo.block3[Experiment.counter-1]);
				}else{
					if(block==4){
						newTrial = Trial.getInfo(PartInfo.block4[Experiment.counter-1]);
					}
				}
			}
		}
		
		myTimer=new Timer(10,this);

		myFrame = new JFrame();


		myFrame.add(this);


		myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		myFrame.setSize(500,300);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);

		myTimer.setInitialDelay(0);
		

		myTimer.start();
	}

	//display
	public void paint(Graphics g)
	{

		super.paint(g);
		String mili=Integer.toString(miliseconds);

		String sec=Integer.toString(seconds);
		String min=Integer.toString(minutes);
		
		String tempPrint = "Slower"; 

		g.setFont(font);
		g.drawString("Perform Speed : ",25,20);

		if(mili.length()==1)
		{
			mili="0"+mili;
		}

		if(sec.length()==1)
		{
			sec="0"+sec;
		}

		if(min.length()==1)
		{
			min="0"+min;
		}
		
		//print the next trial speed
		if(PartInfo.maxSpeed==2){
			if(PartInfo.currentSpeed==1){
				g.setFont(speedFont);
				g.drawString("1", 200, 100);
				g.setFont(font);
				g.drawString("2", 300, 100);
			}else {
				g.setFont(font);
				g.drawString("1", 200, 100);
				g.setFont(speedFont);
				g.drawString("2", 300, 100);
			}
		}
		if(PartInfo.maxSpeed==3){
			if(PartInfo.currentSpeed==1){
				g.setFont(speedFont);
				g.drawString("1", 150, 100);
				g.setFont(font);
				g.drawString("2", 250, 100);
				g.setFont(font);
				g.drawString("3", 350, 100);
			}
			if(PartInfo.currentSpeed==2){
				g.setFont(font);
				g.drawString("1", 150, 100);
				g.setFont(speedFont);
				g.drawString("2", 250, 100);
				g.setFont(font);
				g.drawString("3", 350, 100);
			}
			if(PartInfo.currentSpeed==3){
				g.setFont(font);
				g.drawString("1", 150, 100);
				g.setFont(font);
				g.drawString("2", 250, 100);
				g.setFont(speedFont);
				g.drawString("3", 350, 100);
			}
		}
		if(PartInfo.maxSpeed==4){
			if(PartInfo.currentSpeed==1){
				g.setFont(speedFont);
				g.drawString("1", 100, 100);
				g.setFont(font);
				g.drawString("2", 200, 100);
				g.setFont(font);
				g.drawString("3", 300, 100);
				g.setFont(font);
				g.drawString("4", 400, 100);
			}
			if(PartInfo.currentSpeed==2){
				g.setFont(font);
				g.drawString("1", 100, 100);
				g.setFont(speedFont);
				g.drawString("2", 200, 100);
				g.setFont(font);
				g.drawString("3", 300, 100);
				g.setFont(font);
				g.drawString("4", 400, 100);
			}
			if(PartInfo.currentSpeed==3){
				g.setFont(font);
				g.drawString("1", 100, 100);
				g.setFont(font);
				g.drawString("2", 200, 100);
				g.setFont(speedFont);
				g.drawString("3", 300, 100);
				g.setFont(font);
				g.drawString("4", 400, 100);
			}
			if(PartInfo.currentSpeed==4){
				g.setFont(font);
				g.drawString("1", 100, 100);
				g.setFont(font);
				g.drawString("2", 200, 100);
				g.setFont(font);
				g.drawString("3", 300, 100);
				g.setFont(speedFont);
				g.drawString("4", 400, 100);
			}
		}
		if(PartInfo.maxSpeed==5){
			if(PartInfo.currentSpeed==1){
				g.setFont(speedFont);
				g.drawString("1", 100, 100);
				g.setFont(font);
				g.drawString("2", 175, 100);
				g.setFont(font);
				g.drawString("3", 250, 100);
				g.setFont(font);
				g.drawString("4", 325, 100);
				g.setFont(font);
				g.drawString("5", 400, 100);
			}
			if(PartInfo.currentSpeed==2){
				g.setFont(font);
				g.drawString("1", 100, 100);
				g.setFont(speedFont);
				g.drawString("2", 175, 100);
				g.setFont(font);
				g.drawString("3", 250, 100);
				g.setFont(font);
				g.drawString("4", 325, 100);
				g.setFont(font);
				g.drawString("5", 400, 100);
			}
			if(PartInfo.currentSpeed==3){
				g.setFont(font);
				g.drawString("1", 100, 100);
				g.setFont(font);
				g.drawString("2", 175, 100);
				g.setFont(speedFont);
				g.drawString("3", 250, 100);
				g.setFont(font);
				g.drawString("4", 325, 100);
				g.setFont(font);
				g.drawString("5", 400, 100);
			}
			if(PartInfo.currentSpeed==4){
				g.setFont(font);
				g.drawString("1", 100, 100);
				g.setFont(font);
				g.drawString("2", 175, 100);
				g.setFont(font);
				g.drawString("3", 250, 100);
				g.setFont(speedFont);
				g.drawString("4", 325, 100);
				g.setFont(font);
				g.drawString("5", 400, 100);
			}
			if(PartInfo.currentSpeed==5){
				g.setFont(font);
				g.drawString("1", 100, 100);
				g.setFont(font);
				g.drawString("2", 175, 100);
				g.setFont(font);
				g.drawString("3", 250, 100);
				g.setFont(font);
				g.drawString("4", 325, 100);
				g.setFont(speedFont);
				g.drawString("5", 400, 100);
			}
		}
		if(PartInfo.maxSpeed==6){
			if(PartInfo.currentSpeed==1){
				g.setFont(speedFont);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
			}
			if(PartInfo.currentSpeed==2){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(speedFont);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
			}
			if(PartInfo.currentSpeed==3){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(speedFont);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
			}
			if(PartInfo.currentSpeed==4){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(speedFont);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 325, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
			}
			if(PartInfo.currentSpeed==5){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(speedFont);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 420, 100);
			}
			if(PartInfo.currentSpeed==6){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(speedFont);
				g.drawString("6", 430, 100);
			}
		}
		 if(PartInfo.maxSpeed==7){
			if(PartInfo.currentSpeed==1){
				g.setFont(speedFont);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
				g.setFont(font);
				g.drawString("7", 480, 100);
			}
			if(PartInfo.currentSpeed==2){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(speedFont);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
				g.setFont(font);
				g.drawString("7", 480, 100);
			}
			if(PartInfo.currentSpeed==3){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(speedFont);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
				g.setFont(font);
				g.drawString("7", 480, 100);
			}
			if(PartInfo.currentSpeed==4){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(speedFont);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 325, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
				g.setFont(font);
				g.drawString("7", 480, 100);
			}
			if(PartInfo.currentSpeed==5){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(speedFont);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 420, 100);
				g.setFont(font);
				g.drawString("7", 480, 100);
			}
			if(PartInfo.currentSpeed==6){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(speedFont);
				g.drawString("6", 430, 100);
				g.setFont(font);
				g.drawString("7", 480, 100);
			}
			if(PartInfo.currentSpeed==7){
				g.setFont(font);
				g.drawString("1", 55, 100);
				g.setFont(font);
				g.drawString("2", 130, 100);
				g.setFont(font);
				g.drawString("3", 205, 100);
				g.setFont(font);
				g.drawString("4", 280, 100);
				g.setFont(font);
				g.drawString("5", 355, 100);
				g.setFont(font);
				g.drawString("6", 430, 100);
				g.setFont(speedFont);
				g.drawString("7", 480, 100);
			}
		}
	
		g.setFont(font);
		g.drawString("Slowest", 20, 150);
		g.drawString("Fastest", 480, 150);
		g.drawString(trial+" starts in: ",150,210);		
		g.setFont(timerFont);
		g.drawString(sec,220,250);
	}

	//start trail after the timer hit zero
	public void actionPerformed(ActionEvent event)
	{
		miliseconds = miliseconds-1;

		if(miliseconds == 0)
		{
			miliseconds = 100;

			seconds = seconds-1;
		}
		if(seconds == 0)
		{
			myTimer.stop();
			myFrame.dispose();
			seconds = -1;
			
//			System.out.println("Start");
//			System.out.println(PartInfo.sblock[0]);
//			System.out.println(Trial.getBlockTrials(PartInfo.sblock[0]));
//			System.out.println(Trial.getBlockTrials(PartInfo.sblock[0])[Experiment.counter-1]);
//			System.out.println(Trial.getTrial(Trial.getBlockTrials(PartInfo.sblock[0])[Experiment.counter-1]));
			
			
			try {
//				Experiment.block = new Frame(Trial.getTrial(
//						Trial.getBlockTrials(PartInfo.sblock[(Experiment.counter-1)/54])[(Experiment.counter-1)%54]));
				Experiment.block = new Frame(newTrial);
				Experiment.block.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		repaint();
	}
}
