package com.example.kotlin_video_games.json_game_list;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("added")
	private int added;

	@SerializedName("rating")
	private double rating;

	@SerializedName("metacritic")
	private int metacritic;

	@SerializedName("playtime")
	private int playtime;

	@SerializedName("short_screenshots")
	private List<ShortScreenshotsItem> shortScreenshots;

	@SerializedName("platforms")
	private List<PlatformsItem> platforms;

	@SerializedName("user_game")
	private Object userGame;

	@SerializedName("rating_top")
	private int ratingTop;

	@SerializedName("reviews_text_count")
	private int reviewsTextCount;

	@SerializedName("ratings")
	private List<RatingsItem> ratings;

	@SerializedName("genres")
	private List<GenresItem> genres;

	@SerializedName("saturated_color")
	private String saturatedColor;

	@SerializedName("id")
	private int id;

	@SerializedName("added_by_status")
	private AddedByStatus addedByStatus;

	@SerializedName("parent_platforms")
	private List<ParentPlatformsItem> parentPlatforms;

	@SerializedName("ratings_count")
	private int ratingsCount;

	@SerializedName("slug")
	private String slug;

	@SerializedName("released")
	private String released;

	@SerializedName("suggestions_count")
	private int suggestionsCount;

	@SerializedName("stores")
	private List<StoresItem> stores;

	@SerializedName("tags")
	private List<TagsItem> tags;

	@SerializedName("background_image")
	private String backgroundImage;

	@SerializedName("tba")
	private boolean tba;

	@SerializedName("dominant_color")
	private String dominantColor;

	@SerializedName("esrb_rating")
	private EsrbRating esrbRating;

	@SerializedName("name")
	private String name;

	@SerializedName("updated")
	private String updated;

	@SerializedName("clip")
	private Object clip;

	@SerializedName("reviews_count")
	private int reviewsCount;

	public int getAdded(){
		return added;
	}

	public double getRating(){
		return rating;
	}

	public int getMetacritic(){
		return metacritic;
	}

	public int getPlaytime(){
		return playtime;
	}

	public List<ShortScreenshotsItem> getShortScreenshots(){
		return shortScreenshots;
	}

	public List<PlatformsItem> getPlatforms(){
		return platforms;
	}

	public Object getUserGame(){
		return userGame;
	}

	public int getRatingTop(){
		return ratingTop;
	}

	public int getReviewsTextCount(){
		return reviewsTextCount;
	}

	public List<RatingsItem> getRatings(){
		return ratings;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public String getSaturatedColor(){
		return saturatedColor;
	}

	public int getId(){
		return id;
	}

	public AddedByStatus getAddedByStatus(){
		return addedByStatus;
	}

	public List<ParentPlatformsItem> getParentPlatforms(){
		return parentPlatforms;
	}

	public int getRatingsCount(){
		return ratingsCount;
	}

	public String getSlug(){
		return slug;
	}

	public String getReleased(){
		return released;
	}

	public int getSuggestionsCount(){
		return suggestionsCount;
	}

	public List<StoresItem> getStores(){
		return stores;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public String getBackgroundImage(){
		return backgroundImage;
	}

	public boolean isTba(){
		return tba;
	}

	public String getDominantColor(){
		return dominantColor;
	}

	public EsrbRating getEsrbRating(){
		return esrbRating;
	}

	public String getName(){
		return name;
	}

	public String getUpdated(){
		return updated;
	}

	public Object getClip(){
		return clip;
	}

	public int getReviewsCount(){
		return reviewsCount;
	}
}