public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println(" -- Mağazaya Hoşgeldiniz -- ");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println(" 1- Silahlar ");
            System.out.println(" 2- Zırhlar ");
            System.out.println(" 3- Çıkış Yap ");
            System.out.print(" Seçiminiz :  ");
            int selectCase = input.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.println("Geçersiz bir değer girdiniz");
                selectCase = input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("---Silahlar---");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + "-" + w.getName() + " Para : " + w.getPrice() + " Hasar : " + w.getDamage());
        }
        System.out.println("0-Çıkış yap");
    }

    public void buyWeapon() {

        System.out.print("Bir silah seçiniz : ");
        int selectWeaponId = input.nextInt();
        while (selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length) {
            System.out.println("Geçersiz bir ifade girdiniz ");
            selectWeaponId = input.nextInt();
        }

        if (selectWeaponId != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponId);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır. ");
                } else {
                    System.out.println(selectedWeapon.getId() + "Silahını satın aldınız ! ");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                    //  System.out.println("Önceki silahınız : " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    //  System.out.println("Yeni silahınız : " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }


    }

    public void printArmor() {
        System.out.println("---Zırhlar---");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + "-" + a.getName() + " Para : " + a.getPrice() + " Zırh : " + a.getBlock());
        }
        System.out.println("0-Çıkış yap");
    }

    public void buyArmor() {
        System.out.print("Bir Zırh seçiniz : ");
        int selectArmorId = input.nextInt();
        while (selectArmorId < 0 || selectArmorId > Armor.armors().length) {
            System.out.println("Geçersiz bir ifade girdiniz ");
            selectArmorId = input.nextInt();
        }

        if (selectArmorId != 0) {
            Armor selectedArmor = Armor.getArmorObjById(selectArmorId);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmaktadır ");
                } else {
                    System.out.println(selectedArmor.getName() + " Zırhını satın aldınız ");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    this.getPlayer().setMoney(balance);

                }
            }
        }

    }


}


