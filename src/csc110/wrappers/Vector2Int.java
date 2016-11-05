package csc110.wrappers;

public class Vector2Int {
	public int x, y;
	
	public static Vector2Int newVector (int a, int b){
		Vector2Int vec = new Vector2Int();
		vec.x = a;
		vec.y = b;
		return vec;
	}
}
