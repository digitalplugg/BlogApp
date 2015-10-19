package com.example.blogapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAndTextAdapter extends ArrayAdapter<Article> {
	
	Context context;

	public ImageAndTextAdapter(Context context, int resourceId,
	        List<Article> items) {
	    super(context, resourceId, items);
	    this.context = context;
	}

	/*private view holder class*/
	private class ViewHolder {
	    ImageView imageView;
	    TextView txtTitle;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	    ViewHolder holder = null;
	    Article rowItem = getItem(position);

	    LayoutInflater mInflater = (LayoutInflater) context
	            .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	    if (convertView == null) 
	    {
	        convertView = mInflater.inflate(R.layout.article_list_item, null);
	        holder = new ViewHolder();
	          
	        //set text
	        holder.txtTitle = (TextView) convertView.findViewById(R.id.list_item_text);
	        holder.txtTitle.setText(rowItem.getName());
	        
	        //set image from assets folder
	        holder.imageView = (ImageView) convertView.findViewById(R.id.list_item_image);
	      //get from drawables --  int imgId = context.getResources().getIdentifier(rowItem.getImage(), "raw", context.getPackageName()); 
	        Bitmap bp = getBitmapFromAsset(context, rowItem.getImage());
	        holder.imageView.setImageBitmap(bp);
	    } 
	    
	    return convertView;
	}
	
	public static Bitmap getBitmapFromAsset(Context context, String filePath) {
	    AssetManager assetManager = context.getAssets();
	    InputStream istr;
	    Bitmap bitmap = null;
	    
	    try {
	        istr = assetManager.open(filePath);
	        bitmap = BitmapFactory.decodeStream(istr);
	    } catch (IOException e) {
	        // handle exception
	    }
	    
	    return bitmap;
	}
}