abstract class Hero{
	String name;
	int blood;
	int hurt;   //装备武器后的攻击力
	int addBlood;	//防御回复血量
	double coldTime;   //冷却时间
	int waitTime=0;    //晕眩时间

	//攻击技能
	public void hit(Hero h){
		if (h.blood<=0) {
			System.out.println(this.getClass().getSimpleName() + "胜出!");
			System.exit(0);
		}
		h.setBlood(h.getBlood()-hurt);
		System.out.println(this.getClass().getSimpleName()+"攻击"+h.getClass().getSimpleName()+","
			+ "造成伤害"+hurt+"点————"
			+ this.getClass().getSimpleName() + ":" + this.blood + ","
			+h.getClass().getSimpleName() + ":" + h.blood);
	}
	//防御技能，自身回血
	public void defense(Hero h){
		setBlood(getBlood()+addBlood);
		System.out.println(this.getClass().getSimpleName()+"防御回血"+addBlood+"点————"
			+ this.getClass().getSimpleName() + ":" + this.blood + ","
			+ h.getClass().getSimpleName() + ":" + h.blood);
	}

	public void setBlood(int blood){
		this.blood=blood;
	}
	public int getBlood(){
		return blood;
	}
}

//武器库
interface Iweapon{
	interface Wand{
		public abstract int wand();
	}
	interface Axe{
		public abstract int axe();
	}
	interface Arrow{
		public abstract int arrow();
	}
	interface Dagger{
		public abstract int dagger();
	}
	interface Sword{
		public abstract int sword();
	}
	interface Gun{
		public abstract int gun();
	}
	interface Staff{
		public abstract int staff();
	}
	interface Claw{
		public abstract int claw();
	}
	interface Totem{
		public abstract int totem();
	}	
}

class MAGE extends Hero implements Iweapon.Wand{
	public MAGE(){
		blood=100;
		coldTime=1.0;
		waitTime=0;
		addBlood=10;
		hurt=wand();
	}
	public int wand(){
		return 15;
	}
}

class HUNTER extends Hero implements Iweapon.Arrow{
	public HUNTER(){
		blood=100;
		coldTime=1.2;
		waitTime=0;
		addBlood=10;
		hurt=arrow();
	}
	public int arrow(){
		return 20;
	}
}

class WARRIOR extends Hero implements Iweapon.Axe{
	public WARRIOR(){
		blood=100;
		coldTime=1.4;
		waitTime=0;
		addBlood=10;
		hurt=axe();
	}
	public int axe(){
		return 20;
	}
}

class ROGUE extends Hero implements Iweapon.Dagger{
	public ROGUE(){
		blood=100;
		coldTime=1.6;
		waitTime=0;
		addBlood=10;
		hurt=dagger();
	}
	public int dagger(){
		return 20;
	}
}

class KNIGHT extends Hero implements Iweapon.Sword{
	public KNIGHT(){
		blood=100;
		coldTime=1.8;
		waitTime=0;
		addBlood=10;
		hurt=sword();
	}
	public int sword(){
		return 20;
	}	
}

class HACKBUTEER extends Hero implements Iweapon.Gun{
	public HACKBUTEER(){
		blood=100;
		coldTime=2.0;
		waitTime=0;
		addBlood=10;
		hurt=gun();
	}
	public int gun(){
		return 20;
	}	
}

class PRIEST extends Hero implements Iweapon.Staff{
	public PRIEST(){
		blood=100;
		coldTime=2.2;
		waitTime=0;
		addBlood=10;
		hurt=staff();
	}
	public int staff(){
		return 20;
	}	
}

class DRUID extends Hero implements Iweapon.Claw{
	public DRUID(){
		blood=100;
		coldTime=2.4;
		waitTime=0;
		addBlood=10;
		hurt=claw();
	}
	public int claw(){
		return 20;
	}	
}

class SHAMAN extends Hero implements Iweapon.Totem{
	public SHAMAN(){
		blood=100;
		coldTime=2.6;
		waitTime=0;
		addBlood=10;
		hurt=totem();
	}
	public int totem(){
		return 20;
	}
}