package practica3b.representacion;

public class User {

    Integer id;
    String nickname;
    String password;

    public User(){
        
    }

    public User(Integer id, String nick, String pass){
        this.id = id;
        this.nickname = nick;
        this.password = pass;
    }

    public static User fromString(String line)	{
            String[] fields = line.split("::");

            User us = new User();
            us.setId(Integer.valueOf(fields[0]));
            us.setNickname(String.valueOf(fields[1]));
            us.setPassword(String.valueOf(fields[2]));
            return us;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public String toString()
    {
            return id.toString();
    }
}
