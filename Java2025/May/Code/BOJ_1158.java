package Code;

import java.util.*;
import java.io.*;

class BOJ_1158{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1158;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        LinkedList<Integer> list=new LinkedList<>();
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        for(int i=1;i<=N;i++){
            list.add(i);
        }

        StringBuilder sb=new StringBuilder();
        sb.append("<");
        int idx=-1;
        while(!list.isEmpty()){
            // 만약 연산한 idx가 list 길이보다 크다? 그럼 나머지 연산을 한다.
            // 걍 remove하고 idx -1하는 게 나았나..?

            idx+=K;
            System.out.println("idx+K:"+idx);
            int size=list.size();
            if(idx>size){
                idx%=size;
            }

            System.out.println("size:"+size);
            sb.append(list.get(idx)+", ");
            // System.out.println(list.get(idx));
            list.remove(idx);
            idx-=1;
        }

        sb.delete(sb.length()-2, sb.length());
        sb.append(">");
        System.out.println(sb);
        br.close();
    }
}