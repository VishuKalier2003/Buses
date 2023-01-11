/* You are given a 0-indexed integer array buses of length n, where buses[i] represents the departure time of the ith bus... You are also given a 0-indexed integer array passengers of length m, where passengers[j] represents the arrival time of the jth passenger... All bus departure times are unique... All passenger arrival times are unique... You are given an integer capacity, which represents the maximum number of passengers that can get on each bus... When a passenger arrives, they will wait in line for the next available bus... You can get on a bus that departs at x minutes if you arrive at y minutes where y <= x, and the bus is not full... Passengers with the earliest arrival times get on the bus first... More formally when a bus arrives, either:
* If capacity or fewer passengers are waiting for a bus, they will all get on the bus, or
* The capacity passengers with the earliest arrival times will get on the bus...
Return the latest time you may arrive at the bus station to catch a bus. You cannot arrive at the same time as another passenger... The arrays buses and passengers are not necessarily sorted...
   * Eg 1: buses = [20,30,10]  passengers = [19,13,26,4,25,11,21]    capacity = 2     Output = 20 
   * Eg 2: buses = [10,20]     passengers = [2,17,18,19]             capacity = 2     Output = 16
   * Explanation of above case -> { Suppose you arrive at time 16. At time 10, the first bus departs with the 0th passenger. At time 20, the second bus departs with you and the 1st passenger. Note that you may not arrive at the same time as another passenger, which is why you must arrive before the 1st passenger to catch the bus. }*/
import java.util.*;
public class Bus
{
    public int LastPossibleArrival(int buses[], int passengers[], int capacity)
    {
        Arrays.sort(buses);        // We sort the buses array on the basis of their departure time...
        Arrays.sort(passengers);   // We sort the passengers array on the basis of their arrival time...
        int cap = 0, j = 0;
        Queue<Integer> queue = new LinkedList<Integer>();    // Creating a Queue to check the passengers arrival time...
        for(int i = 0; i < buses.length; i++)
        {
            while(cap < capacity)    // While the current bus is not full...
            {
                if(passengers[j] < buses[i])
                {   // If passenger arrives before the departure of the current bus...
                    queue.add(passengers[j]);    // Load the passenger into the bus...
                    j++;
                }
                cap++;
            }
            if(i != buses.length - 1)   // We only store the Queue for the last bus for processing...
            {
                while(!queue.isEmpty())   // When a bus leaves empty the Queue...
                    queue.remove();
            }
            cap = 0;
        }
        int temp = 0;     // Initializing a temporary variable...
        while(!queue.isEmpty())
        {
            int x = queue.poll();      // Removing the head of the Queue and storing it...
            if(queue.peek() - x == 1)
            {   // If the two current numbers are consecutive, we store the lower number...
                temp = x;
                queue.remove();
            }
            else
            {   // Otherwise we store the higher number...
                temp = queue.peek();
                queue.remove();
            }
        }
        return temp - 1;    // Then we return the unique time for arrival...
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the number of Buses : ");
        x = sc.nextInt();
        int buses[] = new int[x];
        for(int i = 0; i < buses.length; i++)
        {
            System.out.print("Enter the Departure Time of "+(i+1)+" th Bus : ");
            buses[i] = sc.nextInt();
        }
        System.out.print("Enter the number of Passengers : ");
        x = sc.nextInt();
        int passengers[] = new int[x];
        for(int i = 0; i < passengers.length; i++)
        {
            System.out.print("Enter the Arrival Time of the "+(i+1)+" th Passenger : ");
            passengers[i] = sc.nextInt();
        }
        System.out.print("Enter the Maximum Capacity of Each Bus : ");
        x = sc.nextInt();
        Bus bus = new Bus();     // Object creation...
        System.out.println("The Last Arrival Time to Catch a Bus : "+bus.LastPossibleArrival(buses, passengers, x));
        sc.close();
    }
}

// Time Complexity  - O(n log n) time... 
// Space Complexity - O(k) space...      k = Capacity of bus...

/* DEDUCTIONS :- 
 * 1. We will use the Passenger array as a Queue to iterate it over the buses array...
 * 2. A Bus will depart only when its arrival time is reached, even if full or empty...
 * 3. If a bus is full, then no extra passengers will be entertained...
*/