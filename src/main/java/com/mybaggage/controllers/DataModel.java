
package com.mybaggage.controllers;

import javafx.beans.property.*;

//Creates getter and setter methods for each property.
public class DataModel {
	
	
	private SimpleStringProperty id;
	private SimpleStringProperty voornaam;
	private SimpleStringProperty achternaam;
	private SimpleStringProperty gebruikersnaam;
	private SimpleStringProperty wachtwoord;
	private SimpleStringProperty functie;
		protected DataModel(){
			this.id = new SimpleStringProperty();
			this.voornaam = new SimpleStringProperty();
			this.achternaam = new SimpleStringProperty();
			this.gebruikersnaam = new SimpleStringProperty();
			this.wachtwoord = new SimpleStringProperty();
			this.functie = new SimpleStringProperty();
		}
	
		public String getId(){
			return id.get();
		} 
		public void setId(String id){ 
			this.id.set(id); 
		}
		public String getVoornaam(){
			return voornaam.get();
		}
		public void setVoornaam(String voornaam){
			this.voornaam.set(voornaam);
		}
		public String getAchternaam(){
			return achternaam.get();
		}
		public void setAchternaam(String achternaam){
			this.achternaam.set(achternaam);
		}
		public String getGebruikersnaam(){
			return gebruikersnaam.get();
		}
		public void setGebruikersnaam(String gebruikersnaam){
			this.gebruikersnaam.set(gebruikersnaam);
		}
		public String getWachtwoord(){
			return wachtwoord.get();
		}
		public void setWachtwoord(String wachtwoord){
			this.wachtwoord.set(wachtwoord);
		}
		public String getFunctie(){
			return functie.get();
		}
		public void setFunctie(String functie){
			this.functie.set(functie);
		}

		public void add(DataModel book) {
			
		}

		public void add(String string) {
			
		}

		

		
	
}	

