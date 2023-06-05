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
    public    String errorMessage="";
    private String encryptedPassword;
    private String encryptedConfirmPassword;





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

         String name=person.getName();
         String email=person.getEmail();
         String password=person.getPassword();
         String confirmpassword=person.getConfirmpassword();



        if(name.isEmpty()||email.isEmpty()||password.isEmpty()||confirmpassword.isEmpty()){
            return "registration.xhtml";
        } else {
            if(password.equals(confirmpassword)){
                db.insertRecord(name, email, password);
                return "login.xhtml";
            }else {
                return "error.xhtml";
            }
        }
    }
      public void DeletePerson(Person p) throws SQLException {
        String name = p.getName();
        db.deleteRecord(name);
        System.out.print("Success!");
       }
       public String getDataForEdit(Person person){
        System.out.println(person.getEmail());
           SessionMap.put("editUser", person);
           return "/editform2.xhtml?faces-redirect=true";
       }
       public String updateRecord(Person person){
        db.updateRecord(person.getName(),person.getEmail(),person.getPassword());
        return "registration.xhtml?faces-redirect=true";
       }

    }
