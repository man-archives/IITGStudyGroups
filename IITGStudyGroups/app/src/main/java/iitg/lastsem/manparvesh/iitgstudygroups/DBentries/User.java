package iitg.lastsem.manparvesh.iitgstudygroups.DBentries;

import java.util.ArrayList;

/**
 * Created by Man Parvesh on 2/7/2016.
 */
public class User {
    private String username, name, password, email;
    private ArrayList<Group> publicGroups, privateGroups;

    public User() {
        publicGroups = new ArrayList<>();
        privateGroups = new ArrayList<>();
    }



    public User(String name, String password, String email, String  username) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void updateEmail(String email){
        this.email = email;
    }

    public void updateName(String name){
        this.name = name;
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
