package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Coşkun on 21.1.2017.
 */
@Entity
@Table(name = "CITY")
public class City {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", columnDefinition = "NVARCHAR2")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
