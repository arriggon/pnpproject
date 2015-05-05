package ServerNNN.Lobby.Model;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class BaseAbilities {

    private long strength, constitution, dexterity, inteligece, wisdom, charisma, luck;

    public BaseAbilities(long strength, long constitution, long dexterity, long inteligece, long wisdom, long charisma, long luck) {
        this.strength = strength;
        this.constitution = constitution;
        this.dexterity = dexterity;
        this.inteligece = inteligece;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.luck = luck;
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

    public long getInteligece() {
        return inteligece;
    }

    public void setInteligece(long inteligece) {
        this.inteligece = inteligece;
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
