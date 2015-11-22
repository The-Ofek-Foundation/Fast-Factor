/*	Ofek Gila
	August 7th, 2014
	PrimeFactorization.java
	This program finds the prime factors of given number.
*/
import java.math.*;
import java.util.Scanner;
import java.io.*;

public class PrimeFactorization	{
	BigInteger number;
	BigInteger original;
	BigInteger count;
	BigInteger end;
	BigInteger one = new BigInteger("1");
	PrimeFactorization(String n)	{
		number = new BigInteger(n);
		original = new BigInteger(n);
	}
	PrimeFactorization()	{

	}
	public static void main(String[] pumpkins) {
		if (pumpkins[0].equalsIgnoreCase("Load"))	{
			if (pumpkins[1].equalsIgnoreCase("Database"))	{
				PrimeFactorization PF = new PrimeFactorization();
				PF.loadDatabase();
				System.out.println("Database Loaded");
				PF.getUserInput();
			}
		}
		else {
			PrimeFactorization PF = new PrimeFactorization(pumpkins[0]);
			PF.load();
		}
	}
	void getUserInput()	{
		while (true)	{
			System.out.print("Enter a number:\t");
			run(new Scanner(System.in).nextLine());
		}
	}
	void run(String str)	{
		if (str.contains("E"))	number = new BigInteger(sci2str(str));
		else number = new BigInteger(str);
		int i = 0;
		int power = 1;
		StringBuilder previous = new StringBuilder("1");
		boolean firstp = true;
		boolean init = true;

		while (!(number.equals(one)))	{
			try {
				getEnd();
				if (number.remainder(new BigInteger(primes[i].toString())).doubleValue() == 0)	{
					if (primes[i].equals(previous))	{
						if (firstp)	{
							firstp = false;
							System.out.print("^");
						}
						power++;
					}
					else if (power > 1)	{
						firstp = true;
						System.out.print(power + " * " + primes[i]);
						previous = primes[i];
						power = 1;
					}
					else {
						if (init && primes[i].equals(number.toString()))	{
							System.out.print(primes[i] + " is a prime number!");
							break;
						}
						if (init)	System.out.print(primes[i]);
						else 		System.out.print(" * " + primes[i]);
						init = false;
						previous = primes[i];
						power = 1;
					}
					number = number.divide(new BigInteger(primes[i].toString()));
				}
				else if (end.compareTo(new BigInteger(primes[i].toString())) != 1) {
					power = 0;
					System.out.print(" * " + number);
					number = number.divide(number);
					break;
				}
				else i++;
			}	catch (java.lang.ArrayIndexOutOfBoundsException e)	{
				System.out.println("\nPrime Database not vast enough to confirm.");
				System.out.println("Database is at:\t\t" + primes[i-1].toString());
				System.out.println("Database required:\t" + number.toString());
				System.out.println("ETA:\t\t\t" + getTimeString(number.subtract(new BigInteger(primes[i-1].toString())).divide(new BigInteger("133")).add(new BigInteger("7"))));
				System.out.print("Expand Database?\t");
				if (new Scanner(System.in).nextLine().equalsIgnoreCase("yes"))	{
					ListOfPrimes LOP = new ListOfPrimes(number);
					LOP.run();
					loadDatabase();
					run(str);
				}
				break;
			}
		}
		if (power > 1)	System.out.print(power);
		System.out.println();
	}
	boolean isPrime()	{
		getEnd();
		int i;
		for (i = 0; i < primes.length; i++)	{
			if (number.remainder(new BigInteger(primes[i].toString())).doubleValue() == 0)
				break;
			else if (end.compareTo(new BigInteger(primes[i].toString())) != 1)
				return true;
		}
		return false;
	}
	int NOP;

	void run()	{
		count = new BigInteger("2");
		getEnd();
		while (number.subtract(count).doubleValue() >= -2 && !(end.subtract(count).doubleValue() <= 0 && number.equals(original)))	{
			if (number.remainder(count).doubleValue() == 0)	{
				System.out.println(count.toString());
				number = number.divide(count);
			}
			else count = count.add(one);
		}
		if (number.equals(original))	System.out.println(number.toString() + " is a prime number!");
	}
	void getEnd()	{
		end = BigRoot.sqrt(new BigDecimal(number), 40).toBigInteger();
	}
	Scanner input;
	void load()	{
		try {
			input = new Scanner(new File("Primes.txt"));
		}
		catch (FileNotFoundException e)	{
			System.err.println("ERROR: Cannot open file Primes.txt");
			System.exit(97);
		}
		getEnd();
		String str = "";
		int prime = 0;
		BigInteger two = new BigInteger("2");
		input.nextLine();
		while (input.hasNextLong())	{
			str = input.nextLong() + "";
			while (number.remainder(new BigInteger(str)).doubleValue() == 0)	{
				prime = 1;
				System.out.println(str);
				number = number.divide(new BigInteger(str));
			}
			//end = number.divide(new BigInteger(str)).add(two);
			if (end.compareTo(new BigInteger(str)) != 1) {
				prime = 2;
				break;
			}
		}
		if (prime == 0)	{
			System.out.println("Prime Database not vast enough to confirm.");
			System.out.println("Database is at:\t\t" + new BigInteger(str).toString());
			System.out.println("Database required:\t" + end.toString());
			System.out.println("ETA:\t\t\t" + getTimeString(end.subtract(new BigInteger(str)).divide(new BigInteger("155")).add(new BigInteger("25"))));
			System.out.print("Expand Database?\t");
			if (new Scanner(System.in).nextLine().equalsIgnoreCase("yes"))	{
				ListOfPrimes LOP = new ListOfPrimes(end);
				LOP.run();
			}
		}
		else {
			System.out.println(number.toString() + " is a prime number!");
		}
		input.close();
	}
	void loadDatabase()	{
		try {
			input = new Scanner(new File("Primes.txt"));
		}
		catch (FileNotFoundException e)	{
			System.err.println("ERROR: Cannot open file Primes.txt");
			System.exit(97);
		}
		StringBuilder str = null;
		int count = 0;
		BigInteger nop = new BigInteger(input.nextLine());
		NOP = nop.intValue();
		primes = new StringBuilder[NOP];
		while (input.hasNext())	{
			str = new StringBuilder(input.nextLong() + "");
			str.trimToSize();
			primes[count] = str;
			count++;
		}
		input.close();
	}
	StringBuilder[] primes;
	String getTimeString(BigInteger timeInSeconds)	{
		BigInteger sixty = new BigInteger("60");
		int seconds = timeInSeconds.remainder(sixty).intValue();
		timeInSeconds = timeInSeconds.divide(sixty);
		int minutes = timeInSeconds.remainder(sixty).intValue();
		timeInSeconds = timeInSeconds.divide(sixty);
		int hours   = timeInSeconds.remainder(new BigInteger("24")).intValue();
		int days = timeInSeconds.divide(new BigInteger("24")).intValue();
		String time;
		time = "" + days + ":";
		if (hours <= 9)		time += "0";
		time += hours + ":";
		if (minutes <= 9)	time += "0";
		time += minutes + ":";
		if (seconds <= 9)	time += "0";
		time += seconds;
		return time;
	}
	String sci2str(String sci)	{
		boolean addOne = false;
		if (sci.charAt(sci.length()-1) == '+')	{
			addOne = true;
			String str = "";
			for (int i = 0; i < sci.length() - 1; i++)
				str += sci.charAt(i);
			sci = str;
		}
		int power = Integer.parseInt(sci.substring(sci.indexOf('E') + 1));
		String number = sci.substring(0, sci.indexOf('E'));
		for (int i = 0; i < power; i++)	{
			if (number.contains("."))	{
				String newnum = "";
				boolean dot = false;
				for (int a = 0; a < number.length(); a++)	{
					if (number.charAt(a) == '.')	{
						dot = true;
						newnum += number.charAt(a + 1);
					}
					else if (dot) {
						dot = false;
						if (!(a == number.length() - 1))	newnum += '.';
					}
					else {
						newnum += number.charAt(a);
					}
				}
				number = newnum;
			}
			else {
				number += "0";
			}
		}
		if (addOne)	{
			number = new BigInteger(number).add(new BigInteger("1")).toString();
		}
		return number;
	}
}
