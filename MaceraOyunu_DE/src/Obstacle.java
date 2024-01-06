public class Obstacle {
    private int id;
    private int damage;
    private int health;

    private int award;

    private String name;

    private int orgHealth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Obstacle(int id, int damage, int health, int award, String name) {
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.award = award;
        this.name = name;
        this.orgHealth = health;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
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

    public int getAward() {
        return award;
    }

    public int getOrgHealth() {
        return orgHealth;
    }

    public void setOrgHealth(int orgHealth) {
        this.orgHealth = orgHealth;
    }

    public void setAward(int award) {
        this.award = award;
    }
}
