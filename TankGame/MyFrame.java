package com.zwpra.TankGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class MyFrame extends JFrame {
    MyPanel mp;
    public MyFrame(){
        mp=new MyPanel();
        this.add(mp);
        this.setSize(1920,1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        Thread PanelThread = new Thread(mp);
        PanelThread.start();
    }
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
    }
}
class MyPanel extends JPanel implements KeyListener,Runnable {
    Hero hero1;
//    static int BulletNum=0;
    Color enemyColor;
//    Vector<Tank> listAllTanks;
    Vector<EnemyTank> listAllEnemyTanks;
    Thread BulletThread;
    Iterator<Bullet> bulletIterator;
    Iterator<EnemyTank> tankIterator;
    Iterator<Boom> boomIterator;
    Vector<Boom> booms;
    Image img1;
    Image img2;
    int enemyTankSize = 6;
    boolean isGameOver = false;

    {
        try {
            img1 = ImageIO.read(new FileInputStream("C:\\Users\\13723\\IdeaProjects\\teso\\src\\com\\zwpra\\TankGame\\image\\boom1.jpg"));
            img2 = ImageIO.read(new FileInputStream("C:\\Users\\13723\\IdeaProjects\\teso\\src\\com\\zwpra\\TankGame\\image\\boom2.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyPanel(){
        enemyColor =new Color(0, 89, 255);
        hero1=new Hero(900,800,new Color(255, 0, 0),'L');
//        enemy1=new EnemyTank(100,0,EnemyColor,'D');
//        enemy2=new EnemyTank(200,0,EnemyColor,'D');
//        enemy3=new EnemyTank(300,0,EnemyColor,'D');
//        listAllTanks =new Vector<>();
        listAllEnemyTanks=new Vector<>();
        for (int i = 0; i < enemyTankSize; i++) {
            //???????????????????????????
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 200, enemyColor,'D');
            listAllEnemyTanks.add(enemyTank);
            Thread thread = new Thread(enemyTank);
            thread.setName("??????"+(i+1)+"??????");
            thread.start();
        }

        booms = new Vector<Boom>();
//        listAllTanks.add(hero1);
//        listAllTanks.add(enemy1);
//        listAllTanks.add(enemy2);
//        listAllTanks.add(enemy3);
//        listAllEnemyTanks.add(enemy1);
//        listAllEnemyTanks.add(enemy2);
//        listAllEnemyTanks.add(enemy3);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //????????????
        if (hero1!=null&&hero1.isTankAlive == true)
         {
         g.setColor(hero1.getColor());
        if (hero1.getDirection() == 'U') {
            g.fill3DRect(hero1.getX(), hero1.getY(), 10, 60, false);//????????????????????????
            g.fill3DRect(hero1.getX() + 30, hero1.getY(), 10, 60, false);//????????????????????????
            g.fill3DRect(hero1.getX() + 10, hero1.getY() + 10, 20, 40, false);//??????????????????
            g.fillOval(hero1.getX() + 10, hero1.getY() + 20, 20, 20);//??????????????????
            g.drawLine(hero1.getX() + 20, hero1.getY() + 30, hero1.getX() + 20, hero1.getY());//????????????
        } else if (hero1.getDirection() == 'D') {
            g.fill3DRect(hero1.getX(), hero1.getY(), 10, 60, false);//????????????????????????
            g.fill3DRect(hero1.getX() + 30, hero1.getY(), 10, 60, false);//????????????????????????
            g.fill3DRect(hero1.getX() + 10, hero1.getY() + 10, 20, 40, false);//??????????????????
            g.fillOval(hero1.getX() + 10, hero1.getY() + 20, 20, 20);//??????????????????
            g.drawLine(hero1.getX() + 20, hero1.getY() + 30, hero1.getX() + 20, hero1.getY() + 60);//????????????
        } else if (hero1.getDirection() == 'R') {
            g.fill3DRect(hero1.getX(), hero1.getY(), 60, 10, false);//????????????????????????
            g.fill3DRect(hero1.getX(), hero1.getY() + 30, 60, 10, false);//????????????????????????
            g.fill3DRect(hero1.getX() + 10, hero1.getY() + 10, 40, 20, false);//??????????????????
            g.fillOval(hero1.getX() + 20, hero1.getY() + 10, 20, 20);//??????????????????
            g.drawLine(hero1.getX() + 30, hero1.getY() + 20, hero1.getX() + 60, hero1.getY() + 20);
        } else if (hero1.getDirection() == 'L') {
            g.fill3DRect(hero1.getX(), hero1.getY(), 60, 10, false);//????????????????????????
            g.fill3DRect(hero1.getX(), hero1.getY() + 30, 60, 10, false);//????????????????????????
            g.fill3DRect(hero1.getX() + 10, hero1.getY() + 10, 40, 20, false);//??????????????????
            g.fillOval(hero1.getX() + 20, hero1.getY() + 10, 20, 20);//??????????????????
            g.drawLine(hero1.getX() + 30, hero1.getY() + 20, hero1.getX(), hero1.getY() + 20);//????????????
        } else {
            System.out.println("???????????????");
        }
    }

        //????????????
        if (listAllEnemyTanks != null) {
            for (Tank character : listAllEnemyTanks) {

                g.setColor(character.getColor());
                if (character.getDirection() == 'U') {
                    g.fill3DRect(character.getX(), character.getY(), 10, 60, false);//????????????????????????
                    g.fill3DRect(character.getX() + 30, character.getY(), 10, 60, false);//????????????????????????
                    g.fill3DRect(character.getX() + 10, character.getY() + 10, 20, 40, false);//??????????????????
                    g.fillOval(character.getX() + 10, character.getY() + 20, 20, 20);//??????????????????
                    g.drawLine(character.getX() + 20, character.getY() + 30, character.getX() + 20, character.getY());//????????????
                } else if (character.getDirection() == 'D') {
                    g.fill3DRect(character.getX(), character.getY(), 10, 60, false);//????????????????????????
                    g.fill3DRect(character.getX() + 30, character.getY(), 10, 60, false);//????????????????????????
                    g.fill3DRect(character.getX() + 10, character.getY() + 10, 20, 40, false);//??????????????????
                    g.fillOval(character.getX() + 10, character.getY() + 20, 20, 20);//??????????????????
                    g.drawLine(character.getX() + 20, character.getY() + 30, character.getX() + 20, character.getY() + 60);//????????????
                } else if (character.getDirection() == 'R') {
                    g.fill3DRect(character.getX(), character.getY(), 60, 10, false);//????????????????????????
                    g.fill3DRect(character.getX(), character.getY() + 30, 60, 10, false);//????????????????????????
                    g.fill3DRect(character.getX() + 10, character.getY() + 10, 40, 20, false);//??????????????????
                    g.fillOval(character.getX() + 20, character.getY() + 10, 20, 20);//??????????????????
                    g.drawLine(character.getX() + 30, character.getY() + 20, character.getX() + 60, character.getY() + 20);
                } else if (character.getDirection() == 'L') {
                    g.fill3DRect(character.getX(), character.getY(), 60, 10, false);//????????????????????????
                    g.fill3DRect(character.getX(), character.getY() + 30, 60, 10, false);//????????????????????????
                    g.fill3DRect(character.getX() + 10, character.getY() + 10, 40, 20, false);//??????????????????
                    g.fillOval(character.getX() + 20, character.getY() + 10, 20, 20);//??????????????????
                    g.drawLine(character.getX() + 30, character.getY() + 20, character.getX(), character.getY() + 20);//????????????
                } else {
                    System.out.println("???????????????");
                }
            }
        }
//            if (character.bullets!=null)
//            {
        //  System.out.println("????????????");
//                for (int i = 0; i < bullets.size() ; i++) {
////                    System.out.println("??????x"+bullets.get(i).x+"????????????y??????"+bullets.get(i).y);
//                    g.fillOval(bullets.get(i).x,bullets.get(i).y,10,10);
//                }
//                for (Bullet bullet :character.bullets) {
//                    g.fillOval(bullet.x,bullet.y,10,10);
//                }

        //??????????????????
        if(hero1 != null) {
            if (hero1.bullets != null) {
                g.setColor(hero1.getColor());
                for (Bullet bullet : hero1.bullets) {
//                    System.out.println("???????????????");
                    g.fillOval(bullet.x, bullet.y, 10, 10);
                }
            }
        }
        //??????????????????

        for (Tank tank :listAllEnemyTanks) {
            if (tank.bullets != null) {
                g.setColor(tank.getColor());
                for (Bullet bullet : tank.bullets) {
//                    System.out.println("???????????????");
                    g.fillOval(bullet.x, bullet.y, 10, 10);
                }
            }
        }

            /*
            ??????????????????
             */
        if (booms != null) {
            boomIterator=booms.iterator();
           while(boomIterator.hasNext()){
               Boom boom=boomIterator.next();
                if (boom.life > 4) {
                    g.drawImage(img2, boom.x, boom.y, this);
                }
                else if (boom.life>=0)
                {
                    g.drawImage(img1, boom.x, boom.y, this);
                }
                boom.life--;
                if (boom.life<0)
                {
                    boom.isLive=false;
                    boomIterator.remove();
                }

            }
        }

        if(isGameOver)
        {
//            System.out.println("????????????????????????");
            g.setColor(new Color(0x9C7D00));
            g.fillRect(0,0,1920,1080);
            g.setColor(new Color(0x05FFFF));
            g.drawString("????????????",1920/2-200,1080/2-200);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(hero1 != null) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                hero1.setDirection('D');
                hero1.setY(hero1.getY() + 5);
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                hero1.setDirection('U');
                hero1.setY(hero1.getY() - 5);
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                hero1.setDirection('L');
                hero1.setX(hero1.getX() - 5);
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                hero1.setDirection('R');
                hero1.setX(hero1.getX() + 5);
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_J) {
                // System.out.println("?????????J");
                hero1.bullets.add(new Bullet(hero1));
                BulletThread = new Thread(hero1.bullets.get(hero1.bullets.size() - 1));
                BulletThread.start();

//            System.out.println("??????????????????:"+listAllEnemyTanks.size());
            }
            if (e.getKeyCode() == KeyEvent.VK_K) {
                System.out.println("???????????????" + hero1.bullets.size());
                soutAllTankPosition();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while (true) {
            //????????????????????????
            if (!hero1.isTankAlive) {
                if(booms.size()==0)
                isGameOver = true;
                if (isGameOver)
                {
                    for (EnemyTank tank :listAllEnemyTanks) {
                        tank.bullets.clear();
                    }
                    listAllEnemyTanks.clear();
                    hero1 = null;
                    break;
                }
            }
            //?????????????????????????????????????????????
            for (EnemyTank tank :listAllEnemyTanks) {
                if (enteredAttackArea(tank))
//                    System.out.println("????????????")
                                        ;
            }
            //???????????????????????????
            if(hitHeroTank())
            {
                //??????????????????
                for (EnemyTank tank :listAllEnemyTanks) {
                bulletIterator = tank.bullets.iterator();
                while (bulletIterator.hasNext()) {
                    Bullet next = bulletIterator.next();
                    if (!next.isAlive) {
                        bulletIterator.remove();
                    }
                }
            }
                //????????????
                booms.add(new Boom(hero1.getX(), hero1.getY()));
                hero1.isTankAlive = false;
            }
            //????????????????????????
            bulletIterator =hero1.bullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet next =  bulletIterator.next();
                if (!next.isAlive)
                {
                    bulletIterator.remove();
                }
            }

            //????????????????????????
            if(hitEnemyTank()) {
                bulletIterator = hero1.bullets.iterator();
                while (bulletIterator.hasNext()) {
                    Bullet next = bulletIterator.next();
                    if (!next.isAlive) {
                        bulletIterator.remove();
                    }
                }
                    //????????????
                    tankIterator=listAllEnemyTanks.iterator();
                    while (tankIterator.hasNext()) {
                        Tank currTank = tankIterator.next();
                        if (!currTank.isTankAlive) {
//                            System.out.println("????????????????????????");
                            booms.add(new Boom(currTank.getX(), currTank.getY()));
                            tankIterator.remove();
//                            System.out.println("??????????????????"+listAllEnemyTanks);
                        }

                    }



            }
            /*
            * ????????????????????????????????????
            * */


            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
           // System.out.println("????????????repaint");
        }
        System.out.println("run  repaint");
        repaint();
    }
    //????????????????????????tank??????
    public boolean hitEnemyTank(){
        boolean flag=false;
        for (Bullet bullet :hero1.bullets) {
            for (Tank tank :listAllEnemyTanks) {
                if (bullet.x > tank.getX() && bullet.x < tank.getX() + 40
                        && bullet.y > tank.getY() && bullet.y < tank.getY() + 60)
                {
                      bullet.isAlive=false;
                      tank.isTankAlive=false;
                      flag=true;
                }

            }

        }
        return flag;
    }

    public boolean hitHeroTank(){
        boolean flag=false;
        for (EnemyTank tank :listAllEnemyTanks) {

        for (Bullet bullet :tank.bullets) {

                if (bullet.x > hero1.getX() && bullet.x < hero1.getX() + 40
                        && bullet.y > hero1.getY() && bullet.y < hero1.getY() + 60)
                {
                    bullet.isAlive=false;
                    hero1.isTankAlive=false;
                    flag=true;
                }
            }
        }

        return flag;
    }




    public void soutAllTankPosition(){
        int count = 1;
        for (EnemyTank tank :listAllEnemyTanks) {
            System.out.println("??????"+count+"?????????"+tank.switchThread);
            System.out.println("??????"+count+"?????????"+(hero1.getX()-tank.getX())+"  "+(hero1.getY()-tank.getY()));
            System.out.println("??????"+count+"????????????:  "+tank.getX()+"   "+tank.getY());

            count++;
        }
        System.out.println("????????????"+booms.size()+"   "+"?????????????????????"+isGameOver);
    }

    public boolean enteredAttackArea(EnemyTank tank)
    {
        if(hero1.getX()-tank.getX()<40&&hero1.getX()-tank.getX()>0) {
            tank.switchThread = true;
            if (tank.getY() > hero1.getY()) {
                tank.setDirection('U');
            } else {
                tank.setDirection('D');
            }
            return true;
        }
        else if (hero1.getY()-tank.getY()<40&&hero1.getY()-tank.getY()>0)
        {
            tank.switchThread = true;
            if (tank.getX() > hero1.getX()) {
                tank.setDirection('L');
            } else {
                tank.setDirection('R');
            }
            return  true;
        }
        tank.switchThread = false;
        return false;
    }
}