package ServerNNN.Lobby.Model.Items;

import ServerNNN.Lobby.Model.Item;
import ServerNNN.Lobby.Model.ItemType;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class Weapon  implements Item {
    private String name;
    private long wepStrenght, streanght, constitution, dexterity, inteligence, wisdom, charisma, luck;

    public Weapon(String name, long wepStrenght, long streanght, long constitution, long dexterity, long inteligence, long wisdom, long charisma, long luck) {
        this.name = name;
        this.wepStrenght = wepStrenght;
        this.streanght = streanght;
        this.constitution = constitution;
        this.dexterity = dexterity;
        this.inteligence = inteligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.luck = luck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWepStrenght() {
        return wepStrenght;
    }

    public void setWepStrenght(long wepStrenght) {
        this.wepStrenght = wepStrenght;
    }

    public long getStreanght() {
        return streanght;
    }

    public void setStreanght(long streanght) {
        this.streanght = streanght;
    }

    public long getConstitution() {
        return constitution;
    }

    public void setConstitution(long constitution) {
        this.constitution = constitution;
    }

    public long getDexterity() {
        return dexterity;
    }

    public void setDexterity(long dexterity) {
        this.dexterity = dexterity;
    }

    public long getInteligence() {
        return inteligence;
    }

    public void setInteligence(long inteligence) {
        this.inteligence = inteligence;
    }

    public long getWisdom() {
        return wisdom;
    }

    public void setWisdom(long wisdom) {
        this.wisdom = wisdom;
    }

    public long getCharisma() {
        return charisma;
    }

    public void setCharisma(long charisma) {
        this.charisma = charisma;
    }

    public long getLuck() {
        return luck;
    }

    public void setLuck(long luck) {
        this.luck = luck;
    }

    @Override
    public ItemType getCategory() {
        return ItemType.WEAPON;
    }
}
