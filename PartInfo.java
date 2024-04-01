package src;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import src.StartTrail;
import src.Trial;

public class PartInfo implements ActionListener {
  JFrame Frame;

  JPanel Panel;

  JTextField textField;
  JTextField textField1;

  JButton Button;
  public static String user;
  public static String userspeed;
  public static int userid = 0;
  public static int usercat = 0;
  public static int maxSpeed = 0;
  public static int currentSpeed = 0;
  public static int currentSpeedBlocks = 0;
  public static int numTrials = 0 ;
  public static String[] sSpeed = new String[4];
  public static String[] sblock = new String[4]; 
  public static String[] block1;
  public static String[] block2;
  public static String[] block3;
  public static String[] block4;

  // Constructor
  public PartInfo() {
    // Create the frame and container.
    Frame = new JFrame("Insert Your ID");
    Panel = new JPanel();
    Panel.setLayout(new GridLayout(3, 3));

    textField = new JTextField("Your ID");
    textField1 = new JTextField("Speed Category");
    Button = new JButton("START");
    
    Button.addActionListener(this);

    Panel.add(textField);
    Panel.add(textField1);
    Panel.add(Button);

    Frame.getContentPane()
        .add(Panel, BorderLayout.CENTER);

    Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    Frame.setLocationRelativeTo(null);
    Frame.pack();
    Frame.setVisible(true);
  }

  // Implementation of ActionListener interface.
  public void actionPerformed(ActionEvent event) {
	  PrintWriter writer = null;
	  user = textField.getText();
	  userspeed = textField1.getText();
	  Frame.dispose();
	  
	  userid = Integer.parseInt(user);
		usercat = Integer.parseInt(userspeed);
//		sSpeed = Trial.getSpeed(userid).split(", ");
//		maxSpeed = Integer.parseInt(sSpeed[0]);
		maxSpeed = usercat;
		currentSpeed = 1;
		numTrials = usercat*18;
		user = userid +"-"+ usercat;
		sblock = Trial.getSpeedBlocks(userid, maxSpeed);
		block1 = Trial.getBlockTrials(sblock[0]);
		block2 = Trial.getBlockTrials(sblock[1]);
		block3 = Trial.getBlockTrials(sblock[2]);
		block4 = Trial.getBlockTrials(sblock[3]);
		
	  
	try {
		//create the output file
		writer = new PrintWriter("./"+user+"_log.txt", "UTF-8");
	} catch (FileNotFoundException | UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
//	writer.println("Speed "+currentSpeed+" of "+maxSpeed);
	writer.println("User #"+userid+"\t Speed Category :"+maxSpeed);
	writer.println("-------------------------------------------------------");
	writer.println("Block 1: ");
	writer.println("----------");
	writer.close();  
//	System.out.println("Speed "+currentSpeed+" of "+maxSpeed+" - "+ Experiment.counter);
	new StartTrail(1,3);
	
	
  }

}