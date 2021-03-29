package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class RatingsItem{

	@SerializedName("count")
	private int count;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("percent")
	private double percent;

	public int getCount(){
		return count;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public double getPercent(){
		return percent;
	}
}