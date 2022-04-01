package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.utilities.MenuBuilder;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(63);
//    Actor actor = db.findActorById(1);
//    List<Actor> actorList = db.findActorsByFilmId(1);
		System.out.println(film);
//    System.out.println(actor);
//    System.out.println(actorList);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

		MenuBuilder mb = new MenuBuilder("Film Query Menu");
		mb.addBannerWrapper(1);
		mb.addMenuOptions("Look up a film by the film ID", "Look up a film by a search keyword",
				"Exit the application");

		// TODO wrap in try/catch and while loop
		int option;
		do {
			mb.printMenu();
			option = input.nextInt();
			input.nextLine();
			runOption(option);
			
		} while (option != 3);

	}

	private void runOption(int option) {
		
		switch (option) {
		
		case 1:
			System.out.println("In case 1");
			break;
		case 2:
			System.out.println("In case 2");
			break;
		}
		
	}

}
