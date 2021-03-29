package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class AddedByStatus{

	@SerializedName("owned")
	private int owned;

	@SerializedName("beaten")
	private int beaten;

	@SerializedName("dropped")
	private int dropped;

	@SerializedName("yet")
	private int yet;

	@SerializedName("playing")
	private int playing;

	@SerializedName("toplay")
	private int toplay;

	public int getOwned(){
		return owned;
	}

	public int getBeaten(){
		return beaten;
	}

	public int getDropped(){
		return dropped;
	}

	public int getYet(){
		return yet;
	}

	public int getPlaying(){
		return playing;
	}

	public int getToplay(){
		return toplay;
	}
}