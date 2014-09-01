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
 * @author rugal
 */
@Entity
@Table(name = "major")
public class Major implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    private String mid;

    @Size(max = 50)
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Major))
        {
            return false;
        }
        Major other = (Major) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid
            .equals(other.mid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "rugal.center.core.entity.Major[ mid=" + mid + " ]";
    }
}
