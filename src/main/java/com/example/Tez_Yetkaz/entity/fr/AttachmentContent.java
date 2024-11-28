package com.example.Tez_Yetkaz.entity.fr;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AttachmentContent extends AbsEntity {

    @Lob()
    private byte[] mainContent;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attachment attachment;
}
