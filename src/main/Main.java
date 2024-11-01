package main;

import frame.*;
import menu_service.MenuService;
public class Main {

	public static void main(String[] args) {
		// call the service that start the game
		MenuService menuService = new MenuService();
	//	menuService.MenuStart();
		//start game
		GameFrame game = new GameFrame();
		game.start();
	}

}
