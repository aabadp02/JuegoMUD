package juegoMUD;

import java.util.*;

class Object
{
	private String name;
	private int[] verbs; //Guardará las posiciones (según la lista principal de verbos) de los verbos que podrá utilizar.
	private int[] options; //Guardará, para cada verbo, las opciones que pueden pasar cuando la acción se lleva a cabo. (Ejemplo: Al romper el objeto puede: no romperse, solo romperse o revelar un nuevo objeto en el interior (0, 0, 0))
	ArrayList<String[]> actions;
	private String description;
	//Necesitamos un array de arrays de Strings para las diferentes situaciones
	private Place father;
	private String[] principalVerbs; //ESTO PUEDE QUE SEA INNECESARIO
	private Inventory inventory;

	public Object(String name, int[] verbs, String description, Place father, String[] principalVerbs, ArrayList<String[]> actions, Inventory inventory)
	{
		this.name = name;
		this.verbs = verbs;
		this.father = father;
		this.principalVerbs = principalVerbs;
		this.actions = actions;
		this.inventory = inventory;
	}

	public String getName()
	{
		return this.name;
	}

	public int[] getVerbs()
	{
		return this.verbs;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	
	
	//Le pasaremos la posición en la lista de verbos principales del verbo que utilizaremos para este objeto, una nueva acción, por si queremos ser específicos y no genéricos cuando pase algo y un objeto en caso de que, para realizar la acción sea necesario un objeto a mayores
	public void action(int verbPosition/*, String newAction, Object object*/)
	{
		switch(verbPosition)
		{
			case 0:
			case 1:
				System.out.println("No puedes ir ahí");
				break;

			case 2:
			case 3:
				System.out.println(description);
				break;

			case 4:
			case 5:
				System.out.println("Coges [" + name + "]");
				inventory.addObject(this);
				break;

			case 6:
			case 7:
				//Primero comprobamos que se pueda hacer esto
				System.out.println("newAction");
				break;

			case 8:
			case 9:
				//Primero comprobamos que se pueda hacer esto
				System.out.println("newAction");
				break;

			default:
				System.out.println("No puedo hacer eso0");
		}

	}
}