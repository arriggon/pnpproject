package Server.Lobby.Model.Items;

import Server.Lobby.Model.Item;
import Server.Lobby.Model.ItemType;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class Armor implements Item {
    public String name;
    public String type;
    public long strength, constitution, dexterity, inteligence, wisdom, charisma, luck;

    public Armor(String name, String type, long strength, long constitution, long dexterity, long inteligence, long wisdom, long charisma, long luck) {
        this.name = name;
        this.type = type;
        this.strength = strength;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getStrength() {
        return strength;
    }

    public void setStrength(long strength) {
        this.strength = strength;
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
        return ItemType.ARMOR;
    }
}
