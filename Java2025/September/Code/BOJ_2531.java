package Code;

import java.util.*;
import java.io.*;

class BOJ_2531{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2531;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int d=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        int c=Integer.parseInt(st.nextToken());

        int[] sushis=new int[N];
        for(int i=0;i<N;i++){
            sushis[i]=Integer.parseInt(br.readLine());
        }

        Set<Integer> visited=new HashSet<>();
        boolean isCouponDisabled=false;
        int max=0;
        int start=0;
        int end=0;
        while(end<N){
            while(visited.contains(sushis[end])){
                visited.remove(sushis[start]);
                start+=1;
            }
            visited.add(sushis[end]);
            end+=1;

            System.out.println("구간 상태입니다.");
            System.out.println(visited);
            if(max<=(end-start)){
                if((end-start)<=k){
                    if(visited.contains(c)){
                        isCouponDisabled=true;
                    }else{
                        isCouponDisabled=false;
                    }
                    max=end-start;
                    System.out.println("max를 갱신했어요."+max);
                }
            }
        }

        if(isCouponDisabled){
            System.out.println(max);
        }else{
            System.out.println(max+1);
        }

        br.close();
    }
}