package iitg.lastsem.manparvesh.iitgstudygroups.DBentries;

import java.util.ArrayList;

/**
 * Created by Man Parvesh on 2/8/2016.
 */
public class Group {
    //// TODO: 2/9/2016 add the following stuff too!
    //private ArrayList<User> userList;
    //private ArrayList<Tag> tags;
    private String name, description;
//    private boolean isPrivate;

    public Group(String name, String description, ArrayList<Tag> tags, ArrayList<User> userList, boolean isPrivate){
        this.name = name;
        this.description = description;
//        this.tags = tags;
  //      this.userList = userList;
    //    this.isPrivate = isPrivate;
    }

    public Group(){
  //      userList = new ArrayList<>();
    //    tags = new ArrayList<>();
    }

    public Group(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description =  description;
    }

//    public ArrayList<Tag> getTags(){
  //      return tags;
    //}

//    public ArrayList<User> getUserList(){
  //      return userList;
    //}
/*
    public boolean getIsPrivate(){
        return isPrivate;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    public void addUser(User user){
        this.userList.add(user);
    }

    public void changeGroupPrivacyToPrivate(){
        isPrivate = true;
    }

    public void changeGroupPrivacyToPublic(){
        isPrivate = false;
    }
    */

    public void setName(String name){
        this.name = name;
    }

  /*  public void removeUser(User user){
        this.userList.remove(user);
    }

    public void removeTag(Tag tag){
        this.tags.remove(tag);
    }

*/
}
