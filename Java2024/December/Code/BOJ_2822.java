package Code;

import java.util.*;
import java.io.*;

class QuickSort2822{
    public void quickSort(int[][] arr){
        this.quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(int[][] arr, int left, int right){
        if(left>=right){
            return;
        }

        int pivot=partitioning(arr, left, right);

        quickSort(arr, left, pivot-1);
        quickSort(arr, pivot+1, right);
    }

    private int partitioning(int[][] arr, int left, int right){
        int pivotIdx=Integer.valueOf(left);
        int pivot=arr[left][1];

        while(left<right){
            while(arr[right][1]>pivot && left<right){
                right--;
            }

            while(arr[left][1]<=pivot && left<right){
                left++;
            }
            swap(arr, left, right);
        }
        swap(arr, left, pivotIdx);

        return left;
    }

    private void swap(int[][] arr, int x, int y){
        int[] temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }
}
class BOJ_2822{
    static int PROBLEM_COUNT=8;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2822;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[][] scores=new int[PROBLEM_COUNT][2];

        // 총 8개 문제
        // 점수: 문제 푸는 데 걸린 시간+난이도
        // 못 풀었으면 0점.
        // 총 점수: 가장 높은 점수 5개의 합

        for(int i=0;i<PROBLEM_COUNT;i++){
            scores[i][0]=i+1;
            scores[i][1]=Integer.parseInt(br.readLine());
        }

        QuickSort2822 qs=new QuickSort2822();
        qs.quickSort(scores);
        
        int scoring=0;

        int[] idxForSort=new int[5];

        int index=0;
        for(int i=PROBLEM_COUNT-1;i>=PROBLEM_COUNT-5;i--){
            scoring+=scores[i][1];
            idxForSort[index++]=scores[i][0];
        }

        System.out.println(scoring);

        Arrays.sort(idxForSort);

        System.out.println(Arrays.toString(idxForSort).replaceAll("[\\[\\],]",""));

        br.close();
    }
}