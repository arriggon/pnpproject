package ServerN.Model;

/**
 * Created by RAIDER on 10.04.2015.
 */
public class UserInfo {
    private String username;
    private long uid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (uid != userInfo.uid) return false;
        if (!username.equals(userInfo.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        return result;
    }

    public long getUid() {
        return uid;

    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
