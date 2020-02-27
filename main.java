package juegoMUD;

import java.util.*;

class main
{
	public static void main(String[] args)
	{
		String[] principalVerbs = {"ir", "ve", "mirar", "mira", "coger", "coge", "abrir", "abre", "romper", "rompe"};
		Inventory inventory = new Inventory();

		String rootPhrase = "Estás en una pequeña habitación. Un poco de luz entra por la ventana. Al norte de la habitación puedes ver una puerta. En el medio hay una mesa con una llave encima";
		String northPhrase = "Cocina de la casa- Una pequeña cocina. No parece haber nada de interés aquí";

		Place root = new Place("-Sala principal-", rootPhrase, principalVerbs);
		Place northRoot = new Place("-Cocina-", northPhrase, principalVerbs);

		int[] verbs = {4, 5};
		ArrayList<String[]> keyActions = new ArrayList<String[]>();
		String[] takeActions = {"fsd", "sasd"};

		keyActions.add(takeActions);

		Object rootKey = new Object("Llave", verbs, "una llave oxidada", root, principalVerbs, keyActions, inventory);

		Door between = new Door("Una puerta vieja", true, false, inventory, true, principalVerbs, rootKey);

		root.addDoor(between, 0);
		root.setNorth(northRoot);
		northRoot.setSouth(root);
		root.addObject(rootKey);
		northRoot.addDoor(between, 1);

		root.start();
	}
}