package src;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class EndExp extends JPanel
{
	
	static JFrame myFrame;
	static boolean f = true;
	int mspeed = 0 ;
	double time = 0;
	ArrayList<Double> speed;
	
	//set fonts
	Font numfont=new Font("Verdana",Font.BOLD,24);
	Font wordfont=new Font("Verdana",Font.BOLD,36);
	Font font=new Font("Verdana",Font.BOLD,18);
	Font tfont=new Font("Verdana",Font.BOLD,16);
	
	// thank you message
	public EndExp(double avg_time , ArrayList<Double> avg_speed)
	{

		myFrame = new JFrame();
		this.mspeed = PartInfo.maxSpeed;
//		this.mspeed = 5;
		this.time = avg_time;
		this.speed = avg_speed;

		myFrame.add(this);


		myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		myFrame.setSize(500,400);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


	//paint
	public void paint(Graphics g)
	{

		super.paint(g);
		
		//print average time for the user
		g.setFont(font);
		g.drawString("Average Time : "+time, 160, 40);

		// paint in respective space
		if(mspeed==2){
				g.setFont(numfont);
				g.drawString("1", 200, 100);
				g.drawString("2", 300, 100);
				g.setFont(tfont);
				g.drawString(speed.get(0)+"", 160, 200);
				g.drawString(speed.get(1)+"", 280, 200);
		}
		if(mspeed==3){
				g.setFont(numfont);
				g.drawString("1", 150, 100);
				g.drawString("2", 250, 100);
				g.drawString("3", 350, 100);
				g.setFont(tfont);
				g.drawString(speed.get(0)+"", 110, 200);
				g.drawString(speed.get(1)+"", 220, 200);
				g.drawString(speed.get(2)+"", 330, 200);
		}
		if(mspeed==4){
				g.setFont(numfont);
				g.drawString("1", 100, 100);
				g.drawString("2", 200, 100);
				g.drawString("3", 300, 100);
				g.drawString("4", 400, 100);
				g.setFont(tfont);
				g.drawString(speed.get(0)+"", 40, 200);
				g.drawString(speed.get(1)+"", 160, 200);
				g.drawString(speed.get(2)+"", 270, 200);
				g.drawString(speed.get(3)+"", 390, 200);
		}
		if(mspeed==5){
				g.setFont(numfont);
				g.drawString("1", 100, 100);
				g.drawString("2", 175, 100);
				g.drawString("3", 250, 100);
				g.drawString("4", 325, 100);
				g.drawString("5", 400, 100);
				g.setFont(tfont);
				g.drawString(speed.get(0)+"", 30, 200);
				g.drawString(speed.get(1)+"", 125, 200);
				g.drawString(speed.get(2)+"", 220, 200);
				g.drawString(speed.get(3)+"", 310, 200);
				g.drawString(speed.get(4)+"", 405, 200);
		}
		if(mspeed==6){
			g.setFont(numfont);
			g.drawString("1", 75, 100);
			g.drawString("2", 150, 100);
			g.drawString("3", 225, 100);
			g.drawString("4", 300, 100);
			g.drawString("5", 375, 100);
			g.drawString("6", 450, 100);
			g.setFont(tfont);
			g.drawString(speed.get(0)+"", 10, 200);
			g.drawString(speed.get(1)+"", 105, 200);
			g.drawString(speed.get(2)+"", 200, 200);
			g.drawString(speed.get(3)+"", 290, 200);
			g.drawString(speed.get(4)+"", 385, 200);
			g.drawString(speed.get(5)+"", 475, 200);
	}
	 if(mspeed==7){
			g.setFont(numfont);
			g.drawString("1", 75, 100);
			g.drawString("2", 150, 100);
			g.drawString("3", 225, 100);
			g.drawString("4", 300, 100);
			g.drawString("5", 375, 100);
			g.drawString("6", 450, 100);
			g.drawString("7", 525, 100);
			g.setFont(tfont);
			g.drawString(speed.get(0)+"", 10, 200);
			g.drawString(speed.get(1)+"", 105, 200);
			g.drawString(speed.get(2)+"", 200, 200);
			g.drawString(speed.get(3)+"", 290, 200);
			g.drawString(speed.get(4)+"", 385, 200);
			g.drawString(speed.get(5)+"", 475, 200);
			g.drawString(speed.get(6)+"", 550, 200);
	}
	
		g.setFont(font);
		g.drawString("Slowest", 20, 150);
		g.drawString("Fastest", 560, 150);
		g.setFont(wordfont);
		g.drawString("Thank You", 150, 300);
	}

	public static void main(String[] args) {
		ArrayList<Double> avg_speed = new ArrayList<>();
		avg_speed.add(0.03501);
		avg_speed.add(0.02642);
		avg_speed.add(0.01409);
		avg_speed.add(0.00542);
		avg_speed.add(0.00143);
		new EndExp(1.20, avg_speed);
	}
}
