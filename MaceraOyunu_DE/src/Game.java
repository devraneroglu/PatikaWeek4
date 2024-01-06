import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macere Oyununa Hoşgeldiniz : ");
        System.out.println("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sevgili  " + player.getName() + " adaya hoşgeldin !");
        System.out.println("Karekter seçin :   ");
        player.selectChar();
        Location location = null;

        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("Bölgeler");
            System.out.println();
            System.out.println("1- Güvenli Ev");
            System.out.println("2- Store");
            System.out.println("3- Mağara");
            System.out.println("4- Orman");
            System.out.println("5- Nehir");
            System.out.println("6- Maden");
            System.out.println("0- Oyundan Çık ! ");

            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new Safehouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (!(player.controlAward("Yemek"))) {
                        location = new Cave(player);
                        break;
                    } else {
                        System.out.println(" Yemek ödülü kazanıldı. Başka bir Lokasyon seç ! ");
                    }

                case 4:
                    if (!(player.controlAward("Odun"))) {
                        location = new Forest(player);
                        break;
                    } else {
                        System.out.println(" Odun ödülü kazanıldı. Başka bir Lokasyon seç ! ");
                    }
                case 5:
                    if (!(player.controlAward("Su"))) {
                        location = new River(player);
                        break;
                    } else {
                        System.out.println(" Su ödülü kazanıldı. Başka bir Lokasyon seç ! ");
                    }
                case 6:
                    if (!(player.controlAward("*"))) {
                        location = new Mine(player);
                        break;
                    } else {
                        System.out.println(" Su ödülü kazanıldı. Başka bir Lokasyon seç ! ");
                    }

                default:
                    location = new Safehouse(player);
            }

            if (location == null) {
                System.out.println("Oyun bitti !");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("Game Over");
                break;
            }
            if (player.controlAward("Yemek") && player.controlAward("Odun") && player.controlAward("Su") && player.controlAward("*")) {
                player.printInfo();
                System.out.println("Tebrikler oyunu kazandın :)  ");
                break;
            }

        }
    }
}
