package Code;

import java.io.*;
import java.util.*;

class Celebrity{
    int P;
    int C;

    Celebrity(int P, int C){
        this.P=P;
        this.C=C;
    }

    @Override
    public String toString(){
        return "P:"+this.P+", C:"+this.C;
    }
}

public class Softeer_Hanyang_Popularity_Exceeding_Competition {
    static Celebrity[] lis;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="Hanyang_Popularity_Exceeding_Competition";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt")); 
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int X=0;
        int isPChanged=0;

        Celebrity[] arr=new Celebrity[N];
        lis=new Celebrity[N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            int P=Integer.parseInt(st.nextToken());
            int C=Integer.parseInt(st.nextToken());
            isPChanged+=P;

            arr[i]=new Celebrity(P, C);
        }

        // 인기도 기반 LIS
        if(isPChanged>0){
            lis[0]=arr[0];
            int lisIdx=0;
            int arrIdx=1;

            while(arrIdx<arr.length){
                if(lis[lisIdx].P<arr[arrIdx].P){
                    lis[++lisIdx]=arr[arrIdx];
                }else{
                    int idx=binarySearch(0, lisIdx, arr[arrIdx].P);
                    lis[idx]=arr[arrIdx];
                }
                arrIdx++;
            }

            // 그다음에 LIS Celebrity 기반으로 인기도 상승 시키면 될 듯
            for(int i=0;i<lis.length;i++){
                if(lis[i]==null){
                    break;
                }

                if((lis[i].P-X)<=lis[i].C){
                    X++;
                }
            }
        }else{ // TC 2
            for(int i=0;i<arr.length;i++){
                X++;
            }
        }
        System.out.println(X);

        br.close();
    }
    static int binarySearch(int left, int right, int target){
        while(left<right){
            int mid=(left+right)/2;

            if(lis[mid].P<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }

        return right;
    }
}
