package com.example.Tez_Yetkaz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private UUID id;
    private String name;
    private String fileOriginalName;
    private long size;
    private String contentType;
}
