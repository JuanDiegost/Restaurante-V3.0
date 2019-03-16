package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.entity.ManagerRestaurant;
import model.entity.SwingWorkerProductsTable;
import model.persistence.Persistence;
import view.PrincipalWindow;
/**
 * Esta clase es el controlador que se encarga de unir la logica y la vista
 * 
 * @author Andres Torres y Lina Melo
 *
 */
public class Controller implements ActionListener{
	//------------------------------Attributes---------------------------
	/**
	 * Ventana principal de la aplicación
	 */
	private PrincipalWindow window;
	/**
	 * Administrador del restaurante
	 */
	private ManagerRestaurant manager;
	/**
	 * Persistencia de los números pseudo-aleatorios ya validados
	 */
	private Persistence persistence;
	//------------------------------Constructor--------------------------
	public Controller() {
		this.persistence = Persistence.getINSTANCE();
		this.window = new PrincipalWindow(this);
	}
	//--------------------------------Methods----------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
		case "GO_TO_SIMULATE":
			//Creamos el objeto del administrador del restaurante/
			this.manager =ManagerRestaurant.getManagerRestaurant();
			//Calculamos la cantidad de d�as y horas a trabajar/
			float[] daysAndHours = manager.calculateHoursAndDaysToSimulate();
			//Calculamos la cantidad de comensales totales/
			int diners = manager.calculateDinersDependsDaysToSimulate((int)daysAndHours[1]);
			//Seteamos los valores de dias, horas y comensales en la vista/
			window.setValuesOfDaysHoursAndDiners(daysAndHours,diners);
			//Creamos un hilo que actualice la vista a medida que se ejecute la logica/
			SwingWorkerProductsTable sw = new SwingWorkerProductsTable(manager, 10, persistence, window);
			
			
			
//			//Ejecutamos el hilo/
//			manager.startAtention();
			sw.execute();			
			break;
		}
	}
}