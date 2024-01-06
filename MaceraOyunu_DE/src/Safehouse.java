public class Safehouse extends NormalLoc {
    public Safehouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz !");
        System.out.println("Canınınız yenilendi !");
        this.getPlayer().setHealth(this.getPlayer().getOrgHealth());
        return true;
    }
}
