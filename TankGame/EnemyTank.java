package com.zwpra.TankGame;

import java.awt.*;
import java.util.Iterator;

public class EnemyTank extends Tank implements Runnable{
    Thread threadStart;
    Thread threadBullet;
    Iterator iterator;
    Boolean  switchThread = false;

    public EnemyTank(int x, int y, Color color, char direction) {
        super(x, y, color, direction);
//        threadStart=new Thread(this);
//        threadStart.start();

    }

    @Override
    public void run() {
        int sleepTime= 50;
        while (true)
        {   //发射子弹状态
            if (switchThread){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                this.bullets.add(new Bullet(this));
                threadBullet = new Thread(this.bullets.get(this.bullets.size()-1));
                threadBullet.start();
            }
            //走路zhuangtai
            else {
                switch (getDirection()) {
                    case 'U': {
                        for (int i = 0; i < (int) (Math.random() * 30 + 30); i++) {

                            if (switchThread) {
                                break;
                            }

                            if (getY() < 0)
                                setY(0);
                            setY(getY() - 5);


                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    break;
                    case 'D': {
                        for (int i = 0; i < (int) (Math.random() * 30 + 30); i++) {
                            if (switchThread) {
                                break;
                            }
                            if (getY() > 700)
                                setY(700);
                            setY(getY() + 5);

                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    break;
                    case 'R': {
                        for (int i = 0; i < (int) (Math.random() * 30 + 30); i++) {
                            if (switchThread) {
                                break;
                            }
                            if (getX() > 1400)
                                setX(1400);
                            setX(getX() + 5);
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    break;
                    case 'L': {
                        for (int i = 0; i < (int) (Math.random() * 30 + 30); i++) {
                            if (switchThread) {
                                break;
                            }
                            if (getX() < 0)
                                setX(0);
                            setX(getX() - 5);
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    break;
                }
            }
            if (!switchThread) {
                switch ((int) (Math.random() * 4)) {
                    case 0:
                        setDirection('U');
                        break;
                    case 1:
                        setDirection('D');
                        break;
                    case 2:
                        setDirection('R');
                        break;
                    case 3:
                        setDirection('L');
                        break;

                }
            }

//            setDirection('U');
            if (!isTankAlive) {
                break; //退出线程.
            }
        }
    }
}
