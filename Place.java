package juegoMUD;

import java.util.*;

class Place
{
	private String phrase;
	private String name;
	private Place north;
	private Place south;
	private Place east;
	private Place west;
	private Place up;
	private Place down;
	private ArrayList<Object> objects;
	private String[] principalVerbs;
	private Door[] canAccess;

	//Constructor principal
	public Place(String name, String phrase, String[] principalVerbs)
	{
		this.name = name;
		this.phrase = phrase;
		this.north = null;
		this.south = null;
		this.east = null;
		this.west = null;
		this.up = null;
		this.down = null;
		this.objects = new ArrayList<Object>();
		this.principalVerbs = principalVerbs;
		this.canAccess = new Door[6];
	}

	//Constructor sobrecargado
	public Place(String phrase, Place north, Place south, Place east, Place west, Place up, Place down, ArrayList<Object> objects, String[] principalVerbs, Door[] canAccess)
	{
		this.phrase = phrase;
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
		this.up = up;
		this.down = down;
		this.objects = new ArrayList<Object>();
		this.principalVerbs = principalVerbs;

		if(canAccess == null)
		{
			this.canAccess = new Door[6];
		}
		else
		{
			this.canAccess = canAccess;
		}
	}

	public void start()
	{
		//Cada vez que entramos en una nueva zona imprimiremos la descripción de la misma.
		System.out.println(this.phrase);

		boolean end = false;
		String action;
		Scanner scanner = new Scanner(System.in);

		//Cada zona será la que te pregunta qué hacer. De esta forma tenemos total libertad para movernos entre zonas sin depender de una clase central.Es recursivo.
		while(end == false)
		{
			System.out.print("\n" + this.name + "\n>");
			action = scanner.nextLine();

			if(action.equals("salir") || action.equals("exit"))
			{
				end = true;
			}
			else
			{
				analyseAction(action);
				//return;
			}
		}

		System.out.println("Gracias por jugar!");
		System.exit(2);
	}

	public void analyseAction(String action) 
	{
		String[] words = action.split(" ");

		//Primero vamos a ocuparnos de comprobar si se quiere ir a algún sitio utilizando norte sur este u oeste
		for(int i = 0; i < words.length; i++)
		{
			if(words[i].equals("norte") || words[i].equals("north") /*Falta incluir mayusculas*/)
			{
				if(canAccess[0] != null) //comprobamos que exista una puerta en la dirección que queremos ir, porque si no existe, dicha dirección no es accesible
				{
					if(canAccess[0].cross()) //Luego comprobamos si la puerta por la que queremos pasar está o no abierta.
					{
						this.north.start();
						return;
					}
					else
					{
						System.out.println("Se necesita una llave para abrir la puerta.");
						return;
					}
				}
				
				System.out.println("No se puede ir por ahí");
			}

			if(words[i].equals("sur") || words[i].equals("south") /*Falta incluir mayusculas*/)
			{
				if(canAccess[1] != null)
				{
					if(canAccess[1].cross())
					{
						this.south.start();
						return;
					}
					else
					{
						System.out.println("Se necesita una llave para abrir la puerta.");
						return;
					}
				}
				
				System.out.println("No se puede ir por ahí");
			}

			if(words[i].equals("east") || words[i].equals("este") /*Falta incluir mayusculas*/)
			{
				if(canAccess[2] != null)
				{
					if(canAccess[2].cross())
					{
						this.east.start();
						return;
					}
					else
					{
						System.out.println("Se necesita una llave para abrir la puerta.");
						return;
					}
				}
				
				System.out.println("No se puede ir por ahí");
			}

			if(words[i].equals("oeste") || words[i].equals("west") /*Falta incluir mayusculas*/)
			{
				if(canAccess[3] != null)
				{
					if(canAccess[3].cross())
					{
						this.west.start();
						return;
					}
					else
					{
						System.out.println("Se necesita una llave para abrir la puerta.");
						return;
					}
				}
				
				System.out.println("No se puede ir por ahí");
			}

			if(words[i].equals("arriba") || words[i].equals("up") /*Falta incluir mayusculas*/)
			{
				if(canAccess[4] != null)
				{
					if(canAccess[4].cross())
					{
						this.up.start();
						return;
					}
					else
					{
						System.out.println("Se necesita una llave para abrir la puerta.");
						return;
					}
				}
				
				System.out.println("No se puede ir por ahí");
			}

			if(words[i].equals("abajo") || words[i].equals("down") /*Falta incluir mayusculas*/)
			{
				if(canAccess[5] != null)
				{
					if(canAccess[5].cross())
					{
						this.down.start();
						return;
					}
					else
					{
						System.out.println("Se necesita una llave para abrir la puerta.");
						return;
					}
				}		

				System.out.println("No se puede ir por ahí");
			}
		}

		int selectedObject = 0;
		int correction = 0; //Contador que aumentará cada vez que encontremos un objeto en una frase. Si en una sola frase hay más de dos objetos, la frase se descarta

		for(int i = 0; i < words.length; i++)
		{
			for(int j = 0; j < objects.size(); j++)
			{
				if(objects.get(j).getName().equals(words[i])) //Buscamos el objeto con el que se quiere interactuar en la frase.
				{
					selectedObject = j;
					correction++;
				}
			}
		}

		if(correction == 0 || correction > 1) //SI encontramos más de un objeto o ninguno, diremos que no podemos hacer lo que queremos.
		{
			System.out.println("No puedo hacer eso");
		}
		else //Luego, buscamos el verbo que utilizaremos para interacturar con dicho objeto.
		{
			for(int i = 0; i < words.length; i++)
			{
				for(int j = 0; j < principalVerbs.length; j++)
				{
					if(words[i].equals(principalVerbs[j]))
					{
						objects.get(selectedObject).action(j);
						return;
					}
				}
			}
			objects.get(selectedObject).action(-1); //Si no encontramos el verbo, le pasamos un número "falso" para que el método diga que no puede hacer eso
		}
	}

	public void goTo(String[] action, Place place)
	{
		//Nos aseguramos de que el verbo que acompañe al lugar al que queremos ir tenga sentido
		//-----------ESTO LO HAREMOS MAS ADELANTE, CUANDO LA LSITA DE VERBOS ESTÉ DEFINIDA
		/*if(action.length == 1)
		{
			place.start();
		}
		else
		{
			for(int i = 0; i < action.length; i++)
			{
				if(algun verbo de la lista que no sea "ir" o "ve" se encuentra en la frase, la declaramos como sinsentido)
			}
		}*/
	}

	public void addObject(Object object)
	{
		objects.add(object);
	}

	public void addDoor(Door newDoor, int position)
	{
		canAccess[position] = newDoor;
	}

	public void setNorth(Place north)
	{
		this.north = north;
	}

	public void setSouth(Place south)
	{
		this.south = south;
	}

	public void setEast(Place east)
	{
		this.east = east;
	}

	public void setWest(Place west)
	{
		this.west = west;
	}

	public void setUp(Place up)
	{
		this.up = up;
	}

	public void setDown(Place down)
	{
		this.down = down;
	}

}