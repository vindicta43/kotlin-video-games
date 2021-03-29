package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class Platform{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("slug")
	private String slug;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getSlug(){
		return slug;
	}
}