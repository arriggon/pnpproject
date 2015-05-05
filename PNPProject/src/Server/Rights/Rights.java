package Server.Rights;

/**
 * Created by RAIDER on 27.04.2015.
 */
public class Rights {
    public boolean gmFlag, adminFlag, ownerFlag;

    public Rights(boolean gmFlag, boolean adminFlag, boolean ownerFlag) {
        this.gmFlag = gmFlag;
        this.adminFlag = adminFlag;
        this.ownerFlag = ownerFlag;
    }

    public boolean isGmFlag() {
        return gmFlag;
    }

    public void setGmFlag(boolean gmFlag) {
        this.gmFlag = gmFlag;
    }

    public boolean isAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(boolean adminFlag) {
        this.adminFlag = adminFlag;
    }

    public boolean isOwnerFlag() {
        return ownerFlag;
    }

    public void setOwnerFlag(boolean ownerFlag) {
        this.ownerFlag = ownerFlag;
    }
}
