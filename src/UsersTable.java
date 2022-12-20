
public class UsersTable {
    private String Tid;
    private String Tname;
    private String TlastName;
    private String TUname;
    private String Temail; 
    // private String password;
    
    UsersTable(String Tid, String Tname, String TlastName, String TUname, String Temail){
        this.Tname = Tname;
        this.TUname = TUname;
        this.Tid = Tid;

        this.TlastName = TlastName;
        this.Temail = Temail;
    }

    

    public String getTUname() {
        return TUname;
    }

    public String getTemail() {
        return Temail;
    }
    
    public String getTid() {
        return Tid;
    }

    public String getTlastName() {
        return TlastName;
    }

    public String getTname() {
        return Tname;
    }
    
}