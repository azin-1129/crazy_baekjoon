package Code;

import java.util.*;
import java.io.*;

class BOJ_4796{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=4796;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int idx=1;
        StringBuilder sb=new StringBuilder();
        while(true){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            int L=Integer.parseInt(st.nextToken());
            if(L==0){
                break;
            }
            int P=Integer.parseInt(st.nextToken());
            int V=Integer.parseInt(st.nextToken());

            sb.append("Case "+idx+": ");
            if((V%P)>0){
                if((V%P)>=L){
                    sb.append(L*(V/P)+L+"\n");
                }else{
                    sb.append(L*(V/P)+(V%P)+"\n");
                }
            }else{
                sb.append(L*(V/P)+"\n");
            }

            idx+=1;
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}