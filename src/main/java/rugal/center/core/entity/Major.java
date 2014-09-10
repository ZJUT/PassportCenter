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
 * @author rugal
 */
@Entity
@Table(name = "major")
public class Major implements Serializable
{

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "mid")
    private String mid;

    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "mid")
    @JsonIgnore
    private List<Passport> passportList;

    public Major()
    {
    }

    public Major(String mid)
    {
        this.mid = mid;
    }

    public String getMid()
    {
        return mid;
    }

    public void setMid(String mid)
    {
        this.mid = mid;
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
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Major))
        {
            return false;
        }
        Major other = (Major) object;
        return this.mid.equals(other.mid);
    }

    @Override
    public String toString()
    {
        return "rugal.center.core.entity.Major[ mid=" + mid + " ]";
    }

}
