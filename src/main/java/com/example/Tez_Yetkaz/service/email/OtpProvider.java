package com.example.Tez_Yetkaz.service.email;

public interface OtpProvider {

    Integer generatorOTP(String email);

    Integer getOTPByKey(String email);

    void removeOTPFromCache(String email);

}
