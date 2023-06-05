package managedbean;

import backingbean.Person;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.bean.ManagedBean;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@ManagedBean(name = "loginbean")
public class loginbean {
    Person person;
    public loginbean(){
        person=new Person();
    }
    private String decryptedPassword;
    public loginbean(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    dbConnection db=new dbConnection();

    public String login() throws NoSuchAlgorithmException {
        String uname=person.getName();
        String password=person.getPassword();

        if(db.validatepassword(uname,password)){
            return "homepage.xhtml";
        }else{
            return "login.xhtml";
        }


    }


    public List<Person> getdataList() throws SQLException {
        List<Person> personList=new ArrayList<Person>();
        ResultSet rs=db.getRecords();

        String name=person.getName();
        String password=person.getPassword();
        while (rs.next()){
            Person person1=new Person();
            person1.name=rs.getString("uname");
            person1.email=rs.getString("email");
            person1.password=rs.getString("password");
            personList.add(person1);

        }
        return personList;
    }

}
