import java.awt.Color;
import java.util.ArrayList;

//Created by Eric Fehringer + Taylor Dugger

public class ImageProcessor {
	
	Picture pic; //the picture that will be manipulated
	
	public ImageProcessor(String imageFile){
		pic = new Picture(imageFile);
	}

	//reduces the widith  by calling the reduceWidthbyOne() the number of
	//times to get to x*W
	
	public Picture reduceWidth(double x){
		double width = pic.width()*x;
		int decrement = (int) Math.floor(width);
		
		for (int i = decrement ; i>0 ;i--)
		{
			reduceWidthbyOne();
		}
		return pic;
	}
	//follows the algorithm given in the homework to find the optimal
	//pixels to remove when reducing an image
	private void reduceWidthbyOne()
	{
		int height = pic.height();
		int width = pic.width();
		
		//creates I[][] or Importance(M[i,j])
		int[][] I = new int[height][width];
		for(int h = 0; h < height;h++)
		{
			for(int w = 0; w <width;w++)
			{
				//when its not an edge case
				if(h!=0 && h!=height-1 && w!=0 && w!= width-1)
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(w+1, h)) + Dist(pic.get(w, h+1),  pic.get(w, h-1) );
				}
				//when height is zero, it wraps around to grab value
				else if(h==0  && w!=0 && w!= width-1) 
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(w+1, h)) + Dist(pic.get(w, h+1),  pic.get(w, height-1) );
				}
				//when height is at max it wraps around
				else if(h==height-1 && w!=0 && w!= width-1)
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(w+1, h)) + Dist(pic.get(w, 0),  pic.get(w, height-1) );
				}
				//when the widith is at the beginning it wraps around
				else if(h!=0 && h!=height-1 && w==0 ) 
				{
					I[h][w] = Dist(pic.get(width-1, h), pic.get(w+1, h)) + Dist(pic.get(w, h+1),  pic.get(w, h-1) );
				}
				//when the width is at max, it wraps around 
				else if(h!=0 && h!=height-1 &&  w== width-1) 
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(0, h)) + Dist(pic.get(w, h+1),  pic.get(w, h-1) );
				}
				// 0, 0 corner case
				else if(h==0 && w == 0) 
				{
					I[h][w] = Dist(pic.get(width-1, h), pic.get(w+1, h)) + Dist(pic.get(w, h+1),  pic.get(w, height-1) );
				}
				// height-1, 0 corner case
				else if(h==height-1 && w == 0) 
				{
					I[h][w] = Dist(pic.get(width-1, h), pic.get(w+1, h)) + Dist(pic.get(w, 0),  pic.get(w, height-1) );
				}
				//0, width-1 corner case
				else if(h==0 && w==width-1) 
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(0, h)) + Dist(pic.get(w, h+1),  pic.get(w, height-1) );
				}
				//heigh-1, width-1 corner Case
				else if(h==height-1 && w==width-1) 
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(0, h)) + Dist(pic.get(w, 0),  pic.get(w, h-1) );
				}
				//otherwise it prints the point at which it has an error
				else{
					System.out.println("error at ("+h +","+w+")");
				}
			}
		}
		
		
		//calls minCost to find the optimal pixels to remove
		ArrayList<Integer> minCostCut = dynamicProgramming.minCostVC(I);
		

		Picture newPic = new Picture( width-1, height);
		
		for(int h = 0; h < height;h++) //populates the new picture
		{
			int y = minCostCut.get(2*h+1); //grabs the address of the pixel to remove
	
			for(int w = 0; w < y;w++)
			{
				newPic.set( w,h, pic.get(w,h));
			}
			//skips over pixel that will be removed, and calls w-1 instead of w
			for(int w = y+1; w < width-1;w++)
			{
				newPic.set( w-1,h,pic.get(w,h));
			}
		}
		
		pic = newPic;	
	}
	
	//does the RGB comparison of a given pixel
	private int Dist(Color p, Color q)
	{
		int red = p.getRed() - q.getRed();
		red = red*red;
		int blue = p.getBlue() - q.getBlue();
		blue = blue*blue;
		int green = p.getGreen() - q.getGreen();
		green = green*green;
		
		return red+blue+green;
	}
	
	
}
