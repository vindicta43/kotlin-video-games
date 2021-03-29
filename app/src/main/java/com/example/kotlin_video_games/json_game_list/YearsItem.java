package com.example.kotlin_video_games.json_game_list;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class YearsItem{

	@SerializedName("filter")
	private String filter;

	@SerializedName("nofollow")
	private boolean nofollow;

	@SerializedName("decade")
	private int decade;

	@SerializedName("count")
	private int count;

	@SerializedName("from")
	private int from;

	@SerializedName("to")
	private int to;

	@SerializedName("years")
	private List<YearsItem> years;

	@SerializedName("year")
	private int year;

	public String getFilter(){
		return filter;
	}

	public boolean isNofollow(){
		return nofollow;
	}

	public int getDecade(){
		return decade;
	}

	public int getCount(){
		return count;
	}

	public int getFrom(){
		return from;
	}

	public int getTo(){
		return to;
	}

	public List<YearsItem> getYears(){
		return years;
	}

	public int getYear(){
		return year;
	}
}