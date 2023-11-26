package classes;

public class Pokemon {

	private int id;
	private String pokemon;
	private String type_1;
	private String type_2;
	private int attack;
	private int defense;
	private int hp;
	private int special_attack;
	private int special_defense;
	private int speed;
	private String ability_1;
	private String ability_2;
	private String ability_hidden;
	public Pokemon(int id, String pokemon, String type_1, String type_2, int attack, int defense, int hp,
			int special_attack, int special_defense, int speed, String ability_1, String ability_2,
			String ability_hidden) {
		super();
		this.id = id;
		this.pokemon = pokemon;
		this.type_1 = type_1;
		this.type_2 = type_2;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
		this.special_attack = special_attack;
		this.special_defense = special_defense;
		this.speed = speed;
		this.ability_1 = ability_1;
		this.ability_2 = ability_2;
		this.ability_hidden = ability_hidden;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPokemon() {
		return pokemon;
	}
	public void setPokemon(String pokemon) {
		this.pokemon = pokemon;
	}
	public String getType_1() {
		return type_1;
	}
	public void setType_1(String type_1) {
		this.type_1 = type_1;
	}
	public String getType_2() {
		return type_2;
	}
	public void setType_2(String type_2) {
		this.type_2 = type_2;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getSpecial_attack() {
		return special_attack;
	}
	public void setSpecial_attack(int special_attack) {
		this.special_attack = special_attack;
	}
	public int getSpecial_defense() {
		return special_defense;
	}
	public void setSpecial_defense(int special_defense) {
		this.special_defense = special_defense;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getAbility_1() {
		return ability_1;
	}
	public void setAbility_1(String ability_1) {
		this.ability_1 = ability_1;
	}
	public String getAbility_2() {
		return ability_2;
	}
	public void setAbility_2(String ability_2) {
		this.ability_2 = ability_2;
	}
	public String getAbility_hidden() {
		return ability_hidden;
	}
	public void setAbility_hidden(String ability_hidden) {
		this.ability_hidden = ability_hidden;
	}
	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", pokemon=" + pokemon + ", type_1=" + type_1 + ", type_2=" + type_2 + ", attack="
				+ attack + ", defense=" + defense + ", hp=" + hp + ", special_attack=" + special_attack
				+ ", special_defense=" + special_defense + ", speed=" + speed + ", ability_1=" + ability_1
				+ ", ability_2=" + ability_2 + ", ability_hidden=" + ability_hidden + "]";
	}
	public static Pokemon parseCSV(String line) {
		//chatgpt assisted
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].replaceAll("\"", ""));
        String pokemon = parts[1].replaceAll("\"", "");
        String type_1 = parts[2].replaceAll("\"", "");
        String type_2 = parts[3].replaceAll("\"", "");
        int attack = Integer.parseInt(parts[4].replaceAll("\"", ""));
        int defense = Integer.parseInt(parts[5].replaceAll("\"", ""));
        int hp = Integer.parseInt(parts[6].replaceAll("\"", ""));
        int special_attack = Integer.parseInt(parts[7].replaceAll("\"", ""));
        int special_defense = Integer.parseInt(parts[8].replaceAll("\"", ""));
        int speed = Integer.parseInt(parts[9].replaceAll("\"", ""));
        String ability_1 = parts[10].replaceAll("\"", "");
        String ability_2 = parts[11].replaceAll("\"", "");
        String ability_hidden = parts[12].replaceAll("\"", "");

        return new Pokemon(id, pokemon, type_1, type_2, attack, defense, hp,
                special_attack, special_defense, speed, ability_1, ability_2, ability_hidden);
    }
	
}
