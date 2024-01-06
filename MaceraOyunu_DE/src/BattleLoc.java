import java.util.Random;
import java.util.Set;

public class BattleLoc extends Location {

    private Obstacle obstacle;
    private String award;

    private String obsAward;

    private String locAward;

    private int maxObstacle;

    private boolean isDieSnake;

    public BattleLoc() {
        super();
    }


    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }


    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getObsAward() {
        return obsAward;
    }

    public void setObsAward(String obsAward) {
        this.obsAward = obsAward;
    }

    public String awardName(int obsId) {
        switch (obsId) {
            case 0:
                return "Ödül yok";
            case 1:
                return "Yemek";
            case 2:
                return "Odun";
            case 3:
                return "Su";
            case 4:
                Random r = new Random();
                this.isDieSnake = true;
                double awardSnakeProbobilty = r.nextDouble();
                double possibilityGun = r.nextDouble();
                double possibilityArmor = r.nextDouble();
                double possibilityMoney = r.nextDouble();
                int randomGunOrArmor = r.nextInt(2);
                if (awardSnakeProbobilty <= 0.15) {

                    if (randomGunOrArmor == 1) {
                        if (possibilityGun <= 0.2) {
                            System.out.println("%20 ihtimal ile Tüfek kazandın ");
                            getPlayer().addEarnAwardList("Tüfek");
                        } else if (possibilityGun <= 0.3) {
                            System.out.println("%20 ihtimal ile Kılıç kazandın ");
                            getPlayer().addEarnAwardList("Kılıç");
                        } else {
                            System.out.println("%50 ihtimal ile Tabanca kazandın ");
                            getPlayer().addEarnAwardList("Tabanca");
                        }
                    } else {
                        if (possibilityArmor <= 0.2) {
                            System.out.println("%20 ihtimal ile Ağır Zırh kazandın ");
                            getPlayer().addEarnAwardList("Ağır Zırh");
                        } else if (possibilityArmor <= 0.3) {
                            System.out.println("%20 ihtimal ile Orta Zırh kazandın ");
                            getPlayer().addEarnAwardList("Orta Zırh");
                        } else {
                            System.out.println("%50 ihtimal ile Hafif Zırh kazandın ");
                            getPlayer().addEarnAwardList("Hafif Zırh ");
                        }
                    }

                } else if (awardSnakeProbobilty <= 0.25) {
                    if (possibilityArmor <= 0.2) {
                        System.out.println("%20 ihtimal ile 10 para kazandın ");
                        getPlayer().addEarnAwardList("10 para");
                    } else if (possibilityArmor <= 0.3) {
                        System.out.println("%20 ihtimal ile 5 para kazandın ");
                        getPlayer().addEarnAwardList("5 para");
                    } else {
                        System.out.println("%50 ihtimal ile 1 para kazandın ");
                        getPlayer().addEarnAwardList("1 para");
                    }
                } else {
                    return "Şansızsın ödül yok :/";
                }
                getPlayer().addEarnAwardList("*");
        }
        return "";
    }


    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {

        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;

    }

    public String getLocAward() {
        return locAward;
    }

    public void setLocAward(String locAward) {
        this.locAward = locAward;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public int randomObstacle() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    @Override
    public boolean onLocation() {

        int obsNumber = this.randomObstacle();
        System.out.println("----------------------");
        System.out.println("Şuan buradasınız " + this.getName());
        System.out.println("Dikkatli ol burada  " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor olabilir ");
        System.out.println("<S>avaş veya <K>aç ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S")) {

            if (combat(obsNumber)) {
                System.out.println(this.getName() + " daki tüm düşmanları yendiniz ! ve " + this.awardName(this.getObstacle().getId()) + " ödülü kazandınız  ");


                this.getPlayer().addEarnAwardList(this.awardName(this.getObstacle().getId()));
                this.getPlayer().setEarnAwardListStat(true);
                return true;
            }
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println(" Öldünüz :/ ");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {

        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrgHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                //  System.out.println("<V>ur veya <K>aç ");
                System.out.println("<K>açmak istiyor musun eğer <S>avaşacaksan %50 ihtimal ile ilk vuruş hakkı belirlenecek ");
                String selectCombat = input.nextLine().toUpperCase();
                System.out.println("----------------------");
                Random rd = new Random();
                int randomSelect = rd.nextInt(2);

                if (randomSelect == 0) {
                    System.out.println("İlk vurma hakkı oyuncunum");
                } else {
                    System.out.println("İlk vurma hakkı canavarın");
                }
                //   if (selectCombat.equals("V")) {
                if (randomSelect == 0 && !selectCombat.equals("K")) {
                    System.out.println("Siz vurdunuz ! ");
                    this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                }
                  /*
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar bize vurdu ");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                        */

                if (randomSelect == 1 && !selectCombat.equals("K")) {
                    System.out.println();
                    System.out.println("Canavar bize vurdu ");
                    int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                    if (obstacleDamage < 0) {
                        obstacleDamage = 0;
                    }
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                    afterHit();
                }

            }
        }


        if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
            System.out.println("Düşmanı Yendiniz ");
            System.out.println(this.getObstacle().getAward() + " para kazandınız ");
            this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
            System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
        } else {
            return false;
        }
        return true;
    }


    public void afterHit() {
        System.out.println("Canınınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + "Canı  " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("Oyuncu değerleri");
        System.out.println("-----------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName() + " Değerleri ");
        System.out.println("----------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        //  System.out.println("Para : " + this.getObstacle().get);
    }

}
