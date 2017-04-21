import java.awt.Color;
import java.util.ArrayList;

public class ImageProcessor {
	
	Picture pic;

	public ImageProcessor(String imageFile){
		pic = new Picture(imageFile);
	}
	
	public Picture reduceWidth(double x){
		
		for (int i = (int) x; i>0 ;i--)
		{
			reduceWidthbyOne();
		}
		return pic;
		
	}
	
	private void reduceWidthbyOne()
	{
		int height = pic.height();
		
		int width = pic.width();
		
		
		
		Integer[][] Yimportance = new Integer[height][width];
		for(int i = 0; i < height;i++)
		{
			for(int j = 0; j <width;j++)
			{
				if(i==0)
				{
					Yimportance[i][j] = Dist(pic.get(height-1, j), pic.get(i+1, j));
				}
				else if(i==height-1)
				{
					Yimportance[i][j] = Dist(pic.get(i-1, j), pic.get(0, j));
				}
				else{
					Yimportance[i][j] = Dist(pic.get(i-1, j), pic.get(i+1, j));
				}
			}
		}
		Integer[][] Ximportance = new Integer[height][width];
		for(int i = 0; i < height;i++)
		{
			for(int j = 0; j <width;j++)
			{
				if(j==0)
				{
					Ximportance[i][j] = Dist(pic.get(i, width-1), pic.get(i, j+1));
				}
				else if(j==width-1)
				{
					Ximportance[i][j] = Dist(pic.get(i,0), pic.get(i,j+1));
				}
				else{
					Ximportance[i][j] = Dist(pic.get(i, j-1), pic.get(i, j+1));
				}
			}
		}
		
		int[][] I = new int[height][width];
		for(int i = 0; i < height;i++)
		{
			for(int j = 0; j <width;j++)
			{
				I[i][j] = Ximportance[i][j]  + Yimportance[i][j];
			}
		}
		
		ArrayList<Integer> minCostCut = dynamicProgramming.minCostVC(I);
		
		Picture newPic = new Picture( width-1, height);
		
		for(int i = 0; i < height;i++)
		{
			for(int j = 0; j <width;j++)
			{
				
			}
		}
		
		
		
		
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
