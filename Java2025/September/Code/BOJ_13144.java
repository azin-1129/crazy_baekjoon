package Code;

import java.util.*;
import java.io.*;

class BOJ_13144{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=13144;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int[] numbers=new int[N];
        Set<Integer> visited=new HashSet<>();
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            numbers[i]=Integer.parseInt(st.nextToken());
        }

        long answer=0;
        int start=0;
        int end=0;
        while(end<N){
            while(visited.contains(numbers[end])){
                visited.remove(numbers[start]);
                start+=1;
            }
            visited.add(numbers[end]);
            answer+=(end-start+1);
            end+=1;
        }

        System.out.println(answer);
        br.close();
    }
}