abstract class Character {
    String name;

    Character(String name) {
        this.name = name;
    }

    abstract void attack();
}

class Warrior extends Character {
    Warrior(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " attacks with a sword and has strong defense!");
    }
}

class Mage extends Character {
    Mage(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " casts a powerful spell using mana!");
    }
}

class Archer extends Character {
    Archer(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " shoots an arrow with long-range precision!");
    }
}

public class GameBattle {
    public static void main(String[] args) {
        Character[] army = new Character[3];
        army[0] = new Warrior("Thor");
        army[1] = new Mage("Merlin");
        army[2] = new Archer("Legolas");

        for (Character c : army) {
            c.attack();
        }
    }
}
