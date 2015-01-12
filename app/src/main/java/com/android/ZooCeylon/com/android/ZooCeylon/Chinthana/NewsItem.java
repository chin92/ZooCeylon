package com.android.ZooCeylon.com.android.ZooCeylon.Chinthana;

public class NewsItem {
	private String title;
    private String content;
    private String thumbnailUrl;
    private String dateAdded;

    public NewsItem() {
    }

    public NewsItem(String title, String content, String thumbnailUrl, String dateAdded) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.dateAdded = dateAdded;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }





}
