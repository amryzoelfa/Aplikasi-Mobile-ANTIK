package com.example.antik;

public class AppVar {
    //URL to our login.php file, url bisa diganti sesuai dengan alamat server kita
    public static final String LOGIN_URL = "http://192.168.43.97/CIANTIK/Api/loginApi";
    public static final String PROFIL_URL = "http://192.168.43.97/CIANTIK/Api/Profile/";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //    untuk rubah password
    public static final String RUBAH_PASS = "http://192.168.43.121/CIANTIK/Api/Password/";
//    untuk rubah profil
    public static final String UPDATE_PROFIL = "http://192.168.43.121/CIANTIK/Api/updateProfil/";
}
