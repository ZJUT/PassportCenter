package rugal.center.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * This is a entity class used for store data of school.
 * school is a academical unit that is independent in university
 *
 * @author Rugal Bernstein
 */
@Entity
@Table(name = "school")
public class School implements Serializable
{

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "sid")
    private String sid;

    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sid")
    @JsonIgnore
    private List<Passport> passportList;

    public School()
    {
    }

    public School(String sid)
    {
        this.sid = sid;
    }

    public String getSid()
    {
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Passport> getPassportList()
    {
        return passportList;
    }

    public void setPassportList(List<Passport> passportList)
    {
        this.passportList = passportList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof School))
        {
            return false;
        }
        School other = (School) object;
        return this.sid.equals(other.sid);
    }

    @Override
    public String toString()
    {
        return "rugal.center.core.entity.School[ sid=" + sid + " ]";
    }

}
