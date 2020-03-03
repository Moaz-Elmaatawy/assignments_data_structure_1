package eg.edu.alexu.csd.datastructure.iceHockey.cs22;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class Find_player implements IPlayersFinder {

    private static Point[] points ;
    private static int minx;
    private static int maxx;
    private static int miny;
    private static int maxy;

    static class sort_points implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.x-o2.x;
        }
    }

    static int check(char[][]arr,int x,int y,char k,int n,int m){
        int result=0;
        if(x<0||y<0||x>=n||y>=m||arr[x][y]!=k){
            return 0;
        }
        minx=Math.min(minx,x);
        maxx=Math.max(maxx,x);
        miny=Math.min(miny,y);
        maxy=Math.max(maxy,y);
        arr[x][y]='-';
        result+=check(arr,x+1,y,k,n,m);
        result+=check(arr,x-1,y,k,n,m);
        result+=check(arr,x,y+1,k,n,m);
        result+=check(arr,x,y-1,k,n,m);
        return result+1;
    }


    @Override
    public Point[] findPlayers(String[] photo, int team, int threshold) {
        int m=photo.length;
        if (m==0){return null;}
        int n=photo[0].length();
        char[][]arr=new char[n][m];

        for(int i=0;i<m;++i)
            for (int j=0;j<n;++j)
                arr[j][i]=photo[i].charAt(j);

        char k=(char)(team+'0');int counter=0;points=new Point[50];
        for(int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }
        for(int i=0;i<m;++i)
            for (int j=0;j<n;++j){
                if(arr[j][i]==k){
                    minx=maxx=j;miny=maxy=i;
                    int area=check(arr,j,i,k,n,m)*4;
                    if(area>=threshold){
                        points[counter].setLocation(minx+maxx+1,miny+maxy+1);
                        ++counter;
                    }
                }
            }
        Arrays.sort(points,0,counter,new sort_points());
            Point[] p=new Point[counter];
            System.arraycopy(points,0,p,0,counter);


            return  p;
    }
}
