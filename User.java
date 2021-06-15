abstract class User{
    
    protected String first_name;
    protected String last_name;
    protected String login;
    protected String password;

    String getFirstName(){
        return first_name;
    }
    String getLastName(){
        return last_name;
    }
    String getLogin(){
        return login;
    }
    String getPassword(){
        return password;
    }

    void setFirstName(String n){
        this.first_name = n;
    }
    void setLastName(String n){
        this.last_name = n;
    }
    void setLogin(String n){
        this.login = n;
    }
    void setPassword(String n){
        this.password = n;
    }


}