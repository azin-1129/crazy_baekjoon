package Code;
import java.util.*;
import java.io.*;

class BOJ_1525{

    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    static Map<String, Integer> status=new HashMap<>();
    static String correct="123456780";
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\";
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input1525.txt"));

        String arrayInfo="";
        for(int i=0;i<3;i++){
            arrayInfo+=br.readLine();
        }

        arrayInfo=arrayInfo.replaceAll(" ","");

        status.put(arrayInfo,0);
        System.out.println(BFS(arrayInfo));

        br.close();
    }
    static int BFS(String arrayInfo){
        Queue<String> q=new ArrayDeque<>();
        
        q.add(arrayInfo);

        while(!q.isEmpty()){
            String current=q.poll();
            // System.out.println(current);

            int position=current.indexOf('0');
            int cx=position/3;
            int cy=position%3;
            int depth=status.get(current);

            if(current.equals(correct)){
                return depth;
            }

            for(int i=0;i<4;i++){
                int nx=cx+dx[i];
                int ny=cy+dy[i];

                if(nx<0 || ny<0 || nx>=3 || ny>=3){
                    continue;
                }

                int newPosition=nx*3+ny;

                char change=current.charAt(newPosition);
                String next=current.replace(change, 'x');

                next=next.replace('0', change);
                next=next.replace('x','0');

                if(!status.containsKey(next)){ // 중복 처리도 덤
                    status.put(next, depth+1);
                    q.add(next);
                }
            }
        }

        return -1;
    }
}