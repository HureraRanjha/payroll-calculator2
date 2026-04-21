package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter the name of the file employee file to process: ");

        String filePath = "src/main/resources/";
        String startingFileName = filePath + myScanner.nextLine();
        //startingFileName = filePath + startingFileName;

        System.out.print("Enter the name of the payroll file to create: ");
        String fileDestination = filePath + myScanner.nextLine();



        Employee[] employees = new Employee[8];
        try {
            FileReader fileReader = new FileReader(startingFileName);
            BufferedReader bufReader = new BufferedReader(fileReader);
            int index = 0;
            String fileLine;
            boolean skipFirstRead = true;
            while((fileLine = bufReader.readLine()) != null)
            {
                if(skipFirstRead)
                {
                    skipFirstRead = false;
                    continue;
                }
                String[] splittedValue = fileLine.split("\\|");

                int id = Integer.parseInt(splittedValue[0]);
                String name = splittedValue[1];
                double hoursWorked = Double.parseDouble(splittedValue[2]);
                double payRate = Double.parseDouble(splittedValue[3]);

                employees[index] = new Employee(id, name, hoursWorked, payRate);
                index++;
            }
            bufReader.close();

            for (Employee emp: employees)
            {
                System.out.printf("Employee ID: %d  Name: %s  GrossPay: %.2f \n", emp.getEmployeeId(), emp.getName(), emp.getGrossPay());
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File NOT FOUND");
        }
        catch(IOException e)
        {
            System.err.println("IO FUNCTION BAD");
        }

        try
        {
            FileWriter writer = new FileWriter(fileDestination);
            BufferedWriter bufWriter = new BufferedWriter(writer);

            String text;
            bufWriter.write("id|name|gross pay\n");

            for (Employee emp: employees)
            {
                text = String.format("%d|%s|%.2f\n", emp.getEmployeeId(), emp.getName(), emp.getGrossPay());
                bufWriter.write(text);
            }
            bufWriter.close();
        }
        catch(IOException e)
        {
            System.err.println("Something went wrong with Writing to the file destination" + fileDestination);
        }
    }
}
