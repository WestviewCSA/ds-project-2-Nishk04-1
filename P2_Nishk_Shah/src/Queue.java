import java.util.Stack;

public class Queue<N> {
	
	private Stack<N> stackA;
	private Stack<N> stackB;
	
	public Queue(){
	    stackA = new Stack<N>();
	    stackB = new Stack<N>();
	}
	
	//adding to the queue
	public void enqueue(N el) {
		stackA.push(el);
	}
	
	//return NULL if empty
	public N dequeue() {
			if(stackB.empty()) {
				while(!stackA.empty()) {
					stackB.push(stackA.pop()); //order is now reversed in stackB
				}
			}
			if(!stackB.empty()) {
				return stackB.pop();
			}
		
		
		return null;
	}
	
	public N peek() {
		if(stackB.size() != 0) {
			return stackB.pop();
		}
		else if(stackA.size() > 0) {
			while(!stackA.empty()) {
				stackB.push(stackA.pop());
			}
			return stackB.pop();
		}
		return null;
	}
	
	public int size() {
		return stackA.size() + stackB.size();
	}
	
	public boolean empty() {
		return stackA.empty() && stackB.empty();
		
	}
	
	public String toString() {
		String emp = "[";
		Stack<N> tempA = new Stack<N>();
		Stack<N> tempB = new Stack<N>();
		
		while(!stackA.empty()){
		    tempA.push(stackA.pop());
		}
		
		while(!stackB.empty()){
		    tempB.push(stackB.pop());
		}
		
		while(!tempB.empty()) {
			if(!tempB.empty()) {
			    N el = tempB.pop();
			    emp += el + ", ";
			    stackB.push(el);
			}
		}
		
		while(!tempA.empty()){
		    if(!tempA.empty()){
		        N el = tempA.pop();
		        emp += el + ", ";
		        stackA.push(el);
		    }
		}
		if(emp.length() > 1) {
			emp = emp.substring(0, emp.length() -2);
		}
		return emp + "]";
	}
	
}