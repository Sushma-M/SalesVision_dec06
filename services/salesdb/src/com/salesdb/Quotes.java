/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Arrays;
import javax.persistence.Transient;
import javax.persistence.CascadeType;
import javax.persistence.UniqueConstraint;

/**
 * Quotes generated by hbm2java
 */
@Entity
@Table(name = "`QUOTES`", schema = "PUBLIC")
public class Quotes implements java.io.Serializable {

    private Integer id;

    private Date entryDate;

    private Integer estimatedSale;

    private Set<FollowUps> followUpses = new HashSet<FollowUps>(0);

    private Set<Sales> saleses = new HashSet<Sales>(0);

    private Reps reps;

    private Leads leads;

    private Integer leadId;

    private Integer repId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "`ID`", precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "`ENTRY_DATE`", length = 10)
    public Date getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    @Column(name = "`ESTIMATED_SALE`", precision = 10)
    public Integer getEstimatedSale() {
        return this.estimatedSale;
    }

    public void setEstimatedSale(Integer estimatedSale) {
        this.estimatedSale = estimatedSale;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "quotes")
    public Set<FollowUps> getFollowUpses() {
        return this.followUpses;
    }

    public void setFollowUpses(Set<FollowUps> followUpses) {
        this.followUpses = followUpses;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "quotes")
    public Set<Sales> getSaleses() {
        return this.saleses;
    }

    public void setSaleses(Set<Sales> saleses) {
        this.saleses = saleses;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`REP_ID`", insertable = false, updatable = false)
    public Reps getReps() {
        return this.reps;
    }

    public void setReps(Reps reps) {
        if (reps != null) {
            this.setRepId(reps.getId());
        }
        this.reps = reps;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`LEAD_ID`", insertable = false, updatable = false)
    public Leads getLeads() {
        return this.leads;
    }

    public void setLeads(Leads leads) {
        if (leads != null) {
            this.setLeadId(leads.getId());
        }
        this.leads = leads;
    }

    @Column(name = "`LEAD_ID`", nullable = true, precision = 10, scale = 0)
    public Integer getLeadId() {
        return this.leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    @Column(name = "`REP_ID`", nullable = true, precision = 10, scale = 0)
    public Integer getRepId() {
        return this.repId;
    }

    public void setRepId(Integer repId) {
        this.repId = repId;
    }

    public Quotes() {
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null))
            return false;
        if (!(o instanceof Quotes))
            return false;
        Quotes that = (Quotes) o;
        return ((this.getId() == that.getId()) || (this.getId() != null && that.getId() != null && this.getId().equals(that.getId())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        return result;
    }
}
