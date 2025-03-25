package Code;

import java.util.*;
import java.io.*;

class BOJ_25304{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=25304;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        int X=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());

        int result=0;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");

            int price=Integer.parseInt(st.nextToken());
            int count=Integer.parseInt(st.nextToken());

            result+=(price*count);
        }

        if(result==X){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

        br.close();
    }
}