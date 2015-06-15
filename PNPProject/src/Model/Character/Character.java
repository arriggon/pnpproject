package Model.Character;

import GUI.CharEdit.CharacterCreatorException;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * This class represents the character-object stored for every user
 */


public class Character implements Serializable, DataOverNetwork{

    /**
     * Contains the name of the character
     */
    private String name;

    /**
     * Contains the age of the character
     */
    private int age;

    /**
     * Contains the race of the character
     */
    private String race;

    /**
     * Contains the class of the character
     */
    private String _class;

    /**
     * Contains the biography of the character
     */
    private String biography;

    /**
     * Contains the agility of the character
     */
    private int agi;

    /**
     * Contains the wisdom of the character
     */
    private int wis;

    /**
     * Contains the health-points of the character
     */
    private int hp;

    /**
     * Contains the intelligence of the character
     */
    private int intellect;

    /**
     * Contains the attack-value of the character
     */
    private int atk;

    /**
     * Contains the defense-value of the character
     */
    private int def;

    /**
     * Contains the stamina of the character
     */
    private int sta;

    /**
     * Contains the dexterity of the character
     */
    private int dex;

    /**
     * Constructor
     * @param name As above
     * @param age As above
     * @param race As above
     * @param _class As above
     * @param biography As above
     * @param agi As above
     * @param wis As above
     * @param hp As above
     * @param intellect As above
     * @param atk As above
     * @param def As above
     * @param sta As above
     * @param dex As above
     * @throws CharacterCreatorException
     */
    public Character(String name, int age, String race, String _class, String biography, int agi, int wis, int hp, int intellect, int atk, int def, int sta, int dex) throws CharacterCreatorException {
        setName(name);
        this.setAge(age);
        this.setRace(race);
        this.set_class(_class);
        this.setBiography(biography);
        this.setAgi(agi);
        this.setWis(wis);
        this.setHp(hp);
        this.setIntelect(intellect);
        this.setAtk(atk);
        this.setDef(def);
        this.setSta(sta);
        this.setDex(dex);
    }

    /**
     * Get-Method of the character's name
     * @return Returns the String name
     */
    public String getName() {
        return name;
    }

    /**
     * Set-Method for the character's name
     * @param name The name to be set with
     * @throws CharacterCreatorException Thrown if the name-parameter is empty
     */
    public void setName(String name) throws CharacterCreatorException {
        if(name.isEmpty()) {
            throw new CharacterCreatorException("name: too short");
        }
        this.name = name;
    }

    /**
     * Get-Method for the character's age
     * @return Returns the int age
     */
    public int getAge() {
        return age;
    }

    /**
     * Set-Method for the character's age
     * @param age The age to be set with
     * @throws CharacterCreatorException Throw if the Character's age is negative
     */
    public void setAge(int age) throws CharacterCreatorException {
        if(age < 0) {
            throw new CharacterCreatorException("age: must be greater than 0: not"+age);
        }
        this.age = age;
    }

    /**
     * Get-Method for the character's race
     * @return Returns the String race
     */
    public String getRace() {
        return race;
    }

    /**
     * Set-Method for the character's race
     * @param race The race to be set with
     * @throws CharacterCreatorException Thrown if the race-parameter is empty
     */
    public void setRace(String race) throws CharacterCreatorException {
        if(race.isEmpty()) {
            throw new CharacterCreatorException("Race: must not be an empty string");
        }
        this.race = race;
    }

    /**
     * Get-Method for the character's class
     * @return Returns the String _class
     */
    public String get_class() {
        return _class;
    }

    /**
     * Set-Method for the character's class
     * @param _class The class to be set with
     * @throws CharacterCreatorException Thrown if the _class-parameter is empty
     */
    public void set_class(String _class) throws CharacterCreatorException {
        if(_class.isEmpty()) {
            throw new CharacterCreatorException("Class: must not be an empty string");
        }
        this._class = _class;
    }

    /**
     * Get-Method for the character's biography
     * @return Returns String biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * Set-Method for the character's biography
     * @param biography The biography to be set with
     * @throws CharacterCreatorException Throw if the biography-parameter is empty
     */
    public void setBiography(String biography) throws CharacterCreatorException {
        if(biography.isEmpty()) {
            throw new CharacterCreatorException("Biography: must not be an empty string");
        }
        this.biography = biography;
    }

    /**
     * Get-Method for the character's agility
     * @return Returns the int agi
     */
    public int getAgi() {
        return agi;
    }

    /**
     * Set-Method for the character's agility
     * @param agi The agility to be set with
     * @throws CharacterCreatorException Thrown if the agi-parameter is empty
     */
    public void setAgi(int agi) throws CharacterCreatorException {
        if(agi < 0) {
            throw new CharacterCreatorException("AGI: must be smaller than 0 - not "+agi);
        }
        this.agi = agi;
    }

    /**
     * Get-Method for the character's wisdom
     * @return Returns the int wis
     */
    public int getWis() {
        return wis;
    }

    /**
     * Set-Method for the character's wisdom
     * @param wis The wisdom to be set with
     * @throws CharacterCreatorException Thrown if the wis-parameter is empty
     */
    public void setWis(int wis) throws CharacterCreatorException {
        if(wis < 0) {
            throw new CharacterCreatorException("WIS: must not be smaller than 0 - not "+wis);
        }
        this.wis = wis;
    }

    /**
     * Get-Method for the character's health-points
     * @return Returns the int hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Set-Method for the character's health-points
     * @param hp The health-points to be set with
     * @throws CharacterCreatorException Thrown if the hp-parameter is negative
     */
    public void setHp(int hp) throws CharacterCreatorException {
        if(hp < 0) {
            throw new CharacterCreatorException("HP: must be smaller than 0 - not " +hp);
        }
        this.hp = hp;
    }

    /**
     * Get-Method for the character's intelligence
     * @return Returns the int intellect
     */
    public int getIntelect() {
        return intellect;
    }

    /**
     * Set-Method for the character's intelligence
     * @param intellect The intelligence to be set with
     * @throws CharacterCreatorException Thrown if the intellect-parameter is negative
     */
    public void setIntelect(int intellect) throws CharacterCreatorException {
        if(intellect < 0) {
            throw  new CharacterCreatorException("INT: must not be smaller than 0 - not "+intellect);
        }
        this.intellect = intellect;
    }

    /**
     * Get-Method for the character's attack-value
     * @return Returns the int atk
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Set-Method for the character's attack-value
     * @param atk The attack-value to be set with
     * @throws CharacterCreatorException Thrown if the atk-parameter is negative
     */
    public void setAtk(int atk) throws CharacterCreatorException {
        if(atk < 0) {
            throw  new CharacterCreatorException("ATK: must not be smaller than 0 - not "+atk);

        }
        this.atk = atk;
    }

    /**
     * Get-Method for the character's defense-value
     * @return Returns the int def
     */
    public int getDef() {
        return def;
    }

    /**
     * Set-Method for the character's defense-value
     * @param def The defense-value to be set with
     * @throws CharacterCreatorException Throw if the def-parameter is negative
     */
    public void setDef(int def) throws CharacterCreatorException {
        if(def < 0) {
            throw new CharacterCreatorException("DEF: must not be smaller than 0 - not "+def);
        }
        this.def = def;
    }

    /**
     * Get-Method for the character's stamina-value
     * @return Returns the int sta
     */
    public int getSta() {
        return sta;
    }

    /**
     * Set-Method for the character's stamina-value
     * @param sta The stamina-value to be set with
     * @throws CharacterCreatorException Thrown if the sta-parameter is negative
     */
    public void setSta(int sta) throws CharacterCreatorException {
        if(sta < 0) {
            throw  new CharacterCreatorException("STA: must not be smaller than 0 - not "+sta);
        }
        this.sta =  sta;
    }

    /**
     * Get-Method for the character's dexterity
     * @return Returns the int dex
     */
    public int getDex() {
        return dex;
    }

    /**
     * Set-Method for the character's dexterity
     * @param dex The dexterity to be set with
     * @throws CharacterCreatorException Thrown if the dex-parameter is negative
     */
    public void setDex(int dex) throws CharacterCreatorException {
        if(dex < 0) {
            throw new CharacterCreatorException("DEX: must not be smaller than 0 - not "+dex);
        }
        this.dex = dex;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", race='" + race + '\'' +
                ", _class='" + _class + '\'' +
                ", biography='" + biography + '\'' +
                ", agi=" + agi +
                ", wis=" + wis +
                ", hp=" + hp +
                ", intelect=" + intellect +
                ", atk=" + atk +
                ", def=" + def +
                ", sta=" + sta +
                ", dex=" + dex +
                '}';
    }
}
