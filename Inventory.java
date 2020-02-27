package juegoMUD;

import java.util.*;

class Inventory
{
	private ArrayList<Object> objects;

	public Inventory()
	{
		this.objects = new ArrayList<Object>();
	}

	//Constructor sobrecargado
	public Inventory(ArrayList<Object> objects)
	{
		this.objects = objects;
	}

	public void addObject(Object newObject)
	{
		objects.add(newObject);
	}

	public void removeObject(int position)
	{
		objects.remove(position);
	}

	public void getInventory()
	{
		for(int i = 0; i < objects.size(); i++)
		{
			System.out.println(objects.get(i).getName());
		}
	}

	public boolean isObjectIn(Object object)
	{
		for(int i = 0; i < objects.size(); i++)
		{
			if(objects.get(i).equals(object))
			{
				return true;
			}
		}

		return false;
	}
}