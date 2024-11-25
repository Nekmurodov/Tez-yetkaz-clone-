package com.example.Tez_Yetkaz.service.email;


import com.example.Tez_Yetkaz.dto.email.MessageToEmail;

public interface IEmailService {

    void sendMessage(MessageToEmail message);
}
