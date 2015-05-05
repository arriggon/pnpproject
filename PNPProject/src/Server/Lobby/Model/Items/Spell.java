package Server.Lobby.Model.Items;

import Server.Lobby.Model.Item;
import Server.Lobby.Model.ItemType;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class Spell implements Item {

    private String type, effects;
    private long damage, range, radios;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(long damage) {
        this.damage = damage;
    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    public long getRadios() {
        return radios;
    }

    public void setRadios(long radios) {
        this.radios = radios;
    }

    public Spell(String type, String effects, long damage, long range, long radios) {

        this.type = type;
        this.effects = effects;
        this.damage = damage;
        this.range = range;
        this.radios = radios;
    }

    @Override
    public ItemType getCategory() {
        return ItemType.SPELL;
    }
}
