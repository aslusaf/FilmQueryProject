// Written by: Austin Lambert
// https://github.com/aslusaf

package com.skilldistillery.filmquery.utilities;

public class MenuBuilder {

	private String menuTitle;
	private String bannerTitle;
	private String fullMenuTitle;
	private String fullBannerTitle;
	private String fullMenu;
	private int numberOfOptions;
	private int menuSize = 0;
	private int bannerSize = 0;
	private int borderSize = 0;
	private int wrapperOption = 0;
	private int breakBarSize = 0;
	private String titleWrapper;
	private String bannerWrapper;
	private String leftTitleWrapperOption;
	private String rightTitleWrapperOption;
	private StringBuilder sbMenu = new StringBuilder();
	private StringBuilder sbBanner = new StringBuilder();
	private StringBuilder sbMenuTitle = new StringBuilder();
	private StringBuilder sbMenuOptions = new StringBuilder();
	private String inputPromptCharacter = "\u21f6 ";

	public MenuBuilder() {
		numberOfOptions = 1;
	}

	public MenuBuilder(String MenuTitle) {
		this.menuTitle = MenuTitle;
		this.menuSize = collectSize("M");
		numberOfOptions = 1;
	}

	public MenuBuilder(String MenuTitle, String menuSize) {
		this.menuTitle = MenuTitle;
		this.menuSize = collectSize(menuSize);
		numberOfOptions = 1;
	}

	public MenuBuilder(String MenuTitle, int menuSize) {
		this.menuSize = menuSize;
		this.menuTitle = MenuTitle;
		numberOfOptions = 1;
	}

	public MenuBuilder(String MenuTitle, String menuSize, int wrapperOption) {
		this.menuTitle = MenuTitle;
		this.menuSize = collectSize(menuSize);
		this.wrapperOption = wrapperOption;
		numberOfOptions = 1;
	}

	public MenuBuilder(String MenuTitle, int menuSize, int wrapperOption) {
		this.menuSize = menuSize;
		this.menuTitle = MenuTitle;
		this.wrapperOption = wrapperOption;
		numberOfOptions = 1;
	}

	public void addTitleWrapper(int wrapperOption) {
		this.wrapperOption = wrapperOption;
		if (titleWrapper == null) {
			titleWrapper = getWrapper(wrapperOption);
		}

		if (leftTitleWrapperOption == null && rightTitleWrapperOption == null) {
			sbMenuTitle.delete(0, sbMenuTitle.length()).append(titleWrapper).reverse().append(menuTitle)
					.append(titleWrapper);

			fullMenuTitle = sbMenuTitle.toString();
		} else {
			sbMenuTitle.delete(0, sbMenuTitle.length()).append(leftTitleWrapperOption).append(menuTitle)
					.append(rightTitleWrapperOption);

			fullMenuTitle = sbMenuTitle.toString();
		}

	}

	public void addTitleWrapper(String leftTitleWrapperOption, String rightTitleWrapperOption) {
		this.leftTitleWrapperOption = leftTitleWrapperOption;
		this.rightTitleWrapperOption = rightTitleWrapperOption;
	}

	public void addBannerWrapper(int wrapperOption) {

		this.wrapperOption = wrapperOption;
		bannerWrapper = getWrapper(wrapperOption);

		sbBanner.delete(0, sbBanner.length()).append(bannerWrapper).reverse().append(bannerTitle).append(bannerWrapper);

		fullBannerTitle = sbBanner.toString();

	}

	public String getWrapper(int wrapperOption) {

		String wrapper = "";

		switch (wrapperOption) {

		case (1):
			wrapper = " \u2509\u2508";
			break;
		case (2):
			wrapper = " ====";
			break;
		}

		return wrapper;

	}

	public void addTitleWrapper(String customWrapperOption) {
		titleWrapper = customWrapperOption;
	}

	public void printTitleWrapperOptions() {

		MenuBuilder ptwo = new MenuBuilder("Title Wrapper Options", "s");
		ptwo.changeInputPromptCharacter("");
		ptwo.addMenuOptions("      \u2508\u2509  \u2509\u2508", "    ====  ====");
		ptwo.printMenu();

	}
	
	public void printDefaultInputPromptCharacter() {
		System.out.print("  \u21f6 ");
	}

	public void changeInputPromptCharacter(String inputPromptCharacter) {
		this.inputPromptCharacter = inputPromptCharacter;
	}

	public void printMenu() {

		if (fullMenu == null) {
			addTitleWrapper(wrapperOption);
			topOfMenu();
			centerOfMenu();
			bottomOfMenu();
			fullMenu = sbMenu.toString();
		}
		System.out.println(fullMenu);
		System.out.print("  " + inputPromptCharacter);
	}

	public void topOfMenu() {

		int leftInsideSpacing = ((menuSize - fullMenuTitle.length()) / 2);
		int rightInsideSpacing = ((menuSize - fullMenuTitle.length()) / 2);

		if ((fullMenuTitle.length() + leftInsideSpacing + rightInsideSpacing) % menuSize != 0) {
			rightInsideSpacing += 1;
		}

		sbMenu.append(" \u250c");
		for (int i = 0; i < menuSize; i++)
			sbMenu.append("\u2500");
		sbMenu.append("\u2510");
		sbMenu.append("\n \u2502");
		for (int i = 0; i < leftInsideSpacing; i++)
			sbMenu.append(" ");
		sbMenu.append(fullMenuTitle);
		for (int i = 0; i < rightInsideSpacing; i++)
			sbMenu.append(" ");
		sbMenu.append("\u2502");
		sbMenu.append("\n \u251c");
		for (int i = 0; i < menuSize; i++)
			sbMenu.append("\u2500");
		sbMenu.append("\u2524");
	}

	public void centerOfMenu() {
		sbMenu.append(sbMenuOptions.toString());
	}

	private void bottomOfMenu() {
		sbMenu.append("\n ");
		sbMenu.append("\u2514");
		for (int i = 0; i < menuSize; i++)
			sbMenu.append("\u2500");
		sbMenu.append("\u2518");
//		sbMenu.append("\n " + inputPromptCharacter);
	}

	public void addMenuOption(String optionName) {
		int fixedCharactersLength = 5;
		if (numberOfOptions < 10) {
			sbMenuOptions.append("\n \u2502  " + numberOfOptions + ") " + optionName);
		} else {
			sbMenuOptions.append("\n \u2502 " + numberOfOptions + ") " + optionName);
		}
		for (int i = 0; i < menuSize - (fixedCharactersLength + optionName.length()); i++) {
			sbMenuOptions.append(" ");
		}
		sbMenuOptions.append("\u2502");
		numberOfOptions++;
	}

	public void addMenuOptions(String... options) {
		for (String optionName : options) {
			addMenuOption(optionName);
		}
	}

	public int collectSize(String size) {

		size = size.toLowerCase();

		switch (size) {

		case ("xs"):
			borderSize = 14;
			break;
		case ("s"):
			borderSize = 29;
			break;
		case ("m"):
			borderSize = 59;
			break;
		case ("l"):
			borderSize = 89;
			break;
		case ("xl"):
			borderSize = 121;
			break;
		default:
			System.err.println("MenuBuilder --> Error: Invalid size option");
			System.out.println("Valid options: XS | S | M | L | XL | Custom size (int)");
			System.exit(1);
		}

		return borderSize;

	}

	public void printBannerSizes() {

		printBanner("XS", "xs");
		printBanner("S", "s");
		printBanner("M", "m");
		printBanner("Custom Size (72)", 72);
		printBanner("L", "l");
		printBanner("XL", "xl");

	}

	public void printBanner(String bannerTitle) {

		this.bannerTitle = bannerTitle;
		this.bannerSize = collectSize("M");
		this.wrapperOption = 0;

		printBanner(bannerTitle, bannerSize, wrapperOption);

	}

	public void printBanner(String bannerTitle, String size) {

		this.bannerTitle = bannerTitle;
		this.bannerSize = collectSize(size);
		this.wrapperOption = 0;

		printBanner(bannerTitle, bannerSize, wrapperOption);

	}

	public void printBanner(String bannerTitle, String size, int wrapperOption) {

		this.bannerTitle = bannerTitle;
		this.bannerSize = collectSize(size);
		this.wrapperOption = wrapperOption;

		printBanner(bannerTitle, bannerSize, wrapperOption);

	}

	public void printBanner(String bannerTitle, int bannerSize) {

		this.bannerTitle = bannerTitle;
		this.bannerSize = bannerSize;
		this.wrapperOption = 0;

		printBanner(bannerTitle, bannerSize, wrapperOption);

	}

	public void printBanner(String bannerTitle, int bannerSize, int wrapperOption) {

		this.bannerTitle = bannerTitle;
		this.bannerSize = bannerSize;
		this.wrapperOption = wrapperOption;
		addBannerWrapper(wrapperOption);

		if (bannerSize < fullBannerTitle.length()) {

			bannerSize = fullBannerTitle.length() + 4;
//			System.out.println("The banner size is too small for the given title.");
//			System.out.println("Use a larger banner preset, or enter a custom size (i.e. 120, 150, etc.)");
//			return;
		}

		int leftInsideSpacing = (bannerSize - fullBannerTitle.length()) / 2;
		int rightInsideSpacing = (bannerSize - fullBannerTitle.length()) / 2;
		int fullBannerLength = fullBannerTitle.length() + leftInsideSpacing + rightInsideSpacing;

		if ((fullBannerLength) % bannerSize != 0)
			rightInsideSpacing += 1;

		System.out.print(" \u250c");
		for (int i = 0; i < bannerSize; i++)
			System.out.print("\u2500");
		System.out.println("\u2510");
		System.out.print(" \u2502");
		for (int i = 0; i < leftInsideSpacing; i++)
			System.out.print(" ");
		System.out.print(fullBannerTitle);
		for (int i = 0; i < rightInsideSpacing; i++)
			System.out.print(" ");
		System.out.println("\u2502");
		System.out.print(" \u2514");
		for (int i = 0; i < bannerSize; i++)
			System.out.print("\u2500");
		System.out.println("\u2518");

	}

	public int getNumberOfOptions() {
		return numberOfOptions;
	}

	public void printBreakBar(String size) {

		this.breakBarSize = collectSize(size);
		System.out.print("  ");
		for (int i = 0; i < breakBarSize; i++)
			System.out.print("\u2500");
		System.out.println("\n");

	}

	public void printBreakBar(int size) {
		this.breakBarSize = size;
		System.out.print("  ");
		for (int i = 0; i < breakBarSize; i++)
			System.out.print("\u2500");
		System.out.println("\n");

	}

}
