package Code;

import java.util.*;
import java.io.*;

class BOJ_3151{
    static int N;
    static long result;
    static int[] scores;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=3151;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        scores=new int[N];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            scores[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(scores);
        // 합이 음수라 필요한 target 값이이 양수일 때,
        // 중복을 거르기 위해 left는 j+1에서 시작한다.
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                int needScore=-1*(scores[i]+scores[j]);
                result+=binarySearch(j+1, N-1, needScore);
            }
        }

        System.out.println(result);
        br.close();
    }
    static int binarySearch(int left, int right, int target){
        // 중복값을 허용하기 때문에,
        // 찾고자 하는 값이 scores 내 총 몇개 있는지 확인 필요
        int lower=lowerIdx(left, right, target);
        int upper=upperIdx(left, right, target);

        return upper-lower;
    }
    static int lowerIdx(int left, int right, int target){

        while(left<=right){
            int middle=(left+right)/2;

            if(scores[middle]>=target){
                right=middle-1;
            }else{
                left=middle+1;
            }
        }
        return left;
    }
    static int upperIdx(int left, int right, int target){
        while(left<=right){
            int middle=(left+right)/2;

            if(scores[middle]>target){
                right=middle-1;
            }else{
                left=middle+1;
            }
        }
        return left;
    }
}