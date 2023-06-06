package managedbean;

import backingbean.Person;
import com.sun.faces.context.SessionMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ManagedBean(name="personbean")
@SessionScoped
public class PersonBean {

    private Map<String,Object> SessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    Person person;






    public PersonBean(){
        person=new Person();
    }

    public PersonBean(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    dbConnection db=new dbConnection();
    public List<Person> getdataList() throws SQLException {
        List<Person> personList=new ArrayList<Person>();
        ResultSet rs=db.getRecords();
        while (rs.next()){


        }
        return personList;


    }
    public String  saveToDB() throws NoSuchAlgorithmException {

         String firstname=person.getFirstname();
         String email=person.getMail();
         String password=person.getPassword();
         String lastname=person.getLastname();
         int phonenumber=person.getPhonenumber();



        if(firstname.isEmpty()||email.isEmpty()||password.isEmpty()||lastname.isEmpty()){
            return "first.xhtml";
        } else {

                db.insertRecord(firstname, lastname, email, password,phonenumber);
                return "login.xhtml";

        }
    }
      public void DeletePerson(Person p) throws SQLException {
        String mail = p.getMail();
        db.deleteRecord(mail);
        System.out.print("Success!");
       }
       public String getDataForEdit(Person person){
        System.out.println(person.getMail());
           SessionMap.put("editUser", person);
           return "/editform2.xhtml?faces-redirect=true";
       }
       public String updateRecord(Person person){
        db.updateRecord(person.getFirstname(),person.getLastname(),person.getMail(),person.getPassword(),person.getPhonenumber());
        return "first.xhtml?faces-redirect=true";
       }

    }
