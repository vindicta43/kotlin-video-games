package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class RequirementsRu{

	@SerializedName("minimum")
	private String minimum;

	@SerializedName("recommended")
	private String recommended;

	public String getMinimum(){
		return minimum;
	}

	public String getRecommended(){
		return recommended;
	}
}