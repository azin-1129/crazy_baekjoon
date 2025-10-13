package BST;

import java.util.Comparator;

public class BinarySearchTree<E> {
    private Node<E> root;
    private int size;
    
    private final Comparator<? super E> comparator;

    public BinarySearchTree(){
        this(null);
    }

    public BinarySearchTree(Comparator<? super E> comparator){
        this.comparator=comparator;
        this.root=null;
        this.size=0;
    }

    public boolean add(E value){
        return addUsingComparable(value)==null;
    }

    private E addUsingComparable(E value){
        Node<E> current=root;

        if(current==null){
            root=new Node<E>(value);
            size++;
            return null;
        }

        Node<E> currentParent;

        @SuppressWarnings("unchecked")
        Comparable<? super E> compValue=(Comparable<? super E>) value;

        int compResult; // 비교 결과 (+, 0, -)

        do{
            currentParent=current;

            compResult=compValue.compareTo(current.value);
            if(compResult<0){
                current=current.left;
            }else if(compResult>0){
                current=current.right;
            }else{
                return value;
            }
        }while(current!=null);

        Node<E> newNode=new Node<E>(value, currentParent);
        if(compResult<0){
            currentParent.left=newNode;
        }else{
            currentParent.right=newNode;
        }

        size++;
        return null;
    }

    // 삭제할 노드가 왼쪽 자식, 오른쪽 자식 모두 보유했을 경우 사용
    private Node<E> getSuccessorAndUnlink(Node<E> node){
        Node<E> currentParent=node;
        Node<E> current=node.right;

        if(current.left==null){ // 오른쪽 자식의 오른쪽 자식이 대체값이다.
            currentParent.right=current.right;
            if(currentParent.right!=null){ // 오른쪽 자식의 오른쪽 자식이 null이 아닐 경우, 부모 정보 갱신
                current.right.parent=currentParent;
            }
            current.right=null;
            return current;
        }

        // 가장 작은 왼쪽 자식을 탐색한다.
        while(current.left!=null){
            currentParent=current;
            current=current.left;
        }

        // 가장 작은 왼쪽 자식의 오른쪽 자식이 존재하지 않는다면,
        // null 처리만 하고 끝난다.
        currentParent.left=current.right;
        // 가장 작은 왼쪽 자식의 오른쪽 자식이 존재한다면
        if(currentParent.left!=null){
            // 가장 작은 왼쪽 자식의 오른쪽 자식과
            // 가장 작은 왼쪽 자식의 부모를 이어준다. (버려지면 안 됨)
            currentParent.left.parent=currentParent;
        }

        current.right=null;
        return current;
    }
}
