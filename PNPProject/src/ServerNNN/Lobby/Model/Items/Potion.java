package ServerNNN.Lobby.Model.Items;

import ServerNNN.Lobby.Model.Item;
import ServerNNN.Lobby.Model.ItemType;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class Potion implements Item{
    private String name;
    private long mana, health;

    public String getName() {        return name;}

    public Potion(String name, long mana, long health) {
        this.name = name;
        this.mana = mana;
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;

    }

    public long getMana() {
        return mana;
    }

    public void setMana(long mana) {
        this.mana = mana;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    @Override
    public ItemType getCategory() {

        return ItemType.POTION;
    }
}
