package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class Film {

	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private Integer length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private String language;
	private List<Actor> actorList;

	public Film() {

	}

//	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
//			double rentalRate, Integer length, double replacementCost, String rating, String specialFeatures) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.description = description;
//		this.releaseYear = releaseYear;
//		this.languageId = languageId;
//		this.rentalDuration = rentalDuration;
//		this.rentalRate = rentalRate;
//		this.length = length;
//		this.replacementCost = replacementCost;
//		this.rating = rating;
//		this.specialFeatures = specialFeatures;
//
//
//	}

	public Film(int id, String title, String description, Integer releaseYear, int languageId, int rentalDuration,
			double rentalRate, Integer length, double replacementCost, String rating, String specialFeatures,
			String language) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.language = language;
		DatabaseAccessor db = new DatabaseAccessorObject();
		this.actorList = db.findActorsByFilmId(id);
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, languageId, length, rating, releaseYear, rentalDuration, rentalRate,
				replacementCost, specialFeatures, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && id == other.id && languageId == other.languageId
				&& Objects.equals(length, other.length) && Objects.equals(rating, other.rating)
				&& Objects.equals(releaseYear, other.releaseYear) && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ===== Film Information =====\n\n");
//		sb.append("Film ID              : " + id + "\n");
		sb.append(" Film Title           : " + title + "\n");
		sb.append(" Film Release Year    : " + releaseYear + "\n");
		sb.append(" Film Rating          : " + rating + "\n");
		sb.append(" Film Language        : " + language + "\n");
		sb.append(" Film Description     : " + description + "\n");
//		sb.append("Film Language ID     : " + languageId + "\n");
//		sb.append("Film Rental Duration : " + rentalDuration + "\n");
//		sb.append("Film Rental Rate     : " + rentalRate + "\n");
//		sb.append("Film Length          : " + length + "\n");
//		sb.append("Film Replacement Cost: " + replacementCost + "\n");
//		sb.append("Film Special Features: " + specialFeatures + "\n");
		sb.append("\n  <<<<<    Actor List    >>>>>\n\n");

		for (Actor actor : actorList) {
			sb.append(actor);
		}

		return sb.toString();
	}

}