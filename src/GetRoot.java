
public class GetRoot {
	private double InitialValue;
	private int Iterations;
	
	public void setInitialValue(double InitialValue)
	{
		this.InitialValue = InitialValue;
		
	}
	
	public double getInitialValue()
	{
		return this.InitialValue;
		
	}
	public int getIterations()
	{
		return this.Iterations;
	}
	
	public GetRoot()
	{
		this.InitialValue = 0;
		this.Iterations = 0;
	}
	
	public double Calculate(double prime, double n) {
		double initial;
		
		if(prime < 1000)
		{
			initial=1;
		}
		else if((prime > 10000) && (prime < 1000000))
		{
			initial=100;
		}
		else 
		{
			initial=1000;
		}
		
		//System.out.println("Calculating "+ (int)n + "th Root of " + prime);
	   
		while((Math.abs(Math.pow( initial, n )-prime))>0.000000009) // to find the root with precision of 8 decimal places
		{
			initial=(1/n)*((n-1)*initial+(prime/Math.pow( initial, n-1 )));
			this.Iterations++;
		}
		
		return initial;
	}
}
