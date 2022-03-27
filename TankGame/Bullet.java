package com.zwpra.TankGame;

import java.util.Vector;

public class Bullet implements Runnable{
    char direction;
    int x;
    int y;
    boolean isAlive=true;
    Tank tank;

    public Bullet(Tank tank) {
        this.tank = tank;
        direction = tank.getDirection();
        x = tank.getX();
        y = tank.getY();
    }

    @Override
    public void run() {

        if (tank!=null) {
            if (direction=='U'){
            x=x+20;
            }
            if (direction=='D'){
                x=x+20;
                y=y+60;
            }
            if (direction=='R'){
                x=x+60;
                y=y+20;
            }
            if (direction=='L'){
                x=x;
                y=y+20;
            }
            //System.out.println("进入子线程if");
            while (true) {
                if(isAlive==false)
                    break;
                if (direction=='U'){
                 y-=10;
                }
                if (direction=='D'){
                 y+=10;
                }
                if (direction=='R'){
                 x+=10;
                }
                if (direction=='L'){
                  x-=10;
                }
//                System.out.println("子弹的xy坐标"+x+"   "+y);
//                System.out.println("子弹加一");
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (x>=1920||y>=1080||y<0||x<0)
                {
//                    System.out.println("子弹线程销毁");
                    isAlive=false;
                    break;
                }
            }
        }
    }
}
