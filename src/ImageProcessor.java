import java.awt.Color;
import java.util.ArrayList;

public class ImageProcessor {
	
	Picture pic;

	public ImageProcessor(String imageFile){
		pic = new Picture(imageFile);
	}
	
	public Picture reduceWidth(double x){
		double width = pic.width()*x;
		
		int decrement = (int) Math.floor(width);
		
		int f = 0;
		for (int i = decrement ; i>0 ;i--)
		{
			System.out.println(f);
			reduceWidthbyOne();
			f++;
		}
		return pic;
		
	}
	
	private void reduceWidthbyOne()
	{
		int height = pic.height();
		
		int width = pic.width();
		
		
		int[][] I = new int[height][width];
		for(int h = 0; h < height;h++)
		{
			for(int w = 0; w <width;w++)
			{
				if(h!=0 && h!=height-1 && w!=0 && w!= width-1)//when its not an edge case
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(w+1, h)) + Dist(pic.get(w, h+1),  pic.get(w, h-1) );
				}
				else if(h==0  && w!=0 && w!= width-1) //when height is zero, it wraps around to grab value
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(w+1, h)) + Dist(pic.get(w, h+1),  pic.get(w, height-1) );
				}
				else if(h==height-1 && w!=0 && w!= width-1)//when height is at capacity it wraps around
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(w+1, h)) + Dist(pic.get(w, 0),  pic.get(w, height-1) );
				}
				else if(h!=0 && h!=height-1 && w==0 ) //when the widith is at the beginning it wraps around
				{
					I[h][w] = Dist(pic.get(width-1, h), pic.get(w+1, h)) + Dist(pic.get(w, h+1),  pic.get(w, h-1) );
				}
				else if(h!=0 && h!=height-1 &&  w== width-1) //when the width is at max, it wraps around 
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(0, h)) + Dist(pic.get(w, h+1),  pic.get(w, h-1) );
				}
				else if(h==0 && w == 0) // 0, 0 corner case
				{
					I[h][w] = Dist(pic.get(width-1, h), pic.get(w+1, h)) + Dist(pic.get(w, h+1),  pic.get(w, height-1) );
				}
				else if(h==height-1 && w == 0) // height-1, 0 corner case
				{
					I[h][w] = Dist(pic.get(width-1, h), pic.get(w+1, h)) + Dist(pic.get(w, 0),  pic.get(w, height-1) );
				}
				else if(h==0 && w==width-1) //0, width-1 corner case
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(0, h)) + Dist(pic.get(w, h+1),  pic.get(w, height-1) );
				}
				else if(h==height-1 && w==width-1) //heigh-1, width-1 corner Case
				{
					I[h][w] = Dist(pic.get(w-1, h), pic.get(0, h)) + Dist(pic.get(w, 0),  pic.get(w, h-1) );
				}
				else{
					System.out.println("error at ("+h +","+w+")");
				}
			}
		}
		
		
		

		
		ArrayList<Integer> minCostCut = dynamicProgramming.minCostVC(I);
		
		Picture newPic = new Picture( width-1, height);
		
		for(int h = 0; h < height;h++)
		{
			int y = minCostCut.get(h+1);
			
			for(int w = 0; w < y;w++)
			{
				newPic.set( w,h, pic.get(w,h));
			}
			
			for(int w = y+1; w < width-1;w++)
			{
				newPic.set( w-1,h,pic.get(w,h));
			}
		}
		
		pic = newPic;
		
	}
	
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
