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
		Scanner kb = new Scanner(System.in);

		startUserInterface(kb);

		kb.close();
	}

	private void startUserInterface(Scanner kb) {

		MenuBuilder mb = new MenuBuilder("Film Query Menu");
		mb.addBannerWrapper(1);
		mb.addMenuOptions("Look up a film by the film ID", "Look up a film by a search keyword",
				"Exit the application");

		int option;
		do {
			mb.printMenu();
			try {
				option = kb.nextInt();
				kb.nextLine();
				runOption(option, kb, mb);
			} catch (Exception e) {
				System.out.println();
				mb.printBanner("***** Invalid Menu Option *****");
				mb.printBreakBar();
				kb.nextLine();
				option = 0;
			}

		} while (option != 3);

	}

	private void runOption(int option, Scanner kb, MenuBuilder mb) {

		switch (option) {

		case 1:
			do {
				try {
					System.out.println();
					mb.printBreakBar();
					mb.printBanner("Enter the film id:");
					mb.printDefaultInputPromptCharacter();
					int filmId = kb.nextInt();
					kb.nextLine();
					System.out.println();
					mb.printBreakBar();
					System.out.println(db.findFilmById(filmId));
					mb.printBreakBar();
					break;
				} catch (Exception e) {
					System.out.println();
					mb.printBanner("***** Invalid entry. Please enter a valid film ID. *****");
					kb.nextLine();
				}
			} while (true);
			break;

		case 2:
			System.out.println();
			mb.printBreakBar();
			mb.printBanner("Enter the keyword:");
			mb.printDefaultInputPromptCharacter();
			String keyword = kb.next();
			kb.nextLine();
			System.out.println();
			mb.printBreakBar("M");
			List<Film> filmList = db.findFilmByKeyword(keyword);
			if (!filmList.isEmpty()) {
				for (Film film : filmList) {
					System.out.println(film);
					mb.printBreakBar("L");
				}
			} else {
				mb.printBanner("No matching films were found");
				System.out.println();
			}
			mb.printBreakBar();
			break;
		case 3:
			System.out.println();
			mb.printBreakBar();
			mb.printBanner("Goodbye!");
			break;
		default:
			System.out.println();
			mb.printBanner("***** Invalid Menu Option *****");
			mb.printBreakBar();
		}

	}

}
