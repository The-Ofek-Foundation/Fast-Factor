/*	Ofek Gila
	August 7th, 2014
	PrimeDetector.java
	This program detects if your number is prime using my own prime number database to provide speed
*/
import java.util.Scanner;
import java.io.*;
import java.math.*;

public class PrimeDetector	{
	BigInteger number;
	PrimeDetector(String n)	{
		number = new BigInteger(n);
	}
	PrimeDetector()	{
	}
	PrimeDetector(boolean b)	{
		loadDatabase();
	}
	public static void main(String[] pumpkins) {
		if (pumpkins[0].equalsIgnoreCase("Load"))	{
			if (pumpkins[1].equalsIgnoreCase("Database"))	{
				PrimeDetector PD = new PrimeDetector();
				PD.loadDatabase();
				System.out.println("Database Loaded");
				PD.getUserInput();
			}
		}
		else {
			PrimeDetector PD = new PrimeDetector(pumpkins[0]);
			PD.run();
		}
	}
	boolean isPrime(String str)	{
		if (str.contains("E"))	number = new BigInteger(sci2str(str));
		else number = new BigInteger(str);
		getEnd();
		int prime = 0;
		int i;
		for (i = 0; i < primes.length; i++)
			if (number.remainder(new BigInteger(primes[i].toString())).doubleValue() == 0)	{
				prime = 1;
				break;
			}
			else if (end.compareTo(new BigInteger(primes[i].toString())) != 1)	{
				prime = 2;
				break;
			}
		if (prime == 1)	return false;
		else if (prime == 0)	return false;
		else return true;
	}
	void run()	{
		getEnd();
		load();
		/*boolean prime = true;
		for (int i = 0; i < primes.length; i++)
			if (number.remainder(primes[i]).doubleValue() == 0)	{
				prime = false;
				break;
			}
		if (!prime)	{
			System.out.println(number.toString() + " is not a prime number!");
		}
		else if (primes[primes.length - 1].subtract(end).doubleValue() < 0)	{
			System.out.println("Prime Database not vast enough to confirm.");
			System.out.println("Database is at:\t\t" + primes[primes.length-1].toString());
			System.out.println("Database required:\t" + end.toString());
			System.out.print("Expand Database?\t");
			if (new Scanner(System.in).nextLine().equalsIgnoreCase("yes"))	{
				ListOfPrimes LOP = new ListOfPrimes(end);
				LOP.run();
			}
		}
		else {
			System.out.println(number.toString() + " is a prime number!");
		}*/
	}
	void run(String str)	{
		double startTime = System.nanoTime();
		if (str.contains("E"))	number = new BigInteger(sci2str(str));
		else number = new BigInteger(str);
		getEnd();
		int prime = 0;
		int i;
		for (i = 0; i < primes.length; i++)
			if (number.remainder(new BigInteger(primes[i].toString())).doubleValue() == 0)	{
				prime = 1;
				break;
			}
			else if (end.subtract(new BigInteger(primes[i].toString())).doubleValue() <= 0)	{
				prime = 2;
				break;
			}
		if (prime == 1)	System.out.printf("%s is not a prime number! (%s) in %.3f seconds\n", number.toString(), primes[i].toString(), (System.nanoTime() - startTime) / 1E9);
		else if (prime == 0)	{
			System.out.println("Prime Database not vast enough to confirm.");
			System.out.println("Database is at:\t\t" + primes[i-1].toString());
			System.out.println("Database required:\t" + end.toString());
			System.out.println("ETA:\t\t\t" + getTimeString(end.subtract(new BigInteger(primes[i-1].toString())).divide(new BigInteger("133")).add(new BigInteger("7"))));
			System.out.print("Expand Database?\t");
			if (new Scanner(System.in).nextLine().equalsIgnoreCase("yes"))	{
				ListOfPrimes LOP = new ListOfPrimes(end);
				LOP.run();
				loadDatabase();
				run(str);
			}
		}
		else	System.out.printf("%s is a prime number! in %.3f seconds\n", number.toString(), (System.nanoTime() - startTime) / 1E9);
	}
	void getUserInput()	{
		while (true)	{
			System.out.print("Enter a number:\t");
			run(new Scanner(System.in).nextLine());
		}
	}
	void getEnd()	{
		end = BigRoot.sqrt(new BigDecimal(number), 40).toBigInteger();
	}
	StringBuilder[] primes;
	StringBuilder[] primesCopy;
	BigInteger end;
	Scanner input;
	BigInteger two = new BigInteger("2");
	void load()	{
		double startTime = System.nanoTime();
		try {
			input = new Scanner(new File("Primes.txt"));
		}
		catch (FileNotFoundException e)	{
			System.err.println("ERROR: Cannot open file Primes.txt");
			System.exit(97);
		}
		String str = "";
		int prime = 0;
		input.nextLine();
		while (input.hasNextLong())	{
			str = input.nextLong() + "";
			if (number.remainder(new BigInteger(str)).doubleValue() == 0)	{
				prime = 1;
				break;
			}
			//end = number.divide(new BigInteger(str)).add(two);
			if (end.compareTo(new BigInteger(str)) != 1) {
				prime = 2;
				break;
			}
		}
		if (prime == 1)	System.out.printf("%s is not a prime number! (%s) in %.3f seconds\n", number.toString(), str, (System.nanoTime() - startTime) / 1E9);
		else if (prime == 0)	{
			System.out.println("Prime Database not vast enough to confirm.");
			System.out.println("Database is at:\t\t" + new BigInteger(str).toString());
			System.out.println("Database required:\t" + end.toString());
			System.out.println("ETA:\t\t\t" + getTimeString(end.subtract(new BigInteger(str)).divide(new BigInteger("133")).add(new BigInteger("7"))));
			System.out.print("Expand Database?\t");
			if (new Scanner(System.in).nextLine().equalsIgnoreCase("yes"))	{
				ListOfPrimes LOP = new ListOfPrimes(end);
				LOP.run();
			}
		}
		else	System.out.printf("%s is a prime number! in %.3f seconds\n", number.toString(), (System.nanoTime() - startTime) / 1E9);
		input.close();
	}
	int NOP;
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
		primesCopy = new StringBuilder[NOP];
		while (input.hasNextLong())	{
			str = new StringBuilder(input.nextLong() + "");
			str.trimToSize();
			primes[count] = str;
			count++;
		}
		System.out.printf("Prime Certainty Limit:\t%,d\n", (new BigInteger(str.toString()).multiply(new BigInteger(str.toString()))));
		input.close();
	}
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
		if (addOne)	number = new BigInteger(number).add(new BigInteger("1")).toString();
		return number;
	}
}
