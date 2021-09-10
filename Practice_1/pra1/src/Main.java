import javax.management.monitor.Monitor;


public class Main {
    public static void main(String[] args) {
        Object obj=new Object();
        Thread ping = new Thread(new PingPong("Ping", obj));
        Thread pong = new Thread(new PingPong("Pong", obj));
        ping.start();
        pong.start();
    }
}

class PingPong implements Runnable {
    String str;
    Object obj;
    PingPong(String str, Object obj){
        this.str=str;
        this.obj=obj;
    }
    @Override
    public void run() {
        int i=0;
        synchronized (obj)  {
            while (i<100) {
                System.out.println(str);
                i++;
                try {
                    obj.notify();
                    obj.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
