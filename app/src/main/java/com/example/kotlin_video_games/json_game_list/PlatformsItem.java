package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class PlatformsItem{

	@SerializedName("requirements_ru")
	private Object requirementsRu;

	@SerializedName("requirements_en")
	private Object requirementsEn;

	@SerializedName("released_at")
	private String releasedAt;

	@SerializedName("platform")
	private Platform platform;

	public Object getRequirementsRu(){
		return requirementsRu;
	}

	public Object getRequirementsEn(){
		return requirementsEn;
	}

	public String getReleasedAt(){
		return releasedAt;
	}

	public Platform getPlatform(){
		return platform;
	}
}