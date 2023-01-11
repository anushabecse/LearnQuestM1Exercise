package lumaapplication;

import org.testng.annotations.Test;

public class GenerateRandomString {
	
	@Test
	
	public String getAlpahanumericString(int n)
	
	{
		String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb =new StringBuilder(n);
		for(int i =0;i<n;i++)
		{
			int index
		    = (int)(randomString.length()
		      * Math.random());
			sb.append(randomString.charAt(index));
		}
		return sb.toString();
		
	}
	
	@Test
	
	public String generateNumbers(int n)
	{
		String randomnum = "0123456789";
		         
		StringBuilder sb =new StringBuilder(n);
		for(int i =0;i<n;i++)
		{
			int index
		    = (int)(randomnum.length()
		      * Math.random());
			sb.append(randomnum.charAt(index));
		}
		return sb.toString();
		
		
	}

}
