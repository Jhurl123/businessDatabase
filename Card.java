package edu.dtcc.walldatabase;//package edu.dtcc.sqlitelistview;

public class Card {
    private int id;
    private String name;
    private String title;
    private String address;
    private String business;
    private String email;
    private String phone;

    public Card()
    {
    }

    public Card(int id, String name, String title, String business, String address, String email, String phone)
    {
        this.id=id;
        this.name=name;
        this.title=title;
        this.address=address;
        this.business=business;
        this.email=email;
        this.phone=phone;
    }

    public Card(String name, String title, String business, String address, String email, String phone)
    {
        this.name=name;
        this.title=title;
        this.address=address;
        this.business=business;
        this.email=email;
        this.phone=phone;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(String name) {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBusiness(String business) {
        this.name = business;
    }

    public String getBusiness() {
        return business;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

}

