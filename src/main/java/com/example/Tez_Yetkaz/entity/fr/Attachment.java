package com.example.Tez_Yetkaz.entity.fr;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Attachment extends AbsEntity {

    private String name;

    private String fileOriginalName;

    private long size;

    private String contentType;

    @OneToOne(mappedBy = "attachment", cascade = CascadeType.ALL)
    private AttachmentContent attachmentContent;

}
