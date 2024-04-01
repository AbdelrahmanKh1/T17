package src;
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.TileObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.management.timer.Timer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.w3c.dom.css.Rect;


public class Frame extends JFrame {
	public static int IDs = 1;
	public static boolean flag_block1 = true;
	public static boolean flag_block2 = false;
	public static boolean flag_block3 = false;
	public static boolean flag_block4 = false;
	public static boolean flag_block5 = false;
	public static ArrayList<Integer> ids;
	public static ArrayList<Integer> path = new ArrayList<Integer>();
	public static ArrayList<Double> time = new ArrayList<Double>();
	public static ArrayList<Double>[] speed = new ArrayList[PartInfo.maxSpeed];
	private static final long serialVersionUID = 1L;
	public static stopwatch mywatch =  new stopwatch();
	public static String trial = "";
	public static Point spoint = new Point();
	public static Point epoint = new Point();
	private static int x1 = 200;
	private static int x2 = 200;
	private static int x3 = 350;
	private static int x4 = 350;
	private static int y1 = 200;
	private static int y2 = 500;
	private static int y3 = 200;
	private static int y4 = 500;
	private static int mx = 500;
	private static int my = 500;

	public static void main(String[] args) throws IOException {
		new Frame("SVR");
	}
	
	public Frame(String s) throws IOException {

		//set the initial points
		x1 = 200;x2 = 200;x3 = 350;x4 = 350;
		y1 = 200;y2 = 500;y3 = 200;y4 = 500;
		mx = 500;my = 500;
		
		//edit points according to distance and orientation
		if(s.charAt(0)=='M'){
			x3+=200;
			x4+=200;
		}
		if(s.charAt(0)=='L'){
			x3+=400;
			x4+=400;
		}
		if(s.charAt(1)=='S'){
			y1-=100;
			y2-=100;
			y3+=200;
			y4+=200;
		}
		if(s.charAt(1)=='V'){
			y1 -= 100;
			x2 -= 100;
			x3 -= 100;
			x4 -= 100;
			int temp = x2;
			x2 = y2;
			y2 = temp;
			temp = x3;
			x3 = y3;
			y3 = temp;
			temp = x4;
			x4 = y4;
			y4 = temp;
		}
		if(s.charAt(2)=='L'){
			int t = x1;
			x1 = x3;
			x3 = t;
			t = x2;
			x2 = x4;
			x4 = t;
			t = y1;
			y1 = y3;
			y3 = t;
			t = y2;
			y2 = y4;
			y4 = t;
		}
		mx = (x1+x2)/2;
		my = (y1+y2)/2;
		if(s.endsWith("VL"))my+=100;
		if(s.endsWith("HR"))mx-=50;
		if(s.endsWith("HL") || s.endsWith("SL"))mx+=100;
		trial = s; 
		initUI();
	}

	private void initUI() throws IOException {

		setTitle("Mouse Speed Test Experiment : "+PartInfo.currentSpeed+"/"+PartInfo.maxSpeed);
		//setTitle("Mouse Speed Test Experiment ");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		setPreferredSize(new Dimension(width, height));
		
		mywatch.reset();
		
		//add the 2 lines
		CustomComponent temp1 = new CustomComponent(0);
		CustomComponent temp2 = new CustomComponent(1);
		JPanel pane = new JPanel();
		pane.add(temp1);
		pane.add(temp2);
		// add timecomponent to view the timer
//		TimeComponent temp3 = new TimeComponent();
//		pane.add(temp3);
		getContentPane().add(pane, BorderLayout.CENTER);

		
		//move mouse nest to the green line
		try {
			Robot r;
			r = new Robot();
			r.mouseMove(mx, my);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	class CustomMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
			// start timer upon entering the green line
			mywatch = new stopwatch();
		}

		public void mouseExited(MouseEvent e) {


		}
	}

	class CustomMouseListenerLabels implements MouseListener {

		public void mouseClicked(MouseEvent e) {


		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {


		}

		public void mouseExited(MouseEvent e) {
			if(Experiment.counter <= 127 && flag_block1){
				writeTime();
				mywatch.myTimer.stop();
				mywatch = null;
				
				Experiment.counter++;
				if(Experiment.block != null){
					Experiment.block.setVisible(false);
					Experiment.block.dispose();
					Experiment.block = null;
				}
				if(Experiment.counter==128){
//					Experiment.counter=1;
					flag_block1=false;
					flag_block2=true;
					writeBlockTitle(2);
					new StartTrail("Block 2 Trail "+Experiment.counter,10);
				}else{
					new StartTrail("Block 1 Trail "+Experiment.counter,3);
				}						
			}else{
				if(Experiment.counter <= 253 && flag_block2){
					writeTime();
					mywatch.myTimer.stop();
					mywatch = null;
					Experiment.counter++;
					if(Experiment.block != null){
						Experiment.block.setVisible(false);
						Experiment.block.dispose();
						Experiment.block = null;
					}
					if(Experiment.counter==254){
//						Experiment.counter=1;
						flag_block2=false;
						flag_block3=true;
						writeBlockTitle(3);
						new StartTrail("Block 3 Trail "+Experiment.counter,20);
					}else{
						new StartTrail("Block 2 Trail "+Experiment.counter,3);
					}						
				}else{
					if(Experiment.counter <= 380 && flag_block3){
						writeTime();
						mywatch.myTimer.stop();
						mywatch = null;
						Experiment.counter++;
						if(Experiment.block != null){
							Experiment.block.setVisible(false);
							Experiment.block.dispose();
							Experiment.block = null;
						}
						if(Experiment.counter==381){
							Experiment.counter=1;
							flag_block3=false;
							flag_block1=true;
							calcAvgTime();
						}else{
							new StartTrail("Block 3 Trail "+Experiment.counter,3);
						}						
					}
				}
			}
		}
		public void writeTime(){
			PrintWriter pw = null;

			try {
				File file = new File(PartInfo.user+"_log.txt");
				FileWriter fw = new FileWriter(file, true);
				pw = new PrintWriter(fw);
				pw.println(mywatch.minutes*60+mywatch.seconds+mywatch.miliseconds*0.001);
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
		public void writeAvgTime(double avg_time){
			PrintWriter pw = null;

			try {
				File file = new File(PartInfo.user+"_log.txt");
				FileWriter fw = new FileWriter(file, true);
				pw = new PrintWriter(fw);
				pw.println("-------------------------------------------------------");
				pw.println("Average time for 3 blocks: "+avg_time);
				pw.println("-------------------------------------------------------");
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
		public void writeBlockTitle(int num){
			PrintWriter pw = null;

			try {
				File file = new File(PartInfo.user+"_log.txt");
				FileWriter fw = new FileWriter(file, true);
				pw = new PrintWriter(fw);
				pw.println("-------------------------------------------------------");
				pw.println("Block "+num+": ");
				pw.println("---------");
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
		public void writeSpeedTitle(){
			PrintWriter pw = null;

			try {
				File file = new File(PartInfo.user+"_log.txt");
				FileWriter fw = new FileWriter(file, true);
				pw = new PrintWriter(fw);
				pw.println("-------------------------------------------------------");
				pw.println("Speed "+PartInfo.currentSpeed+" of "+PartInfo.maxSpeed);
				pw.println("-------------------------------------------------------");
				pw.println("Block 1:");
				pw.println("---------");
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
		public void calcAvgTime(){
			ArrayList<Double> time = new ArrayList<Double>();
			String path = ".";
			Path path2 = Paths.get(path,PartInfo.user+"_log.txt");
			Charset charset = Charset.forName("UTF-8");
			String line = null;
			try (BufferedReader reader = Files.newBufferedReader(path2 , charset)) {
				while ((line = reader.readLine()) != null ) {
					try{
						time.add(Double.parseDouble(line));
					}catch(Exception ff){
						continue;
					}			
				}
			} catch (IOException e) {
				System.err.println(e);
			}
			double sum = 0.0;
		    for(int i=0; i<time.size(); i++){
		        sum = sum + time.get(i);
		    }
		    double Avg_time = Math.round(sum/time.size()*100.0)/100.0;
		    writeAvgTime(Avg_time);
		}
	}

	private static class CustomComponent extends JComponent {
		
		private Color c;
		private int x, y, w, h;
		
		public CustomComponent(int s){
			//starting line
			if(s == 1){
				c = Color.GREEN;
				//edit coordinates
				x=x1;
				y=y1;
				w=x2-x1;
				h=y2-y1;
				
				addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// start the timer
						mywatch.start();
						// save crossing point
						spoint = new Point(e.getXOnScreen() , e.getYOnScreen());
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			
			}
			//finishing line
			if(s == 0){
				c = Color.RED;
				//edit coordinates
				x=x3;
				y=y3;
				w=x4-x3;
				h=y4-y3;
				
				addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
//						System.out.println("mouse exited");
						
						epoint = new Point(e.getXOnScreen() , e.getYOnScreen());
						if(mywatch.myTimer.isRunning()){
							
							if(Experiment.counter==1){
								for (int i = 0; i < PartInfo.maxSpeed; i++) {
									speed[i] = new ArrayList<Double>();
								}
							}
							
							writeTime();
							mywatch.stop();
							
							if(Experiment.block != null){
								Experiment.block.setVisible(false);
								Experiment.block.dispose();
								Experiment.block = null;
							}
							if(Experiment.counter <= PartInfo.numTrials && flag_block1){

								Experiment.counter++;
								System.out.println(Experiment.counter);
								System.out.println(PartInfo.numTrials);
								if(Experiment.counter==PartInfo.numTrials+1){
									Experiment.counter=1;
									flag_block1=false;
									flag_block2=true;
									writeBlockTitle(2);
									new StartTrail(2,10);
								}else{
									new StartTrail(1,3);
								}						
							}else{
								if(Experiment.counter <= PartInfo.numTrials && flag_block2){

									Experiment.counter++;
									
									if(Experiment.counter==PartInfo.numTrials+1){
										Experiment.counter=1;
										flag_block2=false;
										flag_block3=true;
										writeBlockTitle(3);
										new StartTrail(3,20);
									}else{
										new StartTrail(2,2);
									}						
								}else{
									if(Experiment.counter <= PartInfo.numTrials && flag_block3){

										Experiment.counter++;
										
										if(Experiment.counter==PartInfo.numTrials+1){
											Experiment.counter=1;
											flag_block3=false;
											flag_block4=true;
											writeBlockTitle(4);
											new StartTrail(4, 30);
											
										}else{
											new StartTrail(3,2);
										}						
									}else{
										if(Experiment.counter <= PartInfo.numTrials && flag_block4){

											Experiment.counter++;
											
											if(Experiment.counter==PartInfo.numTrials+1){
												Experiment.counter=1;
												flag_block4=false;
												flag_block5=true;
												calcAvgTime();
												
											}else{
												new StartTrail(4,2);
											}						
										}
//										else {
//											if(Experiment.counter <= PartInfo.numTrials && flag_block5){
//
//												Experiment.counter++;
//												
//												if(Experiment.counter==PartInfo.numTrials+1){
//													Experiment.counter=1;
//													flag_block5=false;
//													flag_block1=true;
//													calcAvgTime();
//													
//												}else{
//													new StartTrail(5,2);
//												}						
//											}
//										}
									}
								}
							}
						}
						
						/*
						if(mywatch != null){
							if(Experiment.counter <= 54 && flag_block1){
								writeTime();
								mywatch.myTimer.stop();
								mywatch = null;
								
								Experiment.counter++;
								if(Experiment.block != null){
									Experiment.block.setVisible(false);
									Experiment.block.dispose();
									Experiment.block = null;
								}
								if(Experiment.counter==55){
//									Experiment.counter=1;
									flag_block1=false;
									flag_block2=true;
									writeBlockTitle(2);
									new StartTrail("Block 2 Trail "+Experiment.counter%54,10);
								}else{
									new StartTrail("Block 1 Trail "+Experiment.counter,3);
								}						
							}else{
								if(Experiment.counter <= 108 && flag_block2){
									writeTime();
									mywatch.myTimer.stop();
									mywatch = null;
									Experiment.counter++;
									if(Experiment.block != null){
										Experiment.block.setVisible(false);
										Experiment.block.dispose();
										Experiment.block = null;
									}
									if(Experiment.counter==109){
//										Experiment.counter=1;
										flag_block2=false;
										flag_block3=true;
										writeBlockTitle(3);
										new StartTrail("Block 3 Trail "+Experiment.counter%54,20);
									}else{
										new StartTrail("Block 2 Trail "+Experiment.counter%54,3);
									}						
								}else{
									if(Experiment.counter <= 162 && flag_block3){
										writeTime();
										mywatch.myTimer.stop();
										mywatch = null;
										Experiment.counter++;
										if(Experiment.block != null){
											Experiment.block.setVisible(false);
											Experiment.block.dispose();
											Experiment.block = null;
										}
										if(Experiment.counter==163){
											Experiment.counter=1;
											flag_block3=false;
											flag_block1=true;
											calcAvgTime();
											if(PartInfo.currentSpeed < PartInfo.maxSpeed){
												PartInfo.currentSpeed++;
												writeSpeedTitle();
												new StartTrail("Block 1 Trail "+Experiment.counter,20);
											}else{
												if(PartInfo.currentSpeedBlocks<4){
													PartInfo.currentSpeedBlocks++;
													PartInfo.maxSpeed = Integer.parseInt(PartInfo.sSpeed[PartInfo.currentSpeedBlocks]);
													PartInfo.currentSpeed = 1;
													PartInfo.sblock = Trial.getSpeedBlocks(PartInfo.userid, PartInfo.maxSpeed);
													writeSpeedTitle();
													new StartTrail("Block 1 Trail "+Experiment.counter,30);
												}
											}
										}else{
											new StartTrail("Block 3 Trail "+Experiment.counter%54,3);
										}						
									}
								}
							}
						}*/
					}
					
					// write trial info and time in the file
					public void writeTime(){
						PrintWriter pw = null;
						if(mywatch != null){
							try {
								File file = new File(PartInfo.user+"_log.txt");
								FileWriter fw = new FileWriter(file, true);
								pw = new PrintWriter(fw);
								double temp = mywatch.minutes*60+mywatch.seconds+mywatch.miliseconds*0.001;
								time.add(temp);
								double speed1 = 0.0;
//								//150 px = 3.5 cm
//								if(trial.charAt(0)=='S')speed1 = (temp/1.5);
//								//350 px = 8 cm
//								if(trial.charAt(0)=='M')speed1 = (temp/3.5);
//								//550 px = 12.5 cm
//								if(trial.charAt(0)=='L')speed1 = (temp/5.5);
								speed1 = temp/epoint.distance(spoint);
								speed[PartInfo.currentSpeed-1].add(speed1);
								pw.println(trial+"\t T:"+temp+"\t "+PartInfo.currentSpeed+"/"+PartInfo.maxSpeed+" S:"+speed1);
							} catch (IOException e1) {
								e1.printStackTrace();
							} finally {
								if (pw != null) {
									pw.close();
								}
							}
						}
						
					}
					// write the total average in the file
					public void writeAvgTime(double avg_time , ArrayList<Double> avg_speed){
						PrintWriter pw = null;

						try {
							File file = new File(PartInfo.user+"_log.txt");
							FileWriter fw = new FileWriter(file, true);
							pw = new PrintWriter(fw);
							pw.println("-------------------------------------------------------");
							pw.println("Avrg Time: "+avg_time+" Avrg Speed:"+avg_speed+" px/s");
							for (int i = 0; i < avg_speed.size(); i++) {
								pw.println("Avrg Speed of "+(i+1)+"/"+PartInfo.maxSpeed+" : "+avg_speed.get(i));
							}
							pw.println("-------------------------------------------------------");
							new EndExp(avg_time, avg_speed);
						} catch (IOException e1) {
							e1.printStackTrace();
						} finally {
							if (pw != null) {
								pw.close();
							}
						}
					}
					// write the current block number in file
					public void writeBlockTitle(int num){
						PrintWriter pw = null;

						try {
							File file = new File(PartInfo.user+"_log.txt");
							FileWriter fw = new FileWriter(file, true);
							pw = new PrintWriter(fw);
							pw.println("-------------------------------------------------------");
							pw.println("Block "+num+": ");
							pw.println("---------");
						} catch (IOException e1) {
							e1.printStackTrace();
						} finally {
							if (pw != null) {
								pw.close();
							}
						}
					}
					// calculate the average time and 1/speed for each speed
					public void calcAvgTime(){
						
						ArrayList<Double> Avg_speed = new ArrayList<Double>();
						for (int x = 0; x < speed.length; x++) {
							double sum1 = 0.0;
						    for(int i=0; i<speed[x].size(); i++){
						        sum1 = sum1 + speed[x].get(i);
						    }
						    Avg_speed.add(Math.round(sum1/speed[x].size()*100000.0)/100000.0);
						}
						
					    
						double sum = 0.0;
					    for(int i=0; i<time.size(); i++){
					        sum = sum + time.get(i);
					    }
					    double Avg_time = Math.round(sum/time.size()*100.0)/100.0;
//					    speed = new ArrayList<Double>();
//					    time = new ArrayList<Double>();
					    writeAvgTime(Avg_time , Avg_speed);
					    
					}
					
				
				});
			
			}
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(w+10, h+10);
		}
		
		
		@Override
		protected void paintComponent(Graphics graphics) {

            Graphics2D g = (Graphics2D) graphics;

            //set drawing stroke
            g.setStroke(new BasicStroke(20));

            //set component location
            setLocation(x, y);
            //set color
            g.setPaint(c);
            //draw the wanted line
            g.drawLine(0, 0, w, h);
        }
    }

	private static class TimeComponent extends JComponent{
		
		@Override
		public Dimension getPreferredSize(){
			return new Dimension(150, 150); 
		}
		
		@Override
		protected void paintComponent(Graphics graphics){

            Graphics2D g = (Graphics2D) graphics;
            setLocation(1000, 0);
            g.setPaint(Color.BLACK);
            g.drawString("The timer", 60, 50);
            paintComponents(g);
            if(mywatch != null){
                g.drawString(mywatch.minutes+"", 25, 80);
                g.drawString(mywatch.seconds+"", 75, 80);
                g.drawString(mywatch.miliseconds+"", 125, 80);
            }
		}
		
	}
	

}

