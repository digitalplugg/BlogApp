package com.example.blogapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParseXML {
	    List<Article> articles;
	    private Article article;
	    private String text;
	 
	    public ParseXML() 
	    {
	        articles = new ArrayList<Article>();
	    }
	 
	    public List<Article> getarticles() 
	    {
	        return articles;
	    }
	 
	    public List<Article> parse(InputStream is) 
	    {
	        XmlPullParserFactory factory = null;
	        XmlPullParser parser = null;
	        try {
	            factory = XmlPullParserFactory.newInstance();
	            factory.setNamespaceAware(true);
	            parser = factory.newPullParser();
	 
	            parser.setInput(is, null);
	 
	            int eventType = parser.getEventType();
	            while (eventType != XmlPullParser.END_DOCUMENT) 
	            {
	                String tagname = parser.getName();
	                switch (eventType) 
	                {
	                case XmlPullParser.START_TAG:
	                    if (tagname.equalsIgnoreCase("item")) 
	                    {
	                        article = new Article();
	                    }
	                    break;
	 
	                case XmlPullParser.TEXT:
	                    text = parser.getText();
	                    break;
	 
	                case XmlPullParser.END_TAG:
	                    if (tagname.equalsIgnoreCase("item")) 
	                    {
	                        articles.add(article);
	                    }
	                    else if (tagname.equalsIgnoreCase("name")) 
	                    {
	                        article.setName(text);
	                    } 
	                    else if (tagname.equalsIgnoreCase("image")) 
	                    {
	                        article.setImage(text);
	                    }
	                    break;
	 
	                default:
	                    break;
	                }
	                eventType = parser.next();
	            }
	 
	        } catch (XmlPullParserException e) 
	        {
	            e.printStackTrace();
	        } catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	 
	        return articles;
	    }	    
}