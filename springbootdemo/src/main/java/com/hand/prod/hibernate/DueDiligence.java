package com.hand.prod.hibernate;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//import javax.persistence.Version;
import java.io.Serializable;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-08-20 11:21
 */
//@Entity(name = "DueDiligence")
//@Data
//@Table(name = "crla_due_diligence")
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class DueDiligence implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(generator = "dueDiligenceIdGenerator")
//    @GenericGenerator(name = "dueDiligenceIdGenerator", strategy = "native", parameters = {
//        @Parameter(name = "sequence", value = "seq_duediligence_id")
//    })
    private Long id;
//    @Column(name = "party_id")
    private String partyId;
//    @Version
//    @Column(name = "version")
    private Integer version;
//    @Column(name = "customer_type")
    private String customerType;
//    @Column(name = "diligence_reason")
    private String diligenceReason;
//    @Column(name = "status")
    private String status;
//    @Column(name = "identify_type")
    private String identifyType;
//    @Column(name = "identify_reason")
    private String identifyReason;

    public DueDiligence() {

    }

    public DueDiligence(Long id, String partyId, String diligenceReason, String identifyType, String identifyReason, String status) {
        this.id = id;
        this.partyId = partyId;
        this.diligenceReason = diligenceReason;
        this.identifyType = identifyType;
        this.identifyReason = identifyReason;
        this.status = status;
    }

//    @Transient
    private String customerName;

//    @Transient
    private String identityType;

//    @Transient
    private String identityNumber;

//    @Transient
    private String unitName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDiligenceReason() {
        return diligenceReason;
    }

    public void setDiligenceReason(String diligenceReason) {
        this.diligenceReason = diligenceReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }

    public String getIdentifyReason() {
        return identifyReason;
    }

    public void setIdentifyReason(String identifyReason) {
        this.identifyReason = identifyReason;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
