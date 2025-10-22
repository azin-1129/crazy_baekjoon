package Code;

import java.util.*;
import java.io.*;

class BOJ_21939{
    static class Problem{
        int level;
        int number;

        public Problem(int level, int number){
            this.level=level;
            this.number=number;
        }

        @Override
        public String toString(){
            return "★"+level+" "+number;
        }
    }
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=21939;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        // 해시맵으로 문제 정보를 기억
        HashMap<Integer, Integer> hm=new HashMap<>();
        // 난이도 순으로 정렬하되, 난이도가 같다면 문제 번호 순으로 정렬
        TreeSet<Problem> ts=new TreeSet<Problem>(new Comparator<Problem>(){
            @Override
            public int compare(Problem p1, Problem p2){
                if(p1.level!=p2.level){
                    return Integer.compare(p1.level, p2.level);
                }else{
                    return Integer.compare(p1.number, p2.number);
                }
            }
        });

        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            int number=Integer.parseInt(st.nextToken());
            int level=Integer.parseInt(st.nextToken());
            Problem problem=new Problem(level, number);
            hm.put(number, level);
            ts.add(problem);
        }

        int M=Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            String command=st.nextToken();

            switch(command){
                case "recommend":
                    int recommendLevel=Integer.parseInt(st.nextToken());
                    // recommendLevel==1? 가장 어려운 문제 : 가장 쉬운 문제
                    if(recommendLevel==1){
                        System.out.println(ts.last().number);
                    }else{
                        System.out.println(ts.first().number);
                    }
                    break;
                case "add":
                    int number=Integer.parseInt(st.nextToken());
                    int level=Integer.parseInt(st.nextToken());
                    hm.put(number, level);
                    ts.add(new Problem(level, number));
                    break;
                case "solved":
                    int solvedNumber=Integer.parseInt(st.nextToken());
                    ts.remove(new Problem(hm.get(solvedNumber), solvedNumber));
                    break;
            }
        }

        br.close();
    }
}