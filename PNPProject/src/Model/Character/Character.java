package Model.Character;

import GUI.CharEdit.CharacterCreatorException;
import Model.DataOverNetwork;

import java.io.Serializable;

/**
 * Created by RAIDER on 16.05.2015.
 */
public class Character implements Serializable, DataOverNetwork{

    private String name;
    private int age;
    private String race;
    private String _class;
    private String biography;

    private int agi;
    private int wis;
    private int hp;
    private int intelect;
    private int atk;
    private int def;
    private int sta;
    private int dex;


    public Character(String name, int age, String race, String _class, String biography, int agi, int wis, int hp, int intelect, int atk, int def, int sta, int dex) throws CharacterCreatorException {
        setName(name);
        this.setAge(age);
        this.setRace(race);
        this.set_class(_class);
        this.setBiography(biography);
        this.setAgi(agi);
        this.setWis(wis);
        this.setHp(hp);
        this.setIntelect(intelect);
        this.setAtk(atk);
        this.setDef(def);
        this.setSta(sta);
        this.setDex(dex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws CharacterCreatorException {
        if(name.isEmpty()) {
            throw new CharacterCreatorException("name: too short");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws CharacterCreatorException {
        if(age < 0) {
            throw new CharacterCreatorException("age: must be greater than 0: not"+age);
        }
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) throws CharacterCreatorException {
        if(race.isEmpty()) {
            throw new CharacterCreatorException("Race: must not be an empty string");
        }
        this.race = race;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) throws CharacterCreatorException {
        if(_class.isEmpty()) {
            throw new CharacterCreatorException("Class: must not be an empty string");
        }
        this._class = _class;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) throws CharacterCreatorException {
        if(biography.isEmpty()) {
            throw new CharacterCreatorException("Biography: must not be an empty string");
        }
        this.biography = biography;
    }

    public int getAgi() {
        return agi;
    }

    public void setAgi(int agi) throws CharacterCreatorException {
        if(agi < 0) {
            throw new CharacterCreatorException("AGI: must be smaller than 0 - not "+agi);
        }
        this.agi = agi;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) throws CharacterCreatorException {
        if(wis < 0) {
            throw new CharacterCreatorException("WIS: must not be smaller than 0 - not "+wis);
        }
        this.wis = wis;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) throws CharacterCreatorException {
        if(hp < 0) {
            throw new CharacterCreatorException("HP: must be smaller than 0 - not " +hp);
        }
        this.hp = hp;
    }

    public int getIntelect() {
        return intelect;
    }

    public void setIntelect(int intelect) throws CharacterCreatorException {
        if(intelect < 0) {
            throw  new CharacterCreatorException("INT: must not be smaller than 0 - not "+intelect);
        }
        this.intelect = intelect;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) throws CharacterCreatorException {
        if(atk < 0) {
            throw  new CharacterCreatorException("ATK: must not be smaller than 0 - not "+atk);

        }
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) throws CharacterCreatorException {
        if(def < 0) {
            throw new CharacterCreatorException("DEF: must not be smaller than 0 - not "+def);
        }
        this.def = def;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) throws CharacterCreatorException {
        if(sta < 0) {
            throw  new CharacterCreatorException("STA: must not be smaller than 0 - not "+sta);
        }
        this.sta =  sta;
    }

    public int getDex() {
        return dex;
    }

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
                ", intelect=" + intelect +
                ", atk=" + atk +
                ", def=" + def +
                ", sta=" + sta +
                ", dex=" + dex +
                '}';
    }
}
