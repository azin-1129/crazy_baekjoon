package Code;

import java.io.*;
import java.util.*;

public class Softeer_Hanyang_Popularity_Exceeding_Competition {
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="Hanyang_Popularity_Exceeding_Competition";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt")); 
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int X=0;

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            int P=Integer.parseInt(st.nextToken());
            int C=Integer.parseInt(st.nextToken());

            if((int)Math.abs((((double)P-X)))<=C){
                X++;
            }
        }
        System.out.println(X);

        br.close();
    }
}
