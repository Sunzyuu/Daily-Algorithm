import java.util.Stack;

public class MyQueue {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;
    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if(outStack.empty()){
            in2Out(inStack, outStack);
        }
        return outStack.pop();
    }

    public int peek() {
        if(outStack.empty()){
            in2Out(inStack, outStack);
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }

    private void in2Out(Stack<Integer> inStack, Stack<Integer> outStack){
        while(!inStack.empty()){
            outStack.push(inStack.pop());
        }
    }

}
