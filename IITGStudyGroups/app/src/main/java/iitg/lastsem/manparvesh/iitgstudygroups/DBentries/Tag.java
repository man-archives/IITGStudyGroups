package iitg.lastsem.manparvesh.iitgstudygroups.DBentries;

import java.util.ArrayList;

/**
 * Created by Man Parvesh on 2/8/2016.
 */
public class Tag {
    private ArrayList<Group> groups;
    private String name;
    public Tag(){groups = new ArrayList<>();}

    public Tag(ArrayList<Group> groups){
        this.groups = groups;
    }
    public Tag(Group g){
        ArrayList<Group> al = new ArrayList<>();
        al.add(g);
        this.groups = al;
    }

    public Tag(String name){
        this.name = name;
        groups = new ArrayList<>();
    }

    public ArrayList<Group> getGroups(){
        return groups;
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public void removeGroup(Group group){
        groups.remove(group);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(String name){
        return name;
    }
}
