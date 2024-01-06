import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Player {
    private int damage;
    private int health;
    private int orgHealth;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;
    private boolean earnAwardListStat;
    private Set<String> earnAwardList;


    public void addEarnAwardList(String awardName) {
        this.earnAwardList.add(awardName);

    }

    public void showEarnAwardList() {

        for (String award : earnAwardList) {
            System.out.println("Ödül -->" + award);
        }
    }

    public boolean controlAward(String award) {
        return earnAwardList.contains(award);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    private Scanner input = new Scanner(System.in);


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.earnAwardListStat = false;
        this.earnAwardList = new HashSet<>();
    }


    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("----------------------");
        for (GameChar gameChar : charList) {
            System.out.println("ID : " + gameChar.getId() + "\t Karakter:" + gameChar.getName() + "\t Hasar:" + gameChar.getDamage() + " \t Sağlık" + gameChar.getHealth() + "\t Para :" + gameChar.getMoney());
        }
        System.out.println("----------------------");
        System.out.println(" Lütfen bir karakter girin : ");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("----------------------");
        System.out.println("Karakter : " + this.getCharName() + ", Hasar : " + this.getDamage() + ", Sağlık : " + this.getHealth() + ", Para :" + this.getMoney());

    }


    public void initPlayer(GameChar gameChar) {
        this.setDamage((gameChar.getDamage()));
        this.setHealth(gameChar.getHealth());
        this.setOrgHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());

    }

    public void printInfo() {
        System.out.println(
                " Silah : " + this.getInventory().getWeapon().getName() +
                        " Zırh  : " + this.getInventory().getArmor().getName() +
                        " Bloklama  : " + this.getInventory().getArmor().getBlock() +
                        " , Hasar  : " + this.getTotalDamage() +
                        " , Sağlık : " + this.getHealth() +
                        " , Para :" + this.getMoney());

        if (this.isEarnAwardListStat()) {
            System.out.println("----------------------");
            this.showEarnAwardList();
        }
    }


    public Weapon getWeapon() {
        return this.getInventory().getWeapon();
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {

        return damage + this.inventory.getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0)
            health = 0;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getOrgHealth() {
        return orgHealth;
    }

    public void setOrgHealth(int orgHealth) {
        this.orgHealth = orgHealth;
    }

    public boolean isEarnAwardListStat() {
        return earnAwardListStat;
    }

    public void setEarnAwardListStat(boolean earnAwardListStat) {
        this.earnAwardListStat = earnAwardListStat;
    }

    public Set<String> getEarnAwardList() {
        return earnAwardList;
    }

    public void setEarnAwardList(Set<String> earnAwardList) {
        this.earnAwardList = earnAwardList;
    }
}


