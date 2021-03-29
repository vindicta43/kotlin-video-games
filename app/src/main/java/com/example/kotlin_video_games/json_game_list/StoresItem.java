package com.example.kotlin_video_games.json_game_list;

import com.google.gson.annotations.SerializedName;

public class StoresItem{

	@SerializedName("id")
	private int id;

	@SerializedName("store")
	private Store store;

	public int getId(){
		return id;
	}

	public Store getStore(){
		return store;
	}
}