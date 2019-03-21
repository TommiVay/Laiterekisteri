package Model;
// Generated Mar 6, 2019 4:26:26 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Resurssit generated by hbm2java
 */
@Entity
@Table(name = "Resurssit",
         catalog = "laiterekisteri"
)
public class Resurssit implements java.io.Serializable {

    private int id;
    private boolean status;
    private String nimi;
    private String tyyppi;
    private int luvanvaraisuus;
    private String kuvaus;
    private Set<Varaukset> varauksets = new HashSet<Varaukset>(0);

    /**
     * Tyhjä konstuktori.
     */
    public Resurssit() {
    }

    /**
     * Konstruktori ilman varausta.
     * @param status
     * @param nimi
     * @param tyyppi
     * @param luvanvaraisuus
     * @param kuvaus
     */
    public Resurssit(boolean status, String nimi, String tyyppi, int luvanvaraisuus, String kuvaus) {
        this.status = status;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.luvanvaraisuus = luvanvaraisuus;
        this.kuvaus = kuvaus;
    }

    /**
     * Konstruktori varausksella.
     * @param status
     * @param nimi
     * @param tyyppi
     * @param luvanvaraisuus
     * @param kuvaus
     * @param varauksets
     */
    public Resurssit(boolean status, String nimi, String tyyppi, int luvanvaraisuus, String kuvaus, Set<Varaukset> varauksets) {
        this.status = status;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.luvanvaraisuus = luvanvaraisuus;
        this.kuvaus = kuvaus;
        this.varauksets = varauksets;
    }

    /**
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Column(name = "Status", nullable = false)
    public boolean isStatus() {
        return this.status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    @Column(name = "Nimi", nullable = false, length = 40)
    public String getNimi() {
        return this.nimi;
    }

    /**
     *
     * @param nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     *
     * @return
     */
    @Column(name = "Tyyppi", nullable = false, length = 40)
    public String getTyyppi() {
        return this.tyyppi;
    }

    /**
     *
     * @param tyyppi
     */
    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    /**
     *
     * @return
     */
    @Column(name = "Luvanvaraisuus", nullable = false)
    public int getLuvanvaraisuus() {
        return this.luvanvaraisuus;
    }

    /**
     *
     * @param luvanvaraisuus
     */
    public void setLuvanvaraisuus(int luvanvaraisuus) {
        this.luvanvaraisuus = luvanvaraisuus;
    }

    /**
     *
     * @return
     */
    @Column(name = "Kuvaus", nullable = false, length = 400)
    public String getKuvaus() {
        return this.kuvaus;
    }

    /**
     *
     * @param kuvaus
     */
    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    /**
     *
     * @return
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resurssit")
    public Set<Varaukset> getVarauksets() {
        return this.varauksets;
    }

    /**
     *
     * @param varauksets
     */
    public void setVarauksets(Set<Varaukset> varauksets) {
        this.varauksets = varauksets;
    }

}
