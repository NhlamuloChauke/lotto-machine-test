Java Test
---
You need to design a Lotto Ticketing and Payout machine (the ones you find in the shops) which:

- Select a lotto. (A lotto will have sequential balls starting from 1 to N and will have X balls to be drawn)
- Accepts R1, R2, R5 coins and R10, R50, R100 notes
- Allows the user to choose any of four different tickets:
    - Single Lotto: R5 (user chooses X numbers beween 1 and N)
    - Random Lotto: R5 (machine generates X numbers randomly beween 1 and N)
    - Quick Five: R25 (user choses 5 x X numbers between 1 and N)
    - Random Five: R25 (machine randomly generates 5 tickets, X numbers each between 1 and N)
- Allow the user to take a refund by cancelling the request.
- Display the selected product (Lotto ticket(s))
- Display the balance
- Withdraw the funds in the relevant currency denominations

The suggested functions to complete this task are given. You do not need to use them, they are just suggested functions.\
No database is needed, this can all be done in memory.\
You can write tests to emulate the use cases rather taking inputs through the terminal or API endpoints.

 ---

Bonus:\
Result the tickets and add the winnings to the tickets
- The user wins R10^x where x is the number of correct selections above 2(excl). This is done per row i.e. the random 5 is 5 rows and has a maximum winnings of R50 000

---
**SQL Test**
---
We do not need an actual database implementation for this section, we only need the SQL. If there are syntax issues we will be lenient since no database was given.

The database needs to contain:

- Who placed the lotto ticket
- What time the ticket was placed
- The amount of money on each ticket
- The numbers chosen on the ticket
- Optional: A loyalty program that could be assigned to a user. Each user can have multiple loyalty programs assigned to them. A ticket can only be assigned to one loyalty program.

We need the sql for the following:

- Select a specific ticket and all information on it including who placed it.
- Select the sum of the money spent by a user over a certain time frame.
- Optional: Select all the loyalty programs assigned to a user.
- Optional: Select the sum of the money spent by a user over a certain time frame for each loyalty program.

Please show the tables that have been created and the SQL used for the queries above. Also please give the indices that should be added to the table.