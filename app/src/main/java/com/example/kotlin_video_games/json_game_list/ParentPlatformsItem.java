package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class ParentPlatformsItem{

	@SerializedName("platform")
	private Platform platform;

	public Platform getPlatform(){
		return platform;
	}
}