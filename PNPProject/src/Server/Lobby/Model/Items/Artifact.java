package Server.Lobby.Model.Items;

import Server.Lobby.Model.Item;
import Server.Lobby.Model.ItemType;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class Artifact implements Item {
    private String name;
    private long strenght, constitution, dexterity, inteligence, wisdom, charisma, luck;

    @Override
    public ItemType getCategory() {
        return ItemType.ARTIFACT;
    }

    public Artifact(String name, long strenght, long constitution, long dexterity, long inteligence, long wisdom, long charisma, long luck) {
        this.name = name;
        this.strenght = strenght;
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

    public long getStrenght() {
        return strenght;
    }

    public void setStrenght(long strenght) {
        this.strenght = strenght;
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
}
