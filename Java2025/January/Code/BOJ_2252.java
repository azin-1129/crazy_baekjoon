package Code;

import java.util.*;
import java.io.*;

class BOJ_2252{
    static int[] parent;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2252;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0];
        int M=inputs[1];

        parent=new int[N+1];

        for(int i=1;i<N+1;i++){
            parent[i]=i;
        }

        for(int i=0;i<M;i++){
            inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            union(inputs[0], inputs[1]);
        }

        br.close();
    }
    public static void union(int a, int b){
        a=find(a);
        b=find(b);

        if(a>b){
            parent[a]=b;
        }else{
            parent[b]=a;
        }
    }

    public static int find(int x){
        if(parent[x]==x){
            return x;
        }else{
            return find(parent[x]);
        }
    }

    // 만약 parents=[1:1, 2:2, 3:3]
    // union(1,3)
    // parent[3]=1;
    // parents=[1:1, 2:2, 3:1]
    // union(2,3)
    // a=2, b=1
    // parent[2]=1;
    // [1:1, 2:1, 3:1]

    // 만약 parents=[1:1, 2:2, 3:3, 4:4]
    // union(4,2)
    // [1:1, 2:2, 3:3, 4:2]
    // union(3,1)
    // [1:1, 2:2, 3:1, 4:2]
    // union&find만으로는 설명이 잘 안되는 거 같다.
    // parents를 원소 내용으로 오름차순 해서 앞뒤만 가려 출력하면 어떨까 싶은디...
    // [1:1, 3:1, 2:2, 4:2]
    // 집합 원소가 2개면 앞 뒤만 가릴 수 있지만 그 이상이면 ...

    // ex

    // [1:1, 2:1, 3:1, 4:2, 5:2, 6:2]
    // 동적으로 기준을 줄 방법이 있는지? -> 동적 정렬? ㄷㄷ
}