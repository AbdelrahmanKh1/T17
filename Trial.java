package src;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


public class Trial {
	
	//get trial info (Distance,Orientation,Direction)
	public static String getTrial( String trial ){
		switch(trial) {
		  case "T1": return "SHR";  
		  case "T2": return "SHL";
		  case "T3": return "SVR";
		  case "T4": return "SVL";
		  case "T5": return "SSR";
		  case "T6": return "SSL";
		  case "T7": return "MHR";
		  case "T8": return "MHL";
		  case "T9": return "MVR";
		  case "T10":return "MVL";
		  case "T11":return "MSR";
		  case "T12":return "MSL";
		  case "T13":return "LHR";
		  case "T14":return "LHL";
		  case "T15":return "LVR";
		  case "T16":return "LVL";
		  case "T17":return "LSR";
		  case "T18":return "LSL";
		  case "T19":return "MHR";//changed
		  case "T20":return "MHL";
		  case "T21":return "MVR";
		  case "T22":return "MVL";
		  case "T23":return "MSR";
		  case "T24":return "MSL";
		  case "T25":return "MHR";
		  case "T26":return "MHL";
		  case "T27":return "MVR";
		  case "T28":return "MVL";
		  case "T29":return "MSR";
		  case "T30":return "MSL";
		  case "T31":return "MVR";
		  case "T32":return "MVL";
		  case "T33":return "MSR";
		  case "T34":return "MSL";
		  case "T35":return "MHR";
		  case "T36":return "MHL";
		  case "T37":return "LSR";
		  case "T38":return "LSL";
		  case "T39":return "LHR";
		  case "T40":return "LHL";
		  case "T41":return "LVR";
		  case "T42":return "LVL";
		  case "T43":return "LSR";
		  case "T44":return "LSL";
		  case "T45":return "LHR";
		  case "T46":return "LHL";
		  case "T47":return "LVR";
		  case "T48":return "LVL";
		  case "T49":return "LHR";
		  case "T50":return "LHR";
		  case "T51":return "LSL";
		  case "T52":return "LSR";
		  case "T53":return "LVL";
		  case "T54":return "LVR";
		  default :return "SHR";
		}
		
	}

	public static String getInfo(String trial) {
		PartInfo.currentSpeed = Integer.parseInt(trial.charAt(0)+"");
		return getTrial(trial.substring(2));
	}
	
	public static String getSpeed (int player){
		Properties prop = new Properties();
		InputStream input = null;
		String ss = ""; 

		try {

			input = new FileInputStream("C:\\\\Users\\\\M0KIN\\\\eclipse-workspace\\\\yarab\\\\MouseSpeed\\\\asd\\\\MouseSpeed\\\\resources\\\\player.properties_");
			
		// load a properties file
			prop.load(input);

			// get the property value and print it out
			String s = (prop.getProperty("p"+player));
			ss = s;
			
			//System.out.println(ss);
//			ss.add(prop.getProperty("p"+player+"."+s.charAt(0)));
//			ss.add(prop.getProperty("p"+player+"."+s.charAt(3)));
//			ss.add(prop.getProperty("p"+player+"."+s.charAt(6)));
//			ss.add(prop.getProperty("p"+player+"."+s.charAt(9)));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ss;

	}

	//get trials order in the given block
	public static String[] getBlockTrials(String b){
		Properties prop = new Properties();
		InputStream input = null;
//		String[] trials = new String[54];
		String[] trials = new String[(PartInfo.usercat*3)];

		try {

			input = new FileInputStream("C:\\Users\\M0KIN\\eclipse-workspace\\yarab\\MouseSpeed\\asd\\MouseSpeed\\resources\\config.properties");

			// load a properties file
			prop.load(input);
			
			String temp = prop.getProperty(b);

			// get the property value and print it out
			//System.out.println(temp);
			trials = temp.split(" , ");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return trials;

	}
	
	//get the blocks order for this user
	public static String[] getSpeedBlocks (int p , int s){
		Properties prop = new Properties();
		InputStream input = null;
		String ss = ""; 

		try {

			input = new FileInputStream("C:\\Users\\M0KIN\\eclipse-workspace\\yarab\\MouseSpeed\\asd\\MouseSpeed\\resources\\player.properties_");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			ss = (prop.getProperty("p"+p+"."+s));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(ss);
		return ss.split(" , ");

	}
	
	 public static void main(String[] args) {

						
			System.out.println(getInfo("5.T1"));
			System.out.println(getTrial("T1"));
			
		  }
	 
}
