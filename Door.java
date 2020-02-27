package juegoMUD;

import java.util.*;

class Door
{
	private String openDescription;
	private boolean isClosed;
	private boolean keyNeeded;
	private boolean canBreak;
	private Inventory inventory;
	private String[] availableVerbs;
	private String[] principalVerbs;
	private Object neededKey;

	public Door(String openDescription, boolean isClosed, boolean canBreak, Inventory inventory, boolean keyNeeded, String[] principalVerbs, Object neededKey)
	{
		this.openDescription = openDescription;
		this.isClosed = isClosed;
		this.canBreak = canBreak;
		this.inventory = inventory;
		this.keyNeeded = keyNeeded;
		//this.availableVerbs = {"mirar", "mira", "abrir", "abre", "romper", "rompe"};
		this.principalVerbs = principalVerbs;
		this.neededKey = neededKey;
	}

	public boolean cross()
	{
		if(isClosed == true) //Si la puerta está cerrada
		{
			if(keyNeeded == true) //Si se necesita una llave para abrir la puerta, comprobamos si el jugador la tiene en el inventario
			{
				if(inventory.isObjectIn(this.neededKey))
				{
					isClosed = false;
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				isClosed = false;
				return true;
			}
		}
		else
		{
			return true;
		}
	}

	public void look()
	{
		System.out.println(openDescription);
	}
}