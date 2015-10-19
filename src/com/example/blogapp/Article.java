package com.example.blogapp;

public class Article {
	 
    private String name;
    private String image;
 
    public String getName() {
        return name;
    }
    
    public void setImage(String image) {
    	this.image = image;
    }
    
    public String getImage() {
    	return image;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    @Override
    public String toString() {
        return name;
    }
}
