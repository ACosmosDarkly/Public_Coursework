# Public_Coursework
A selection of projects I have worked on in various college courses. They aren't perfect by any means, since they all were completed in a week or so if I recall correctly.

## Pig
Pig is a jeopardy dice game in which the players take turns rolling a die and accumulating points, but their points are lost if they roll a one. The turn is also over if a player decides to “hold” in which case they save all their points. I wrote this game as part of CNM's Intermediate Programming course. In the development of the game I wanted to track the player score and moves in real time. I managed to get this much done in the week that I had to do it.

## Networking
This is a network assignment from the same course as Pig. The purpose is to use previous concepts of networking and multithreading to create a program that will: 
1. Establish a connection between the Server and the Client
2. Send a file containing two number matricies from the client to the server
3. Sum the matricies by breaking them into quadrants (1-4), and ,using multithreadding, concurrently sum the quadrants from each matrix.
4. Return the matrix summation back to the client.
The program uses a GUI to extract the file name. If you choose to try to run it, there is a line in "Client.java" that you may have to modify the file path. The line has a comment next to it indicating what needs to be changed.

## Divide and Conquer
This one is from the NYU CS Bridge Program that I was particularly proud of because I managed to write it in an hour with very little outside help (i.e. Google). It only took an hour because once I finished I realized I had used dynamic arrays and not vectors like the instructions indicated.


## Numbers and Calendars
This folder contains a few selections from the NYU Bridge Program
Perfect_and_Amicable - Finds perfect and amicable numbers between 2 and the user entered integers. We were supposed to try implement this to run in $\Theta \(\sqrt{n}\)$ time.

Print_A_Calendar - Prints a calendar for the given year and first day of the year. Accounts for leap years.

Approximate_e - Approximates the value of e by calculating the partial sum of the infinite sum $e = \sum\nolimits_{n=0}^{\infty} \frac{1}{n!}$. A practice in getting our function to run with a specific time efficiency, this one should be $\Theta \(n\)$ for the eApprox function.

Print_Divisors - Prints the divisors for a given number. Similar to above, this one is supposed to run in $\Theta \(\sqrt{n}\)$ time.

I have many other programs from these courses, but I thought that these were probably the best out of what I had. That's why I chose to share these, and hopefully I can add more to it as I get better.
