package com.wikia.webdriver.Trash;

public class Car 
{
	
	public int numberOfDoors; 
	public int numberOfWheels; 
	public int numberOfWindows; 
	public boolean hasSteeringWheel = true;
	
	int liczba = 5;
	String slowo = "dupa";
	boolean flaga = true;
	
	
	public void getSteeringWheelFlag()
	{
		System.out.println(hasSteeringWheel);
	}
	
	
	public void setNumberOfDoors(int i)
	{
		numberOfDoors = i;
	}
	
	public void getNumberOfDoors()
	{
		System.out.println(numberOfDoors);
	}

	public void setNumberOfWheels(int i)
	{
		numberOfDoors = i;
	}
	
	public void getNumberOfWheels()
	{
		System.out.println(numberOfDoors);
	}
	
	public void setNumberOfWindows(int i)
	{
		numberOfDoors = i;
	}
	
	public void getNumberOfWindows()
	{
		System.out.println(numberOfDoors);
	}
	
	public void driveForward()
	{
		System.out.println("My car is driving forward");
	}
	
	public void driveBackward()
	{
		System.out.println("My car is driving backward");
	}
	
	public String returnString(String slowo)
	{
		String temp = slowo + "123";
		System.out.println(temp);
		return temp;
	}
	
	public void petlaFor(String zmienna)
	{
		String temp = zmienna + "123";
		for (int i=0; i<100; i++)
		{
			System.out.println(temp);
		}
	}
	
	public void ifelse(int i)
	{
		if (i==0)
		{
			System.out.println("pierwszy if");
		}
		else if (i==1)
		{
			System.out.println("drugi if");
		}
		else
		{
			System.out.println("else");
		}
	}
	
}
