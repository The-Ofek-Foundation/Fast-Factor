# Fast-Factor
This program prime factorieses or tells you if a number is prime in a much faster method than any other program.
This uses a database of primes and simply checks if your number is divisable by the primes.
Sadly, this program does have then have a limit, but it's pretty darn large AND you can always expand the database with one of the programs.

# Usage: PrimeFactorization
- compile all java files (javac *.java)
- run: (java PrimeFactorization load database)
- input format: either plain integer, scientific notation (5E6) or scientific notation plus one (5E6+)
- Depending on the size of the database, wait between 5 to 15 seconds
- Now you can instantly prime factories as many numbers as you like
- It is formatted like following, 120 would be: 2^3 * 3 * 5

# Usage: PrimeDetector
- compile all java files (javac *.java)
- For only one number: run (java PrimeDetector [number])
- For multiple numbers: run (java PrimeDetector load database)
- If multiple numbers: format either plain integer, scientific notation (5E6) or scientific notation plus one (5E6+)
- Output: if prime, it tells you it's prime, if it isn't, it tells you it isn't and outputs its smallest factor

# Expanding the Database
- compile all java files (javac *.java)
- If you try telling if a number is prime and it is too large, it will give you an option to automatically expand it
- run: (java ListOfPrimes [how many seconds you are willing to allocate])
- WARNING: While ListOfPrimes is running, DO NOT run another of the programs or make any modifications to Primes.txt because you might lose all your data! List of Primes automatically saves periodically so don't feel bad about force quiting it if you want to use Primes.txt

