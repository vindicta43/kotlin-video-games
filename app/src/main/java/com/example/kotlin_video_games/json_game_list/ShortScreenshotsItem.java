package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class ShortScreenshotsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("id")
	private int id;

	public String getImage(){
		return image;
	}

	public int getId(){
		return id;
	}
}