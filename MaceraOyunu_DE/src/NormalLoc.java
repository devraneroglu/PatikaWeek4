public class NormalLoc extends Location {
    @Override
    public boolean onLocation() {
        return true;   //ölme ihtimali yok
    }

    public NormalLoc(Player player, String name) {
        super(player, name);
    }
}
