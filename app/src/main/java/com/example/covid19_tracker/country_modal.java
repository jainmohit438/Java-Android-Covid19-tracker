package com.example.covid19_tracker;

public class country_modal {
    private String flag,country,cases,recovered,critical,active,todaycases,deaths,todaydeath;

    public country_modal() {
    }

    public country_modal(String flag, String country, String cases, String recovered, String critical, String active, String todaycases, String deaths, String todaydeath) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.recovered = recovered;
        this.critical = critical;
        this.active = active;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeath = todaydeath;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeath() {
        return todaydeath;
    }

    public void setTodaydeath(String todaydeath) {
        this.todaydeath = todaydeath;
    }


}
