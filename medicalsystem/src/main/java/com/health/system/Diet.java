package com.health.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="diets")
public class Diet {

@Column(name="diet_id")
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;

public String getCondition() {
	return condition;
}

public void setCondition(String condition) {
	this.condition = condition;
}

@Column(name="disease")
private String condition;

@Column(name="proteins")
private String proteins;

@Column(name="carbohydrates")
private String carbohydrates;

@Column(name="vitamins")
private String vitamins;

@Column(name="avoids")
private String avoids;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getProteins() {
	return proteins;
}

public void setProteins(String proteins) {
	this.proteins = proteins;
}

public String getCarbohydrates() {
	return carbohydrates;
}

public void setCarbohydrates(String carbohydrates) {
	this.carbohydrates = carbohydrates;
}

public String getVitamins() {
	return vitamins;
}

public void setVitamins(String vitamins) {
	this.vitamins = vitamins;
}

public String getAvoids() {
	return avoids;
}

public void setAvoids(String avoids) {
	this.avoids = avoids;
}



}
