package test;

public class smart_person {

    public static int N = 5;

    public static int[] status = new int[N];  // 0:思考 1:饥饿 2:进餐
    public static int mutex = 0;

    public static void main(String[] args) {

    }

    public void test(int i){
        if(status[i] == 1 &&  status[(i + 1) % N] != 2 && status[(i + N - 1) % N] != 2){
            status[i] = 2;

        }
    }
}
