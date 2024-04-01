package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ReadOut {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		ArrayList<Double>[] speed = new ArrayList[5];
		for (int i = 0; i < speed.length; i++) {
			speed[i] = new ArrayList<Double>();
		}
		File file = new File("./Mouse/4/12-4_log.txt");
		 try {
			    FileReader reader = new FileReader(file);
			    BufferedReader br = new BufferedReader(reader);
			    String s;
			    double time = 0;
			    int c1=0;int c2=0;int c3=0;int c4=0;int c5=0; int c6=0;
			    double s1=0;double s2=0;double s3=0;double s4=0;double s5=0; double s6=0;
			    while((s = br.readLine()) != null){
			        if(s.charAt(0)=='S'||s.charAt(0)=='M'||s.charAt(0)=='L'){
			        	StringTokenizer st = new StringTokenizer(s);
			        	st.nextToken();
			        	time+= (double) Double.parseDouble(st.nextToken().substring(2));
			        	char ch = st.nextToken().charAt(0);
			        	if(ch=='1'){
			        		c1++;
			        		double temp = (double) Double.parseDouble(st.nextToken().substring(2));
			        		s1 += temp;
			        		speed[0].add(temp);
			        	}if(ch=='2'){
			        		c2++;
			        		double temp = (double) Double.parseDouble(st.nextToken().substring(2));
			        		s2 += temp;
			        		speed[1].add(temp);
			        	}if(ch=='3'){
			        		c3++;
			        		double temp = (double) Double.parseDouble(st.nextToken().substring(2));
			        		s3 += temp;
			        		speed[2].add(temp);
			        	}if(ch=='4'){
			        		c4++;
			        		double temp = (double) Double.parseDouble(st.nextToken().substring(2));
			        		s4 += temp;
			        		speed[3].add(temp);
			        	}if(ch=='5'){
			        		c5++;
			        		double temp = (double) Double.parseDouble(st.nextToken().substring(2));
			        		s5 += temp;
			        		speed[4].add(temp);
			        	}
			        	if(ch=='6'){
			        		c6++;
			        		double temp = (double) Double.parseDouble(st.nextToken().substring(2));
			        		s6 += temp;
			        		speed[5].add(temp);
			        	}
			        
			        }
			    }

			    ArrayList<Double> Avg_speed = new ArrayList<Double>();
				for (int x = 0; x < speed.length; x++) {
					double sum1 = 0.0;
				    for(int i=0; i<speed[x].size(); i++){
				        sum1 = sum1 + speed[x].get(i);
				    }
				    Avg_speed.add(Math.round(sum1/speed[x].size()*100000.0)/100000.0);
				}
				
	        	System.out.println("Total time: "+time+" Avrg: "+time/(c1+c2+c3+c4+c5+c6));
	        	System.out.println("Speed 1: "+c1 + " " + s1+" Avrg: "+(s1/c1)+"\t Inv: "+(c1/s1));
	        	System.out.println("Speed 2: "+c2 + " " + s2+" Avrg: "+(s2/c2)+"\t Inv: "+(c2/s2));
	        	System.out.println("Speed 3: "+c3 + " " + s3+" Avrg: "+(s3/c3)+"\t Inv: "+(c3/s3));
	        	System.out.println("Speed 4: "+c4 + " " + s4+" Avrg: "+(s4/c4)+"\t Inv: "+(c4/s4));
	        	System.out.println("Speed 5: "+c5 + " " + s5+" Avrg: "+(s5/c5)+"\t Inv: "+(c5/s5));
	        	System.out.println("Speed 6: "+c6 + " " + s6+" Avrg: "+(s6/c6)+"\t Inv: "+(c6/s6));
	        	System.out.print(Avg_speed);
			 }
			  catch(IOException e){
			    System.exit(0);
			}
	}

}
