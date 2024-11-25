package Code;

import java.util.*;
import java.io.*;

class BOJ_12738{
    static int[] memo;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=12738;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        memo=new int[N];

        int[] numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int length=0;

        for(int i=0;i<N;i++){
            if(numbers[i]>memo[length]){
                memo[++length]=numbers[i];
            }else{
                memo[binarySearch(0, length, numbers[i])]=numbers[i];
            }
            System.out.println(Arrays.toString(memo));
            System.out.println("length:"+length);
        }

        // System.out.println(Arrays.toString(memo));
        System.out.println(length);

        br.close();
    }
    // 이진탐색을 통해 memo[i-1] < num <= memo[i]인 곳을 찾아 memo[i] = num을 갱신
	static int binarySearch(int left, int right, int key) {
		int mid =0;
		while(left<right) { // left==right일 때 종료
            // System.out.println("L"+left+" R"+right);
			mid = (left+right)/2;
			if(memo[mid] < key) { // memo[i-1] < num
				left = mid+1;
			}else { // num <= memo[i]
				right = mid;
			}
		}

        // System.out.println("L"+left+" R"+right);

		return right;
	}
}