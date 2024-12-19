package Code;

import java.util.*;
import java.io.*;

class QuickSort13164{
    public void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(int[] arr, int left, int right){
        if(left>=right){
            return;
        }

        int pivot=partition(arr, left, right);

        quickSort(arr, left, pivot-1);
        quickSort(arr, pivot+1, right);
    }

    private int partition(int[] arr, int left, int right){
        int pivotIdx=Integer.valueOf(left);

        int pivot=arr[left];

        while(left<right){
            while(arr[right]>pivot && left<right){
                right--;
            }
            while(arr[left]<=pivot && left<right){
                left++;
            }
            swap(arr, left, right);
        }

        swap(arr, pivotIdx, left);

        return left;
    }

    private void swap(int[] arr, int x, int y){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }
}

class BOJ_13164{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=13164;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0]; // 원생 수
        int K=inputs[1]; // 분할 수

        int[] heights=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 조마다 티셔츠 비용은 키 최대-키 최소 만큼 든다.
        // 이때 최소 티셔츠 비용 합계는?

        int[] diff=new int[N-1];

        // 인접한 애기들 키 차이 리스트 뽑기
        for(int i=0;i<heights.length-1;i++){
            diff[i]=heights[i+1]-heights[i];
        }

        QuickSort13164 qs=new QuickSort13164();

        qs.quickSort(diff);

        int result=0;

        // 그루핑
        for(int i=0;i<N-K;i++){
            result+=diff[i];
        }

        System.out.println(result);
        br.close();
    }
}