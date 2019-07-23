package br.com.challenge.swapi.documents;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
@ToString
public class Planet {

    @Id
    private ObjectId id;
    private String name;
    private String climate;
    private String terrain;
    private Long appearances;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Version
    private long version;

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Planet) {

            Planet p = (Planet) obj;

            return this.getId() != null && this.getId().equals(p.getId());
        }

        return false;
    }
}
