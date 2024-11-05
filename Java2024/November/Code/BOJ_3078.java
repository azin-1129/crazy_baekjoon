package Code;

import java.util.*;
import java.io.*;

class BOJ_3078{
    static class Student{
        int rank;
        String name;

        Student(int rank, String name){
            this.rank=rank;
            this.name=name;
        }

        @Override
        public String toString(){
            return "("+this.name+":"+this.rank+")";
        }
    }
    static int K;
    static long result;

    static Queue<Student> cq=new LinkedList<>();
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=3078;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        Queue<Student> queue1=new LinkedList<>();
        Queue<Student> queue2=new LinkedList<>();
        Queue<Student> queue3=new LinkedList<>();
        Queue<Student> queue4=new LinkedList<>();
        Queue<Student> queue5=new LinkedList<>();
        Queue<Student> queue6=new LinkedList<>();
        Queue<Student> queue7=new LinkedList<>();
        Queue<Student> queue8=new LinkedList<>();
        Queue<Student> queue9=new LinkedList<>();
        Queue<Student> queue10=new LinkedList<>();
        Queue<Student> queue11=new LinkedList<>();
        Queue<Student> queue12=new LinkedList<>();
        Queue<Student> queue13=new LinkedList<>();
        Queue<Student> queue14=new LinkedList<>();
        Queue<Student> queue15=new LinkedList<>();
        Queue<Student> queue16=new LinkedList<>();
        Queue<Student> queue17=new LinkedList<>();
        Queue<Student> queue18=new LinkedList<>();
        Queue<Student> queue19=new LinkedList<>();
        Queue<Student> queue20=new LinkedList<>();

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N=inputs[0];
        K=inputs[1];

        for(int i=0;i<N;i++){
            String name=br.readLine();
            // System.out.println(name+", 길이:"+name.length());

            switch(name.length()){
                case 1:
                    queue1.offer(new Student(i, name));
                    break;
                case 2:
                    queue2.offer(new Student(i, name));
                    break; 
                case 3:
                    queue3.offer(new Student(i, name));                 
                    break; 
                case 4:
                    queue4.offer(new Student(i, name));
                    break;
                case 5:
                    queue5.offer(new Student(i, name));
                    break;
                case 6:
                    queue6.offer(new Student(i, name));
                    break;
                case 7:
                    queue7.offer(new Student(i, name));
                    break;
                case 8:
                    queue8.offer(new Student(i, name));
                    break;
                case 9:
                    queue9.offer(new Student(i, name));
                    break;
                case 10:
                    queue10.offer(new Student(i, name));
                    break;
                case 11:
                    queue11.offer(new Student(i, name));
                    break;
                case 12:
                    queue12.offer(new Student(i, name));
                    break;
                case 13:
                    queue13.offer(new Student(i, name));
                    break;
                case 14:
                    queue14.offer(new Student(i, name));
                    break;
                case 15:
                    queue15.offer(new Student(i, name));
                    break;
                case 16:
                    queue16.offer(new Student(i, name));
                    break;
                case 17:
                    queue17.offer(new Student(i, name));
                    break;
                case 18:
                    queue18.offer(new Student(i, name));
                    break;
                case 19:
                    queue19.offer(new Student(i, name));
                    break;
                case 20:
                    queue20.offer(new Student(i, name));
                    break;
            }
        }

        solution(queue1);
        solution(queue2);
        solution(queue3);
        solution(queue4);
        solution(queue5);
        solution(queue6);
        solution(queue7);
        solution(queue8);
        solution(queue9);
        solution(queue10);
        solution(queue11);
        solution(queue12);
        solution(queue13);
        solution(queue14);
        solution(queue15);
        solution(queue16);
        solution(queue17);
        solution(queue18);
        solution(queue19);
        solution(queue20);

        System.out.println(result);

        br.close();
    }
    static void solution(Queue<Student> q){
        if(q.isEmpty()){
            return;
        }

        cq.clear(); // 이전 값 클리어

        cq.offer(q.poll());

        // System.out.println("학생 queue:"+q.toString());
        // System.out.println("초기 calc queue:"+cq.toString());

        while(!q.isEmpty()){
            while(!cq.isEmpty()){
                while(!q.isEmpty() && (q.peek().rank-cq.peek().rank)<=K){ // 등수 차가 K 이하인 것만 cq에 넣어둠
                    cq.offer(q.poll());
                }
                // System.out.println(cq);

                if(cq.size()<2){ // 짝을 생성하지 못함
                    cq.poll();
                    if(q.isEmpty()){
                        return;
                    }else{
                        cq.offer(q.poll());
                    }
                }else{
                    result+=cq.size()-1;
                    cq.poll();
                }
            }
        }
    }
}