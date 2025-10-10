package Code;

import java.util.*;
import java.io.*;

class BOJ_13414{
    static class Student implements Comparable<Student>{
    String number;
    int idx;

    public Student(String number, int idx){
        this.number=number;
        this.idx=idx;
    }

    @Override
    public int compareTo(Student s){
        return Integer.compare(this.idx, s.idx);
    }

    @Override
    public String toString(){
        return number+"번 학생의 idx:"+idx;
    }
}
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=13414;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
    
        st=new StringTokenizer(br.readLine(), " ");
        // 수강 가능 인원 K, 대기목록 길이 L
        int K=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());
        // 학번, idx를 보유한 해시맵
        Map<String, Integer> hm=new HashMap<>();

        String[] numbers=new String[L];
        for(int i=0;i<L;i++){
            numbers[i]=br.readLine();
            hm.put(numbers[i], i);
        }

        Queue<Student> pq=new PriorityQueue<>();
        for(String s:hm.keySet()){
            // System.out.println(s+": "+hm.get(s));
            pq.offer(new Student(s, hm.get(s)));
        }

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<K;i++){
            if(pq.isEmpty()){
                break;
            }
            
            sb.append(pq.poll().number+"\n");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}