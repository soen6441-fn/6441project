
public class Prime {
	private double Root;
	private double factor;
	//private double NumberToCheckForPrimality;
	
	public void setRoot(double Root)
	{
		this.Root = Root;
		
	}
	/*
	public void setNumberToCHeckForPrimality(double NumberToCheckForPrimality)
	{
		this.NumberToCheckForPrimality = NumberToCheckForPrimality;
		
	}*/
	public Prime()
	{
		this.Root = 0.0;
	}
	
	public double getFactor()
    {
        return this.factor;
    }
	
	public  boolean isPrime(double Prime) {
		// TODO Auto-generated method stub
		this.factor=(int)this.Root;
		//System.out.println("Tesing for primality ...");
		if(Prime%10==0||Prime%10==2||Prime%10==4||Prime%10==6||Prime%10==8||Prime%10==5) 
		    {
		        if(Prime%10==5) this.factor=5;
		        else this.factor=2;
		        return false; //this will eliminate 60% of all possibilities (all even numbers and multiples of 5)
		    }
		if(Prime%3==0)return false; // this will eliminate the need to check for multiples of 3
		while(this.factor>5)
		{
			if(Prime%this.factor==0)
					return false;
			this.factor=this.factor-2; //This will prevent from checking even numbers
		}		
		return true;
	}


}
