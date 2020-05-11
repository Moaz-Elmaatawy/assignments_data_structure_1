package home;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    SimpleStringProperty name,mob,mail;
    Contact(String name, String mob, String mail){
        this.mail=new SimpleStringProperty(mail);
        this.name=new SimpleStringProperty(name);
        this.mob=new SimpleStringProperty(mob);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name=new SimpleStringProperty(name);
    }

    public String getMob() {
        return mob.get();
    }

    public void setMob(String mob) {
        this.mob=new SimpleStringProperty(mob);
    }

    public String getMail() {
        return mail.get();
    }

    public void setMail(String mail) {
        this.mail=new SimpleStringProperty(mail);
    }
}
