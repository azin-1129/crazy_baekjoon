package Code;

import java.util.*;
import java.io.*;

class QuickSort{
    public void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(int[] arr, int left, int right){
        if(left>=right){
            return;
        }

        // 왼쪽 피벗 파티셔닝
        int pivot=partition(arr, left, right);

        // 분할+정복 재귀
        quickSort(arr, left, pivot-1);
        quickSort(arr, pivot+1, right);
    }

    // 파티셔닝 함수
    private int partition(int[] arr, int left, int right){
        // System.out.println("partitioning... L:"+left+", R:"+right);
        int pivotIdx=Integer.valueOf(left); // 기존 피벗 idx
        // System.out.println("현재 pivotIdx는 "+pivotIdx+", pivot value는 "+arr[left]);

        // 왼쪽 피벗 값. 비교를 위함
        int pivot=arr[left];

        // 피벗을 기준으로 교차 전까지 좌, 우 값 교환
        while(left<right){
            // System.out.println("L:"+left+", R:"+right);

            // 오른쪽부터 피벗보다 작은 값 탐색
            while(arr[right]>pivot && left<right){
                // System.out.println("while right");
                right--;
            }

            // 왼쪽부터 피벗보다 큰 값 탐색
            while(arr[left]<=pivot && left<right){
                // System.out.println("while left");
                left++;
            }

            swap(arr, left, right);
        }

        // System.out.println("left와 right가 교차했습니다.");
        // System.out.println("pivotIdx "+pivotIdx+"와 left "+left+"를 스와핑합니다.");

        // 교차 시 indedx 기반 스와핑.
        swap(arr, pivotIdx, left);

        // System.out.println("최종 arr:"+Arrays.toString(arr));

        // System.out.println("return?: "+left);
        // System.out.println();
        // left 인덱스가 기존 왼쪽 피벗에 위치함.
        return left;
    }

    // 스와핑 함수
    private void swap(int[] arr, int x, int y){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }
}
class BOJ_16435{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=16435;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0]; // 과일 수
        int L=inputs[1]; // 초기 스네이크버드 길이

        int[] heights=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 오름차순 정렬 후 판단해야겠네

        QuickSort quickSort=new QuickSort();
        quickSort.quickSort(heights);

        // System.out.println("정렬 결과:"+Arrays.toString(heights));

        for(int i=0;i<heights.length;i++){
            if(heights[i]<=L){
                L+=1;
            }
        }

        System.out.println(L);
        br.close();
    }
}