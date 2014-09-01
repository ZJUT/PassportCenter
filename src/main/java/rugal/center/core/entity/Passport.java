package rugal.center.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import rugal.common.security.encoder.Md5PwdEncoder;
import rugal.common.security.encoder.PwdEncoder;

/**
 *
 * @author Rugal Bernstein
 */
@Entity
@Table(name = "passport")
public class Passport implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String id;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    @JsonIgnore
    private String password;

    @Size(max = 50)
    private String nickname;

    @Size(max = 4)
    private String gender;

    @NotNull
    private int activated = 0;

    @Size(max = 20)
    private String idcard;

    @Size(max = 20)
    private String wx;

    @Size(max = 20)
    private String qq;

    @Size(max = 15)
    @Column(name = "full_phone")
    private String fullPhone;

    @Size(max = 10)
    @Column(name = "inner_phone")
    private String innerPhone;

    @Size(max = 50)
    @Column(name = "bind_mail")
    private String bindMail;

    @Size(max = 50)
    private String domitory;

    @Basic(optional = false)
    @NotNull
    private String type;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private List<Account> accountList;

    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @JsonIgnore
    @ManyToOne
    private School sid;

    @JoinColumn(name = "mid", referencedColumnName = "mid")
    @JsonIgnore
    @ManyToOne
    private Major mid;

    public Passport()
    {
    }

    public Passport(String id)
    {
        this.id = id;
    }

    public Passport(String id, String type)
    {
        this.id = id;
        this.type = type;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @JsonIgnore
    public boolean isActivated()
    {
        return activated == 1;
    }

    public int getActivated()
    {
        return activated;
    }

    public void setActivated(int active)
    {
        this.activated = active;
    }

    public boolean noPassword()
    {
        return null == this.password || password.isEmpty();
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        PwdEncoder encoder = new Md5PwdEncoder();
        this.password = encoder.encodePassword(password);
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getIdcard()
    {
        return idcard;
    }

    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public boolean checkPassword(String password)
    {
        PwdEncoder encoder = new Md5PwdEncoder();
        return encoder.isPasswordValid(this.password, password);
    }

    public boolean checkName(String name)
    {
        return this.name.equals(name);
    }

    public boolean checkIDCard(String idcard)
    {
        return this.idcard.equals(idcard);
    }

    public String getWx()
    {
        return wx;
    }

    public void setWx(String wx)
    {
        this.wx = wx;
    }

    public String getQq()
    {
        return qq;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    public String getFullPhone()
    {
        return fullPhone;
    }

    public void setFullPhone(String fullPhone)
    {
        this.fullPhone = fullPhone;
    }

    public String getInnerPhone()
    {
        return innerPhone;
    }

    public void setInnerPhone(String innerPhone)
    {
        this.innerPhone = innerPhone;
    }

    public boolean hasEmail()
    {
        return bindMail != null && !bindMail.isEmpty();
    }

    public String getBindMail()
    {
        return bindMail;
    }

    public void setBindMail(String bindMail)
    {
        this.bindMail = bindMail;
    }

    public String getDomitory()
    {
        return domitory;
    }

    public void setDomitory(String domitory)
    {
        this.domitory = domitory;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public List<Account> getAccountList()
    {
        return accountList;
    }

    public void setAccountList(List<Account> accountList)
    {
        this.accountList = accountList;
    }

    public School getSid()
    {
        return sid;
    }

    public void setSid(School sid)
    {
        this.sid = sid;
    }

    public Major getMid()
    {
        return mid;
    }

    public void setMid(Major mid)
    {
        this.mid = mid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passport))
        {
            return false;
        }
        Passport other = (Passport) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString()
    {
        return "rugal.center.core.entity.Passport[ id=" + id + " ]";
    }
}
