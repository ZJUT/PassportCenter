package rugal.center.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    private String sid;

    @Size(max = 50)
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof School))
        {
            return false;
        }
        School other = (School) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid
            .equals(other.sid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "rugal.center.core.entity.School[ sid=" + sid + " ]";
    }
}
