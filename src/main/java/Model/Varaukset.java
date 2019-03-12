package Model;
// Generated Mar 6, 2019 4:26:26 PM by Hibernate Tools 4.3.1

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Varaukset generated by hbm2java
 */
@Entity
@Table(name = "Varaukset",
         catalog = "laiterekisteri"
)
public class Varaukset implements java.io.Serializable {

    private int id;
    private Kayttaja kayttaja;
    private Resurssit resurssit;
    private Timestamp alkupvm;
    private Timestamp paattymispvm;
    private String kuvaus;
    private boolean palautettu;
    private String nimi;
    private Boolean hyvaksytty;

    public Varaukset() {
    }

    public Varaukset(Kayttaja kayttaja, Resurssit resurssit, LocalDateTime alkupvm, LocalDateTime paattymispvm, String kuvaus, boolean palautettu, String nimi) {
        this.kayttaja = kayttaja;
        this.resurssit = resurssit;
        this.alkupvm = Timestamp.valueOf(alkupvm);
        this.paattymispvm = Timestamp.valueOf(paattymispvm);
        this.kuvaus = kuvaus;
        this.palautettu = palautettu;
        this.nimi = resurssit.getNimi();
    }

    public Varaukset(Kayttaja kayttaja, Resurssit resurssit, LocalDateTime alkupvm, LocalDateTime paattymispvm, String kuvaus, boolean palautettu, String nimi, Boolean hyvaksytty) {
        this.kayttaja = kayttaja;
        this.resurssit = resurssit;
        this.alkupvm = Timestamp.valueOf(alkupvm);
        this.paattymispvm = Timestamp.valueOf(paattymispvm);
        this.kuvaus = kuvaus;
        this.palautettu = palautettu;
        this.nimi = resurssit.getNimi();
        this.hyvaksytty = hyvaksytty;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KayttajaId", nullable = false)
    public Kayttaja getKayttaja() {
        return this.kayttaja;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ResurssiId", nullable = false)
    public Resurssit getResurssit() {
        return this.resurssit;
    }

    public void setResurssit(Resurssit resurssit) {
        this.resurssit = resurssit;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Alkupvm", nullable = false, length = 26)
    public Timestamp getAlkupvm() {
        return this.alkupvm;
    }

    public void setAlkupvm(Timestamp alkupvm) {
        this.alkupvm = alkupvm;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Paattymispvm", nullable = false, length = 26)
    public Timestamp getPaattymispvm() {
        return this.paattymispvm;
    }

    public void setPaattymispvm(Timestamp paattymispvm) {
        this.paattymispvm = paattymispvm;
    }

    @Column(name = "Kuvaus", nullable = false, length = 40)
    public String getKuvaus() {
        return this.kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    @Column(name = "Palautettu", nullable = false)
    public boolean isPalautettu() {
        return this.palautettu;
    }

    public void setPalautettu(boolean palautettu) {
        this.palautettu = palautettu;
    }

    @Column(name = "Nimi", nullable = false, length = 40)
    public String getNimi() {
        return this.nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    @Column(name = "Hyvaksytty")
    public Boolean getHyvaksytty() {
        return this.hyvaksytty;
    }

    public void setHyvaksytty(Boolean hyvaksytty) {
        this.hyvaksytty = hyvaksytty;
    }

    //käytä näitä purkka settereitä ja gettereitä jos käytät localDateTime
    public void setAlkuAika(LocalDateTime alkupvm) {
        this.alkupvm = Timestamp.valueOf(alkupvm);
    }

    public LocalDateTime getAlkuAika() {
        return this.alkupvm.toLocalDateTime();
    }

    public void setLoppuAika(LocalDateTime paattymispvm) {
        this.paattymispvm = Timestamp.valueOf(paattymispvm);
    }

    public LocalDateTime getLoppuAika() {
        return this.paattymispvm.toLocalDateTime();
    }
}
