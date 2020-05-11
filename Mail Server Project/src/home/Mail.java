package home;

import javafx.beans.property.SimpleStringProperty;

public class Mail {
    SimpleStringProperty name,subject,date,imp,dir;
    Mail(String name, String sub, String date, String imp, String dir){
        this.date=new SimpleStringProperty(date);
        this.name=new SimpleStringProperty(name);
        this.subject=new SimpleStringProperty(sub);
        this.imp=new SimpleStringProperty(imp);
        this.dir=new SimpleStringProperty(dir);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name=new SimpleStringProperty(name);
    }

    public String getSubject() {
        return subject.get();
    }

    public void setSubject(String sub) {
        this.subject=new SimpleStringProperty(sub);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date=new SimpleStringProperty(date);
    }

    public String getImp() {
        return imp.get();
    }
    public String getdir() {
        return dir.get();
    }

    public void setImp(String imp) {
        this.imp=new SimpleStringProperty(imp);
    }
}
