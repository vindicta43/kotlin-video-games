package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class RequirementsEn{

	@SerializedName("minimum")
	private String minimum;

	public String getMinimum(){
		return minimum;
	}
}