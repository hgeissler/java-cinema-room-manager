package com.company;

import java.util.Scanner;

public class Main {
    public static int rows = 0;
    public static int seats = 0;
    public static int[][] booked;
    public static int priceSum = 0;
    public static int seatCounter = 0;
    public static Scanner sc = new Scanner(System.in);

    public static void showTheSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i < seats+1; i++) {
            System.out.print(i == seats ? i : i + " ");
        }
        System.out.print("\n");
        boolean b;
        for (int i = 1; i < rows+1; i++) {
            for (int j = 0; j < seats+1; j++) {
                b = false;
                for (int[] ints : booked) {
                    if (i == ints[0] && j == ints[1]) {
                        System.out.print("B ");
                        b = true;
                        break;
                    }
                }
                if (j == 0) {
                    System.out.print(i + " ");
                } else if (!b) {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void buyATicket() {
        int sRow = 0;
        int sSeat = 0;
        boolean keepAsking = true;
        while (keepAsking) {
            System.out.println("Enter a row number:");
            sRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            sSeat = sc.nextInt();

            keepAsking = !seatExists(sRow, sSeat) || (isSeatTaken(sRow, sSeat));
        }


        booked[seatCounter][0] = sRow;
        booked[seatCounter][1] = sSeat;
        seatCounter++;

        int price = seats * rows <= 60 || sRow <= (rows / 2) ? 10 : 8;
        priceSum += price;
        System.out.printf("Ticket price: $%d%n",price);
    }

    public static boolean seatExists(int row, int seat) {
        boolean exists = (row > 0 && row <= rows) && (seat > 0 && seat <= seats);
        if (!exists) {
            System.out.println("Wrong input!");
        }
        return exists;
    }

    public static boolean isSeatTaken(int row, int seat) {
        for (int[] rows : booked) {
            if (row == rows[0] && seat == rows[1]) {
                System.out.println("That ticket has already been purchased");
                return true;
            }
        }
        return false;
    }

    public static void showStatistics() {
        System.out.printf("Number of purchased tickets: %d%n", seatCounter);
        System.out.printf("Percentage: %.2f%%%n", calcPercentage());
        System.out.printf("Current income: $%d%n", priceSum);
        System.out.printf("Total income: $%d%n", calcTotal());
    }

    public static float calcPercentage() {
        return (float)seatCounter / (seats * rows) * 100;
    }

    public static int calcTotal() {
        int sum = seats * rows;
        if ( sum <= 60) {
            return sum * 10;
        } else {
            int high = rows / 2;
            int low = rows - rows / 2;
            return high * seats * 10 + low * seats * 8;
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();
        booked = new int[rows * seats][2];

        boolean goOn = true;
        while (goOn) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            String input = sc.next();
            switch(input) {
                case "1": {
                    showTheSeats();
                    break;
                }
                case "2": {
                    buyATicket();
                    break;
                }
                case "3": {
                    showStatistics();
                    break;
                }
                case "0": {
                    goOn = false;
                    break;
                }
                default: {
                    System.out.println("No correct input. Please try again");
                    break;
                }
            }
        }
    }
}