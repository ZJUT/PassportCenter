package rugal.center.core.entity;

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
 * @author Rugal Bernstein
 */
@Entity
@Table(name = "passport_type")
public class PassportType implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ptid")
    private Short ptid;

    @Size(max = 20)
    @Column(name = "name")
    private String name;

    @Size(max = 5)
    @Column(name = "abbreviation")
    private String abbreviation;

    @OneToMany(mappedBy = "ptid")
    private List<Passport> passportList;

    public PassportType()
    {
    }

    public PassportType(Short ptid)
    {
        this.ptid = ptid;
    }

    public Short getPtid()
    {
        return ptid;
    }

    public void setPtid(Short ptid)
    {
        this.ptid = ptid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
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
        hash += (ptid != null ? ptid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassportType))
        {
            return false;
        }
        PassportType other = (PassportType) object;
        if ((this.ptid == null && other.ptid != null) || (this.ptid != null && !this.ptid
            .equals(other.ptid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "rugal.center.core.entity.PassportType[ ptid=" + ptid + " ]";
    }

}
