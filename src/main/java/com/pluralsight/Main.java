package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner myScanner = new Scanner(System.in);
        String filePath = "src/main/resources/";
        String fileName = myScanner.nextLine();
        filePath += fileName;

        Employee[] employees = new Employee[8];
        try {
            FileReader fileReader = new FileReader(filePath);
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
    }
}
