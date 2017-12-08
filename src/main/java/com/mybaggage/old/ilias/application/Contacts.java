/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybaggage.old.ilias.application;

/**
 *
 * @author Ilias
 */
public class Contacts {

    private String FName;
    private String LName;
    private String PNum;
    private String EMail;

    //public Contacts(String pFname, String pLname, String pPnum, String pEmail) {
        //super();
        //this.FName = pFname;
        //this.LName = pLname;
        //this.PNum  = pPnum;
        //this.EMail = pEmail;
    //}
    public Contacts(String pFname,String pLname, String pPnum, String pEmail){
        FName = pFname;
        LName = pLname;
        PNum =  pPnum;
        EMail = pEmail;
    }

    @Override
    public String toString(){
    return FName + "," + 
           LName + "," +
           PNum  + "," +
           EMail + "," + '\r' ;
}
    public String toFile(){
        return FName + "," + LName + "," + PNum + "," + EMail + "," +'\r';
    }
    public String getFName(){
        return FName;
    }
    public String getLName(){
        return LName;
    }
    public String getPnum(){
        return PNum;
    }
    public String getEmail(){
        return EMail;
    }
}

