import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
  
//三个人物的基类
abstract class Person {
  int val;       //生命值
  double coldTime;   //冷却时间
  int waitTime;    //晕眩时间
  int fight;     //攻击力
  int chanceHit;   //发起主动技能的概率
  int chanceDefense; //发起防御技能的概率
  
  abstract void hit(Person p);  //攻击技能
  
  abstract int defense(Person p); //防御技能，返回被伤害点数
}
  
class DH extends Person {
  public DH() {
    val = 10;
    coldTime = 1.0;
    fight = 30;
    chanceHit = 3;   //表示30%的概率
    chanceDefense = 3;
    waitTime = 0;
  }
  
  Random rand = new Random();
  boolean hitFlag = false;   //主动技能发动的标识
  boolean defenseFlag = false; //防御技能发动的标识
  
  public void hit(Person p) {
    if (rand.nextInt(10) < chanceHit) { //发动主动技能
      int hurt = p.defense(this);
      p.val = p.val - hurt;
      if (p.val <= 0) {
        System.out.println(this.getClass().getSimpleName() + "胜出!");
        System.exit(0);
      }
      val = val + hurt;
      if (val > 600)
        val = 600;
      hitFlag = true;   //标记主动技能已经发动
    } else { //进行普通攻击
      int hurt = p.defense(this);
      p.val = p.val - hurt;
      if (p.val <= 0) {
        System.out.println(this.getClass().getSimpleName() + "胜出!");
        System.exit(0);
      }
    }
    System.out.println(this.getClass().getSimpleName() + "攻击"
        + p.getClass().getSimpleName() + ","
        + this.getClass().getSimpleName()
        + (this.hitFlag ? "发动攻击技能  " : "未发动攻击技能  ")
        + p.getClass().getSimpleName()
        + (this.defenseFlag ? "发动防御技能  " : "未发动防御技能  ")
        + this.getClass().getSimpleName() + ":" + this.val + ","
        + p.getClass().getSimpleName() + ":" + p.val);
    hitFlag = false;   //
    defenseFlag = false; //重置标记，下次重用
  }
  
  public int defense(Person p) {
    if (rand.nextInt(10) < chanceDefense) {
      defenseFlag = true;  //标记防御技能已经发动
      return 0;
    } else {
      return p.fight;
    }
  }
}

class BM extends Person {
  public BM() {
    val = 50;
    coldTime = 1.5;
    fight = 40;
    chanceHit = 3;
    chanceDefense = 3;
    waitTime = 0;
  }
  
  int count = 0;  //防御技能发动的次数
  int temp = 40;  //攻击力，值同fight
  boolean hitFlag = false;
  boolean defenseFlag = false;
  Random rand = new Random();
  
  public void hit(Person p) {
    if (rand.nextInt(10) < chanceHit) {
      fight = fight * 2;  //发动双倍攻击
      hitFlag = true;
    }
    int hurt = p.defense(this);
    p.val = p.val - hurt;
    fight = temp;   //还原为单倍攻击
    if (p.val <= 0) {
      System.out.println(this.getClass().getSimpleName() + "胜出!");
      System.exit(0);
    }
    System.out.println(this.getClass().getSimpleName() + "攻击"
        + p.getClass().getSimpleName() + ","
        + this.getClass().getSimpleName()
        + (this.hitFlag ? "发动攻击技能  " : "未发动攻击技能  ")
        + p.getClass().getSimpleName()
        + (this.defenseFlag ? "发动防御技能  " : "未发动防御技能  ")
        + this.getClass().getSimpleName() + ":" + this.val + ","
        + p.getClass().getSimpleName() + ":" + p.val);
    hitFlag = false;
    defenseFlag = false;
  }
  
  public int defense(Person p) {
    if (rand.nextInt(10) < chanceDefense) {
      if (count != 0) {
        p.val = p.val - p.fight;
        count++;
        defenseFlag = true;
        if (p.val <= 0) {
          System.out.println(this.getClass().getSimpleName() + "胜出!");
          System.exit(0);
        }
      }
    }
    return p.fight;
  }
}
  
class MK extends Person {
  public MK() {
    val = 100;
    coldTime = 2.5;
    fight = 50;
    chanceDefense = 6;
    chanceHit = 3;
    waitTime = 0;
  }
  
  boolean hitFlag = false;
  boolean defenseFlag = false;
  Random rand = new Random();
  
  public void hit(Person p) {
    if (rand.nextInt(10) < chanceHit) {
      p.waitTime = 3;  //使对方晕眩3s
      hitFlag = true;
    }
    int hurt = p.defense(this);
    p.val = p.val - hurt;
    if (p.val <= 0) {
      System.out.println(this.getClass().getSimpleName() + "胜出!");
      System.exit(0);
    }
    System.out.println(this.getClass().getSimpleName() + "攻击"
        + p.getClass().getSimpleName() + ","
        + this.getClass().getSimpleName()
        + (this.hitFlag ? "发动攻击技能  " : "未发动攻击技能  ")
        + p.getClass().getSimpleName()
        + (this.defenseFlag ? "发动防御技能  " : "未发动防御技能  ")
        + this.getClass().getSimpleName() + ":" + this.val + ","
        + p.getClass().getSimpleName() + ":" + p.val);
    hitFlag = false;
    defenseFlag = false;
  }
  
  public int defense(Person p) {
    if (rand.nextInt(10) < chanceDefense) {
      defenseFlag = true;
      return p.fight / 2;  //防御技能发动，伤害减半
    }
    return p.fight;
  }
}

public class Rpg {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    System.out.println("在这里输入两个人物进行PK,以英文逗号分隔： [BM，DH，MK]");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Class<Person> c1;
    Class<Person> c2;
    try {
      String temp = br.readLine();
      String[] str = temp.split(",");
      if (str.length != 2) {
        throw new Exception("输入格式有误，按默认PK");
      }
      c1 = (Class<Person>) Class.forName(str[0].toUpperCase());
      c2 = (Class<Person>) Class.forName(str[1].toUpperCase());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      c1 = (Class<Person>) Class.forName("BM");
      c2 = (Class<Person>) Class.forName("DH");
    }
    try {
      Person p1 = c1.newInstance();
      Person p2 = c2.newInstance();
      long time = System.currentTimeMillis();
      long nextTime1 = (long) (time + p1.coldTime*1000); //
      long nextTime2 = (long) (time + p2.coldTime*1000); //发动攻击的时间
      System.out.println("---游戏开始---");
      while (true) {
        long currenTime = System.currentTimeMillis();
  
        if (nextTime1 < currenTime) { //时间到则发动攻击
          p1.hit(p2);
          nextTime1 += p1.coldTime*1000 + p1.waitTime*1000; //下次攻击时间=冷却时间+被晕眩时间
          p1.waitTime = 0; //回合结束，重置被晕眩时间为0
        }
        if (nextTime2 < currenTime) {
          p2.hit(p1);
          nextTime2 += p2.coldTime*1000 + p2.waitTime*1000;
          p2.waitTime = 0;
        }
      }
    } catch (ClassCastException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}