package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class GenresItem{

	@SerializedName("games_count")
	private int gamesCount;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("image_background")
	private String imageBackground;

	@SerializedName("slug")
	private String slug;

	public int getGamesCount(){
		return gamesCount;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getImageBackground(){
		return imageBackground;
	}

	public String getSlug(){
		return slug;
	}
}