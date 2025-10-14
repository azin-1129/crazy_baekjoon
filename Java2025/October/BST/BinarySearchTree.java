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
    // 후계자의 자식 정보 갱신
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

    private Node<E> deleteNode(Node<E> node){
        if(node!=null){
            // 자식 노드가 없을 경우
            if(node.left==null && node.right==null){
                // 삭제하려는 노드가 root일 경우, root 끊고 종료
                if(node==root){
                    root=null;
                }else{ // 단말 노드
                    node=null;
                }
                return null;
            }

            // 양쪽 자식 노드가 모두 있을 경우
            if(node.left!=null && node.right!=null){
                Node<E> replacement=getSuccessorAndUnlink(node);

                // 삭제된 노드에 대체 노드를 부여한다.
                node.value=replacement.value;
            }else if(node.left!=null){ // 왼쪽 노드만 존재
                if(node==root){
                    node=node.left;
                    root=node;
                    root.parent=null;
                }else{
                    node=node.left;
                }
            }else{ // 오른쪽 노드만 존재
                if(node==root){
                    node=node.right;
                    root=node;
                    root.parent=null;
                }else{
                    node=node.right;
                }
            }
        }
        return node;
    }

    public E remove(Object o){
        if(root==null){
            return null;
        }
        return removeUsingComparable(o);

    }

    /**
     * @param ㅁㄴㅇㄹ
     * @return ㅁㄴㅇㄹ
     */
    // 정상적으로 삭제되었을 경우 value를 반환, else null
    private E removeUsingComparable(Object value){
        E oldVal=(E) value;
        Node<E> parent=null, current=root;
        // 삭제하고자 하는 노드가 부모의 왼쪽 자식인지, 오른쪽 자식인지 판단
        boolean hasLeft=false;

        if(root==null){
            return null;
        }

        // 삭제할 노드 탐색
        Comparable<? super E> compValue=(Comparable<? super E>) value;
        do{
            int resComp=compValue.compareTo(current.value);
            if(resComp==0){
                break;
            }

            parent=current;
            if(resComp<0){
                hasLeft=true;
                current=current.left;
            }else{
                hasLeft=false;
                current=current.right;
            }
        }while(current!=null);

        // 삭제할 노드를 찾지 못함
        if(current==null){
            return null;
        }

        // 삭제하려는 노드가 root일 경우
        if(parent==null){
            deleteNode(current);
            size--;
            return oldVal;
        }

        // 정상적으로 삭제 되었다면, 삭제 노드의 부모 정보 갱신
        // 삭제 노드가 부모 노드의 왼쪽 자식
        if(hasLeft){
            parent.left=deleteNode(current);
            if(parent.left!=null){
                parent.left.parent=parent;
            }
        }else{ // 삭제 노드가 부모 노드의 오른쪽 자식
            parent.right=deleteNode(current);
            if(parent.right!=null){
                parent.right.parent=parent;
            }
        }
        size--;
        return oldVal;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public boolean contains(Object o){
        return containsUsingComparable(o);
    }

    private boolean containsUsingComparable(Object o){
        Comparable<? super E> value=(Comparable<? super E>) o;

        Node<E> node=root;
        while(node!=null){
            int res=value.compareTo(node.value);
            if(res<0){
                node=node.left;
            }else if(res>0){
                node=node.right;
            }else{
                return true;
            }
        }

        return false;
    }

    public void clear(){
        size=0;
        root=null; // GC
    }
}
