import java.util.*;
import java.io.*;

class BOJ_2250 {
    static class Node{
        int col;
        int idx;
        Node left;
        Node right;

        Node(int idx){
            this.idx=idx;
        }

        @Override
        public String toString(){
            return "{idx:"+idx+", col:"+col+"}";
        }
    }
    static class BST{
        Node root;

        BST(){
            this.root=new Node(1);
        }

        void init(int idx, int left, int right){
            // System.out.println(idx+"번 노드의 자녀:"+left+", "+right);
            // System.out.println("****"+idx+"노드가 있는지 탐색합니다..");
            Node node=find(this.root, idx);
            // System.out.println("노드를 찾았어요."+node);
            if(left!=-1){
                node.left=new Node(left);
            }
            if(right!=-1){
                node.right=new Node(right);
            }
        }

        Node find(Node node, int idx){
            // System.out.println(idx+"번을 찾는중..");
            // System.out.println("현재:"+node);
            // if x<y : less than 0
            // if x>y : greater than 0
            Queue<Node> q=new ArrayDeque<>();
            q.offer(node);
            while(!q.isEmpty()){
                Node current=q.poll();
                // System.out.println(current);
                if(current.idx==idx){
                    return current;
                }
                if(current.left!=null){
                    q.offer(current.left);
                }
                if(current.right!=null){
                    q.offer(current.right);
                }
            }

            return null;
        }

        void inOrder(int level){
            inOrder(this.root, level);
        }

        void inOrder(Node node, int level){
            if(node.left!=null){
                inOrder(node.left, level+1);
            }
            node.col=order++;
            levels.get(level).add(node);
            if(node.right!=null){
                inOrder(node.right, level+1);
            }
        }
    }
    static List<List<Node>> levels=new ArrayList<>();
    static int order=1;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum=2250;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        BST bst=new BST();
        int N=Integer.parseInt(br.readLine());
        // 레벨 별 노드정보 초기화
        for(int i=0;i<=N;i++){
            levels.add(new ArrayList<>());
        }
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            int number=Integer.parseInt(st.nextToken());
            int left=Integer.parseInt(st.nextToken());
            int right=Integer.parseInt(st.nextToken());

            bst.init(number, left, right);
        }

        // 열 순서 매기고, 레벨 정보 계산
        bst.inOrder(1);

        // System.out.println(levels);
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] arr1, int[] arr2){
                if(arr1[1]==arr2[1]){
                    return Integer.compare(arr1[0], arr2[0]);
                }else{
                    return Integer.compare(arr2[1], arr1[1]);
                }
            }
        });
        for(int lv=1;lv<=N;lv++){
            List<Node> nodes=levels.get(lv);
            int size=nodes.size();
            if(size<=0){ // 더이상 노드 없음
                break;
            }
            int length=(nodes.get(size-1).col-nodes.get(0).col)+1;
            // System.out.println(lv+"층의 폭:"+length);
            int[] temp=new int[2];
            temp[0]=lv;
            temp[1]=length;
            pq.offer(temp);
        }

        int[] result=pq.poll();
        System.out.println(result[0]+" "+result[1]);
        br.close();
    }
}