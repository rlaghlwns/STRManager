package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RepMaxCalc {
	private double lomonerm, brzonerm, eplonerm, mayonerm, ocoonerm, watonerm, lanonerm, avgonerm;
    //for all reps > 1 calculate the 1RMs    
	private void calculateOneRM(double weight, double reps) {
	    lomonerm = Math.round(weight * Math.pow(reps, 0.10));
	    brzonerm = Math.round(weight * (36 / (37 - reps)));
	    eplonerm = Math.round(weight * (1 + (reps / 30)));
	    mayonerm = Math.round((weight * 100) / (52.2 + (41.9 * Math.exp(-1 * (reps * 0.055)))));
	    ocoonerm = Math.round(weight * (1 + reps * 0.025));
	    watonerm = Math.round((weight * 100) / (48.8 + (53.8 * Math.exp(-1 * (reps * 0.075)))));
	    lanonerm = Math.round(weight * 100 / (101.3 - 2.67123 * reps));
	    avgonerm = Math.round((lomonerm + brzonerm + eplonerm + mayonerm + ocoonerm + watonerm + lanonerm) / 7);
    }
	private double lomrm, brzrm, eplrm, mayrm, ocorm, watrm, lanrm, avgrm;
    // calculate RMs 2-10 and append it to the table
    private void calculateRMs(double i) {
    	lomrm = Math.round(lomonerm / (Math.pow(i, 0.1)));
    	brzrm = Math.round((brzonerm * (37 - i)) / 36);
    	eplrm = Math.round(eplonerm / ((1 + (i / 30))));
    	mayrm = Math.round((mayonerm * (52.2 + (41.9 * Math.exp(-1 * (i * 0.055))))) / 100);
    	ocorm = Math.round((ocoonerm / (1 + i * 0.025)));
    	watrm = Math.round((watonerm * (48.8 + (53.8 * Math.exp(-1 * (i * 0.075))))) / 100);
    	lanrm = Math.round(((lanonerm * (101.3 - 2.67123 * i)) / 100));
    	avgrm = Math.round((lomrm + brzrm + eplrm + mayrm + ocorm + watrm + lanrm) / 7);
    }
    public ArrayList<String[]> rmCalc(double weight, double reps) {
    	calculateOneRM(weight, reps);
    	DecimalFormat df = new DecimalFormat("0");
    	String[] info = {"RM","Average","Lombardi","Brzycki","Epley","Mayhew","O'Conner","Wathan","Lander"};
    	String[] oneRM = {"1RM", df.format(avgonerm), df.format(lomonerm), df.format(brzonerm), df.format(eplonerm), 
    			df.format(mayonerm), df.format(ocoonerm), df.format(watonerm), df.format(lanonerm)};
    	ArrayList<String[]> rm = new ArrayList<String[]>();
    	rm.add(info);    	rm.add(oneRM);
    	for(int i = 2 ; i <= 10 ; i++) {
    		calculateRMs(i);
    		String[] tmp = {i+"RM", df.format(avgrm), df.format(lomrm), df.format(brzrm), df.format(eplrm), 
    				df.format(mayrm), df.format(ocorm), df.format(watrm), df.format(lanrm)};
    		rm.add(tmp);
    	}
    	return rm;
    }
	public double Mround(double weight, double customUnit) {		// MROUND of Excel
		double unitsDigit = weight;
		while(unitsDigit > 10) {unitsDigit %= 10;}
		double remainder = unitsDigit % customUnit;
		double customNum = unitsDigit - remainder;
		double round = 0;
		if(remainder>customUnit / 2) {round = customUnit;}
		else {round = 0;}
		return weight - unitsDigit + customNum + round;
	}
}
