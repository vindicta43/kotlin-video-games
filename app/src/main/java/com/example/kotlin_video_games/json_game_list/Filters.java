package com.example.kotlin_video_games.json_game_list;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Filters{

	@SerializedName("years")
	private List<YearsItem> years;

	public List<YearsItem> getYears(){
		return years;
	}
}