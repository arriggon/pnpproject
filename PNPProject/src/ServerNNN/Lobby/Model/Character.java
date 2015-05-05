package ServerNNN.Lobby.Model;

import java.util.ArrayList;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class Character {
    private CharInfo info;
    private BaseAbilities abilitiys;
    private ArrayList<Item> inventory;

    public Character(CharInfo  cinfo, BaseAbilities base) {
        info = cinfo;
        abilitiys = base;
        inventory = new ArrayList<Item>();
    }

    public CharInfo getInfo() {

        return info;
    }

    public void setInfo(CharInfo info) {
        this.info = info;
    }

    public BaseAbilities getAbilitiys() {
        return abilitiys;
    }

    public void setAbilitiys(BaseAbilities abilitiys) {
        this.abilitiys = abilitiys;
    }


}
