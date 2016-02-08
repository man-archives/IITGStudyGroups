package iitg.lastsem.manparvesh.iitgstudygroups.DBentries;

import java.util.ArrayList;

/**
 * Created by Man Parvesh on 2/7/2016.
 */
public class User {
    private String fullName, userName, password, uID, email;
    private ArrayList<Group> publicGroups, privateGroups;

    public User() {
        publicGroups = new ArrayList<>();
        privateGroups = new ArrayList<>();
    }

    public User(String fullName, String userName, String uID, String password, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.uID = uID;
        this.password = password;
        this.email = email;
    }

    public User(String fullName, String userName, String password, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail(){
        return email;
    }

    public String getuID() {
        return uID;
    }

    public String getPassword() {
        return password;
    }

    public void updateEmail(String email){
        this.email = email;
    }

    public void updateFullName(String userName){
        this.userName = userName;
    }

    public ArrayList<Group> getPublicGroups(){
        return publicGroups;
    }

    public ArrayList<Group> getPrivateGroups(){
        return privateGroups;
    }

    public void addPublicGroup(Group group){
        this.publicGroups.add(group);
    }

    public void addPrivateGroup(Group group){
        this.privateGroups.add(group);
    }

    public void removePublicGroup(Group group){
        publicGroups.remove(group);
    }

    public void removePrivateGroup(Group group){
        privateGroups.remove(group);
    }
}
