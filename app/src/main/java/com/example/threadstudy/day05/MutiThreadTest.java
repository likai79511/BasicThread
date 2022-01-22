package com.example.threadstudy.day05;

public class MutiThreadTest {

    public static void main(String[] args) {
        Source source = new Source();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n--thread-step-02");
            source.sendEvent();
        }).start();

        MutiThreadTest t1 = new MutiThreadTest(source);
    }


    int count;

    public MutiThreadTest(Source source) {
        source.registListener(new EventListener() {
            @Override
            public void onEvent() {
                System.out.println(" count: " + count);
            }
        });
        for (int i = 0; i < 10000; i++)
            System.out.print(i + ",");
        count = 100;
    }

    static class Source {
        private EventListener listener;

        void registListener(EventListener lis) {
            listener = lis;
        }

        void sendEvent() {
            if (listener != null) {
                listener.onEvent();
            } else {
                System.out.println("listener has no initial");
            }
        }
    }

    interface EventListener {
        void onEvent();
    }


}
