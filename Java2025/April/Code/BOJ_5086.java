package Code;

import java.util.*;
import java.io.*;

class BOJ_5086{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=5086;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        StringBuilder sb=new StringBuilder();
        while(true){
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            if(x==0 & y==0){
                break;
            }

            // 1. y가 x로 나누어 떨어지는가? factor
            // 2. x가 y로 나누어 떨어지는가? multiple
            // 3. else= neither
            if(y%x==0){
                sb.append("factor\n");
            }else if(x%y==0){
                sb.append("multiple\n");
            }else{
                sb.append("neither\n");
            }
        }
        System.out.println(sb);
        br.close();
    }
}