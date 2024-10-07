import java.util.*;

// 그때그때 가능해진 가지 다 탐색하고 돌아와야 하는데.. 어떡하냐 이건
// 아이디어가 더 뻗질 않음

class Solution {
    class Node{
		int to;
		int weight;
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Node [to=" + to + ", weight=" + weight + "]";
		}
	}

    public int solution(int[] info, int[][] edges) {
        List<Stack<Integer>> edgeInfo=new ArrayList<>();
        Stack<Integer> stack=new Stack<>();
        int sheepCnt=0;
        int wolfCnt=0;
        
        int idx;
    	int nextStat;
        
        for(int i=0;i<info.length;i++){
            edgeInfo.add(new Stack<Integer>());
        }
    	
        for(int i=0;i<edges.length;i++) {
        	edgeInfo.get(edges[i][0]).add(edges[i][1]);
        	System.out.println(edges[i][0]+"은 "+edges[i][1]+"과 연결");
        }
        // edgeInfo 완성
        
        stack.add(0);

        if(info[edgeInfo.get(0).elementAt(0)]==0){
            stack.add(edgeInfo.get(0).elementAt(0));
            edgeInfo.get(0).removeElementAt(0);
        }else{
            stack.add(edgeInfo.get(0).elementAt(1));
            edgeInfo.get(0).removeElementAt(1);
        }

        sheepCnt+=2;
        
        idx=stack.peek(); // init
        
        System.out.println("그래프:"+edgeInfo);
        while(!stack.isEmpty()) {
            System.out.println("양:"+sheepCnt+", 늑대:"+wolfCnt);
        	System.out.println("stack info:"+stack);
        	System.out.println("시작점:"+idx);
        	if(edgeInfo.get(idx).size()==0) { // 말단
        		System.out.println("연결된 곳이 없다.");
        		stack.pop();
        		
        		if(stack.size()==0) break; // 돌아왔다.
        		
        		idx=stack.peek();
        		continue; // 연결된 곳이 없다.
        	}else { // 이외
        		nextStat=edgeInfo.get(idx).pop();
        		System.out.println("다음 노드:"+nextStat);
        		System.out.println("해당 노드의 정보는:"+info[nextStat]);
        		if(info[nextStat]==0) { // 양
        			System.out.println("다음 노드는 양. stack add"+nextStat);
        			sheepCnt+=1;
        			stack.add(nextStat);
        			idx=nextStat;
        		}else { // 늑대
        			if((wolfCnt+1)>=sheepCnt) { // 큰일남.
        				System.out.println("이리 가면 안됨.");
        				continue; // 더 진행 X
        			}else {
        				wolfCnt++;
        				stack.add(nextStat); // 일단 진행
        				idx=nextStat;
        			}
        		}
        	}
        }
        
        return sheepCnt;
    }
}