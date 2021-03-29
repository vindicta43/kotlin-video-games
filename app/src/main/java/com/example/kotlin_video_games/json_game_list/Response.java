package com.example.kotlin_video_games.json_game_list;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("next")
	private String next;

	@SerializedName("nofollow")
	private boolean nofollow;

	@SerializedName("noindex")
	private boolean noindex;

	@SerializedName("nofollow_collections")
	private List<String> nofollowCollections;

	@SerializedName("previous")
	private Object previous;

	@SerializedName("count")
	private int count;

	@SerializedName("description")
	private String description;

	@SerializedName("seo_h1")
	private String seoH1;

	@SerializedName("filters")
	private Filters filters;

	@SerializedName("seo_title")
	private String seoTitle;

	@SerializedName("seo_description")
	private String seoDescription;

	@SerializedName("results")
	private List<ResultsItem> results;

	@SerializedName("seo_keywords")
	private String seoKeywords;

	public String getNext(){
		return next;
	}

	public boolean isNofollow(){
		return nofollow;
	}

	public boolean isNoindex(){
		return noindex;
	}

	public List<String> getNofollowCollections(){
		return nofollowCollections;
	}

	public Object getPrevious(){
		return previous;
	}

	public int getCount(){
		return count;
	}

	public String getDescription(){
		return description;
	}

	public String getSeoH1(){
		return seoH1;
	}

	public Filters getFilters(){
		return filters;
	}

	public String getSeoTitle(){
		return seoTitle;
	}

	public String getSeoDescription(){
		return seoDescription;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public String getSeoKeywords(){
		return seoKeywords;
	}
}