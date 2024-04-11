package Gybing;
import java.io.*;
import java.util.*;

class Main {
  public static byte[][] grid;
  public static byte w;
  public static int l;

  public static int getScore(byte x, int y, byte dir) {
    if (y == l - 1) return grid[y][x];
    if (x == 0 || x == w - 1) {
      int score = 0;
      if (y == 0) {
        score = grid[y][x];
      } else {
        score = - grid[y][x] * grid[y][x];
      }
      return score + getScore((byte) (x - dir), y + 1, (byte) -dir);
    } else {
      return Math.max(grid[y][x] + getScore((byte) (x + dir), y + 1, dir), - grid[y][x] * grid[y][x] + getScore((byte) (x - dir), y + 1, (byte) -dir));
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    w = Byte.parseByte(st.nextToken());
    l = Integer.parseInt(st.nextToken());
    grid = new byte[l][w];
    int maxScore = (int) Double.NEGATIVE_INFINITY; // -Infinity
    for (int y = 0; y < l; y++) {
      st = new StringTokenizer(br.readLine());
      for (int x = 0; x < w; x++) {
        grid[y][x] = Byte.parseByte(st.nextToken());
      }
    }
    maxScore = Math.max(getScore((byte) 0, 0, (byte) -1), getScore((byte) (w - 1), 0, (byte) 1));
    for (byte x = 1; x < grid[0].length - 1; x++) {
      maxScore = Math.max(maxScore, Math.max(getScore(x, 0, (byte) 1), getScore(x, 0, (byte) -1)));
    }
    System.out.println(maxScore);
  }
}

/*
3 10
9 1 1 
5 2 3 
1 2 0 
10 6 5 
2 9 9 
8 3 1 
6 4 0 
10 5 5 
1 5 5 
3 3 3
*/