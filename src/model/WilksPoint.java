package model;

import java.text.DecimalFormat;

public class WilksPoint {
	private double maleA = -216.0475144;
	private double maleB = 16.2606339;
	private double maleC = -0.002388645;
	private double maleD = -0.00113732;
	private double maleE = 7.01863E-06;
	private double maleF = -1.291E-08;
	private double femaleA = 594.31747775582;
	private double femaleB = -27.23842536447;
	private double femaleC = 0.82112226871;
	private double femaleD = -0.00930733913;
	private double femaleE = 4.731582E-05;
	private double femaleF = -9.054E-08;
	public double Coeff(Member member){
		DecimalFormat df = new DecimalFormat("0.##");
		double x = member.getWeight();
		double total = member.getBp1rm() + member.getDl1rm() + member.getSq1rm();
		if(member.getGender().equals("male")) {
			String tmp = df.format(
				total / (maleA + (maleB*x) + (maleC*x*x) + (maleD*x*x*x) + 
						(maleE*x*x*x*x) + (maleF*x*x*x*x*x))/2*1000
			);
			double result = Double.parseDouble(tmp);
			return result;
		}
		else {
			String tmp = df.format(
				total / (femaleA + (femaleB*x) + (femaleC*x*x) + (femaleD*x*x*x) +
						(femaleE*x*x*x*x) + (femaleF*x*x*x*x*x))/2*1000
			);
			double result = Double.parseDouble(tmp);
			return result;
		}
	}
}
