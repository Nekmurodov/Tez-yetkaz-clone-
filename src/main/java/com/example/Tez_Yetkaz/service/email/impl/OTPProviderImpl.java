package com.example.Tez_Yetkaz.service.email.impl;


import com.example.Tez_Yetkaz.service.email.OtpProvider;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class OTPProviderImpl implements OtpProvider {

    public static Integer expirationOtpMin = 5;
    private final LoadingCache<String, Integer> otpCache;

    public OTPProviderImpl() {
        super();
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(expirationOtpMin, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    @NonNull
                    public Integer load(@NonNull String key) {
                        return generatorOTP(key);
                    }
                });
    }
    @Override
    public Integer generatorOTP(String key) {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        otpCache.put(key, otp);
        return otp;
    }
    @Override
    public Integer getOTPByKey(String key) {
        return otpCache.getIfPresent(key);
    }
    @Override
    public void removeOTPFromCache(String key) {
        otpCache.invalidate(key);
    }
}
