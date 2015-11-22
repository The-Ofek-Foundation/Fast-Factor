/*	Ofek Gila
	August 7th, 2014
	ListOfPrimes.java
	This program finds prime numbers.
*/
import java.math.*;
import java.io.*;
import java.util.Scanner;

public class ListOfPrimes	{
	public static void main(String[] pumpkins) {
		ListOfPrimes LOP = new ListOfPrimes();
		double st = System.nanoTime();
		LOP.run(Double.parseDouble(pumpkins[0]));
		System.out.println("Time Elapsed\t" + (System.nanoTime() - st) / 1E9);
	}
	ListOfPrimes(BigInteger endAt)	{
		end = endAt;
	}
	ListOfPrimes()	{

	}
	void getEnd()	{
		end = BigRoot.sqrt(new BigDecimal(count), 1).toBigInteger();
	}
	BigInteger end;
	int c = 0;
	void run(double time)	{
		load();
		startTime = System.nanoTime();
		boolean prime;
		boolean np = false;
		c = NOP;
		while ((System.nanoTime() - startTime)/1E9 < time)	{
			if ((NOP-c) % 500 == 0 && np)	{
				save();
				np = false;
			}
			//c++;
			count = count.add(two);
			prime = true;
			getEnd();
			for (int i = 0; end.compareTo(new BigInteger(primes[i].toString())) == 1; i++)
				if (count.remainder(new BigInteger(primes[i].toString())).doubleValue() == 0)	{
					prime = false;
					break;
				}
			if (prime)	{
				np = true;
				NOP++;
				//System.out.println(count.toString());
				primesCopy = new StringBuilder[primesCopy.length + 1];
				for (int i = 0; i < primesCopy.length-1; i++)
					primesCopy[i] = primes[i];
				primesCopy[primesCopy.length-1] = new StringBuilder(count.toString());
				primes = new StringBuilder[primesCopy.length];
				for (int i = 0; i < primesCopy.length; i++)
					primes[i] = primesCopy[i];
			}
		}
		//System.out.println(c);
		save();
	}
	void run()	{
		load();
		boolean prime;
		while (true)	{
			//System.out.println(end.subtract(count).toString());
			count = count.add(one);
			prime = true;
			for (int i = 0; i < primes.length; i++)
				if (count.remainder(new BigInteger(primes[i].toString())).doubleValue() == 0)	{
					prime = false;
					break;
				}
			if (prime)	{
				//System.out.println(count.toString());
				NOP++;
				primesCopy = new StringBuilder[primesCopy.length + 1];
				for (int i = 0; i < primesCopy.length-1; i++)
					primesCopy[i] = primes[i];
				primesCopy[primesCopy.length-1] = new StringBuilder(count.toString());
				primes = new StringBuilder[primesCopy.length];
				for (int i = 0; i < primesCopy.length; i++)
					primes[i] = primesCopy[i];
				if (end.subtract(count).doubleValue() <= 0)	break;
			}
		}
		save();
	}
	double startTime, timePassed;
	String file;
	String addOn;
	Scanner input;
	BigInteger count;
	BigInteger one = new BigInteger("1");
	BigInteger two = new BigInteger("2");
	StringBuilder[] primes, primesCopy;
	BigInteger nop;
	int NOP;
	void load()	{
		try {
			input = new Scanner(new File("Primes.txt"));
		}
		catch (FileNotFoundException e)	{
			System.err.println("ERROR: Cannot open file Primes.txt");
			System.exit(97);
		}
		StringBuilder str = null;
		
		int count = 0;
		nop = new BigInteger(input.nextLine());
		NOP = nop.intValue();
		primes = new StringBuilder[NOP];
		primesCopy = new StringBuilder[NOP];
		while (input.hasNextLong())	{
			str = new StringBuilder(input.nextLong() + "");
			str.trimToSize();
			primes[count] = str;
			count++;
			//primesCopy = new BigInteger[count];
			//for (int i = 0; i < count-1; i++)
				//primesCopy[i] = primes[i];
			//primesCopy[count-1] = new BigInteger(str);
			//primes = new BigInteger[count];
			//for (int i = 0; i < count; i++)
			//	primes[i] = primesCopy[i];
		}
		System.out.println("Loaded");
		this.count = new BigInteger(str.toString());
		input.close();
	}
	PrintWriter output;
	void save()	{
		try {
			output = new PrintWriter(new File("Primes.txt"));
		}
		catch (IOException e)	{
			System.err.println("ERROR: Cannot open file Mars2Save.txt");
			System.exit(99);
		}
		output.println(NOP);
		for (int i = 0; i < primes.length; i++)
			output.print(primes[i] + " ");
		output.close();
	}
}
