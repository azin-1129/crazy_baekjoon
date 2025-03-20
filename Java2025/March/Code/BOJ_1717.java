package Code;

import java.util.*;
import java.io.*;

class BOJ_1717{
    static int[] parent;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1717;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        parent=new int[N+1];
        for(int i=0;i<=N;i++){
            parent[i]=i;
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());

            int op=Integer.parseInt(st.nextToken());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());

            switch(op){
                case 0: // Union
                    union(a, b);
                    break;
                case 1: // find
                    if(a==b){
                        sb.append("YES\n");
                    }
                    else if(find(a)==find(b)){
                        sb.append("YES\n");
                    }else{
                        sb.append("NO\n");
                    }
                    break;
            }
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);
        br.close();
    }
    static void union(int a, int b){
        if(a==b){
            return;
        }

        int aParent=find(a);
        int bParent=find(b);

        if(aParent>bParent){ // 루트 노드 이어야 함 주의
            parent[aParent]=bParent;
        }else{
            parent[bParent]=aParent;
        }
    }
    static int find(int x){
        if(parent[x]==x){
            return x;
        }else{
            return find(parent[x]);
        }
    }
}