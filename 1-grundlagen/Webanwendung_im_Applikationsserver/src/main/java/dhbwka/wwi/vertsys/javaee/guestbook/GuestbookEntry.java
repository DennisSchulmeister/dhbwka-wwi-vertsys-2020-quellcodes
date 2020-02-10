/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 *
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 *
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.guestbook;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * Persistenzklasse für einen Gästebucheintrag.
 */
@Entity
public class GuestbookEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "guestbook_entry_ids")
    @TableGenerator(name = "guestbook_entry_ids", initialValue = 0, allocationSize = 50)
    private Long id;

    private String name = "";
    private Date visitDate = new Date(System.currentTimeMillis());
    private Time visitTime = new Time(System.currentTimeMillis());

    public GuestbookEntry() {
    }

    public GuestbookEntry(String name) {
        this.name = name;
    }

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Time getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Time visitTime) {
        this.visitTime = visitTime;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Von Objekt geerbter Kram">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GuestbookEntry)) {
            return false;
        }
        GuestbookEntry other = (GuestbookEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dhbwka.wwi.vertsys.javee.guestbook.GuestbookEntry[ id=" + id + " ]";
    }
    //</editor-fold>

}
