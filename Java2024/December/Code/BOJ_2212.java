package Code;

import java.util.*;
import java.io.*;

class QuickSort2212{
    public void quickSort(int[] arr){
        this.quickSort(arr, 0, arr.length-1);
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

        swap(arr, left, pivotIdx);

        return left;
    }

    private void swap(int[] arr, int x, int y){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }
}
class BOJ_2212{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2212;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int K=Integer.parseInt(br.readLine());

        // HashSet 이용해서 distinct int[] 추출
        StringTokenizer st=new StringTokenizer(br.readLine());
        Set<Integer> hs=new HashSet<>();

        while(st.hasMoreTokens()){
            int sensor=Integer.parseInt(st.nextToken());
            hs.add(sensor);
        }

        int[] sensors=new int[hs.size()];
        Iterator<Integer> iter=hs.iterator();
        int index=0;

        while(iter.hasNext()){
            sensors[index++]=iter.next();
        }

        // 정렬되어 있다는 보장이 없으므로 정렬
        QuickSort2212 qs=new QuickSort2212();
        qs.quickSort(sensors);

        // 기존에 입력받은 센서의 개수 N과 상이해졌기 때문에 센서 개수 변수 선언
        int sensorCount=sensors.length;
        int[] diffs=new int[sensorCount-1];

        // 센서 원소 간 거리 차이를 가진 diffs 배열
        for(int i=1;i<sensorCount;i++){
            diffs[i-1]=sensors[i]-sensors[i-1];
        }
        qs.quickSort(diffs);

        int result=0;
        for(int i=0;i<sensorCount-K;i++){
            result+=diffs[i];
        }

        System.out.println(result);

        br.close();
    }
}