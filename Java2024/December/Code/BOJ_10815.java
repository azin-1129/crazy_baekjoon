package Code;

import java.util.*;
import java.io.*;

class BOJ_10815{
    static int N;
    static int[] cards;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10815;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        N=Integer.parseInt(br.readLine());
        cards=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(cards);

        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡcardsㅡㅡㅡㅡㅡㅡㅡㅡ");

        System.out.println(Arrays.toString(cards));

        int M=Integer.parseInt(br.readLine());
        int[] toFind=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int m:toFind){
            sb.append(binarySearch(m)+" ");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);

        br.close();
    }
    static int binarySearch(int target){
        System.out.println("이진 탐색을 시작합니다. target:"+target);
        int left=0;
        int right=N-1;

        while(left<=right){
            int middle=(left+right)/2;

            System.out.println("middle :"+middle);
            System.out.println("L:"+left+", R:"+right);

            if(cards[middle]>target){
                right=middle-1;
            }else if(cards[middle]<target){
                left=middle+1;
            }else{
                return 1;
            }
        }
        return 0;
    }
}